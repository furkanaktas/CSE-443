import java.util.ArrayList;
import java.util.List;

public class Composite implements MailAdress
{
    private List<MailAdress> MailList = new ArrayList<MailAdress>();

    /**
     *  ilişkili tüm mailleri ekrana basar
     */
    @Override
    public void print() {
        for(MailAdress adrr: MailList)
        {
            adrr.print();
        }
    }

    /**
     *
     * @param adrr eklenecek mail
     */
    public void addMail(MailAdress adrr)
    {
        MailList.add(adrr);
    }

    /**
     *
     * @param adrr silinecek mail
     */
    public void removeMail(MailAdress adrr)
    {
        MailList.remove(adrr);
    }
}

