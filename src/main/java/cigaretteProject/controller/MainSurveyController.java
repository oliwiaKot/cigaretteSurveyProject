package cigaretteProject.controller;

import cigaretteProject.mapper.Mapper;
import cigaretteProject.model.Answer;
import cigaretteProject.model.PersonalInfo;
import cigaretteProject.model.Survey;
import cigaretteProject.repo.AnswerRepository;
import cigaretteProject.repo.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MainSurveyController {

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private Mapper mapper;

    @PostMapping(path ="/personalinfo", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public void postSurveyPersonalInfo(Survey survey, HttpServletResponse response) throws IOException {

        if(checkIfCorrect(survey) == true){

            //tworzymy nowy obiekt personal info, wywołujemy metodę z mappera, ktora przypisuje mu wartości pobrane z formularza
            //zapisujemy obiekt jako wiersz tablicy
            PersonalInfo personalInfo = this.mapper.getPersonalInfo(survey);
            this.personalInfoRepository.save(personalInfo);

            //Tworzymy listę odpowiedzi na pojedyncze pytania użytkonika wywołując metodę z mappera, która wpisuje do niej odpowiedzi pobrane z formularza
            //w pętli zapisujemy pojedyncze obiekty Answer przechowywane w liście jako wiersze tabeli answers
            List<Answer> answerList = this.mapper.getAnswerList(survey, personalInfo);
            for(int i =0; i<answerList.size(); i++){
                this.answerRepository.save(answerList.get(i));
            }

            response.sendRedirect("/thankyou.html");
        }else{
            response.sendRedirect("/notfunny.html");
        }

    }


        public boolean checkIfCorrect(Survey survey){

        boolean isCorrect = false;
            if(survey.getAge().equals("18-25") || survey.getAge().equals("26-35") || survey.getAge().equals("36-45") || survey.getAge().equals("46-55") || survey.getAge().equals("56-and-more")){
                if(survey.getGender().equals("male") || survey.getGender().equals("female")){
                    if(survey.getEducation().equals("primary") || survey.getEducation().equals("secondary") || survey.getEducation().equals("higher")){
                        if(survey.getMaritalStatus().equals("single") || survey.getMaritalStatus().equals("married") || survey.getMaritalStatus().equals("divorced")){
                            if(survey.getResidence().equals("city up to 25thousand") || survey.getResidence().equals("city 25-50 thousand") || survey.getResidence().equals("city 50-100 thousand") || survey.getResidence().equals("city 100-250 thousand") || survey.getResidence().equals("city 250-500 thousand") || survey.getResidence().equals("city up to 1 million") || survey.getResidence().equals("city above 1 million") || survey.getResidence().equals("village up to 1 thousand") || survey.getResidence().equals("village 1-5 thousand") || survey.getResidence().equals("village 5-10 thousand") || survey.getResidence().equals("village above 10 thousand")){
                                if(survey.getIncome().equals("0") || survey.getIncome().equals("1500") || survey.getIncome().equals("2500") || survey.getIncome().equals("5000") || survey.getIncome().equals("10000") || survey.getIncome().equals("10000+")){
                                    if(survey.getSmoker().equals("no") || survey.getSmoker().equals("yes") || survey.getSmoker().equals("ex")){
                                        if(survey.getFamily().equals("no") || survey.getFamily().equals("yes")){
                                            if(survey.getFriends().equals("no") || survey.getFriends().equals("yes")){
                                                if(survey.getOtherAddictions().equals("no") || survey.getOtherAddictions().equals("yes")){
                                                    if(survey.getFrequency().equals("everyday") || survey.getFrequency().equals("few times a week")  || survey.getFrequency().equals("once a week")  || survey.getFrequency().equals("few times a month")  || survey.getFrequency().equals("less than once a month")  ){
                                                        if(checkReasons(survey) == true){
                                                            if(checkSituations(survey)) {

                                                                isCorrect = true;
                                                            }


                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }

                }
            }

            return isCorrect;
        }
         public boolean checkReasons(Survey survey){
                boolean iseverythingOK = false;
            for(int i=0; i<survey.getReasons().length; i++){
                if( survey.getReasons()[i].equals("health") || survey.getReasons()[i].equals("finance") || survey.getReasons()[i].equals("friends") || survey.getReasons()[i].equals("smell") ){
                  iseverythingOK = true;
                }
            }
            return iseverythingOK;
         }

         public boolean checkSituations(Survey survey){
             boolean iseverythingOK = false;
             for(int i=0; i<survey.getSituations().length; i++){
                 if(survey.getSituations()[i].equals("stress") || survey.getSituations()[i].equals("alcohol") || survey.getSituations()[i].equals("boredom") || survey.getSituations()[i].equals("friends") ){
                     iseverythingOK = true;
                 }
             }
             return iseverythingOK;
         }



}
