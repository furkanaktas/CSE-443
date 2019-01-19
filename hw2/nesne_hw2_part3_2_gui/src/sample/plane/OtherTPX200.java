package sample.plane;

import sample.store.Plane;

public class OtherTPX200 extends Plane {

    public OtherTPX200(){
        name = "OtherTPX200";
        purpose = "Domestic and short international flights";
        skeleton = "Nickel Alloy";
        engine ="Twin Jet Engine - (Geared turbofan)";
        seat = "100 seats - (Leather)";
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
        System.out.println(name + " Twin Jet Engine - (Geared turbofan)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){System.out.println(name +" 100 seats - (Leather)");}
}
