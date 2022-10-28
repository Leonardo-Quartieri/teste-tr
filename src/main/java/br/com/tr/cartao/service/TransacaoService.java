package br.com.tr.cartao.service;

import br.com.tr.cartao.dto.SolicitaTransacaoDTO;
import br.com.tr.cartao.exception.DeniedTransactionException;
import br.com.tr.cartao.model.Cartao;
import br.com.tr.cartao.model.SaldoCartao;
import br.com.tr.cartao.repository.CartaoRepository;
import br.com.tr.cartao.repository.SaldoCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import java.math.BigDecimal;
import java.util.Optional;

@org.springframework.stereotype.Service
@ApplicationScope
public class TransacaoService {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private SaldoCartaoRepository saldoCartaoRepository;

    public void realizarTransacao(SolicitaTransacaoDTO cartao) {
        Optional<Cartao> card = cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao());
        if (card==null || card.isEmpty()){
            throw new DeniedTransactionException("CARTAO_INEXISTENTE");
        }
        if(!card.get().getSenha().equals(cartao.getSenha())){
            throw new DeniedTransactionException("SENHA_INVALIDA");
        }

        SaldoCartao objSaldo = saldoCartaoRepository.findByNumeroCartao(cartao.getNumeroCartao()).get();
        BigDecimal saldoAtual = new BigDecimal(objSaldo.getSaldo());
        BigDecimal saqueSolicitado = new BigDecimal(cartao.getValor());

        if(saqueSolicitado.compareTo(saldoAtual) == 1) {
            throw new DeniedTransactionException("SALDO_INSUFICIENTE");
        }
        String val = (saldoAtual.subtract(saqueSolicitado)) + "";
        objSaldo.setSaldo(val);
        saldoCartaoRepository.save(objSaldo);

    }
}