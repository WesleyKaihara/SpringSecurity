package br.com.example.springsecurity.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "content")
    private String content;

    @Column(name = "data_criacao")
    private Date data_criacao = new Date();

    public Comentario(){

    }

    public Comentario(long user_id, String content) {
        this.user_id = user_id;
        this.content = content;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
