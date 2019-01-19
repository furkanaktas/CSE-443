package sample.suits;

public class Dec extends Armor {

    public Dec(){
        descripton = "Dec";
    }

    /**
     *
     * @return cost
     */
    @Override
    public double cost() {
        return 500000;
    }

    /**
     *
     * @return weight
     */
    @Override
    public double weight() {
        return 25;
    }
}
