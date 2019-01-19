public class PersonalMail implements AugmentedMailAdress {

    private String name, surname, mail;

    public PersonalMail(String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    /**
     *
     * @return mail adress
     */
    public String getMail(){
        return mail;
    }

    /**
     *  personele uygun şekilde print eder
     */
    @Override
    public void print() {
        System.out.println(mail + " "+ name+ " " + surname);
    }
}
