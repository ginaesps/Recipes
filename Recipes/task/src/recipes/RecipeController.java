package recipes;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.Security.User;
import recipes.Security.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @PostMapping("/new")
    public ResponseEntity<Map<String, Long>> addRecipe(@RequestBody @Valid Recipe recipe, @AuthenticationPrincipal UserDetails auth) {
        return recipeService.addRecipe(recipe, auth);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id, @AuthenticationPrincipal UserDetails auth) {
        return recipeService.deleteRecipe(id, auth);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editRecipe(@RequestBody @Valid Recipe recipe, @PathVariable Long id, @AuthenticationPrincipal UserDetails auth) {
        return recipeService.editRecipe(recipe, id, auth);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipe(@RequestParam(required = false) @Valid String category, @RequestParam(required = false) @Valid String name) {
        if ((category == null && name == null) || (category != null && name != null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return recipeService.searchRecipe(category, name);
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@RequestBody @Validated User body) {
        //return new ResponseEntity<>(body.getEmail(), HttpStatus.OK);
        return userService.registerUser(body);
    }
}
