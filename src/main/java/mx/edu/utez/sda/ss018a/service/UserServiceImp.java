package mx.edu.utez.sda.ss018a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.sda.ss018a.model.User;
import mx.edu.utez.sda.ss018a.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    

    @Override
    public List<User> listar() {
        List<User>  lista = userRepository.findAll();
        return lista;
    }

    @Override
    public boolean guardar(User user) {
        try{
            userRepository.save(user);

            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String username) {
        User existe = userRepository.findById(username).get();
        if(existe != null){
            existe.setEnabled(false);
            userRepository.save(existe);
            return true;
        }
        /*boolean existe = userRepository.existsById(username);
        if(existe){
            userRepository.deleteById(username);
            return !userRepository.existsById(username);
        }*/
        return false;
    }

    @Override
    public User obtener(String username) {
        return userRepository.findById(username).get();
    }
    
}
