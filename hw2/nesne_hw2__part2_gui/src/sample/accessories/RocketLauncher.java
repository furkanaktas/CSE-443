package sample.accessories;
import sample.suits.Armor;

public class RocketLauncher extends CondimentDecorator {
    private Armor armor;

    public RocketLauncher(Armor o){
        this.armor = o;
    }

    /**
     *
     * @return description
     */
    @Override
    public String getDescription() {
        return armor.getDescription()+", RocketLauncher";
    }

    /**
     *
     * @return cost
     */
    @Override
    public double cost() {
        return 150000 + armor.cost();
    }

    /**
     *
     * @return weight
     */
    @Override
    public double weight() {
        return 7.5 + armor.weight();
    }
}
