package cigaretteProject.repo;

import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

import cigaretteProject.model.Question;

@Repository
@RepositoryDefinition(domainClass = Question.class, idClass = Long.class)
public interface QuestionRepository {

    Question findById(Long id);
    List<Question> findAllByOrderByIdAsc();
    Long count();
    void save(Question question);

}
