package br.com.projeto_webJemaTMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Produto;
import br.com.projeto_webJemaTMS.repository.ProdutoRepository;

@Service
public class CachingServiceProduto {
	 @Autowired
	    private ProdutoRepository rep;

	    @Cacheable("produtos")
	    public List<Produto> findAll() {
	        return rep.findAll();
	    }

	    @Cacheable(value = "produto", key = "#id")
	    public Optional<Produto> findById(Long id) {
	        return rep.findById(id);
	    }

	    @CacheEvict(value = {"produtos", "produto"}, allEntries = true)
	    public void limparCache() {
	    }
}
