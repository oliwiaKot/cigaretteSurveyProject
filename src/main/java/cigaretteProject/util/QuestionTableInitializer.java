package cigaretteProject.util;

import cigaretteProject.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cigaretteProject.repo.QuestionRepository;
//klasa, która pozwala jednokrotnie stworzyć w bazie danych tabelę z pytaniami, zawierającą pytanie i id pytania
//wywołana jest w momencie uruchomienia aplikacji, poprzez metodę afterPropertiesSet interfejsu InitializingBean
@Component
public class QuestionTableInitializer implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(QuestionTableInitializer.class);
    //Numery pytań jako id pytania do ustawienia w tabeli
    public static final Long frequencyQuestionId = 1L;
    public static final Long reasonsQuestionId = 2L;
    public static final Long familyQuestionId = 3L;
    public static final Long friendsQuestionId = 4L;
    public static final Long otherAddictionsQuestionId = 5L;
    public static final Long situationsQuestionId = 6L;


    @Autowired
    private QuestionRepository questionRepository;

    //metoda ktora zapisuje pojedyncze pytanie i jego id w tabeli pytan
        public void giveQuestionContents( Long id, String text){
        Question q = new Question();
        q.setText(text);
        q.setId(id);
        questionRepository.save(q);
    }

    //metoda wywolywana przy uruchomieni aplikacji
    //sprawdza czy pytania sa w bazie poprzez count. Jeśli jest zero, to pytań nie ma i są dodawane przez wywoływanie metody giveQuestionContents
    //jeśli są to nic nie robi, w konsoli pojawi się informacja, że pytania już są w bazie zapisane
    @Override
    public void afterPropertiesSet() throws Exception {

        if (this.questionRepository.count() == 0) {
            //nie ma zadnych pytan w bazie, trzeba je wgrac
            giveQuestionContents(frequencyQuestionId, "Jak czesto palisz>");
            giveQuestionContents(reasonsQuestionId, "Z jakich powodow nie palisz?");
            giveQuestionContents(familyQuestionId, "Czy ktos w Twojej rozinie pali?");
            giveQuestionContents(friendsQuestionId, "Czy ktos wsrod Twoich bliskich znajomych pali?");
            giveQuestionContents(otherAddictionsQuestionId, "Czy stosujesz inne uzywki?");
            giveQuestionContents(situationsQuestionId, "W jakich sytuacjach najczesciej palisz/paliles?");

            logger.info("Pytania zostały zapisane"); //komunikat dla nas w logach

        } else {
            logger.info("Pytania juz sa w tabeli, zadne pytania nie zostana wgrane"); //komunikat dla nas w logach
        }

    }
}
