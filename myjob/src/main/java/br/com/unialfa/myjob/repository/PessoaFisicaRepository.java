package br.com.unialfa.myjob.repository;

import br.com.unialfa.myjob.domain.PessoaFisica;
import org.springframework.data.repository.CrudRepository;

public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {
    PessoaFisica findByUsuarioId(long id);
}
