package br.com.unialfa.myjob.business;
import br.com.unialfa.myjob.DAO.EmpresaDAO;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.domain.Empresa;
import br.com.unialfa.myjob.repository.EmpresaRepository;
import br.com.unialfa.myjob.repository.VagasEmpregoRepository;
import org.springframework.stereotype.Service;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import br.com.unialfa.myjob.repository.EnderecoRepository;

import java.util.Optional;

@Service
public class EmpresaBusiness {


    private final EnderecoRepository enderecoRepository;
    private final EmpresaRepository empresaRepository;
    private final CadastroLoginRepository cadastroLoginRepository;
    private final VagasEmpregoRepository vagasEmpregoRepository;


    public EmpresaBusiness(EnderecoRepository enderecoRepository, EmpresaRepository empresaRepository, CadastroLoginRepository cadastroLoginRepository, VagasEmpregoRepository vagasEmpregoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.empresaRepository = empresaRepository;
        this.cadastroLoginRepository = cadastroLoginRepository;
        this.vagasEmpregoRepository = vagasEmpregoRepository;
    }

    public Empresa salvarEmpresa(EmpresaDAO empresaDAO) {
        Usuario usuario = cadastroLoginRepository.findByEmail(empresaDAO.getUsuario().getEmail());
        Empresa empresa = new Empresa();
        empresa.setNomeFant(empresaDAO.getNomeFant());
        empresa.setRazaoSocial(empresaDAO.getRazaoSocial());
        empresa.setCnpj(empresaDAO.getCnpj());
        empresa.setCadastroLogin(usuario);
        return empresaRepository.save(empresa);

    }

    public Iterable<Empresa> findAll() {
        return empresaRepository.findAll();
    }
}





