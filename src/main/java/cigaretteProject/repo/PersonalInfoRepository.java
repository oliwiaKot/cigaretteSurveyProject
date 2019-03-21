package cigaretteProject.repo;

import cigaretteProject.model.PersonalInfo;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = PersonalInfo.class, idClass = Long.class)
public interface PersonalInfoRepository {

    void save(PersonalInfo personalInfo);
}
