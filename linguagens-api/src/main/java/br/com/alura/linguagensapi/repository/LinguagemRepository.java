package br.com.alura.linguagensapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.linguagensapi.model.Linguagem;

public interface LinguagemRepository extends JpaRepository<Linguagem, Long> {

	Optional<Linguagem> findByTitleContainingIgnoreCase(String title);
	
}
