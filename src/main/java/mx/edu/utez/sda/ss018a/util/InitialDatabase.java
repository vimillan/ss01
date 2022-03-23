package mx.edu.utez.sda.ss018a.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDatabase implements CommandLineRunner {
    //inyección de la dependencia que ya se configuró
    @Autowired
    PasswordEncoder passwordEncoder;

    //codificación
    @Override
    public void run(String... args) throws Exception {
        String myPassword = "Uno23456";
        System.out.println( myPassword + " encode: "+ passwordEncoder.encode(myPassword));
    }
}
