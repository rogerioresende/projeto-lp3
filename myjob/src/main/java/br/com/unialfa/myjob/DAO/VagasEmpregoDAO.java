package br.com.unialfa.myjob.DAO;

public class VagasEmpregoDAO {

    private String nomeEmp;
    private String nivelEsco;
    private String nivelTec;
    private String espVaga;
    private boolean vagaPreenchida;
    private long empresa_idEmp;

    public VagasEmpregoDAO() {

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

    public long getEmpresa_idEmp() {
        return empresa_idEmp;
    }

    public void setEmpresa_idEmp(long empresa_idEmp) {
        this.empresa_idEmp = empresa_idEmp;
    }
}
