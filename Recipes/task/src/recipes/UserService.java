package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.Security.User;
import recipes.Security.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public ResponseEntity<?> registerUser(User body) {
        if(userRepository.existsByEmail(body.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        body.setPassword(passwordEncoder.encode(body.getPassword()));
        body.setRole("ROLE_USER");
        userRepository.save(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
