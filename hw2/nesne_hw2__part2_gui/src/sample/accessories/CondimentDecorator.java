package sample.accessories;

import sample.suits.Armor;

public abstract class CondimentDecorator extends Armor {
    /**
     *
     * @return Armordaki getDescription methodu'unu implement ettirmeye zorlamak için
     */
    public abstract String getDescription();
}
