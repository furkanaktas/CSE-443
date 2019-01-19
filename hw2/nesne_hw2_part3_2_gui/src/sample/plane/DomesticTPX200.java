package sample.plane;

import sample.store.Plane;

public class DomesticTPX200 extends Plane {

    public DomesticTPX200(){
        name = "DomesticTPX200";
        purpose = "Domestic and short international flights";
        skeleton = "Nickel Alloy";
        engine ="Twin Jet Engine - (Turbo Jet)";
        seat = "100 seats - (velvet)";
    }

    /**
     * some implementation
     */
    protected void constructSkeleton(){
        System.out.println(name + " Nickel Alloy");
    }
    /**
     * some implementation
     */
    protected void placeEngines(){
        System.out.println(name + " Twin Jet Engine - (Turbo Jet)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 100 seats - (velvet)");
    }
}
