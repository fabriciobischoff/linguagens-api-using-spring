package br.com.alura.linguagensapi.configs.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.linguagensapi.exceptions.EntidadeNaoEncontradaException;
import br.com.alura.linguagensapi.model.UserModel;
import br.com.alura.linguagensapi.repository.UserRepository;

@Transactional // para ter acesso as roles
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	// método implementado da interface UserDetailsService
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> user = userRepository.findByUsername(username);
		
		if (!user.isPresent()) {
			throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
		}
		
		return new User(user.get().getUsername(), user.get().getPassword(), true, true, true, true, user.get().getAuthorities());
	}

}
