package br.com.lanchonete.apilanchonete.produto;

public class ProdutoNotFoundException extends Throwable {
    public ProdutoNotFoundException(String message){
        super(message);
    }
}
