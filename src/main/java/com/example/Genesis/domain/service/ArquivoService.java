package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.buscarArquivoDTO;
import com.example.Genesis.domain.entity.Arquivo;
import com.example.Genesis.domain.entity.OrdemDeServico;
import com.example.Genesis.domain.repository.ArquivoRepository;
import com.example.Genesis.domain.repository.OrdenDeServicoRepository;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ArquivoService {

    @Autowired
    private OrdenDeServicoRepository ordenRepository;
    @Autowired
    private ArquivoRepository arquivoRepository;

    @Value("${app.pasta.arquivos:src/main/java/com/example/Genesis/arquivos/}")
    private String pastaArquivos;


    public Arquivo salvarArquivo(MultipartFile file, Long ordemID) throws IOException {

        OrdemDeServico ordem = ordenRepository.getReferenceById(ordemID);
        Long empresaID = ordem.getEmpresa().getId();

        File pastaEmpresa = new File(pastaArquivos, "empresaID_"+ empresaID);
        if (!pastaEmpresa.exists()) pastaEmpresa.mkdirs();
        String nomeArquivo = System.currentTimeMillis() + "_ordemID_" + ordemID + "_" + file.getOriginalFilename();
        Path caminho = Paths.get(pastaEmpresa.getAbsolutePath(), nomeArquivo);
        Files.copy(file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
        Arquivo arquivo = new Arquivo(nomeArquivo, ordem);

        arquivoRepository.save(arquivo);

        return arquivo;
    }

    public Resource carregarArquivo(Long arquivoID) throws MalformedURLException {
        Arquivo arquivo = arquivoRepository.getReferenceById(arquivoID);
        OrdemDeServico ordem = ordenRepository.getReferenceById(arquivo.getOrdemDeServico().getId());

        Path caminho = Paths.get(pastaArquivos+"empresaID_"+ordem.getEmpresa().getId()).resolve(arquivo.getRota()).normalize();
        Resource resource = new UrlResource(caminho.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Arquivo não encontrado: " + arquivo.getRota());
        }
    }

    public void deletarArquivo(Long id) {
        Arquivo arquivo = arquivoRepository.getReferenceById(id);
        if (arquivo != null) {
            OrdemDeServico ordem = ordenRepository.getReferenceById(arquivo.getOrdemDeServico().getId());
            Path caminho = Paths.get(pastaArquivos+"empresaID_"+ordem.getEmpresa().getId()).resolve(arquivo.getRota()).normalize();
            try {
                Files.deleteIfExists(caminho); // deleta do sistema de arquivos
            } catch (IOException e) {
                throw new RuntimeException("Erro ao deletar arquivo: " + arquivo.getRota(), e);
            }
            arquivoRepository.delete(arquivo); // deleta do banco
        }
    }

}

