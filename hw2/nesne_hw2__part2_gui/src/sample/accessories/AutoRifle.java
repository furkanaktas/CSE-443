package sample.accessories;
import sample.suits.Armor;

public class AutoRifle extends CondimentDecorator {
    private Armor armor;

    public AutoRifle(Armor o){
        this.armor = o;
    }

    /**
     *
     * @return description
     */
    @Override
    public String getDescription() {
        return armor.getDescription()+", AutoRifle";
    }

    /**
     *
     * @return cost
     */
    @Override
    public double cost() {
        return 30000 + armor.cost();
    }

    /**
     *
     * @return weight
     */
    @Override
    public double weight() {
        return 1.5 + armor.weight();
    }
}
