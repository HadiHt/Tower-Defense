package hr.tvz.defense.tower.towerdefense.towerdefense.Models;

import javafx.scene.image.Image;

public class Tower {
    private final int damagePerShot = 5;
    private final int damagePerSecond = 5;
    private final int range = 1;
    private final int attackSpeed = 1;


    public int getDamagePerShot() {
        return damagePerShot;
    }

    public int getDamagePerSecond() {
        return damagePerSecond;
    }

    public int getRange() {
        return range;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

}
