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


    //metoda, która do obiektu klasy PersonalInfo przypisuje odpowiedzi z formularza z danymi osobowymi i zwraca ten obiekt
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

    //metoda, która zwróci listę pojedynczych odpowiedzi na pytania szczegółowe, ktorych udzielił użytkownik w ankiecie
    //w zależności od tego, czy zadeklarował się jako palacz, były palacz czy osoba niepaląca
    public List<Answer> getAnswerList(Survey survey, PersonalInfo personalInfo){
        List<Answer> answerList = new ArrayList<>();

        //ify żeby dodać odpowiedzi na pytania specyficzne dla palacza/byłego palacza/niepalącego
        if(survey.getSmoker().equalsIgnoreCase("yes") || survey.getSmoker().equalsIgnoreCase("ex")) {

            getOneAnswer(survey.getFrequency(), questionRepository.findById(QuestionTableInitializer.frequencyQuestionId), personalInfo, answerList);
            for(int i=0; i<survey.getSituations().length; i++){
                getOneAnswer(survey.getSituations()[i], questionRepository.findById(QuestionTableInitializer.situationsQuestionId), personalInfo, answerList);
            }

        }
        if(survey.getSmoker().equalsIgnoreCase("no") || survey.getSmoker().equalsIgnoreCase("ex")) {

            for(int i=0; i<survey.getReasons().length; i++){
                getOneAnswer(survey.getReasons()[i], questionRepository.findById(QuestionTableInitializer.reasonsQuestionId), personalInfo, answerList);
            }

        }
        //pytania wspólne dla wszystich
        getOneAnswer(survey.getFamily(), questionRepository.findById(QuestionTableInitializer.familyQuestionId), personalInfo, answerList);
        getOneAnswer(survey.getFriends(), questionRepository.findById(QuestionTableInitializer.friendsQuestionId), personalInfo, answerList);
        getOneAnswer(survey.getOtherAddictions(), questionRepository.findById(QuestionTableInitializer.otherAddictionsQuestionId), personalInfo, answerList);

        //zwracamy listę odpowiedzi
        return answerList;
    }

    //metoda, ktora dodaje pojedynczą odpowiedź do listy odpowiedzi użytkownika
    public void getOneAnswer(String answerText, Question quest, PersonalInfo persInfo, List<Answer> ansList){
        Answer answer = new Answer();
        answer.setAnswer(answerText);
        answer.setQuestion(quest);
        answer.setPersonalInfo(persInfo);
        ansList.add(answer);
    }
}
