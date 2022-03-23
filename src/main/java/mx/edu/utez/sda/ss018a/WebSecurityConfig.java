package mx.edu.utez.sda.ss018a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Para la conexión de base de datos.
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Rutas autorizadas para que cualquier usuario pueda visualizarlas
                .antMatchers("/", "home").permitAll()

                // Cualquier otra ruta necesita autenticación
                .anyRequest().authenticated()
                .and()
                // El formulario de registro de login
                .formLogin()
                    // Pertenecera a esta ruta y la vista que se encuentre en esa ruta
                    // Permitido para todos
                    .loginPage("/login").permitAll()
                .and()
                // El metodo de logout pueden acceder todos
                .logout().permitAll();
        // super.configure(http);
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    /*@Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // Crea un usuario en memoria para iniciar seción
        UserDetails userAdmin = User.withDefaultPasswordEncoder()
                .username("admin@localhost.com")
                .password("12345")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userAdmin);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
