package hr.tvz.defense.tower.towerdefense.towerdefense.Models;

import javafx.scene.image.Image;

public class Enemy {
    private final Image picture = new Image("C:/Uni/Java/TowerDefense/src/main/resources/hr/tvz/defense/tower/towerdefense/towerdefense/images/enemy.jpg");
    private final float speed = 1.0F;
    private final int baseHealth = 30;
    private final int baseDamage = 10;

    public Image getPicture() {
        return picture;
    }
    public float getSpeed() {
        return speed;
    }
    public int getBaseHealth() {
        return baseHealth;
    }
    public int getBaseDamage() {
        return baseDamage;
    }
}
