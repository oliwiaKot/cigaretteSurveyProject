package cigaretteProject.controller;

import cigaretteProject.mapper.Mapper;
import cigaretteProject.model.PersonalInfo;
import cigaretteProject.model.PersonalInfoSurvey;
import cigaretteProject.repo.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainSurveyController {

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private Mapper mapper;

    @PostMapping(path ="/personalinfo", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public void postSurveyPersonalInfo(PersonalInfoSurvey personalInfoSurvey) {

        PersonalInfo personalInfo = this.mapper.getPersonalInfo(personalInfoSurvey);
        this.personalInfoRepository.save(personalInfo);

        }

}
