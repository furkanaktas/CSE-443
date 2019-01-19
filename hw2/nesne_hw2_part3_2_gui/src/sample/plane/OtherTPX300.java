package sample.plane;

import sample.store.Plane;

public class OtherTPX300 extends Plane {

    public OtherTPX300(){
        name = "OtherTPX300";
        purpose = "Transatlantic flights";
        skeleton = "Titanium Alloy";
        engine = "Quadro Jet Engine - (Geared turbofan)";
        seat = "250 seats - (Leather)";
    }
    /**
     * some implementation
     */
    protected void constructSkeleton(){
        System.out.println(name + " Titanium Alloy");
    }
    /**
     * some implementation
     */
    protected void placeEngines(){
        System.out.println(name + " Quadro Jet Engine - (Geared turbofan)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 250 seats - (Leather)");
    }
}
