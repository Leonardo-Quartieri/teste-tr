package br.com.tr.cartao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitaTransacaoDTO implements Serializable {

    @JsonProperty("numeroCartao")
    private String numeroCartao;
    @JsonProperty("senha")
    private String senha;
    @JsonProperty("valor")
    private String valor;
}
