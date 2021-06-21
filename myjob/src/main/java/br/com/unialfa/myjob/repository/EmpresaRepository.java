package br.com.unialfa.myjob.repository;

import br.com.unialfa.myjob.domain.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository  extends CrudRepository<Empresa, Long> {

}
