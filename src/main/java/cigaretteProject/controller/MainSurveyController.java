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

/**
 * Klasa służąca do odpowiedzi na metodę "POST" formularza - przechwycenie danych do obiektu klasy Survey, następnie sprawdzenie poprawności danych, podział danych na dwie tabele PersonalInfo i Answer
 * a następnie zapis tych danych do bazy, oraz odpowiednie obliczenie na podstawie Naiwnego Klasyfikatora Bayesa do jakiej klasy (palacz, były palacz, niepalący) ankietowany
 * zostalby sklasyfkowany na podstawie odpowiedzi na pytania podstawowe i przeslanie tej informacji do klienta.
 */
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
            String[] wynik = bayesClassifier(survey);
            String URL = "/thankyou.html" + "?" + "classifiedas=" + wynik[0] + "&" + "is=" + wynik[1];

            response.sendRedirect(URL);
        }else{
            response.sendRedirect("/notfunny.html");
        }

    }

    /**
     * Metoda służąca do klasyfikacji ankietowanego do jednej z trzech klas. Tworzone są 3 tabele przechowujące wartosci prawdopodobieństw warunkowych obliczonych dla 9 odpowiedzi na zadane pytania.
     * Najpierw tabele wypełniane są danymi zgodnie z udzielonymi przez ankietowanego odpowiedziami. Następnie poprzez wywołanie metody countProb obliczane są prawdopodobieństwa przynależności ankietowanego do każdej z 3 klas.
     * Prawdopodobieństwa te są porównywane i podejmowana jest decyzja o klasyfikacji ankietowanego do jednej z trzech klas.
     * @param survey
     * @return zwraca tablicę stringów, gdzie pierwsza wartość w tabeli to klasa, do której przypisany zostałby ankietowany na podstawie Naiwnego klasyfikatora Bayesa, a druga wartość to klasa, do której ankietowany deklaruje przynależność.
     */
        public String[] bayesClassifier(Survey survey){

        double AssumeSmoker[] = new double[9];
            double AssumeExSmoker[] = new double[9];
            double AssumeNonSmoker[] = new double[9];

//prawdopodobieństwa wieku


        if(survey.getAge().equals("18-25")){
            AssumeSmoker[0] = 0.667;
            AssumeExSmoker[0] = 0.6;
            AssumeNonSmoker[0] = 0.78667;

        }else  if(survey.getAge().equals("26-35")){
            AssumeSmoker[0] = 0.1852;
            AssumeExSmoker[0] = 0.2889;
            AssumeNonSmoker[0] = 0.08;

        }else  if(survey.getAge().equals("36-45")){
            AssumeSmoker[0] = 0.05556;
            AssumeExSmoker[0] = 0.06667;
            AssumeNonSmoker[0] = 0.04;

        }else  if(survey.getAge().equals("46-55")){
            AssumeSmoker[0] = 0.07407;
            AssumeExSmoker[0] = 0.02222;
            AssumeNonSmoker[0] = 0.05333;

        }else  if(survey.getAge().equals("56-and-more")){
            AssumeSmoker[0] = 0.01852;
            AssumeExSmoker[0] = 0.0222;
            AssumeNonSmoker[0] = 0.04;

        }


        //prawdopodobieństwa wykształcenia



            if(survey.getEducation().equals("primary")){
                AssumeSmoker[1] = 0.21153;
                AssumeExSmoker[1] = 0.11627;
                AssumeNonSmoker[1] = 0.12329;

            } else if(survey.getEducation().equals("secondary") ){
                AssumeSmoker[1] = 0.6153;
                AssumeExSmoker[1] = 0.58139;
                AssumeNonSmoker[1] = 0.643836;
            } else if(survey.getEducation().equals("higher")){
                AssumeSmoker[1] = 0.173;
                AssumeExSmoker[1] = 0.302;
                AssumeNonSmoker[1] = 0.23287;
            }



                //prawdopodobieństwo płeć

            if(survey.getGender().equals("male")){
                AssumeSmoker[2] = 0.2353;
                AssumeExSmoker[2] = 0.30952;
                AssumeNonSmoker[2] = 0.208333;


            }else if(survey.getGender().equals("female")){
                AssumeSmoker[2] = 0.7647;
                AssumeExSmoker[2] = 0.69;
                AssumeNonSmoker[2] = 0.791667;
            }


        //dochody

            if(survey.getIncome().equals("0")){
                AssumeSmoker[3] = 0.34545;
                AssumeExSmoker[3] = 0.2391304;
                AssumeNonSmoker[3] = 0.35526;
            }else if(survey.getIncome().equals("1500")){
                AssumeSmoker[3] = 0.181818;
                AssumeExSmoker[3] = 0.2608;
                AssumeNonSmoker[3] = 0.302;
            }else if(survey.getIncome().equals("2500")){
                AssumeSmoker[3] = 0.218;
                AssumeExSmoker[3] = 0.195652;
                AssumeNonSmoker[3] = 0.157894;
            }else if(survey.getIncome().equals("5000")){
                AssumeSmoker[3] = 0.16363;
                AssumeExSmoker[3] = 0.21739;
                AssumeNonSmoker[3] = 0.157894;
            }else if(survey.getIncome().equals("10000")){
                AssumeSmoker[3] = 0.0727;
                AssumeExSmoker[3] = 0.0217;
                AssumeNonSmoker[3] = 0.013158;
            }else if(survey.getIncome().equals("10000+")){
                AssumeSmoker[3] = 0.01818;
                AssumeExSmoker[3] = 0.0652;
                AssumeNonSmoker[3] = 0.013157;
            }

                //stan cywilny

            if(survey.getMaritalStatus().equals("single")){

                AssumeSmoker[4] = 0.423;
                AssumeExSmoker[4] = 0.48837;
                AssumeNonSmoker[4] = 0.46575;
            }else if (survey.getMaritalStatus().equals("married") ){

                AssumeSmoker[4] = 0.538;
                AssumeExSmoker[4] = 0.4883;
                AssumeNonSmoker[4] = 0.52;

            }else if(survey.getMaritalStatus().equals("divorced")){

                AssumeSmoker[4] = 0.038;
                AssumeExSmoker[4] = 0.0232;
                AssumeNonSmoker[4] = 0.0136986;
            }


                //miejsce zamieszkania


            if(survey.getResidence().equals("city up to 25thousand")){
                AssumeSmoker[5] = 0.1333;
                AssumeExSmoker[5] = 0.0392;
                AssumeNonSmoker[5] = 0.0987;
            }else if( survey.getResidence().equals("city 25-50 thousand")){
                AssumeSmoker[5] = 0.1;
                AssumeExSmoker[5] = 0.15686;
                AssumeNonSmoker[5] = 0.1358;
            }else if(survey.getResidence().equals("city 50-100 thousand")){
                AssumeSmoker[5] = 0.13333;
                AssumeExSmoker[5] = 0.1176;
                AssumeNonSmoker[5] = 0.0617;
            }else if(survey.getResidence().equals("city 100-250 thousand")){
                AssumeSmoker[5] = 0.18333;
                AssumeExSmoker[5] = 0.07843;
                AssumeNonSmoker[5] = 0.07407;
            }else if(survey.getResidence().equals("city 250-500 thousand")){
                AssumeSmoker[5] =0.11667;
                AssumeExSmoker[5] = 0.17647;
                AssumeNonSmoker[5] = 0.2098765;
            }else if(survey.getResidence().equals("city up to 1 million")){
                AssumeSmoker[5] = 0.1;
                AssumeExSmoker[5] = 0.15686;
                AssumeNonSmoker[5] =0.1605;
            }else if(survey.getResidence().equals("city above 1 million")){
                AssumeSmoker[5] = 0.0333;
                AssumeExSmoker[5] = 0.058823;
                AssumeNonSmoker[5] = 0.061728;
            }else if(survey.getResidence().equals("village up to 1 thousand")){
                AssumeSmoker[5] = 0.066667;
                AssumeExSmoker[5] =0.098;
                AssumeNonSmoker[5] = 0.061728;
            }else if(survey.getResidence().equals("village 1-5 thousand")){
                AssumeSmoker[5] = 0.05;
                AssumeExSmoker[5] = 0.058823;
                AssumeNonSmoker[5] = 0.061728;
            }else if(survey.getResidence().equals("village 5-10 thousand")){
                AssumeSmoker[5] = 0.03333;
                AssumeExSmoker[5] = 0.0196;
                AssumeNonSmoker[5] = 0.061728;
            }else if( survey.getResidence().equals("village above 10 thousand")){

                AssumeSmoker[5] = 0.05;
                AssumeExSmoker[5] = 0.0392157;
                AssumeNonSmoker[5] = 0.0123457;
            }



                //rodzina

            if(survey.getFamily().equals("no")){
                AssumeSmoker[6] = 0.216;
                AssumeExSmoker[6] = 0.238;
                AssumeNonSmoker[6] = 0.3889;

            }else if(survey.getFamily().equals("yes")){
                AssumeSmoker[6] = 0.784;
                AssumeExSmoker[6] = 0.7619;
                AssumeNonSmoker[6] = 0.6111;

            }


            //znajomi

            if(survey.getFriends().equals("no")){
                AssumeSmoker[7] = 0.024;
                AssumeExSmoker[7] = 0.119;
                AssumeNonSmoker[7] = 0.26389;
            }else if(survey.getFriends().equals("yes")){
                AssumeSmoker[7] = 0.98;
                AssumeExSmoker[7] = 0.88;
                AssumeNonSmoker[7] = 0.736;
            }


                //inne używki
            if(survey.getOtherAddictions().equals("no")){
                AssumeSmoker[8] = 0.2692;
                AssumeExSmoker[8] = 0.25581;
                AssumeNonSmoker[8] = 0.4794;
            }else if(survey.getOtherAddictions().equals("yes")){
                AssumeSmoker[8] = 0.711;
                AssumeExSmoker[8] = 0.7209;
                AssumeNonSmoker[8] = 0.493;
            }

            double Probabilities[] = new double[3];
            Probabilities[0] = countProb(AssumeSmoker);
            Probabilities[1] = countProb(AssumeExSmoker);
            Probabilities[2] = countProb(AssumeNonSmoker);

            String[] wyniki = new String[2];

            if(Probabilities[0] >= Probabilities[1] && Probabilities[0]>=Probabilities[2]){
                wyniki[0] = "yes";
            }else if(Probabilities[1] >= Probabilities[0] && Probabilities[1]>=Probabilities[2]){
                wyniki[0] = "ex";
            }else if(Probabilities[2] >= Probabilities[1] && Probabilities[2]>=Probabilities[0]){
                wyniki[0] = "no";
            }

            wyniki[1] = survey.getSmoker();

            return wyniki;

        }

    /**
     * Metoda obliczająca prawdopodobieństwo przynależności do danej klasy na podstawie prawdopodobieńst warunkowych dla udzielonych odpowiedzi
     * @param table
     * @return
     */
        public double countProb(double[] table){
            double P= 1000.0;

            for (int i=0; i<table.length; i++){
                P=P*table[i];
            }

            return P;

        }

    /**
     * Metoda służąca do sprawdzenia poprawności danych odpowiadających danym w tabeli pytań podstawowych PersonalInfo oraz pytaniom szczegółowym jednokrotnego wyboru
     * @param survey
     * @return
     */

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

    /**
     * Metoda sprawdzająca poprawność wszystkich udzielonych odpowiedzi na pytanie wielokrotnego wyboru o sytuacje sprzyjające paleniu
     * @param survey
     * @return
     */
    public boolean checkReasons(Survey survey){
                boolean iseverythingOK = false;
            for(int i=0; i<survey.getReasons().length; i++){
                if( survey.getReasons()[i].equals("health") || survey.getReasons()[i].equals("finance") || survey.getReasons()[i].equals("friends") || survey.getReasons()[i].equals("smell") ){
                  iseverythingOK = true;
                }
            }
            return iseverythingOK;
         }

    /**
     *  Metoda sprawdzająca poprawność wszystkich udzielonych odpowiedzi na pytanie wielokrotnego wyboru o powody niepalenia
     * @param survey
     * @return
     */
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
