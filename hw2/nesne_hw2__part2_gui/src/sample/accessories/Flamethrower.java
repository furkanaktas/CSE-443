package sample.accessories;
import sample.suits.Armor;

public class Flamethrower extends CondimentDecorator {
    private Armor armor;

    public Flamethrower(Armor o){
        this.armor = o;
    }

    /**
     *
     * @return description
     */
    @Override
    public String getDescription() {
        return armor.getDescription()+", Flamethower";
    }

    /**
     *
     * @return cost
     */
    @Override
    public double cost() {
        return 50000 + armor.cost();
    }

    /**
     *
     * @return weight
     */
    @Override
    public double weight() {
        return 2 + armor.weight();
    }
}
