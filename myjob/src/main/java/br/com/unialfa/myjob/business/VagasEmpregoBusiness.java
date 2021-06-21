package br.com.unialfa.myjob.business;

import br.com.unialfa.myjob.DAO.VagasEmpregoDAO;
import br.com.unialfa.myjob.domain.Empresa;
import br.com.unialfa.myjob.domain.VagasEmprego;
import org.springframework.stereotype.Service;
import br.com.unialfa.myjob.repository.EmpresaRepository;
import br.com.unialfa.myjob.repository.VagasEmpregoRepository;

import java.util.Optional;

@Service
public class VagasEmpregoBusiness {

    private final VagasEmpregoRepository vagasEmpregoRepository;
    private final EmpresaRepository empresaRepository;

    public VagasEmpregoBusiness(VagasEmpregoRepository vagasEmpregoRepository, EmpresaRepository empresaRepository) {
        this.vagasEmpregoRepository = vagasEmpregoRepository;
        this.empresaRepository = empresaRepository;
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

    public Iterable<VagasEmprego> findAll() {
        return vagasEmpregoRepository.findAll();
    }


    public void deletarVagaEmprego(long id) {
        vagasEmpregoRepository.deleteById(id);
    }
}
