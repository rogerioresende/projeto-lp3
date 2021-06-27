package br.com.unialfa.myjob.service;
import br.com.unialfa.myjob.DAO.EmpresaDAO;
import br.com.unialfa.myjob.DAO.PessoaFisicaDAO;
import br.com.unialfa.myjob.domain.Empresa;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import br.com.unialfa.myjob.repository.EmpresaRepository;
import br.com.unialfa.myjob.business.EmpresaBusiness;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/empresa")
public class EmpresaController {

    final EmpresaBusiness empresaBusiness;
    final EmpresaRepository empresaRepository;
    final CadastroLoginRepository cadastroLoginRepository;


    public EmpresaController(EmpresaBusiness empresaBusiness, EmpresaRepository empresaRepository, CadastroLoginRepository cadastroLoginRepository) {
        this.empresaBusiness = empresaBusiness;
        this.empresaRepository = empresaRepository;
        this.cadastroLoginRepository = cadastroLoginRepository;
    }

    @GetMapping(value = "/listar/{email}")
    public Empresa listarEmpresa(@PathVariable(name = "email") String email) {
        return empresaBusiness.listarEmpresa(email);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody EmpresaDAO empresaDAO){
        EmpresaDAO teste = empresaDAO;
        try {
            return new ResponseEntity<>(empresaBusiness.salvarEmpresa(empresaDAO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/edit/{email}")
    public ResponseEntity<?> editarEmpresa(@RequestBody Empresa empresa, @PathVariable(name = "email") String email) {
        try {
            Usuario usuario = cadastroLoginRepository.findByEmail(email);
            empresa.setCadastroLogin(usuario);
            return new ResponseEntity<>(empresaRepository.save(empresa), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{idEmp}")
    public ResponseEntity<?> bucarTipoPorId(@PathVariable(name = "idEmp") long idEmp){
        try {
            return new ResponseEntity<>(empresaRepository.findById(idEmp), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(path = "/delete/{idEmp}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable(name = "idEmp") long idEmp){
        try {
            empresaRepository.deleteById(idEmp);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}

