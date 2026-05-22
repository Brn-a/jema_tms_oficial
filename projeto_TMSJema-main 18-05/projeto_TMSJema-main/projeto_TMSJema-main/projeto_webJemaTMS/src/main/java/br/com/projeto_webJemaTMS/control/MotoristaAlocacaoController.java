package br.com.projeto_webJemaTMS.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
	
import br.com.projeto_webJemaTMS.model.Motorista;
import br.com.projeto_webJemaTMS.service.MotoristaAlocacaoService;

@RestController
@RequestMapping("/alocacao")
public class MotoristaAlocacaoController {

    @Autowired
    private MotoristaAlocacaoService service;

    @GetMapping("/lista/{listaId}/motoristas-disponiveis")
    public List<Motorista> listar(@PathVariable Long listaId) {
        return service.buscarMotoristasDisponiveis(listaId);
    }
}