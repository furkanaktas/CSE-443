package sample.plane;

import sample.store.Plane;

public class EurasiaTPX100 extends Plane {

    public EurasiaTPX100(){
        name = "EurasiaTPX100";
        purpose = "Domestic flights";
        skeleton = "Aliminum Skeleton";
        engine ="Single Jet Engine - (Turbo Fan)";
        seat = "50 seats - (Linen)";
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
        System.out.println(name + " Single Jet Engine - (Turbo Fan)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 50 seats - (Linen)");
    }
}
