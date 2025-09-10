package com.example.Genesis.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ArquivoService {

    @Value("${app.pasta.arquivos:src/main/java/com/example/Genesis/arquivos/}")
    private String pastaArquivos;


    public String salvarArquivo(MultipartFile file, Long ordemID) throws IOException {
        File pasta = new File(pastaArquivos);
        if (!pasta.exists()) pasta.mkdirs(); // garante que a pasta existe

        String nomeArquivo = System.currentTimeMillis() + "_ordemID_" + ordemID + "_" + file.getOriginalFilename();
        Path caminho = Paths.get(pastaArquivos, nomeArquivo);
        Files.copy(file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
        return caminho.toString();
    }
}

