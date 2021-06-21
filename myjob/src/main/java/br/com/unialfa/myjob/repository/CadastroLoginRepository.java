package br.com.unialfa.myjob.repository;

import br.com.unialfa.myjob.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface CadastroLoginRepository extends CrudRepository<Usuario,Long> {


    Usuario findByEmail(String email);
}
