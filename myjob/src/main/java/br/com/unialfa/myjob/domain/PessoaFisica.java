package br.com.unialfa.myjob.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class PessoaFisica  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long idPess;
    private String nome;
    private String sexo;
    private long idade;

    @Column(unique = true, nullable = false)
    private String cpf;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Curriculo curriculo;



    public List<CandidatarVaga> getCandidatarVagas() {
        return candidatarVagas;
    }

    public void setCandidatarVagas(List<CandidatarVaga> candidatarVagas) {
        this.candidatarVagas = candidatarVagas;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "pessoaFisica")
    private List<CandidatarVaga> candidatarVagas;

    public long getIdPess() {
        return idPess;
    }

    public void setIdPess(long idPess) {
        this.idPess = idPess;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long getIdade() {
        return idade;
    }

    public void setIdade(long idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getCadastroLogin() {
        return usuario;
    }

    public void setCadastroLogin(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }
}
