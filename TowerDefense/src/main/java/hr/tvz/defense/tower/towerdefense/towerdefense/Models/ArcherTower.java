package hr.tvz.defense.tower.towerdefense.towerdefense.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ArcherTower extends Tower{
    private final ImageView picture = new ImageView("C:\\Uni\\Java\\TowerDefense\\src\\main\\resources\\hr\\tvz\\defense\\tower\\towerdefense\\towerdefense\\images\\tower.jpg");
    private final ImageView pictureGame = new ImageView("C:\\Uni\\Java\\TowerDefense\\src\\main\\resources\\hr\\tvz\\defense\\tower\\towerdefense\\towerdefense\\images\\tower-game.jpg");
    private final Image shootingPicture = new Image("C:\\Uni\\Java\\TowerDefense\\src\\main\\resources\\hr\\tvz\\defense\\tower\\towerdefense\\towerdefense\\images\\towerShooting.jpg");

    private int damagePerShot;
    private int damagePerSecond;
    private int range;
    private int attackSpeed;

    public ArcherTower() {
        this.damagePerSecond = getDamagePerSecond();
        this.damagePerShot = getDamagePerShot();
        this.range = getRange();
        this.attackSpeed = getAttackSpeed();
    }

    public Image getShootingPicture() {
        return shootingPicture;
    }
    public ImageView getPicture() {
        return picture;
    }

    public ImageView getPictureGame() {
        return pictureGame;
    }
}
