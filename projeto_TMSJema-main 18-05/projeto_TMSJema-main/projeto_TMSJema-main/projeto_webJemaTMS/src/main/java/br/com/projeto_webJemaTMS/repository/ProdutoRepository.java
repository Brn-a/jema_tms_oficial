package br.com.projeto_webJemaTMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import br.com.projeto_webJemaTMS.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Optional<Produto> findBySku(String sku);
}