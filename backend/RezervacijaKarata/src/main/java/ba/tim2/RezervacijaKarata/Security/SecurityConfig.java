package ba.tim2.RezervacijaKarata.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(GET, "/message").permitAll()
//                .requestMatchers(POST, "/dodajKorisnika").permitAll()
//                .requestMatchers(POST, "/dodajFilm").permitAll()
//                .requestMatchers(GET, "/filmovi").permitAll()
//                .requestMatchers(GET, "/korisnici").permitAll()
//                .requestMatchers(GET, "rezervacija-karata/korisnici").permitAll()
//                .requestMatchers(GET, "rezervacija-karata/**").permitAll()
//                .requestMatchers(GET, "/sale").permitAll()
//                .requestMatchers(POST, "/dodajSalu").permitAll()
//                .requestMatchers(GET, "/sjedista").permitAll()
//                .requestMatchers(POST, "/dodajSjediste").permitAll()
//                .requestMatchers(GET, "/zanrovi/**").permitAll()

//                // Film
//                .requestMatchers(GET, "/filmovi").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(GET, "/filmovi/{id}").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(POST, "/filmovi/dodaj").hasRole("ADMIN")
//                .requestMatchers(PUT, "/filmovi/azuriraj").hasRole("ADMIN")
//                .requestMatchers(DELETE, "/filmovi/obrisi").hasRole("ADMIN")
//                // Karta
//                .requestMatchers(GET, "/karte/**").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(POST, "/karte/**").hasRole("ADMIN")
//                .requestMatchers(PUT, "/karte/**").hasRole("ADMIN")
//                .requestMatchers(DELETE, "/karte/**").hasRole("ADMIN")
//                // Korisnik
//                .requestMatchers(GET, "/korisnici/**").hasRole("ADMIN")
//                .requestMatchers(POST, "/dodajKorisnika").hasRole("ADMIN")
//                .requestMatchers(POST, "/korisnici/**").hasRole("ADMIN")
//                .requestMatchers(PUT, "/korisnici/**").hasRole("ADMIN")
//                .requestMatchers(DELETE, "/korisnici/**").hasRole("ADMIN")
//                // Popust
//                .requestMatchers(GET, "/popusti/**").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(POST, "/popusti/**").hasRole("ADMIN")
//                .requestMatchers(PUT, "/popusti/**").hasRole("ADMIN")
//                .requestMatchers(DELETE, "/popusti/**").hasRole("ADMIN")
//                // Zanr
//                .requestMatchers(GET, "/zanrovi").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(GET, "/zanrovi/{id}").hasAnyRole("USER", "ADMIN")
//                .requestMatchers(POST, "/zanrovi/**").hasRole("ADMIN")
//                .requestMatchers(PUT, "/zanrovi/**").hasRole("ADMIN")
//                .requestMatchers(DELETE, "/zanrovi/**").hasRole("ADMIN")
//
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(request -> HttpMethod.GET.matches(request.getMethod()) ||
                HttpMethod.POST.matches(request.getMethod()) ||
                HttpMethod.PUT.matches(request.getMethod()) ||
                HttpMethod.DELETE.matches(request.getMethod())).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                .anyRequest()
//                .authenticated()
//                .and()
//                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
         return http.build();



    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");  //allowed origins
        //configuration.addAllowedMethod("*");  // Ukoliko zatreba za metode
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}