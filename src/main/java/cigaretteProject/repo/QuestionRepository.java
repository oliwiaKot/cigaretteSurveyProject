package cigaretteProject.repo;

import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

import cigaretteProject.model.Question;

@Repository
@RepositoryDefinition(domainClass = Question.class, idClass = Long.class)
public interface QuestionRepository {

    Question findById(Long id); //znajduje pytanie po jego Id, potrzebne do zapisania w bazie odp z formularza
    List<Question> findAllByOrderByIdAsc(); //można sobie je wypisać w porzadku rosnącym
    Long count(); //Liczy ile jest pytań w liście pytań, potrzebne do sprawdzenia czy już są zapisane czy nie w QuestionTableInitializer
    void save(Question question); //zapis rzedu w tabeli pytań

}
