package sample.store;

public abstract class PlaneStore {

    /**
     *
     * @param type for plane that will be created
     * @return plane itself
     */
    public Plane orderPlane(String type){
        Plane plane;

        plane = createPlane(type);

        plane.constructSkeleton();
        plane.placeEngines();
        plane.placeSeats();

        return plane;
    }

    /**
     *  while in child class, to enforce this method to implement
     *
     * @param type for plane that will be created
     * @return plane
     */
    abstract Plane createPlane(String type);
}
