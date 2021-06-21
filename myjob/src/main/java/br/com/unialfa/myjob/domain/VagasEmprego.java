package br.com.unialfa.myjob.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.awt.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class VagasEmprego implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long idVaga;
    private String nomeEmp;
    private String nivelEsco;
    private String nivelTec;
    private String espVaga;
    private boolean vagaPreenchida;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="empresa_id_emp")
    private Empresa empresa ;

    @OneToMany(mappedBy = "vagasEmprego")
    private List<CandidatarVaga> candidatarVagas;



    public long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(long idVaga) {
        this.idVaga = idVaga;
    }

    public String getNomeEmp() {
        return nomeEmp;
    }

    public void setNomeEmp(String nomeEmp) {
        this.nomeEmp = nomeEmp;
    }

    public String getNivelEsco() {
        return nivelEsco;
    }

    public void setNivelEsco(String nivelEsco) {
        this.nivelEsco = nivelEsco;
    }

    public String getNivelTec() {
        return nivelTec;
    }

    public void setNivelTec(String nivelTec) {
        this.nivelTec = nivelTec;
    }

    public String getEspVaga() {
        return espVaga;
    }

    public void setEspVaga(String espVaga) {
        this.espVaga = espVaga;
    }

    public boolean isVagaPreenchida() {
        return vagaPreenchida;
    }

    public void setVagaPreenchida(boolean vagaPreenchida) {
        this.vagaPreenchida = vagaPreenchida;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<CandidatarVaga> getCandidatarVagas() {
        return candidatarVagas;
    }

    public void setCandidatarVagas(List<CandidatarVaga> candidatarVagas) {
        this.candidatarVagas = candidatarVagas;
    }
}
