package hr.tvz.defense.tower.towerdefense.towerdefense.Services;

import hr.tvz.defense.tower.towerdefense.towerdefense.LevelOneController;
import hr.tvz.defense.tower.towerdefense.towerdefense.Models.EasyEnemy;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class EnemiesFactory{
    private int threadId= 0;
    private int level;
    private boolean[][] enemyPath;

    private EasyEnemy enemy1;
    //private MediumEnemy enemy2;
    //private HardEnemy enemy3;
    private GridPane myGridPane;
    private int start;
    private ImageView imageView;

    public EnemiesFactory(boolean[][] path, int level){

        System.out.println("Starting Enemies Factory...");
        this.level = level;
        enemyPath= path;
        enemy1= new EasyEnemy();
        imageView = new ImageView(enemy1.getPicture());

    }

    public EasyEnemy getEnemy1() {
        return enemy1;
    }

    public ImageView sendEnemies(GridPane grid) {
        this.myGridPane = grid;
        for (int row = 0; row < enemyPath.length; row++) {
                if(enemyPath[row][0]){
                    start=row;
                    imageView.setFitWidth(30);
                    imageView.setFitHeight(30);
                    Platform.runLater(() -> myGridPane.add(imageView, 0, start));
                    //Thread.sleep(100);
                    System.out.println("Sending Enemy...");


                }

        }

        return imageView;
    }


}
