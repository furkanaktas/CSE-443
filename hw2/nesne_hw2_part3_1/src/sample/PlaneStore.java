package sample;

import sample.plane.Plane;

public class PlaneStore {
    private PlaneFactory factory;

    public PlaneStore(PlaneFactory factory){
        this.factory = factory;
    }

    /**
     *
     * @param type type for Plane that will be created
     * @return  plane itself
     */
    public Plane orderPlane(String type){
        Plane plane;

        plane = factory.createPlane(type);

        plane.constructSkeleton();
        plane.placeEngines();
        plane.placeSeats();

        return plane;
    }
}
