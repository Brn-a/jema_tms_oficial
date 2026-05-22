package br.com.projeto_webJemaTMS.control;

import java.util.List;

import br.com.projeto_webJemaTMS.repository.ListaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.projeto_webJemaTMS.model.Cliente;
import br.com.projeto_webJemaTMS.model.Lista;
import br.com.projeto_webJemaTMS.service.CachingServiceLista;

@RestController
@RequestMapping("/listas")
public class ListaController {
	@PostMapping("/{listaId}/adicionar-nf/{nfId}")
	public Lista adicionarNf(@PathVariable Long listaId,
	                         @PathVariable Long nfId) {

	    return service.adicionarNf(listaId, nfId);
	}
	
	
    @Autowired
    private CachingServiceLista service;

    
    @GetMapping("/todos")
    public List<Lista> listar() {
        return service.findAll();
    }
    


   
    @GetMapping("/{id}")
    public Lista buscar(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

   
    @PostMapping("/novo")
    public Lista inserir(@RequestBody Lista lista) {
        return service.salvar(lista);
    }

    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}