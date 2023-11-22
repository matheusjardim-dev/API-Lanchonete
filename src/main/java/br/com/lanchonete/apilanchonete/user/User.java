package br.com.lanchonete.apilanchonete.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer RA;

    @Column(length = 5, nullable = false)
    private String password;
    
    @Column(length = 15, nullable = false, name = "telefone")
    private String telefone;

    @Column(length = 45, nullable = false, name = "nome")
    private String nome;

    @Column(length = 15, nullable = false, name = "nivel")
    private Integer nivel;

    @Column(length = 10, nullable = false, name = "curso")
    private String curso;
    
    @Column(length = 5, nullable = false, name = "periodo")
    private String periodo;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRA() {
        return RA;
    }

    public void setRA(Integer rA) {
        RA = rA;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getCurso() {
        return curso;
    }
    
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public String getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", RA=" + RA + ", password=" + password + ", telefone=" + telefone + ", nome=" + nome + ", nivel=" + nivel + ", curso=" + curso +", periodo=" + periodo +"]";
    }
}
