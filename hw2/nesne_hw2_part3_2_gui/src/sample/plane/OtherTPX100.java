package sample.plane;

import sample.store.Plane;

public class OtherTPX100 extends Plane {

    public OtherTPX100(){
        name = "OtherTPX100";
        purpose = "Domestic flights";
        skeleton = "Aliminum Skeleton";
        engine ="Single Jet Engine - (Geared turbofan)";
        seat = "50 seats - (Leather)";
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
        System.out.println(name + " Single Jet Engine - (Geared turbofan)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 50 seats - (Leather)");
    }
}
