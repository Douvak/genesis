package com.example.Genesis.domain.service.components;

import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Pedido;
import com.example.Genesis.domain.repository.EmpresaRepository;
import com.example.Genesis.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validacao {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public Empresa validarEmpresa(Long empresaID){
        return empresaRepository.getReferenceById(empresaID);
    }

    public Pedido validarPedido(Long pedidoID){ return pedidoRepository.getReferenceById(pedidoID);}
}
