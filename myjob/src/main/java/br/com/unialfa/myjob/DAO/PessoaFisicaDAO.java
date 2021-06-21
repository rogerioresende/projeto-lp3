package br.com.unialfa.myjob.DAO;

import br.com.unialfa.myjob.domain.Usuario;

public class PessoaFisicaDAO {


    private String nome;
    private String sexo;
    private long idade;
    private String cpf;
    private long idPess;
    private Usuario usuario;
    public PessoaFisicaDAO() {
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

    public long getIdPess() {
        return idPess;
    }

    public void setIdPess(long idPess) {
        this.idPess = idPess;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
