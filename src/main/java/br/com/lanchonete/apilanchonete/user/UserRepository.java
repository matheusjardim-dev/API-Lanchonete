package br.com.lanchonete.apilanchonete.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
    public Optional<User> findByRA(Integer rA);
}