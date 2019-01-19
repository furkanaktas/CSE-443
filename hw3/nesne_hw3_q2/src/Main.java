public class Main {

    public static void main(String[] args) {

        PersonalMail personal = new PersonalMail("ali","velioğlu","alivelioğlu@xyz.com");
        PersonalMail personal2 = new PersonalMail("ali","velivelioğlu","velioğlu@xyz.com");
        PersonalMail personal3 = new PersonalMail("furkan","aktas","aktas@xyz.com");
        PersonalMail personal4 = new PersonalMail("hasan","huseyin","hashus@xyz.com");
        PersonalMail personal5 = new PersonalMail("abc","def","abcdef@xyz.com");
        PersonalMail personal6 = new PersonalMail("qwe","rty","qwerty@xyz.com");

        GroupMail group = new GroupMail("studens@gtu.edu.tr");
        group.addMail(personal);
        group.addMail(personal2);


        GroupMail group2 = new GroupMail("staff@gtu.edu.tr");
        group2.addMail(personal3);
        group2.addMail(personal4);


        System.out.println("Adress Book \n");
        Composite addressBook = new Composite();

        addressBook.addMail(group);
        addressBook.addMail(group2);
        addressBook.addMail(personal5);
        addressBook.addMail(personal6);

        addressBook.print();

    }
}
