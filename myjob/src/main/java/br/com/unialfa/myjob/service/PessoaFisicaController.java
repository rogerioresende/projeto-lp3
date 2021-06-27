package br.com.unialfa.myjob.service;
import br.com.unialfa.myjob.DAO.PessoaFisicaDAO;
import br.com.unialfa.myjob.domain.PessoaFisica;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import br.com.unialfa.myjob.repository.PessoaFisicaRepository;
import br.com.unialfa.myjob.business.PessoaFisicaBusiness;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pessoaFisica")
public class PessoaFisicaController {

    final PessoaFisicaBusiness pessoaFisicaBusiness;
    final PessoaFisicaRepository pessoaFisicaRepository;
    final CadastroLoginRepository cadastroLoginRepository;


    public PessoaFisicaController(PessoaFisicaBusiness pessoaFisicaBusiness, PessoaFisicaRepository pessoaFisicaRepository, CadastroLoginRepository cadastroLoginRepository) {
        this.pessoaFisicaBusiness = pessoaFisicaBusiness;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.cadastroLoginRepository = cadastroLoginRepository;
    }

    @GetMapping(value = "/listar/{email}")
    public PessoaFisica listarPessoa(@PathVariable(name = "email") String email) {
        return pessoaFisicaBusiness.listarPessoa(email);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> cadastrarPessoaFisica(@RequestBody PessoaFisicaDAO pessoaFisicaDAO) {
        PessoaFisicaDAO teste = pessoaFisicaDAO;
        try {
            return new ResponseEntity<>(pessoaFisicaBusiness.salvarPessoaFisica(pessoaFisicaDAO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/edit/{email}")
    public ResponseEntity<?> editarPessoaFisica(@RequestBody PessoaFisica pessoaFisica, @PathVariable(name = "email") String email) {
        Usuario usuario = cadastroLoginRepository.findByEmail(email);
        pessoaFisica.setCadastroLogin(usuario);
        try {
            return new ResponseEntity<>(pessoaFisicaRepository.save(pessoaFisica), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{idPess}")
    public ResponseEntity<?> bucarTipoPorId(@PathVariable(name = "idPess") long idPess) {
        try {
            return new ResponseEntity<>(pessoaFisicaRepository.findById(idPess), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/delete/{idPess}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable(name = "idPess") long idPess){
        try {
            pessoaFisicaRepository.deleteById(idPess);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
