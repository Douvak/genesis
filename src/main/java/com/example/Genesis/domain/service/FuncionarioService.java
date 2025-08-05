package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.EditarFuncionarioDTO;
import com.example.Genesis.domain.dto.FuncionarioDTO;
import com.example.Genesis.domain.dto.ListaFuncionariosDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Funcionario;
import com.example.Genesis.domain.repository.EmpresaRepository;
import com.example.Genesis.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private EmpresaRepository empresaRepository;

    public String cadastrarFuncionario(FuncionarioDTO dados) {
        Empresa empresa = empresaRepository.getReferenceById(dados.empresaID());

        Funcionario novoFuncionario = new Funcionario(dados , empresa);
        repository.save(novoFuncionario);
        return novoFuncionario.getNome();
    }

    public List<ListaFuncionariosDTO> listarFuncionarios(Long empresaID) {
        return repository.findAllByEmpresaId(empresaID).stream().map(ListaFuncionariosDTO::new).toList();

    }

    public void deletarFuncionario(Long id) {
        Funcionario funcionario = repository.getReferenceById(id);
        repository.delete(funcionario);
    }

    public EditarFuncionarioDTO editarFuncionario(EditarFuncionarioDTO dados) {
        Funcionario funcionario = repository.getReferenceById(dados.funcionarioID());
        if (dados.cargo() != null){
            funcionario.setCargo(dados.cargo());
        }
        if (dados.nome() != null){
            funcionario.setNome(dados.nome());
        }
        if (dados.email() != null){
            funcionario.setEmail(dados.email());
        }
        repository.save(funcionario);

        return new EditarFuncionarioDTO(funcionario);
    }
}
