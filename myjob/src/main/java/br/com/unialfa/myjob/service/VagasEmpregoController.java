package br.com.unialfa.myjob.service;

import br.com.unialfa.myjob.DAO.VagasEmpregoDAO;
import br.com.unialfa.myjob.domain.VagasEmprego;
import br.com.unialfa.myjob.repository.VagasEmpregoRepository;
import br.com.unialfa.myjob.business.VagasEmpregoBusiness;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vagasEmprego")
public class VagasEmpregoController {

    final VagasEmpregoRepository vagasEmpregoRepository;
    final VagasEmpregoBusiness vagasEmpregoBusiness;


    public VagasEmpregoController(VagasEmpregoRepository vagasEmpregoRepository, VagasEmpregoBusiness vagasEmpregoBusiness) {
        this.vagasEmpregoRepository = vagasEmpregoRepository;
        this.vagasEmpregoBusiness = vagasEmpregoBusiness;
    }

    @GetMapping(path = "/{email}")
    public Iterable<VagasEmprego> listarVasgasEmpresa(@PathVariable(name = "email") String email) {
        return vagasEmpregoBusiness.listarVasgasEmpresa(email);
    }

    @PostMapping(path = "/add")
    public void cadastrarVagasEmprego(@RequestBody VagasEmpregoDAO vagasEmpregoDTO) {
        vagasEmpregoBusiness.salvarVagasEmprego(vagasEmpregoDTO);
    }

    @PutMapping(path = "/edit")
    public void editarVagasEmprego(@RequestBody VagasEmprego vagasEmprego) {
        vagasEmpregoRepository.save(vagasEmprego);
    }
    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody void deletarVagaEmprego(@PathVariable(name = "id") long id){
        vagasEmpregoBusiness.deletarVagaEmprego(id);
    }
}
