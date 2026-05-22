package br.com.projeto_webJemaTMS.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.projeto_webJemaTMS.model.Caminhao;
import br.com.projeto_webJemaTMS.repository.CaminhaoRepository;
import br.com.projeto_webJemaTMS.service.*;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

    @Autowired
    private CachingServiceCaminhao service;

    @Autowired
    private CaminhaoRepository repCam;

    @GetMapping("/todos")
    public List<Caminhao> listar() {
        return service.findAll();
    }


    @GetMapping("/{id}")
    public Caminhao buscar(@PathVariable Long id) {

        Optional<Caminhao> op = service.findById(id);

        return op.orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/novo")
    public Caminhao inserir(@RequestBody Caminhao c) {

        return service.salvar(c);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {

        service.deletar(id);
    }
}