package br.com.unialfa.myjob.service;
import br.com.unialfa.myjob.DAO.CandidatarVagaDAO;
import br.com.unialfa.myjob.business.CandidatarVagaBusiness;
import br.com.unialfa.myjob.domain.CandidatarVaga;
import br.com.unialfa.myjob.repository.CandidatarVagaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/candidatarVaga")

public class CandidatarVagaController {

    private final CandidatarVagaRepository candidatarVagaRepository;
    private final CandidatarVagaBusiness candidatarVagaBusiness;

    public CandidatarVagaController(CandidatarVagaRepository candidatarVagaRepository, CandidatarVagaBusiness candidatarVagaBusiness) {
        this.candidatarVagaRepository = candidatarVagaRepository;
        this.candidatarVagaBusiness = candidatarVagaBusiness;
    }
    @PostMapping(path = "/add")
    public void candidatarVaga(@RequestBody CandidatarVagaDAO candidatarVagaDTO) {
        candidatarVagaBusiness.candidatarVaga(candidatarVagaDTO);
    }
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<CandidatarVaga> listarEndereco(){
        return candidatarVagaRepository.findAll();
    }
}
