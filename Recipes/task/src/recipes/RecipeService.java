package recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.Security.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Map<String, Long>> addRecipe(Recipe recipe, UserDetails auth) {
        recipe.setUserPerson(auth.getUsername());
        System.out.println(recipe);
        Recipe addedRecipe = recipeRepository.save(recipe);
        Map<String, Long> response = new HashMap<>();
        response.put("id", addedRecipe.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Recipe> getRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recipe.get());
    }

    public ResponseEntity<?> deleteRecipe(Long id, UserDetails auth) {
        if (recipeRepository.existsById(id)) {
            Recipe recipe = recipeRepository.findById(id).get();
            if (recipe.getUserPerson().equals(auth.getUsername())){
                recipeRepository.delete(recipe);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> editRecipe(Recipe newRecipe, Long id, UserDetails auth) {
        if (recipeRepository.existsById(id)) {
            Recipe recipe = recipeRepository.findById(id).get();
            if (recipe.getUserPerson().equals(auth.getUsername())) {
                recipe.setName(newRecipe.getName());
                recipe.setCategory(newRecipe.getCategory());
                recipe.setDirections(newRecipe.getDirections());
                recipe.setIngredients(newRecipe.getIngredients());
                recipe.setDescription(newRecipe.getDescription());
                recipeRepository.save(recipe);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(HttpStatus.valueOf(400));
    }

    public ResponseEntity<List<Recipe>> searchRecipe(String category, String name) {
        if (category != null) {
            List<Recipe> list = recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
            return ResponseEntity.ok(list);
        }
        List<Recipe> list = recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
        return ResponseEntity.ok(list);
    }
}