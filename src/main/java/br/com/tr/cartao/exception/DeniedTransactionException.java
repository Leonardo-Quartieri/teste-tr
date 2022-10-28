package br.com.tr.cartao.exception;

public class DeniedTransactionException extends RuntimeException {
    public DeniedTransactionException(String msg){
        super(msg);
    }
}
