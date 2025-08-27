package com.SysGroup.Alcaldia.Seguridad;

import javax.sql.DataSource;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;


@Configuration
@EnableWebSecurity

public class DatabaseWebSecurity {

    @Bean
public UserDetailsManager customUser(DataSource dataSource) {
    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    users.setUsersByUsernameQuery("SELECT login, clave,status as enabled FROM usuario WHERE login = ?");
    users.setAuthoritiesByUsernameQuery("SELECT u.login, r.nombre as authority FROM usuario_rol ur " +
            "INNER JOIN usuario u ON u.id = ur.usuario_id " +
            "INNER JOIN rol r ON r.id = ur.rol_id " +
            "WHERE u.login = ?");
    
    return users;
}


@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            // Recursos públicos
            .requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/error/**", "/", "/privacy/**", "/terms/**").permitAll()
            
            // Rutas por roles
            .requestMatchers("/usuarios/**").hasAuthority("admin")
            .requestMatchers("/proyectos/**").hasAnyAuthority("Doc","admin")
            .requestMatchers("/cargo/**").hasAuthority("RRHH")
            .requestMatchers("/empleados/**").hasAuthority("RRHH")
            .requestMatchers("/tipodocumentoarchivo/**").hasAuthority("Doc")
            .requestMatchers("/documentos/**").hasAuthority("Doc")
            .requestMatchers("/municipios/**").hasAuthority("admin")
            
            // Cualquier otra ruta requiere autenticación
            .anyRequest().authenticated()
        )
        // Login personalizado
        .formLogin(form -> form
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/", true)
        )
        // Página personalizada cuando no tiene acceso
        .exceptionHandling(ex -> ex
            .accessDeniedPage("/acceso-denegado")  // 👈 Redirige aquí si no tiene permisos
        );

    return http.build();
}


@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
