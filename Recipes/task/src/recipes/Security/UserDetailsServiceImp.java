package recipes.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.Recipe;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserRepository userRepo;

    public UserDetailsImp loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }
        return new UserDetailsImp(user);
    }
}
