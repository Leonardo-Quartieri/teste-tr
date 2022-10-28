package br.com.tr.cartao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("SaldoCartao")
@Entity
public class SaldoCartao {

    @ApiModelProperty("Identificador")
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "numerocartao")
    private String numeroCartao;

    @Column(name = "saldo")
    private String saldo;

}
