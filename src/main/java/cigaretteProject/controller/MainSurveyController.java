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
    public void postSurveyPersonalInfo(Survey survey) {

        PersonalInfo personalInfo = this.mapper.getPersonalInfo(survey);
        this.personalInfoRepository.save(personalInfo);

        List<Answer> answerList = this.mapper.getAnswerList(survey, personalInfo);
        for(int i =0; i<answerList.size(); i++){
         this.answerRepository.save(answerList.get(i));

        }
        }



}
