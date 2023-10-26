package br.com.lanchonete.apilanchonete.produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_prod;

    @Column(length = 45, nullable = false, name = "nome")
    private String nome;
    
    @Column(length = 45, nullable = false)
    private boolean disponivel;
    
    @Column(length = 45, nullable = false)
    private Double valor;

    public Integer getId_prod() {
        return id_prod;
    }

    public void setId_prod(Integer id_prod) {
        this.id_prod = id_prod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Produto [id_prod=" + id_prod + ", nome=" + nome + ", disponivel=" + disponivel + ", valor=" + valor
                + "]";
    }    
}
