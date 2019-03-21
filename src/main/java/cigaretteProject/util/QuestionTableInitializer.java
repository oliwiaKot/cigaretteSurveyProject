package cigaretteProject.util;

import cigaretteProject.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cigaretteProject.repo.QuestionRepository;

@Component
public class QuestionTableInitializer implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(QuestionTableInitializer.class);
public static final Long frequencyQuestionId = 1l;
    public static final Long reasonQuestionId = 2l;



    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (this.questionRepository.count() == 0) {
            //nie ma zadnych pytan w bazie, trzeba je wgrac
            Question question = new Question();
            question.setText("Jak często palisz");
            question.setId(frequencyQuestionId);
            questionRepository.save(question);

            question = new Question();
            question.setText("Z jakich powodów nie palisz");
            question.setId(reasonQuestionId);

            questionRepository.save(question);

            //...
            logger.info("Pytania zostały zapisane");

        } else {
            logger.info("Pytania juz sa w tabeli, zadne pytania nie zostana wgrane");
        }

    }
}
