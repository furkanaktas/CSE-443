package sample.accessories;
import sample.suits.Armor;

public class Laser extends CondimentDecorator {
    private Armor armor;

    public Laser(Armor o){
        this.armor = o;
    }

    /**
     *
     * @return description
     */
    @Override
    public String getDescription() {
        return armor.getDescription()+", Laser";
    }

    /**
     *
     * @return cost
     */
    @Override
    public double cost() {
        return 200000 + armor.cost();
    }

    /**
     *
     * @return weight
     */
    @Override
    public double weight() {
        return 5.5 + armor.weight();
    }
}
