package cigaretteProject.mapper;

import cigaretteProject.model.PersonalInfo;
import cigaretteProject.model.Survey;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public PersonalInfo getPersonalInfo(Survey survey){
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setAge(survey.getAge());
        personalInfo.setEducation(survey.getEducation());
        personalInfo.setGender(survey.getGender());
        personalInfo.setIncome(survey.getIncome());
        personalInfo.setMaritalStatus(survey.getMaritalStatus());
        personalInfo.setResidence(survey.getResidence());
        personalInfo.setSmoker(survey.getSmoker());

        return personalInfo;
    }
}
