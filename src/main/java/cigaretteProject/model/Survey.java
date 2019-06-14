package cigaretteProject.model;

/**
 * Klasa odpowiadająca za pobranie wszystkich danych przesłanych metodą "POST" w formularzu
 */
public class Survey {
//zbieramy wszystkie informacje z formularza, nawet te zbędne - wszystkie pytania jakie są
    private String age; //wiek, jednokrotny wybor
    private String gender; //płeć jednokrotny wybor
    private String income; //dochód, jednokrotny wybór
    private String smoker; //czy ktoś pali, jednokrotny wybór
    private String residence; //gdzie mieszka, jednokrotny wybór
    private String education; //wykształcenie jednokrotny wybór
    private String maritalStatus; //stan cywilny, jednokrotny wybór

    private String otherAddictions; //inne uzależnienia, jednokrotny wybór
    private String family; //czy ktoś w rodzinie pali, jednokrotny wybór
    private String friends; //czy ktoś wśród znajomych pali, jednokrotny wybór
    private String frequency; //częstotliwość, jednokrotny wybór
    private String[] situations; //sytuacje, wielokrotny wybór
    private String[] reasons; //powody, wielokrotny wybór

    //metody getter i setter dla wszystkich pól formularza
    public String[] getSituations() {
        return situations;
    }

    public void setSituations(String[] situations) {
        this.situations = situations;
    }

    public String[] getReasons() {
        return reasons;
    }

    public void setReasons(String[] reasons) {
        this.reasons = reasons;
    }

    public String getOtherAddictions() {
        return otherAddictions;
    }

    public void setOtherAddictions(String otherAddictions) {
        this.otherAddictions = otherAddictions;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
