package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.ArquivoDTO;
import com.example.Genesis.domain.entity.Arquivo;
import com.example.Genesis.domain.service.ArquivoService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("arquivos")
public class ArquivosController {

    @Autowired
    private ArquivoService service;

    @PostMapping("upload/{ordemID}")
    public ResponseEntity<ArquivoDTO>upload(@PathVariable Long ordemID, @RequestParam("file")MultipartFile file) throws IOException {
        Arquivo arquivo = service.salvarArquivo(file, ordemID);
        return ResponseEntity.ok(new ArquivoDTO(arquivo));

        }
    @GetMapping("/download/{nomeArquivo}")
    public ResponseEntity<Resource> downloadArquivo(@PathVariable String nomeArquivo) throws MalformedURLException {
        Resource arquivo = service.carregarArquivo(nomeArquivo);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getFilename() + "\"")
                .body(arquivo);
    }
}
