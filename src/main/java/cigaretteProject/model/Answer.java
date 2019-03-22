package cigaretteProject.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Answer {
//Id odpowiedzi, generowane automatycznie przez Spring
    @Id
    @GeneratedValue
    private Long id;
    //Id osoby jest FK w tabeli z odp., przechowywane ma być w tabeli Answers.
    //Wystarczy opisać związek jednostronnie, że Many Answers To One Person.
    //Po przypisaniu relacji do obiektu, Spring za klucz przyjmie Id obiektu, czyli Id osoby
    @ManyToOne
    private PersonalInfo personalInfo;

    //analogicznie jak dla osoby
    @ManyToOne
    private Question question;

    @NotBlank
    private String answer; //nie chcemy zapisywać pustych odpowiedzi

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
