package br.com.unialfa.myjob.service;


import br.com.unialfa.myjob.business.CadastroLoginBusiness;
import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cadastroLogin")

public class CadastroLoginController {

    final CadastroLoginBusiness cadastroLoginBusiness;
    final CadastroLoginRepository cadastroLoginRepository;

    public CadastroLoginController(CadastroLoginBusiness cadastroLoginBusiness, CadastroLoginRepository cadastroLoginRepository) {
        this.cadastroLoginBusiness = cadastroLoginBusiness;
        this.cadastroLoginRepository = cadastroLoginRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Usuario> listarCadastroLogin() {
        return cadastroLoginBusiness.findAll();
    }

    @PostMapping(path = "/add")
    public void cadastrarCadastroLogin(@RequestBody Usuario usuario) {
        cadastroLoginBusiness.salvar(usuario);
    }

    @PutMapping(path = "/edit")
    public void editarCadastroLogin(@RequestBody Usuario usuario) {
        cadastroLoginRepository.save(usuario);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> bucarTipoPorId(@RequestBody Usuario usuario){
        try {
            Usuario usuarioDao = cadastroLoginRepository.findByEmail(usuario.getEmail());
            if(usuarioDao.getSenha().equals(usuario.getSenha())){
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
