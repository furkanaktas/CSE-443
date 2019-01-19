package sample.plane;

import sample.store.Plane;

public class DomesticTPX100 extends Plane {

    public DomesticTPX100(){
        name = "DomesticTPX100";
        purpose = "Domestic flights";
        skeleton = "Aliminum Skeleton";
        engine ="Single Jet Engine - (Turbo Jet)";
        seat = "50 seats - (velvet)";
    }

    /**
     * some implementation
     */
    protected void constructSkeleton(){
        System.out.println(name + " Aliminum Skeleton");
    }
    /**
     * some implementation
     */
    protected void placeEngines(){
        System.out.println(name + " Single Jet Engine - (Turbo Jet)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 50 seats - (velvet)");
    }
}
