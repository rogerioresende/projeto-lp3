package br.com.unialfa.myjob.business;
import br.com.unialfa.myjob.DAO.PessoaFisicaDAO;
import br.com.unialfa.myjob.domain.Curriculo;
import br.com.unialfa.myjob.domain.Empresa;
import br.com.unialfa.myjob.domain.PessoaFisica;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.repository.CurriculoRepository;
import br.com.unialfa.myjob.repository.EmpresaRepository;
import br.com.unialfa.myjob.repository.PessoaFisicaRepository;
import org.springframework.stereotype.Service;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;

import java.util.Optional;

@Service
public class PessoaFisicaBusiness {

    private final CurriculoRepository curriculoRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final CadastroLoginRepository cadastroLoginRepository;

    public PessoaFisicaBusiness(CurriculoRepository curriculoRepository,
                                PessoaFisicaRepository pessoaFisicaRepository,
                                CadastroLoginRepository cadastroLoginRepository) {
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

        Usuario usuario = cadastroLoginRepository.findByEmail(pessoaFisicaDAO.getUsuario().getEmail());
        pessoaFisica.setCadastroLogin(usuario);

        Curriculo curriculo = new Curriculo();
        curriculo.setFormaAcade(pessoaFisicaDAO.getCurriculo().getFormaAcade());
        curriculo.setGrauEsco(pessoaFisicaDAO.getCurriculo().getGrauEsco());
        curriculo.setInfoTec(pessoaFisicaDAO.getCurriculo().getInfoTec());
        pessoaFisica.setCurriculo(curriculoRepository.save(curriculo));

        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public PessoaFisica listarPessoa(String email) {
        Usuario usuario = cadastroLoginRepository.findByEmail(email);
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findByUsuarioId(usuario.getId());
        return pessoaFisica;
    }


}
