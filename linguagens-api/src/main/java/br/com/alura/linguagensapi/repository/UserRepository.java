package br.com.alura.linguagensapi.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.linguagensapi.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

	Optional<UserModel> findByUsername(String username);
}
