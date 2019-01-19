package sample.plane;

import sample.store.Plane;

public class EurasiaTPX200 extends Plane {

    public EurasiaTPX200(){
        name = "EurasiaTPX200";
        purpose = "Domestic and short international flights";
        skeleton = "Nickel Alloy";
        engine ="Twin Jet Engine - (Turbo Fan)";
        seat = "100 seats - (Linen)";
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
        System.out.println(name + " Twin Jet Engine - (Turbo Fan)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 100 seats - (Linen)");
    }
}
