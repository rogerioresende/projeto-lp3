package br.com.unialfa.myjob.business;

import br.com.unialfa.myjob.DAO.VagasEmpregoDAO;
import br.com.unialfa.myjob.domain.Empresa;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.domain.VagasEmprego;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.unialfa.myjob.repository.EmpresaRepository;
import br.com.unialfa.myjob.repository.VagasEmpregoRepository;

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

    public ResponseEntity<?> salvarVagasEmprego(VagasEmpregoDAO vagasEmpregoDAO, String email) {
        try {
        VagasEmprego vagasEmprego = new VagasEmprego();
        vagasEmprego.setNomeEmp(vagasEmpregoDAO.getNomeEmp());
        vagasEmprego.setNivelEsco(vagasEmpregoDAO.getNivelEsco());
        vagasEmprego.setNivelTec(vagasEmpregoDAO.getNivelTec());
        vagasEmprego.setEspVaga(vagasEmpregoDAO.getEspVaga());
        vagasEmprego.setVagaPreenchida(vagasEmpregoDAO.isVagaPreenchida());
        Usuario usuario = cadastroLoginRepository.findByEmail(email);
        Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId());
        vagasEmprego.setEmpresa(empresa);
            return new ResponseEntity<>(vagasEmpregoRepository.save(vagasEmprego),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<VagasEmprego> listarVagasEmpresa(String email) {
        Usuario usuario = cadastroLoginRepository.findByEmail(email);
        Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId());
        if(empresa == null){
            return null;
        }
        return empresa.getVagasEmpregos();
    }

    public ResponseEntity<?> deletarVagaEmprego(long id) {
        try {
            vagasEmpregoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
