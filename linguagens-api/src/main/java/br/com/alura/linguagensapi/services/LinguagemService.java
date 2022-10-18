package br.com.alura.linguagensapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.linguagensapi.exceptions.EntidadeNaoEncontradaException;
import br.com.alura.linguagensapi.exceptions.NegocioException;
import br.com.alura.linguagensapi.model.Linguagem;
import br.com.alura.linguagensapi.repository.LinguagemRepository;

@Service
public class LinguagemService {
	
	private LinguagemRepository linguagemRepository;
	
	public LinguagemService(LinguagemRepository linguagemRepository) {
		this.linguagemRepository = linguagemRepository;
	}
	
	public List<Linguagem> listar() {
		return linguagemRepository.findAll();
	}
	
	public Linguagem buscar(Long linguagemId) {
		Optional<Linguagem> linguagem = linguagemRepository.findById(linguagemId);
		
		if (!linguagem.isPresent()) {
			throw new EntidadeNaoEncontradaException("Linguagem não encontrada.");
		}
		
		return linguagem.get();
	}
	
	@Transactional
	public Linguagem salvar(Linguagem linguagem) {
		Optional<Linguagem> linguagemExistente = linguagemRepository.findByTitleContainingIgnoreCase(linguagem.getTitle());
		
		if (linguagemExistente.isPresent()) {
			if (!linguagemExistente.get().equals(linguagem)) {
				throw new NegocioException("Já existe uma linguagem cadastrada com este title.");
			}
		}
		
		return linguagemRepository.save(linguagem);
	}
	
	@Transactional
	public void excluir(Long linguagemId) {
		Linguagem linguagemExistente = buscar(linguagemId);
		
		linguagemRepository.delete(linguagemExistente);
	}
	

}
