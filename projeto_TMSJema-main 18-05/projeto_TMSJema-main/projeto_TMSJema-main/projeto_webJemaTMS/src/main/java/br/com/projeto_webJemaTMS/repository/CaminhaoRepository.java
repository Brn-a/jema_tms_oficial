package br.com.projeto_webJemaTMS.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projeto_webJemaTMS.model.Caminhao;
import br.com.projeto_webJemaTMS.model.Cliente;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long>{

}

