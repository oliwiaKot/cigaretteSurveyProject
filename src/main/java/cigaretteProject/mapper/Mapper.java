package cigaretteProject.mapper;

import cigaretteProject.model.PersonalInfo;
import cigaretteProject.model.PersonalInfoSurvey;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public PersonalInfo getPersonalInfo(PersonalInfoSurvey personalInfoSurvey){
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setAge(personalInfoSurvey.getAge());
        personalInfo.setEducation(personalInfoSurvey.getEducation());
        personalInfo.setGender(personalInfoSurvey.getGender());
        personalInfo.setIncome(personalInfoSurvey.getIncome());
        personalInfo.setMaritalStatus(personalInfoSurvey.getMaritalStatus());
        personalInfo.setResidence(personalInfoSurvey.getResidence());
        personalInfo.setSmoker(personalInfoSurvey.getSmoker());

        return personalInfo;
    }
}
