package br.com.projeto_webJemaTMS.service;

import java.util.List;
import java.util.Optional;

import br.com.projeto_webJemaTMS.model.Cliente;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Cliente;
import br.com.projeto_webJemaTMS.repository.ClienteRepository;

import br.com.projeto_webJemaTMS.model.*;
import br.com.projeto_webJemaTMS.repository.*;

@Service
public class CachingServiceCliente {

	 @Autowired
	    private ClienteRepository rep;

	    @Cacheable("clientes")
	    public List<Cliente> findAll() {
	        return rep.findAll();
	    }

	    @Cacheable(value = "cliente", key = "#id")
	    public Optional<Cliente> findById(Long id) {
	        return rep.findById(id);
	    }

	    @CacheEvict(value = {"clientes", "cliente"}, allEntries = true)
	    public void removerCache() {
	    }
	    
}

