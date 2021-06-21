package br.com.unialfa.myjob.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
public class CandidatarVaga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long idCand;
    private boolean candidatar;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="vagas_emprego_id_vaga")
    private VagasEmprego vagasEmprego ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="pessoa_fisica_id_pess")
    private PessoaFisica pessoaFisica;

    public VagasEmprego getVagasEmprego() {
        return vagasEmprego;
    }

    public void setVagasEmprego(VagasEmprego vagasEmprego) {
        this.vagasEmprego = vagasEmprego;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public long getIdCand() {
        return idCand;
    }

    public void setIdCand(long idCand) {
        this.idCand = idCand;
    }

    public boolean isCandidatar() {
        return candidatar;
    }

    public void setCandidatar(boolean candidatar) {
        this.candidatar = candidatar;
    }

    public CandidatarVaga() {


    }
}
