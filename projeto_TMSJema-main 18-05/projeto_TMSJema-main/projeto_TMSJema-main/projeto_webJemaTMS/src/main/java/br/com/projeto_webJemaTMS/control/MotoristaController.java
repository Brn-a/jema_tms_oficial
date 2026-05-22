package br.com.projeto_webJemaTMS.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.projeto_webJemaTMS.model.Cliente;
import br.com.projeto_webJemaTMS.model.Motorista;
import br.com.projeto_webJemaTMS.repository.ListaRepository;
import br.com.projeto_webJemaTMS.service.CachingServiceMotorista;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    private final CachingServiceMotorista service;
    

    @Autowired
    public MotoristaController(CachingServiceMotorista service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<Motorista> listar() {
        return service.findAll();
    }
    
   
    
    @GetMapping("/{id}")
    public Motorista buscar(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/novo")
    public Motorista inserir(@RequestBody Motorista m) {
        return service.salvar(m);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}