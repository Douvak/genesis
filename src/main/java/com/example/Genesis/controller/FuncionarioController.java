package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.EditarFuncionarioDTO;
import com.example.Genesis.domain.dto.FuncionarioDTO;
import com.example.Genesis.domain.dto.ListaFuncionariosDTO;
import com.example.Genesis.domain.service.FuncionarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("funcionario")
@RestController
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    @PostMapping
    @Transactional
    public ResponseEntity<String> criarFuncionario(@RequestBody FuncionarioDTO dados){
        String funcionario = service.cadastrarFuncionario(dados);
        return ResponseEntity.ok(funcionario);
    }

        @GetMapping("/{empresaID}")
        public ResponseEntity<List<ListaFuncionariosDTO>> listarFuncionario(@PathVariable Long empresaID){
            var listarFuncionarios = service.listarFuncionarios(empresaID);
            return ResponseEntity.ok(listarFuncionarios);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletarFuncionario(@PathVariable Long id){
        service.deletarFuncionario(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<EditarFuncionarioDTO> editarFuncionario(@RequestBody EditarFuncionarioDTO dados){
        var editado = service.editarFuncionario(dados);
        return ResponseEntity.ok(editado);
    }
}
