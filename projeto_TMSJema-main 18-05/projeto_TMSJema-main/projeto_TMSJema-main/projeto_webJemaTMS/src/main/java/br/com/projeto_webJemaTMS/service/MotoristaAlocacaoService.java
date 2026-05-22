package br.com.projeto_webJemaTMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto_webJemaTMS.model.Lista;
import br.com.projeto_webJemaTMS.model.Motorista;
import br.com.projeto_webJemaTMS.repository.ListaRepository;
import br.com.projeto_webJemaTMS.repository.MotoristaRepository;

@Service
public class MotoristaAlocacaoService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private ListaRepository listaRepository;

    public List<Motorista> buscarMotoristasDisponiveis(Long listaId) {

        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        double peso = lista.getPesoTotal() != null ? lista.getPesoTotal() : 0;
        double volume = lista.getVolumetriaTotal() != null ? lista.getVolumetriaTotal() : 0;

        return motoristaRepository.findAll()
                .stream()
                .filter(m -> m.getCaminhao() != null)
                .filter(m -> {
                    Double capacidade = m.getCaminhao()
                            .getTipo()
                            .getCapacidadeToneladas();

                    return capacidade >= peso && capacidade >= volume;
                })
                .toList();
    }
}