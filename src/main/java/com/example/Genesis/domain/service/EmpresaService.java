package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.EmpresaDTO;
import com.example.Genesis.domain.dto.CriarEmpresaDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.repository.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EmpresaService {
    @Autowired
    private final EmpresaRepository empresaRepository;


    public String criarEmpresa(CriarEmpresaDTO dados) {
        Empresa verificarEmpresa = empresaRepository.findByCnpj(dados.cnpj());
        if (verificarEmpresa != null){
            throw new RuntimeException("Já existe uma empresa nesse cnpj!");
        }else {
            Empresa novaEmpresa = new Empresa(dados);
            empresaRepository.save(novaEmpresa);
            return "Empresa" + dados.nome() + " criada com sucesso!";
        }
    }

    public Page<EmpresaDTO> listarEmpresas(Pageable pageable) {

        return empresaRepository.findAll(pageable).map(EmpresaDTO::new);
    }

    public void deletarEmpresa(Long id) {

        Empresa empresa = empresaRepository.getReferenceById(id);
        empresaRepository.delete(empresa);
    }
}
