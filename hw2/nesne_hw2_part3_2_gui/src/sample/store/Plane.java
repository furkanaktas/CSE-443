package sample.store;

public abstract class Plane {

    protected String name,purpose, skeleton, engine, seat;

    /**
     * some implementation
     */
    protected void constructSkeleton(){}
    /**
     * some implementation
     */
    protected void placeEngines(){}
    /**
     * some implementation
     */
    protected void placeSeats(){}


    // other common methods

    /**
     *
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return purpose
     */
    public String getPurpose(){
        return purpose;
    }

    /**
     *
     * @return skeleton
     */
    public String getSkeleton(){return skeleton;}

    /**
     *
     * @return engine
     */
    public String getEngine(){return engine;}

    /**
     *
     * @return seat
     */
    public String getSeat(){return seat;}
}
