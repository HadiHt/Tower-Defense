package hr.tvz.defense.tower.towerdefense.towerdefense.Models;

import javafx.scene.image.Image;

public class EasyEnemy extends Enemy{

    private Image enemyPic;
    private float speed;
    private int baseHealth;
    private int baseDamage;

    public EasyEnemy(){
        enemyPic = getPicture();
        speed = getSpeed();
        baseHealth = getBaseHealth();
        baseDamage = getBaseDamage();

    }
}
