package br.com.projeto_webJemaTMS.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.projeto_webJemaTMS.model.Caminhao;
import br.com.projeto_webJemaTMS.model.Endereco;
import br.com.projeto_webJemaTMS.model.Produto;
import br.com.projeto_webJemaTMS.repository.EnderecoRepository;
import br.com.projeto_webJemaTMS.service.CachingServiceCliente;
import br.com.projeto_webJemaTMS.service.*;


@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	 @Autowired
	    private EnderecoRepository repE;

	    @Autowired
	    private CachingServiceEndereco cacheE;

	   
	    @GetMapping("/todos")
	    public List<Endereco> listarTodos() {
	        return repE.findAll();
	    }

	    
	    
	    @GetMapping("/todos_cache")
	    public List<Endereco> listarCache() {
	        return cacheE.findAll();
	    }

	    
	    @GetMapping("/{id}")
	    public Endereco buscarPorId(@PathVariable Long id) {

	        Optional<Endereco> op = cacheE.findById(id);

	        if (op.isPresent()) {
	            return op.get();
	        }

	        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }

	 
	    @GetMapping(value = "/endereco/{cep}")
	    public ResponseEntity<Endereco> buscarPorCep(@PathVariable String cep) {

	        Optional<Endereco> op = repE.findByCep(cep);

	        if (op.isPresent()) {
	            return ResponseEntity.ok(op.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	    
	    
	    @PostMapping("/novo")
	    public Endereco inserir(@RequestBody Endereco endereco) {

	        repE.save(endereco);
	        cacheE.limparCache();

	        return endereco;
	    }

	    	    @PutMapping("/atualizar/{id}")
	    public Endereco atualizar(@PathVariable Long id,
	                              @RequestBody Endereco endereco) {

	        Optional<Endereco> op = cacheE.findById(id);

	        if (op.isPresent()) {

	            Endereco endBanco = op.get();

	            endBanco.setLogradouro(endereco.getLogradouro());
	            endBanco.setBairro(endereco.getBairro());
	            endBanco.setCidade(endereco.getCidade());
	            endBanco.setUf(endereco.getUf());
	            endBanco.setNum(endereco.getNum());
	            endBanco.setCep(endereco.getCep());
	            endBanco.setComplemento(endereco.getComplemento());

	            repE.save(endBanco);
	            cacheE.limparCache();

	            return endBanco;
	        }

	        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }

	    
	    @DeleteMapping("/{id}/remover")
	    public Endereco remover(@PathVariable Long id) {

	        Optional<Endereco> op = cacheE.findById(id);

	        if (op.isPresent()) {

	            Endereco end = op.get();

	            repE.deleteById(id);
	            cacheE.limparCache();

	            return end;
	        }

	        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }
}
	