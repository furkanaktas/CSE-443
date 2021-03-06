package sample.plane;

import sample.store.Plane;

public class DomesticTPX300 extends Plane {

    public DomesticTPX300(){
        name = "DomesticTPX300";
        purpose = "Transatlantic flights";
        skeleton = "Titanium Alloy";
        engine = "Quadro Jet Engine - (Turbo Jet)";
        seat = "250 seats - (velvet)";
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
        System.out.println(name + " Quadro Jet Engine - (Turbo Jet)");
    }
    /**
     * some implementation
     */
    protected void placeSeats(){
        System.out.println(name +" 250 seats - (velvet)");
    }
}
