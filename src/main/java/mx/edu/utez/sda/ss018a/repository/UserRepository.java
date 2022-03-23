package mx.edu.utez.sda.ss018a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.sda.ss018a.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
