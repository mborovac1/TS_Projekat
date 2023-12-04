package ba.tim2.RezervacijaKarata.Controller.Auth;

import ba.tim2.RezervacijaKarata.Entity.Auth.User;
import ba.tim2.RezervacijaKarata.Repository.Auth.UserRepository;
import ba.tim2.RezervacijaKarata.Service.Auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/id")
    private ResponseEntity<Integer> getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getID();
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }
}
