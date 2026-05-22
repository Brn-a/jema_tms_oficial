package br.com.projeto_webJemaTMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto_webJemaTMS.model.Endereco;
import br.com.projeto_webJemaTMS.model.Lista;

public interface ListaRepository extends JpaRepository<Lista, Long> {

}