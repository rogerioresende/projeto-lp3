package br.com.unialfa.myjob.business;

import br.com.unialfa.myjob.domain.Usuario;
import br.com.unialfa.myjob.domain.Endereco;
import br.com.unialfa.myjob.repository.CadastroLoginRepository;
import br.com.unialfa.myjob.repository.EnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroLoginBusiness {

    private final CadastroLoginRepository cadastroLoginRepository;
    private final EnderecoRepository enderecoRepository;

    public CadastroLoginBusiness(CadastroLoginRepository cadastroLoginRepository,
                                 EnderecoRepository enderecoRepository) {
        this.cadastroLoginRepository = cadastroLoginRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public ResponseEntity<?> salvar(Usuario usuario) {
        try {
        Endereco endereco = new Endereco();
        endereco.setRua(usuario.getEndereco().getRua());
        endereco.setBairro(usuario.getEndereco().getBairro());
        endereco.setEstado(usuario.getEndereco().getEstado());
        endereco.setComplemento(usuario.getEndereco().getComplemento());
        endereco = enderecoRepository.save(endereco);

        Usuario usuarioDao = new Usuario();
        usuario.setEmail(usuario.getEmail());
        usuario.setSenha(usuario.getSenha());
        usuario.setUsuario(usuario.getUsuario());
        usuario.setEndereco(endereco);
            return new ResponseEntity<>(cadastroLoginRepository.save(usuario), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }


    public Iterable<Usuario> findAll() {
        return cadastroLoginRepository.findAll();
    }


}

