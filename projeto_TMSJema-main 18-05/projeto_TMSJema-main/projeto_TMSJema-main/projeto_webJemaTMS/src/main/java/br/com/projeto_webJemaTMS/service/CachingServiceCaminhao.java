package br.com.projeto_webJemaTMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Caminhao;
import br.com.projeto_webJemaTMS.repository.CaminhaoRepository;

@Service
public class CachingServiceCaminhao {
	@Autowired
    private CaminhaoRepository rep;

    public List<Caminhao> findAll() {
        return rep.findAll();
    }

    public Optional<Caminhao> findById(Long id) {
        return rep.findById(id);
    }

    public Caminhao salvar(Caminhao c) {
        return rep.save(c);
    }

    public void deletar(Long id) {
        rep.deleteById(id);
    }
}
