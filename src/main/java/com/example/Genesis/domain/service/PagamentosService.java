package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.EfetuarPagamentoDTO;
import com.example.Genesis.domain.dto.TipoPagamentoDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Pedido;
import com.example.Genesis.domain.entity.TipoPagamento;
import com.example.Genesis.domain.repository.PagamentosRepository;
import com.example.Genesis.domain.service.components.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentosService {

    @Autowired
    private Validacao validacao;
    @Autowired
    private PagamentosRepository pagamentosRepository;
    public List<TipoPagamentoDTO> criarPagamentos(List<TipoPagamentoDTO> dados) {
        List<TipoPagamento> pagamentos = dados.stream().map(dto ->{
            Empresa empresa = validacao.validarEmpresa(dto.empresaID());
            Pedido pedido =validacao.validarPedido(dto.pedidoID());
            TipoPagamento pagamento = new TipoPagamento(dto, pedido, empresa);

            return pagamento;
        }).toList();

        List<TipoPagamento> salvos = pagamentosRepository.saveAll(pagamentos);

        return salvos.stream().map(TipoPagamentoDTO::new).toList();
    }

    public void efetuarPagamento(EfetuarPagamentoDTO dados){
        TipoPagamento pagamento = pagamentosRepository.getReferenceById(dados.pagamentoID());
        pagamento.setStatus(true);
        pagamento.setDataPagamento(dados.data());
    }
}
