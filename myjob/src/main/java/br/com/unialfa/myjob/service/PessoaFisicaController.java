package br.com.unialfa.myjob.service;
import br.com.unialfa.myjob.DAO.PessoaFisicaDAO;
import br.com.unialfa.myjob.domain.PessoaFisica;
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


    public PessoaFisicaController(PessoaFisicaBusiness pessoaFisicaBusiness, PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaBusiness = pessoaFisicaBusiness;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<PessoaFisica> listarPessoa() {
        return pessoaFisicaRepository.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> cadastrarPessoaFisica(@RequestBody PessoaFisicaDAO pessoaFisicaDAO) {

        try {
            return new ResponseEntity<>(pessoaFisicaBusiness.salvarPessoaFisica(pessoaFisicaDAO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/edit")
    public ResponseEntity<?> editarPessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.save(pessoaFisica);
        try {
            return new ResponseEntity<>(pessoaFisicaRepository.save(pessoaFisica), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> bucarTipoPorId(@PathVariable(name = "idPess") long idPess) {
        try {
            return new ResponseEntity<>(pessoaFisicaRepository.findById(idPess), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
