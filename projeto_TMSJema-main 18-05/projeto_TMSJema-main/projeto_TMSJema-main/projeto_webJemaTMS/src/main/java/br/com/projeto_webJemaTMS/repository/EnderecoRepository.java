package br.com.projeto_webJemaTMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto_webJemaTMS.model.Caminhao;
import br.com.projeto_webJemaTMS.model.Endereco;
import br.com.projeto_webJemaTMS.model.Produto;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	Optional<Endereco> findByCep(String cep);
}