package br.com.unialfa.myjob.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Curriculo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long idCurr;
    private String grauEsco;
    private String infoTec;
    private String formaAcade;




    public long getIdCurr() {
        return idCurr;
    }

    public void setIdCurr(long idCurr) {
        this.idCurr = idCurr;
    }

    public String getGrauEsco() {
        return grauEsco;
    }

    public void setGrauEsco(String grauEsco) {
        this.grauEsco = grauEsco;
    }

    public String getInfoTec() {
        return infoTec;
    }

    public void setInfoTec(String infoTec) {
        this.infoTec = infoTec;
    }

    public String getFormaAcade() {
        return formaAcade;
    }

    public void setFormaAcade(String formaAcade) {
        this.formaAcade = formaAcade;
    }

    public Curriculo() {


    }

    public void setPessoaFifica(PessoaFisica pessoaFisica) {
    }
}
