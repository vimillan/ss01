package mx.edu.utez.sda.ss018a.service;

import java.util.List;

import mx.edu.utez.sda.ss018a.model.User;

public interface UserService {
    List<User> listar();
    boolean guardar(User user);
    boolean eliminar(String username);
    User obtener(String username);
}
