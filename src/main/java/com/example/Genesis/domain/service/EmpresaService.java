package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.EmpresaDadosDTO;
import com.example.Genesis.domain.dto.NovaEmpresaDTO;
import com.example.Genesis.domain.dto.CriarEmpresaDTO;
import com.example.Genesis.domain.dto.NovaSenhaDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Usuario;
import com.example.Genesis.domain.repository.EmpresaRepository;
import com.example.Genesis.domain.repository.UsuarioRepositiry;
import com.example.Genesis.domain.service.components.Validacao;
import com.example.Genesis.infra.security.AutenticationService;
import com.example.Genesis.infra.security.SecurityConfigurations;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmpresaService {
    @Autowired
    private final EmpresaRepository empresaRepository;
    @Autowired
    private final Validacao validacao;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UsuarioRepositiry usuarioRepositiry;


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

    public Page<NovaEmpresaDTO> listarEmpresas(Pageable pageable) {

        return empresaRepository.findAll(pageable).map(NovaEmpresaDTO::new);
    }

    public void deletarEmpresa(Long id) {

        Empresa empresa = empresaRepository.getReferenceById(id);
        empresaRepository.delete(empresa);
    }

    public EmpresaDadosDTO dadosEmpresa(Long id) {
        Empresa empresa = validacao.validarEmpresa(id);
        return new EmpresaDadosDTO(empresa);

    }

    public Boolean alterarSenhaEmpresa(NovaSenhaDTO dados) {
        Empresa empresa = empresaRepository.getReferenceById(dados.empresaID());
        var lista = empresa.getUsuarios().stream().filter(user -> "ADMIN".equals(user.getPapel().toString())).findFirst();

        System.out.println("nome: "+lista.get().getUsername()+"\nsenha: "+lista.get().getSenha()+"\npapel: "+lista.get().getPapel());

        var optionalAdmin = empresa.getUsuarios().stream()
                .filter(usuario -> "ADMIN".equals(usuario.getPapel().toString()))
                .findFirst();
        System.out.println("optionao admin ->>"+optionalAdmin);

        if (optionalAdmin.isEmpty()) {
            throw new RuntimeException("Administrador da empresa não encontrado");
        }

        var userAdmin = optionalAdmin.get();

        if (passwordEncoder.matches(dados.senhaAtual(), userAdmin.getSenha())) {
            userAdmin.setSenha(passwordEncoder.encode(dados.novaSenha()));
            usuarioRepositiry.save(userAdmin);
            return true;
        } else {
            return false;
        }
    }


}
