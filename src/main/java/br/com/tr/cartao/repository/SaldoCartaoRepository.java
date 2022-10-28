package br.com.tr.cartao.repository;

import br.com.tr.cartao.model.SaldoCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaldoCartaoRepository extends JpaRepository<SaldoCartao, Long> {

    Optional<SaldoCartao> findByNumeroCartao(String numeroCartao);
}
