package br.com.unialfa.myjob.business;

import br.com.unialfa.myjob.DAO.VagasEmpregoDAO;
import br.com.unialfa.myjob.domain.Empresa;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.domain.VagasEmprego;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import org.springframework.stereotype.Service;
import br.com.unialfa.myjob.repository.EmpresaRepository;
import br.com.unialfa.myjob.repository.VagasEmpregoRepository;

import java.util.Optional;

@Service
public class VagasEmpregoBusiness {

    private final VagasEmpregoRepository vagasEmpregoRepository;
    private final EmpresaRepository empresaRepository;
    private final CadastroLoginRepository cadastroLoginRepository;

    public VagasEmpregoBusiness(VagasEmpregoRepository vagasEmpregoRepository, EmpresaRepository empresaRepository, CadastroLoginRepository cadastroLoginRepository) {
        this.vagasEmpregoRepository = vagasEmpregoRepository;
        this.empresaRepository = empresaRepository;
        this.cadastroLoginRepository = cadastroLoginRepository;
    }

    public void salvarVagasEmprego(VagasEmpregoDAO vagasEmpregoDAO) {
        Optional<Empresa> empresa = empresaRepository.findById(vagasEmpregoDAO.getEmpresa_idEmp());
        VagasEmprego vagasEmprego = new VagasEmprego();
        vagasEmprego.setNomeEmp(vagasEmpregoDAO.getNomeEmp());
        vagasEmprego.setNivelEsco(vagasEmpregoDAO.getNivelEsco());
        vagasEmprego.setNivelTec(vagasEmpregoDAO.getNivelTec());
        vagasEmprego.setEspVaga(vagasEmpregoDAO.getEspVaga());
        vagasEmprego.setVagaPreenchida(vagasEmpregoDAO.isVagaPreenchida());
        vagasEmprego.setEmpresa(empresa.get());
        vagasEmpregoRepository.save(vagasEmprego);

    }

    public Iterable<VagasEmprego> listarVasgasEmpresa(String email) {
        Usuario usuario = cadastroLoginRepository.findByEmail(email);
        return usuario.getEmpresa().getVagasEmpregos();

    }


    public void deletarVagaEmprego(long id) {
        vagasEmpregoRepository.deleteById(id);
    }
}
