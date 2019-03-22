package cigaretteProject.repo;

import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import cigaretteProject.model.Answer;

@Repository
@RepositoryDefinition(domainClass = Answer.class, idClass = Long.class)
public interface AnswerRepository {

    void save(Answer answer); //zapis wiersza w tabeli asocjacyjnej zawierającej odpowiedzi użytkowników

}
