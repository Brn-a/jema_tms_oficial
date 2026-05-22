package br.com.projeto_webJemaTMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto_webJemaTMS.model.Cliente;
import br.com.projeto_webJemaTMS.model.Endereco;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByCpf(String cpf);
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
}