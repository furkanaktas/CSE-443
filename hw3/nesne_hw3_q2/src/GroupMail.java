import java.util.ArrayList;
import java.util.List;

public class GroupMail implements AugmentedMailAdress {

    private String mail;
    private List<AugmentedMailAdress> mails = new ArrayList<AugmentedMailAdress>();

    public GroupMail(String mail) {
        this.mail = mail;
    }

    /**
     *  grub'a mail'i ekler
     *
     * @param mail eklenecek mail
     */
    public void addMail(AugmentedMailAdress mail){
        mails.add(mail);
    }


    /**
     *  grup mail adresini ve grup taki mailleri print eder
     */
    @Override
    public void print() {
        System.out.println(mail);
        mails.forEach(mail -> System.out.println(mail.getMail()));
        System.out.println();
    }

    /**
     *
     * @return mail adress
     */
    public String getMail() {
        return mail;
    }
}
