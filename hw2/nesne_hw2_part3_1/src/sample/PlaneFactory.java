package sample;

import sample.plane.*;

public class PlaneFactory {

    /**
     *
     * @param type for plane that will be created
     * @return plane itself
     */
    public Plane createPlane(String type){
        Plane plane = null;

        if (type.equals("tpx100"))
        {
            plane = new TPX100();
        }
        else if (type.equals("tpx200")){
            plane = new TPX200();
        }
        else if (type.equals("tpx300")){
            plane = new TPX300();
        }
        return plane;
    }
}
