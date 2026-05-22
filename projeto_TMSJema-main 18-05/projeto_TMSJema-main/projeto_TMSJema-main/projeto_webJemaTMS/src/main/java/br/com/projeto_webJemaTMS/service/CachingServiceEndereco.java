package br.com.projeto_webJemaTMS.service;
import br.com.projeto_webJemaTMS.model.Cliente;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Endereco;
import br.com.projeto_webJemaTMS.repository.EnderecoRepository;

@Service
public class CachingServiceEndereco {
	 @Autowired
	    private EnderecoRepository rep;

	    @Cacheable("enderecos")
	    public List<Endereco> findAll() {
	        return rep.findAll();
	    }

	    @Cacheable(value = "endereco", key = "#id")
	    public Optional<Endereco> findById(Long id) {
	        return rep.findById(id);
	    }

	    @CacheEvict(value = {"enderecos", "endereco"}, allEntries = true)
	    public void limparCache() {}
}
