package br.com.example.springsecurity.Model;

import javax.persistence.*;

@Entity
@Table(name = "filamentos")
public class Filamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "cor")
    private String cor;

    @Column(name = "peso")
    private Integer peso;

    public Filamento(){

    }

    public Filamento(String tipo, String cor, Integer peso) {
        this.tipo = tipo;
        this.cor = cor;
        this.peso = peso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}
