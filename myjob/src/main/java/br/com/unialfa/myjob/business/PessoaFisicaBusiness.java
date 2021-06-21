package br.com.unialfa.myjob.business;
import br.com.unialfa.myjob.DAO.PessoaFisicaDAO;
import br.com.unialfa.myjob.domain.PessoaFisica;
import br.com.unialfa.myjob.repository.CurriculoRepository;
import br.com.unialfa.myjob.repository.PessoaFisicaRepository;
import org.springframework.stereotype.Service;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;

import java.util.Optional;

@Service
public class PessoaFisicaBusiness {

    private final CurriculoRepository curriculoRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final CadastroLoginRepository cadastroLoginRepository;

    public PessoaFisicaBusiness(CurriculoRepository curriculoRepository, PessoaFisicaRepository pessoaFisicaRepository, CadastroLoginRepository cadastroLoginRepository) {
        this.curriculoRepository = curriculoRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.cadastroLoginRepository = cadastroLoginRepository;
    }


    public PessoaFisica salvarPessoaFisica(PessoaFisicaDAO pessoaFisicaDAO) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(pessoaFisicaDAO.getNome());
        pessoaFisica.setIdade(pessoaFisicaDAO.getIdade());
        pessoaFisica.setSexo(pessoaFisicaDAO.getSexo());
        pessoaFisica.setCpf(pessoaFisicaDAO.getCpf());
        return pessoaFisicaRepository.save(pessoaFisica);
    }
    public Iterable<PessoaFisica> findAll() {
        return pessoaFisicaRepository.findAll();
}


}
