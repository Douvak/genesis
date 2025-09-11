package com.example.Genesis.domain.service;

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
        File pasta = new File(pastaArquivos);
        if (!pasta.exists()) pasta.mkdirs(); // garante que a pasta existe

        OrdemDeServico ordem = ordenRepository.getReferenceById(ordemID);

        String nomeArquivo = System.currentTimeMillis() + "_ordemID_" + ordemID + "_" + file.getOriginalFilename();
        Path caminho = Paths.get(pastaArquivos, nomeArquivo);
        Files.copy(file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
        Arquivo arquivo = new Arquivo(caminho.toString(), ordem);

        arquivoRepository.save(arquivo);

        return arquivo;
    }

    public Resource carregarArquivo(String nomeArquivo) throws MalformedURLException {
        Path caminho = Paths.get(pastaArquivos).resolve(nomeArquivo).normalize();
        Resource resource = new UrlResource(caminho.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Arquivo não encontrado: " + nomeArquivo);
        }
    }
}

