package cigaretteProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Klasa będąca odwzorowaniem struktury tabeli w bazie danych przechowującej treści pytań i ich numery.
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
