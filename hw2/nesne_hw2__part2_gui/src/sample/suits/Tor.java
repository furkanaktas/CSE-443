package sample.suits;


public class Tor extends Armor {

    public Tor(){
        descripton = "Tor";
    }

    /**
     *
     * @return cost
     */
    @Override
    public double cost() {
        return 5000000;
    }

    /**
     *
     * @return weight
     */
    @Override
    public double weight() {
        return 50;
    }
}
