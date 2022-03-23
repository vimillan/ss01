package mx.edu.utez.sda.ss018a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.sda.ss018a.model.Authority;
import mx.edu.utez.sda.ss018a.repository.AuthorityRepository;

@Service
public class AuthorityServiceImp implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public boolean guardar(Authority authority) {
        try{
            authorityRepository.save(authority);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Authority obtener(String username) {
        return authorityRepository.findById(username).get();
    }

    @Override
    public List<Authority> listar() {
        return authorityRepository.findAll();
    }

    @Override
    public boolean eliminar(String username) {
        boolean existe = authorityRepository.existsById(username);
        if(existe){
            authorityRepository.deleteById(username);
            return !authorityRepository.existsById(username);
        }
        return false;
    }
    
}
