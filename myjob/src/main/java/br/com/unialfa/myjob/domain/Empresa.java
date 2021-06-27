package br.com.unialfa.myjob.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long idEmp;
    private String razaoSocial;
    private String nomeFant;

    @Column (unique = true, nullable = false)
    private String cnpj;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
    private List<VagasEmprego> vagasEmpregos;

    public long getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(long idEmp) {
        this.idEmp = idEmp;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFant() {
        return nomeFant;
    }

    public void setNomeFant(String nomeFant) {
        this.nomeFant = nomeFant;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Usuario getCadastroLogin() {
        return usuario;
    }

    public void setCadastroLogin (Usuario usuario) {
        this.usuario = usuario;
    }

    public List<VagasEmprego> getVagasEmpregos() {
        return vagasEmpregos;
    }

    public void setVagasEmpregos(List<VagasEmprego> vagasEmpregos) {
        this.vagasEmpregos = vagasEmpregos;
    }
}
