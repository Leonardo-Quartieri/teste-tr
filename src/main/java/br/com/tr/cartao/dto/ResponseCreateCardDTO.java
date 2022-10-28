package br.com.tr.cartao.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateCardDTO  implements Serializable {

    private String senha;
    private String numeroCartao;

}
