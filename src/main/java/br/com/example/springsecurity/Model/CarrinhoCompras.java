package br.com.example.springsecurity.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carrinhoCompras")
public class CarrinhoCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private Long id_cliente;

    @Column(name = "id_produto")
    private Long id_produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "data_criacao")
    private Date data_criacao = new Date();

    @Column(name = "status")
    private String status = "Aguardando Pagamento";

    public CarrinhoCompras(){

    }
    public CarrinhoCompras(Integer quantidade,Long id_cliente,Long id_produto) {
        this.id_cliente = id_cliente;
        this.quantidade = quantidade;
        this.id_produto = id_produto;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
