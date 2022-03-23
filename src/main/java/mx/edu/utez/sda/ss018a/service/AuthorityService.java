package mx.edu.utez.sda.ss018a.service;

import java.util.List;

import mx.edu.utez.sda.ss018a.model.Authority;

public interface AuthorityService {
    boolean guardar(Authority authority);
    Authority obtener(String username);
    List<Authority> listar();
    boolean eliminar(String username);
}
