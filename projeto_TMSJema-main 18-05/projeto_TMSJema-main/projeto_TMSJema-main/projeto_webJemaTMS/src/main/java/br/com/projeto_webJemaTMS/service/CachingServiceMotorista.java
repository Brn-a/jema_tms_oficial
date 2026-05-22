package br.com.projeto_webJemaTMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Motorista;
import br.com.projeto_webJemaTMS.repository.MotoristaRepository;

@Service
public class CachingServiceMotorista {

    @Autowired
    private MotoristaRepository rep;

    // 🔥 LISTAR TODOS (CACHE)
    @Cacheable("motoristas")
    public List<Motorista> findAll() {
        return rep.findAll();
    }

    // 🔥 BUSCAR POR ID (CACHE POR ID)
    @Cacheable(value = "motorista", key = "#id")
    public Optional<Motorista> findById(Long id) {
        return rep.findById(id);
    }

    // ➕ SALVAR (limpa cache)
    @CacheEvict(value = {"motoristas", "motorista"}, allEntries = true)
    public Motorista salvar(Motorista m) {
        return rep.save(m);
    }

    // ❌ DELETAR (limpa cache)
    @CacheEvict(value = {"motoristas", "motorista"}, allEntries = true)
    public void deletar(Long id) {
        rep.deleteById(id);
    }
}