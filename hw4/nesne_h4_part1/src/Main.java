public class Main {

    public static void main(String[] args) {

        String exercise = "exercise";
        String hardWork = "hardwork";
        String buyingGTX1080 = "gtx1080";
        String cheating = "cheating";
        String outTillLate = "outtilllate";
        String sleep = "sleep";
        String coffeeAndWork = "coffeeandwork";


        Manager manager = new Manager();

        System.out.println("Exercise Scenario");
        manager.Do(exercise);
        manager.Do(hardWork);
        //manager.Do("anything");   // we are in last state


        System.out.println("\nPerseverance and Hard work Scenario");
        manager = new Manager();
        manager.Do(hardWork);
        //manager.Do("anything");   // we are in last state


        System.out.println("\nBuying GTX1080 Scenario");
        manager = new Manager();
        manager.Do(buyingGTX1080);
        //manager.Do("anything");   // we are in last state


        System.out.println("\nCheating Scenario");
        manager = new Manager();
        manager.Do(cheating);
        //manager.Do("anything");   // we are in last state


        System.out.println("\nOut Till Late Scenario");
        manager = new Manager();
        manager.Do(outTillLate);
        manager.Do(coffeeAndWork);
        //manager.Do("anything");   // we are in last state


        System.out.println("\nOut Till Late, Sleep, Out Till Late, Sleep, Out Till Late, Coffee and Work Scenario");
        manager = new Manager();
        manager.Do(outTillLate);
        manager.Do(sleep);
        manager.Do(outTillLate);
        manager.Do(sleep);
        manager.Do(outTillLate);
        manager.Do(coffeeAndWork);
        //manager.Do("anything");   // we are in last state


        System.out.println("\nOut Till Late, Sleep, Exercise, HardWork Scenario");
        manager = new Manager();
        manager.Do(outTillLate);
        manager.Do(sleep);
        manager.Do(exercise);
        manager.Do(hardWork);
        //manager.Do("anything");   // we are in last state


    }
}
