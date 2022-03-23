package mx.edu.utez.sda.ss018a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.edu.utez.sda.ss018a.model.Authority;
import mx.edu.utez.sda.ss018a.model.User;
import mx.edu.utez.sda.ss018a.service.AuthorityServiceImp;
import mx.edu.utez.sda.ss018a.service.UserServiceImp;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityServiceImp authorityService;

    @GetMapping("/registrar")
    public String crearUsuario(User user) {
        return "/users/registro";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        boolean respuesta = userService.guardar(user);
        if(respuesta){
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authorityService.guardar(authority);
            return "redirect:/users/";
        }else{
            return "redirect:/users/registrar";
        }
        
    }

    @GetMapping("/")
    public String listarUsuarios(Model model) {
        model.addAttribute("listaUsuarios", userService.listar());
        return "/users/listaUsuarios";
    }

    @GetMapping("/eliminar/{username}")
    public String eliminarUsuario(@PathVariable String username) {

        userService.eliminar(username);
        /*boolean respuesta = authorityService.eliminar(username);
        if(respuesta){
            userService.eliminar(username);
        }*/
        return "redirect:/users/";
    }

    @GetMapping("/modificar/{username}")
    public String modificar(@PathVariable String username, Model model) {
        User user = userService.obtener(username);
        model.addAttribute("user", user);
        return "/users/actualizar";
    }

    @PostMapping("/actualizar")
    public String actualizar(User user) {
        User anterior = userService.obtener(user.getUsername());
        anterior.setPassword(user.getPassword());
        boolean respuesta = userService.guardar(anterior);
        if(respuesta){
            return "redirect:/users/";
        }else{
            return "redirect:/users/registrar";
        }
    }
}
