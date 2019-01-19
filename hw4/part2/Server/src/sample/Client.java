package sample;

import java.io.Serializable;

public class Client implements Serializable{

    private static final long serialVersionUID = 7891011L;

    private String id, password, name, surname;
    private Integer credit, cardNo;


    public Client(String id, String password, String name, String surname,Integer cardNo, Integer credit) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cardNo = cardNo;
        this.credit = credit;
    }

    /**
     *
     * @return id of user
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return name of user
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return surname of user
     */
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return password of user
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return card no of user
     */
    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    /**
     *
     * @return credit amount of user
     */
    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
