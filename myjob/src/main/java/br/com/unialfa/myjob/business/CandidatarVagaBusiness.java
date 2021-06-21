package br.com.unialfa.myjob.business;

import br.com.unialfa.myjob.DAO.CandidatarVagaDAO;
import br.com.unialfa.myjob.domain.CandidatarVaga;
import br.com.unialfa.myjob.domain.PessoaFisica;
import br.com.unialfa.myjob.domain.VagasEmprego;
import br.com.unialfa.myjob.repository.CandidatarVagaRepository;
import br.com.unialfa.myjob.repository.PessoaFisicaRepository;
import br.com.unialfa.myjob.repository.VagasEmpregoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatarVagaBusiness {
    private final CandidatarVagaRepository candidatarVagaRepository;
    private final VagasEmpregoRepository vagasEmpregoRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    public CandidatarVagaBusiness(CandidatarVagaRepository candidatarVagaRepository, VagasEmpregoRepository vagasEmpregoRepository, PessoaFisicaRepository pessoaFisicaRepository) {
        this.candidatarVagaRepository = candidatarVagaRepository;
        this.vagasEmpregoRepository = vagasEmpregoRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }
    public void candidatarVaga(CandidatarVagaDAO candidatarVagaDAO) {

        CandidatarVaga candidatarVaga = new CandidatarVaga();
        candidatarVaga.setCandidatar(candidatarVagaDAO.isCandidatar());
        Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(candidatarVagaDAO.getPessoaFisica_idPess());
        candidatarVaga.setPessoaFisica(pessoaFisica.get());
        Optional<VagasEmprego> vagasEmprego = vagasEmpregoRepository.findById(candidatarVagaDAO.getVagasEmprego_idVaga());
        candidatarVaga.setVagasEmprego(vagasEmprego.get());
        candidatarVagaRepository.save(candidatarVaga);

    }
    public Iterable<CandidatarVaga> findAll() {
        return candidatarVagaRepository.findAll();
    }
}
