package br.com.unialfa.myjob.DAO;

public class CandidatarVagaDAO {

    private boolean candidatar;
    private long pessoaFisica_idPess;
    private long vagasEmprego_idVaga;

    public boolean isCandidatar() {
        return candidatar;
    }

    public void setCandidatar(boolean candidatar) {
        this.candidatar = candidatar;
    }

    public long getPessoaFisica_idPess() {
        return pessoaFisica_idPess;
    }

    public void setPessoaFisica_idPess(long pessoaFisica_idPess) {
        this.pessoaFisica_idPess = pessoaFisica_idPess;
    }

    public long getVagasEmprego_idVaga() {
        return vagasEmprego_idVaga;
    }

    public void setVagasEmprego_idVaga(long vagasEmprego_idVaga) {
        this.vagasEmprego_idVaga = vagasEmprego_idVaga;
    }

    public CandidatarVagaDAO() {



    }
}

