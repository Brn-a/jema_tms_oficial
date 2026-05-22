package br.com.projeto_webJemaTMS.control;


import br.com.projeto_webJemaTMS.model.Nf;
import br.com.projeto_webJemaTMS.service.CachingServiceNf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/nfs")
public class NfController {
	@Autowired
    private CachingServiceNf service;

    @GetMapping("/todos")
    public List<Nf> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Nf buscar(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/novo")
    public Nf inserir(@RequestBody Nf nf) {
        return service.salvar(nf);
    }
    
    @PutMapping("/atualizar/{id}")
    public Nf atualizar(@PathVariable Long id,
                        @RequestBody Nf nf) {

        Nf nfBanco = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        nfBanco.setNumero(nf.getNumero());
        nfBanco.setVolume(nf.getVolume());
        nfBanco.setPeso(nf.getPeso());
        nfBanco.setQuemRecebe(nf.getQuemRecebe());
        nfBanco.setValorFinal(nf.getValorFinal());
        nfBanco.setValorFrete(nf.getValorFrete());
        nfBanco.setCliente(nf.getCliente());
        nfBanco.setItens(nf.getItens());

        return service.salvar(nfBanco);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
