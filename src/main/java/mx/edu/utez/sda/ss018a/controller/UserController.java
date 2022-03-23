package mx.edu.utez.sda.ss018a.controller;

import mx.edu.utez.sda.ss018a.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @PostMapping("/guardar")
    public String guardarUsuario(User user){
        return "";
    }

    @GetMapping("/")
    public String listarUsuarios(){
        return  "/users/listaUsuarios";
    }
}
