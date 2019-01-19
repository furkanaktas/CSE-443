package sample.plane;

import sample.store.Plane;

public class EurasiaTPX300 extends Plane {

    public EurasiaTPX300(){
        name = "EurasiaTPX300";
        purpose = "Transatlantic flights";
        skeleton = "Titanium Alloy";
        engine = "Quadro Jet Engine - (Turbo Fan)";
        seat = "250 seats - (Linen)";
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
        System.out.println(name + " Quadro Jet Engine - (Turbo Fan)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 250 seats - (Linen)");
    }
}
