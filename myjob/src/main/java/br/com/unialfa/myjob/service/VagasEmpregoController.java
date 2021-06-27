package br.com.unialfa.myjob.service;

import br.com.unialfa.myjob.DAO.VagasEmpregoDAO;
import br.com.unialfa.myjob.domain.Empresa;
import br.com.unialfa.myjob.domain.PessoaFisica;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.domain.VagasEmprego;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import br.com.unialfa.myjob.repository.EmpresaRepository;
import br.com.unialfa.myjob.repository.VagasEmpregoRepository;
import br.com.unialfa.myjob.business.VagasEmpregoBusiness;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vagasEmprego")
public class VagasEmpregoController {

    final VagasEmpregoRepository vagasEmpregoRepository;
    final VagasEmpregoBusiness vagasEmpregoBusiness;
    final CadastroLoginRepository cadastroLoginRepository;
    private final EmpresaRepository empresaRepository;


    public VagasEmpregoController(VagasEmpregoRepository vagasEmpregoRepository, VagasEmpregoBusiness vagasEmpregoBusiness, CadastroLoginRepository cadastroLoginRepository, EmpresaRepository empresaRepository) {
        this.vagasEmpregoRepository = vagasEmpregoRepository;
        this.vagasEmpregoBusiness = vagasEmpregoBusiness;
        this.cadastroLoginRepository = cadastroLoginRepository;
        this.empresaRepository = empresaRepository;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarVagas() {
        try {
            return new ResponseEntity<>(vagasEmpregoRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/listar/{email}")
    public ResponseEntity<?> listarVagasEmpresa(@PathVariable(name = "email") String email) {
        try {
            return new ResponseEntity<>(vagasEmpregoBusiness.listarVagasEmpresa(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> bucarVagaPorId(@PathVariable(name = "id") long id) {
        try {
            return new ResponseEntity<>(vagasEmpregoRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add/{email}")
    public ResponseEntity<?> cadastrarVagasEmprego(@RequestBody VagasEmpregoDAO vagasEmpregoDTO , @PathVariable(name = "email") String email) {
       return vagasEmpregoBusiness.salvarVagasEmprego(vagasEmpregoDTO, email);
    }


    @PutMapping(path = "/edit/{email}")
    public ResponseEntity<?> editarVagasEmprego(@RequestBody VagasEmprego vagasEmprego, @PathVariable(name = "email") String email) {
        try {
        Usuario usuario = cadastroLoginRepository.findByEmail(email);
        Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId());
        vagasEmprego.setEmpresa(empresa);
            return new ResponseEntity<>(vagasEmpregoRepository.save(vagasEmprego), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletarVagaEmprego(@PathVariable(name = "id") long id){
       return vagasEmpregoBusiness.deletarVagaEmprego(id);
    }
}
