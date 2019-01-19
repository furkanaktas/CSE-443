package sample.suits;

public abstract class Armor {
    String descripton= "Unknown Armor";

    /**
     *
     * @return destriciton about armor
     */
    public String getDescription(){
        return descripton;
    }

    /**
     *
     * @return cost
     */
    public abstract double cost();

    /**
     *
     * @return weight
     */
    public abstract double weight();
}
