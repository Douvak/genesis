package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.ArquivoDTO;
import com.example.Genesis.domain.dto.buscarArquivoDTO;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("arquivos")
public class ArquivosController {

    @Autowired
    private ArquivoService service;

    @PostMapping("upload/{ordemID}")
    public ResponseEntity<List<ArquivoDTO>> upload(@PathVariable Long ordemID, @RequestParam("file") List<MultipartFile> files) throws IOException {

        List<ArquivoDTO> arquivosSalvos = new ArrayList<>();

        for (MultipartFile file : files) {
            Arquivo arquivo = service.salvarArquivo(file, ordemID);
            arquivosSalvos.add(new ArquivoDTO(arquivo));
        }

        return ResponseEntity.ok(arquivosSalvos);
    }


    @GetMapping("/download/{arquivoID}")
    public ResponseEntity<Resource> downloadArquivo(@PathVariable Long arquivoID) throws MalformedURLException {
        Resource arquivo = service.carregarArquivo(arquivoID);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getFilename() + "\"")
                .body(arquivo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>deletarArquivo(@PathVariable Long id){
        service.deletarArquivo(id);
        return ResponseEntity.ok("Arquivo Deletado");
    }
}
