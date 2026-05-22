package br.com.projeto_webJemaTMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Nf;
import br.com.projeto_webJemaTMS.repository.NfRepository;
@Service
public class CachingServiceNf {
	@Autowired
    private NfRepository rep;

    public List<Nf> findAll() {
        return rep.findAll();
    }

    public Optional<Nf> findById(Long id) {
        return rep.findById(id);
    }

    public Nf salvar(Nf nf) {
        return rep.save(nf);
    }

    public void deletar(Long id) {
        rep.deleteById(id);
    }
}
