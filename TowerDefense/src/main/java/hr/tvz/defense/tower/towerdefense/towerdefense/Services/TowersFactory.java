package hr.tvz.defense.tower.towerdefense.towerdefense.Services;

import hr.tvz.defense.tower.towerdefense.towerdefense.Models.ArcherTower;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class TowersFactory {

    private boolean[][] enemyPath;
    private ArcherTower archerTower;
    private GridPane myGridPane;
    private ImageView imageView;

    public TowersFactory(boolean[][] path){

        System.out.println("Starting Towers Factory...");
        this.enemyPath = path;
        this.archerTower = new ArcherTower();
        this.imageView = archerTower.getPicture();
    }

    public ImageView placeTower(GridPane grid){
        this.myGridPane = grid;
        return imageView;
    }
}
