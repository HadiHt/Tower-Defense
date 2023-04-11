package hr.tvz.defense.tower.towerdefense.towerdefense.Services;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class EnemyController implements Runnable {
    private EnemyController enemyController;
    private GridPane grid;
    private ImageView imageView;
    private int threadId;
    private Thread thread;

public EnemyController(GridPane grid, ImageView image, int id){
    this.grid = grid;
    this.imageView = image;
    this.threadId = id;
}

public void moveEnemy(){
    enemyController = new EnemyController(grid, imageView,threadId+1);
    thread = new Thread(enemyController);
    thread.start();
}
    @Override
    public void run() {
    System.out.println("testttt");
        try {
            this.thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
