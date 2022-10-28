package br.com.tr.cartao.service;

import br.com.tr.cartao.dto.CreateCardDTO;
import br.com.tr.cartao.dto.ResponseCreateCardDTO;
import br.com.tr.cartao.exception.CardNotFoundException;
import br.com.tr.cartao.model.Cartao;
import br.com.tr.cartao.model.SaldoCartao;
import br.com.tr.cartao.repository.CartaoRepository;
import br.com.tr.cartao.repository.SaldoCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@ApplicationScope
public class CartaoService {

    public static final String SALDO_INICIAL = "500.00";

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private SaldoCartaoRepository saldoCartaoRepository;

    public ResponseCreateCardDTO saveCartao(CreateCardDTO card){
        //valida

        //salva
        Cartao entidadeCartao = Cartao.builder().numeroCartao(card.getNumeroCartao()).senha(card.getSenha()).build();
        Cartao cartaoSalvo = cartaoRepository.save(entidadeCartao);
        saldoCartaoRepository.save(SaldoCartao.builder().numeroCartao(cartaoSalvo.getNumeroCartao()).saldo(SALDO_INICIAL).build());
        return ResponseCreateCardDTO.builder().numeroCartao(cartaoSalvo.getNumeroCartao()).senha(cartaoSalvo.getSenha()).build();
    }

    public List<Cartao> buscarCotacoes(){

        return  cartaoRepository.findAll();
    }

    public BigDecimal getSaldoCartao(String numeroCartao) {
            Optional<Cartao> cartao = cartaoRepository.findByNumeroCartao(numeroCartao);
            if(cartao.isPresent()){
                Optional<SaldoCartao> saldo = saldoCartaoRepository.findByNumeroCartao(numeroCartao);
                return new BigDecimal(saldo.get().getSaldo());
            }else{
                throw new CardNotFoundException();
            }
    }
}