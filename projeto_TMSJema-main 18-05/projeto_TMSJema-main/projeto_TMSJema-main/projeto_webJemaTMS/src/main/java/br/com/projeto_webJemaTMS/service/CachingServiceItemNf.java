package br.com.projeto_webJemaTMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.ItemNota;
import br.com.projeto_webJemaTMS.repository.ItemNotaRepository;

@Service
public class CachingServiceItemNf {

    @Autowired
    private ItemNotaRepository rep;

    public List<ItemNota> findAll() {
        return rep.findAll();
    }

    public ItemNota salvar(ItemNota item) {
        return rep.save(item);
    }
}