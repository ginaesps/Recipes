package recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    public Optional<Recipe> findById(Long id);
    public boolean existsById(Long id);

    public List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    public List<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(String name);
}