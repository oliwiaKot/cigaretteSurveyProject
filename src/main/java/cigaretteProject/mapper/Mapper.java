package cigaretteProject.mapper;

import cigaretteProject.model.Answer;
import cigaretteProject.model.PersonalInfo;
import cigaretteProject.model.Question;
import cigaretteProject.model.Survey;
import cigaretteProject.repo.QuestionRepository;
import cigaretteProject.util.QuestionTableInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    @Autowired
    private QuestionRepository questionRepository;

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

    public List<Answer> getAnswerList(Survey survey, PersonalInfo personalInfo){
        List<Answer> answerList = new ArrayList<>();
        if(survey.getSmoker().equalsIgnoreCase("yes")) {
            Answer answer = new Answer();
            answer.setAnswer(survey.getFrequency());
            answer.setPersonalInfo(personalInfo);
            Question question=questionRepository.findById(QuestionTableInitializer.frequencyQuestionId);
            answer.setQuestion(question);
            answerList.add(answer);

            for(int i=0; i<survey.getSituations().length; i++){
                //to samo co wyżej tylko id sobie ustaw i dodaj każde do listy
            }


        }else if(survey.getSmoker().equalsIgnoreCase("no")) {

        }else if(survey.getSmoker().equalsIgnoreCase("ex")) {

        }
        return answerList;
    }
}
