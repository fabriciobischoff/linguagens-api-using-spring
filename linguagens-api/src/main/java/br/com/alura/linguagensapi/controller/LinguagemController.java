package br.com.alura.linguagensapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.linguagensapi.model.Linguagem;
import br.com.alura.linguagensapi.services.LinguagemService;

@RequestMapping("/linguagens")
@RestController
public class LinguagemController {

	private LinguagemService linguagemService;
	
	public LinguagemController(LinguagemService linguagemService) {
		this.linguagemService = linguagemService;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping
	public List<Linguagem> obterLinguagens() {
		return linguagemService.listar();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Linguagem> buscar(@PathVariable("id") Long linguagemId) {
		Linguagem linguagem = linguagemService.buscar(linguagemId);
		
		return ResponseEntity.ok(linguagem);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Linguagem cadastrarLinguagem(@RequestBody Linguagem linguagem) {
		Linguagem linguagemSalva = linguagemService.salvar(linguagem);
		
		return linguagemSalva;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirLinguagem(@PathVariable("id") Long linguagemId) {
		linguagemService.excluir(linguagemId);
	}
}
