package br.com.tr.cartao.rest;

import br.com.tr.cartao.dto.CreateCardDTO;
import br.com.tr.cartao.dto.ResponseCreateCardDTO;
import br.com.tr.cartao.dto.SolicitaTransacaoDTO;
import br.com.tr.cartao.exception.CardNotFoundException;
import br.com.tr.cartao.exception.DeniedTransactionException;
import br.com.tr.cartao.service.CartaoService;
import br.com.tr.cartao.service.TransacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TRRest {

    static final String API_CARTOES ="/cartoes";
    static final String API_TRANSACOES ="/transacoes";

    @Autowired
    private CartaoService cartaoService;
    @Autowired
    private TransacaoService transacaoService;


    @ApiOperation(value = "Realiza uma transacao")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Realizada com sucesso"),
            @ApiResponse(code = 422, message = "Barrado por regra"),
            @ApiResponse(code = 500, message = "Exceção durante processamento")
    })
    @PostMapping(API_TRANSACOES)
    public ResponseEntity<String> realizarTransacao(@RequestBody SolicitaTransacaoDTO cartao) {
        try{
            transacaoService.realizarTransacao(cartao);
            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        } catch(DeniedTransactionException deniedEX) {
            deniedEX.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(deniedEX.getMessage());

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation(value = "Retorna o saldo do cartao")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o saldo"),
            @ApiResponse(code = 404, message = "Cartao nao encontrado"),
            @ApiResponse(code = 422, message = "Excessao no processamento")
    })
    @GetMapping(API_CARTOES +"/{numeroCartao}")
    public ResponseEntity<String> getSaldoCartao(@PathVariable ("numeroCartao") String numeroCartao) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cartaoService.getSaldoCartao(numeroCartao).toString());
        } catch(CardNotFoundException cardE) {
            cardE.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @ApiOperation(value = "Salva um cartao")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criado com sucesso"),
            @ApiResponse(code = 422, message = "Exceção durante processamento")
    })
    @PostMapping(API_CARTOES)
    public ResponseEntity<ResponseCreateCardDTO> saveCartao(@RequestBody CreateCardDTO cartao) {
        try{

            return ResponseEntity.status(HttpStatus.CREATED).body(cartaoService.saveCartao(cartao));

        } catch(Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    ResponseCreateCardDTO.builder().numeroCartao(cartao.getNumeroCartao()).senha(cartao.getSenha()).build()
            );

        }
    }

}