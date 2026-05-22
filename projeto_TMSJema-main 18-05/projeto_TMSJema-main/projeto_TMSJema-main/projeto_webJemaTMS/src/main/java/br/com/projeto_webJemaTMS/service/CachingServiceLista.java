package br.com.projeto_webJemaTMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Lista;
import br.com.projeto_webJemaTMS.model.Nf;
import br.com.projeto_webJemaTMS.repository.ListaRepository;
import br.com.projeto_webJemaTMS.repository.NfRepository;

@Service
public class CachingServiceLista {

    @Autowired
    private ListaRepository rep;

    @Autowired
    private NfRepository nfRep;

    // 🔥 LISTAR TODAS (CACHE)
    @Cacheable("listas")
    public List<Lista> findAll() {
        return rep.findAll();
    }

    // 🔥 BUSCAR POR ID (CACHE)
    @Cacheable(value = "lista", key = "#id")
    public Optional<Lista> findById(Long id) {
        return rep.findById(id);
    }

    // ➕ SALVAR
    @CacheEvict(value = {"listas", "lista"}, allEntries = true)
    public Lista salvar(Lista l) {
        return rep.save(l);
    }

    // ❌ DELETAR
    @CacheEvict(value = {"listas", "lista"}, allEntries = true)
    public void deletar(Long id) {
        rep.deleteById(id);
    }

    // ➕ ADICIONAR NF NA LISTA
    @CacheEvict(value = {"listas", "lista"}, allEntries = true)
    public Lista adicionarNf(Long listaId, Long nfId) {

        Lista lista = rep.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        Nf nf = nfRep.findById(nfId)
                .orElseThrow(() -> new RuntimeException("NF não encontrada"));

        lista.getNfs().add(nf);

        return rep.save(lista);
    }
}