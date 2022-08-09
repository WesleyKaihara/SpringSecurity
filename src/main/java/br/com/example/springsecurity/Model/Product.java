package br.com.example.springsecurity.Model;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private float valor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nomeImagem")
    private String nomeImagem;

    public Product(){

    }

    public Product(String nomeImagem,String nome, float valor, String descricao) {
        this.nomeImagem = nomeImagem;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
