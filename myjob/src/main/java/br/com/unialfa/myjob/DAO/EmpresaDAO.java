package br.com.unialfa.myjob.DAO;

import br.com.unialfa.myjob.domain.Usuario;

public class EmpresaDAO {

    private String razaoSocial;
    private String nomeFant;
    private String cnpj;
    private long cadastroLogin_id;
    private long vagasEmprego_idVaga;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getVagasEmprego_idVaga() {
        return vagasEmprego_idVaga;
    }

    public void setVagasEmprego_idVaga(long vagasEmprego_idVaga) {
        this.vagasEmprego_idVaga = vagasEmprego_idVaga;
    }

    public EmpresaDAO() {

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

    public long getCadastroLogin_id() {
        return cadastroLogin_id;
    }

    public void setCadastroLogin_id(long cadastroLogin_id) {
        this.cadastroLogin_id = cadastroLogin_id;
    }
}

