package br.com.unialfa.myjob.service;
import br.com.unialfa.myjob.DAO.EmpresaDAO;
import br.com.unialfa.myjob.DAO.PessoaFisicaDAO;
import br.com.unialfa.myjob.domain.Empresa;
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


    public EmpresaController(EmpresaBusiness empresaBusiness, EmpresaRepository empresaRepository) {
        this.empresaBusiness = empresaBusiness;
        this.empresaRepository = empresaRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Empresa> listarEmpresa() {
        return empresaRepository.findAll();
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

    @PutMapping(path = "/edit")
    public ResponseEntity<?> editarEmpresa(@RequestBody Empresa empresa) {
        empresaRepository.save(empresa);
        try {
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
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable(name = "idEmp") long idEmp){
        try {
            empresaRepository.deleteById(idEmp);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}

