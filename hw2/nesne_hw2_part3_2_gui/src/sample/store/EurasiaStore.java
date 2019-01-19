package sample.store;

import sample.plane.*;

public class EurasiaStore extends PlaneStore {

    /**
     *
     * @param type for plane that will be created
     * @return plane itself
     */
    @Override
    public Plane createPlane(String type) {
        Plane plane = null;

        if (type.equals("tpx100"))
        {
            plane = new EurasiaTPX100();
        }
        else if (type.equals("tpx200")){
            plane = new EurasiaTPX200();
        }
        else if (type.equals("tpx300")){
            plane = new EurasiaTPX300();
        }
        return plane;
    }
}
