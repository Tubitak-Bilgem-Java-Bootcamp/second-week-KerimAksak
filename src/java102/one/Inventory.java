package java102.one;

import java102.one.armor.Armor;
import java102.one.armor.Shirt;
import java102.one.weapons.Fist;
import java102.one.weapons.Weapon;

public class Inventory {
    private boolean water;
    private boolean food;
    private boolean firewood;
    private Armor armor;
    private Weapon weapon;

    public Inventory() {
        this.water = false;
        this.food = false;
        this.firewood = false;
        this.weapon = new Fist();
        this.armor = new Shirt();
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
