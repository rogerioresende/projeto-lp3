package br.com.unialfa.myjob.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long idEnd;
    private String estado;
    private String bairro;
    private String rua;
    private String complemento;



    public Endereco() {

    }

    public long getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(long idEnd) {
        this.idEnd = idEnd;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Endereco(Usuario usuario) {



    }
}
