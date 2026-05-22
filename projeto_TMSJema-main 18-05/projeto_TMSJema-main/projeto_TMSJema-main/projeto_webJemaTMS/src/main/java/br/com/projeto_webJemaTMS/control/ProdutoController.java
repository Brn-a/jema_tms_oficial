package br.com.projeto_webJemaTMS.control;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.projeto_webJemaTMS.model.Produto;
import br.com.projeto_webJemaTMS.repository.ProdutoRepository;
import br.com.projeto_webJemaTMS.service.CachingServiceProduto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
    private ProdutoRepository rep;

    @Autowired
    private CachingServiceProduto cache;


    @GetMapping(value = "/todos")
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(rep.findAll());
        
    }
    
    @GetMapping(value = "/sku/{sku}")
    public ResponseEntity<Produto> buscarPorSku(@PathVariable String sku) {

        Optional<Produto> op = rep.findBySku(sku);

        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

 
    @GetMapping("/todos_cache")
    public ResponseEntity<List<Produto>> listarCache() {
        return ResponseEntity.ok(cache.findAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> buscarPorId
    (@PathVariable Long id) {

        Optional<Produto> op = cache.findById(id);

        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PostMapping("/novo")
    public Produto inserir(@RequestBody Produto produto) {

        rep.save(produto);
        cache.limparCache();

        return produto;
    }


    @PutMapping("/atualizar/{id}")
    public Produto atualizar(@PathVariable Long id,
                             @RequestBody Produto produto) {

        Optional<Produto> op = cache.findById(id);

        if (op.isPresent()) {

            Produto prodBanco = op.get();

            prodBanco.setNome(produto.getNome());
            prodBanco.setCor(produto.getCor());
            prodBanco.setSku(produto.getSku());
            prodBanco.setPreco(produto.getPreco());
            prodBanco.setVolumetriaTotal(produto.getVolumetriaTotal());

            rep.save(prodBanco);
            cache.limparCache();

            return prodBanco;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}/remover")
    public ResponseEntity<Produto> remover(@PathVariable Long id) {

        Optional<Produto> op = cache.findById(id);

        if (op.isPresent()) {

            Produto prod = op.get();

            rep.deleteById(id);
            cache.limparCache();
            return ResponseEntity.noContent().build();                
            
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        
    }
}
