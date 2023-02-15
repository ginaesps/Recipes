package recipes.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class SecurityController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/register")
    public void registerUser(@RequestBody @Valid User user) {
        if (userRepo.existsByEmail(user.getEmail())
                || user.getEmail() == null
                || user.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userRepo.save(user);
        }
    }
}
