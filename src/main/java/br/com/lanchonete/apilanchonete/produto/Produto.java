package br.com.lanchonete.apilanchonete.produto;

import br.com.lanchonete.apilanchonete.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(length = 45, nullable = false, name = "nome")
    private String nome;
    
    @Column(length = 45, nullable = false, name = "disponivel")
    private String disponivel;

    @Column(length = 45, nullable = false, name = "valor")
    private Double valor;
    
    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return "Produto [id=" + id + ", user=" + user +  ", nome=" + nome + ", disponivel=" + disponivel + ", valor=" + valor
                + "]";
    }
}
