package br.com.projeto_webJemaTMS.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.projeto_webJemaTMS.model.Cliente;
import br.com.projeto_webJemaTMS.model.Endereco;
import br.com.projeto_webJemaTMS.repository.ClienteRepository;
import br.com.projeto_webJemaTMS.service.CachingServiceCliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	 @Autowired
	    private ClienteRepository repC;

	    @Autowired
	    private CachingServiceCliente cacheC;

	   
	    @GetMapping("/todos")
	    public List<Cliente> listarTodos() {
	        return repC.findAll();
	    }
	    
	    
	    @GetMapping("/nome/{nome}")
	    public List<Cliente> buscarPorNome(@PathVariable String nome) {

	        return repC.findByNomeContainingIgnoreCase(nome);
	    }
	    
	    
	    @GetMapping("/todos_cache")
	    public List<Cliente> listarTodosCache() {
	        return cacheC.findAll();
	    }

	   
	    @GetMapping("/{id}")
	    public Cliente buscarPorId(@PathVariable Long id) {

	        Optional<Cliente> op = cacheC.findById(id);

	        if (op.isPresent()) {
	            return op.get();
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    @GetMapping(value = "/clientes/{cpf}")
	    public ResponseEntity<Cliente> buscarPorC(@PathVariable String cpf) {

	        Optional<Cliente> op = repC.findByCpf(cpf);

	        if (op.isPresent()) {
	            return ResponseEntity.ok(op.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

	    
	    @PostMapping("/novo")
	    public Cliente inserir(@RequestBody Cliente cliente) {

	        repC.save(cliente);
	        cacheC.removerCache();

	        return cliente;
	    }

	    
	    @PutMapping("/atualizar/{id}")
	    public Cliente atualizar(@PathVariable Long id,
	                             @RequestBody Cliente cliente) {

	        Optional<Cliente> op = cacheC.findById(id);

	        if (op.isPresent()) {

	            Cliente clienteBanco = op.get();

	            // atualiza campos manualmente
	            clienteBanco.setNome(cliente.getNome());
	            clienteBanco.setSobrenome(cliente.getSobrenome());
	            clienteBanco.setCpf(cliente.getCpf());
	            clienteBanco.setCnpj(cliente.getCnpj());
	            clienteBanco.setEmail(cliente.getEmail());
	            clienteBanco.setNumero(cliente.getNumero());

	            repC.save(clienteBanco);
	            cacheC.removerCache();

	            return clienteBanco;

	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }
	    }

	    
	    @DeleteMapping("/{id}/remover")
	    public Cliente remover(@PathVariable Long id) {

	        Optional<Cliente> op = cacheC.findById(id);

	        if (op.isPresent()) {

	            Cliente cliente = op.get();

	            repC.deleteById(id);
	            cacheC.removerCache();

	            return cliente;

	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }
	    }
}
