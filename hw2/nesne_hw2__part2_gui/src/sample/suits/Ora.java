package sample.suits;

public class Ora extends Armor {

    public Ora(){
        descripton = "Ora";
    }

    /**
     *
     * @return cost
     */
    @Override
    public double cost() {
        return 1500000;
    }

    /**
     *
     * @return weight
     */
    @Override
    public double weight() {
        return 30;
    }
}
