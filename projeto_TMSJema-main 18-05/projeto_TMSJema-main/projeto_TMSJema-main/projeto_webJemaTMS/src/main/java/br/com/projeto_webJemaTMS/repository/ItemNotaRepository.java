package br.com.projeto_webJemaTMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projeto_webJemaTMS.model.ItemNota;

public interface ItemNotaRepository extends JpaRepository<ItemNota, Long> {
}