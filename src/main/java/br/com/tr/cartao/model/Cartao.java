package br.com.tr.cartao.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Cartao")
@Entity
public class Cartao {

    @ApiModelProperty("Identificador da requisicao")
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "senha")
    private String senha;

    @Column(name = "numerocartao")
    private String numeroCartao;

    @Column(name = "saldo")
    private String saldo;

    @ApiModelProperty("TimeStamp da requisição")
    @Column(name = "timestamp_requisicao")
    private LocalDateTime timestampRequisicao;

    @ApiModelProperty("data da cotação")
    @Column(name = "data_cotacao_dolar")
    private String dataCotacaoDolar;

    @ApiModelProperty("Valor de compra")
    @Column(name = "valor_compra")
    @JsonProperty("cotacaoCompra")
    private Double valorCompra;

    @ApiModelProperty("Valor de venda")
    @Column(name = "valor_venda")
    @JsonProperty("cotacaoVenda")
    private Double valorVenda;


}