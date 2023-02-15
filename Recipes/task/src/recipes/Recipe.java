package recipes;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "recipes")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    @ElementCollection
    private List<String> ingredients;
    @NotEmpty
    @ElementCollection
    private List<String> directions;
    @NotBlank
    private String category;
    @UpdateTimestamp
    private LocalDateTime date;
    @JsonIgnore
    private String userPerson;
}