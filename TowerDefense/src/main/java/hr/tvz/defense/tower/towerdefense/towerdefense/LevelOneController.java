package hr.tvz.defense.tower.towerdefense.towerdefense;

import hr.tvz.defense.tower.towerdefense.towerdefense.Models.ArcherTower;
import hr.tvz.defense.tower.towerdefense.towerdefense.Services.EnemiesFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LevelOneController implements Initializable,Runnable {
    private static ImageView updatedImageView;
    private static Label money;
    private static Label health;
    private int threadId,row,column;
    private static int[][] TowersPosition = new int[10][11];
    public static int[][][] trackedPositionsByArcherTowers = new int[10][11][6];
    @FXML
    private StackPane mainStack;
    public static Image childImage = null;
    @FXML
    private Pane panee;
    @FXML
    private GridPane myGridPane;
    private ArcherTower archerTower = new ArcherTower();
    int startedId=0;
    public LevelOneController(){

    }
    private LevelOneController(GridPane runningPaneGrid, int id){
    this.myGridPane = runningPaneGrid;
    this.threadId = id;
    }
    Image backgroundImage = new Image("C:/Uni/Java/TowerDefense/src/main/resources/hr/tvz/defense/tower/towerdefense/towerdefense/images/grass-background.jpg");
    Image dirtPath = new Image("C:/Uni/Java/TowerDefense/src/main/resources/hr/tvz/defense/tower/towerdefense/towerdefense/images/dirt-path.jpg");

    boolean[][] enemyPath ={{false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false},
            {true,true,true,true,true,true,true,true,true,true,true},
            {false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false}};

    private LevelOneController controller;
    public void setMyGridPane(GridPane myGridPane) {
        this.myGridPane = myGridPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int row = 0; row < myGridPane.getRowCount(); row++) {
            for (int col = 0; col < myGridPane.getColumnCount(); col++) {
                ImageView imageView = new ImageView(backgroundImage);
                imageView.setFitWidth(90.909);
                imageView.setFitHeight(70);
                myGridPane.add(imageView, col, row);

                imageView.setOnMouseClicked(e -> clickCell(e));

            }
        }
        for (int row = 0; row < enemyPath.length; row++) {
            for (int col = 0; col < enemyPath[0].length; col++) {
                ImageView imageView = new ImageView(dirtPath);
                if(enemyPath[row][col]){
                    imageView.setFitWidth(90.909);
                    imageView.setFitHeight(70);
                    myGridPane.add(imageView, col, row);
                }
            }
        }
        panee.setPickOnBounds(false);

        controller = new LevelOneController(myGridPane, startedId);
        Thread thread1 = new Thread(controller);
        thread1.start();

    }

    public static void setAttributes(Label money, Label health) {
        LevelOneController.money = money;
        LevelOneController.health = health;

    }

    public LevelOneController getInstance(){
        return this;
    }
    public void clickCell(MouseEvent event){
        String o="000";
        o = money.getText().substring(2, money.getText().length());
        int oo = Integer.parseInt(o);
        oo = oo-50;
        if(oo >=0){
        money.setText("$ "+oo);



        if(childImage != null){
            updatedImageView = (ImageView) event.getSource();
            column = ((int) ((ImageView) event.getSource()).getLayoutX()/90);
            row = ((int) ((ImageView) event.getSource()).getLayoutY()/70);
            TowersPosition[row][column] = 1;
            updatedImageView.setImage(childImage);

            //x 0-10
            //y 0-9
            int lowX = column-2;
            int maxX = column+2;
            int lowY = row-2;
            int maxY = row+2;
            if(lowX >= (enemyPath[0].length -4)){
                maxX= enemyPath[0].length-1;
            }
            if(maxX <= 1){
                lowX = 0;
            }
            if(lowY >= (enemyPath.length -4)){
                maxY= enemyPath.length-1;
            }
            if(lowY <= 1){
                lowY = 0;
            }

                        for(int temp = lowX; temp<=maxX; temp++){
                            for(int temp2 = lowY; temp2<=maxY; temp2++){
                                if(enemyPath[temp2][temp] == true){
                                    for(int depth = 0; depth <= 5; depth++){
                                        if(trackedPositionsByArcherTowers[temp2][temp][depth]==0){
                                            System.out.println("found 1");
                                            trackedPositionsByArcherTowers[temp2][temp][depth]=1;
                                            break;
                                        }
                                    }
                                }
                                //ImageView radar = myGridPane.getNodeOrientation();
                            }
                        }
                        try {
                            Thread.sleep(400); //Move balloons
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

        }}}
    @Override
    public void run() {
        while(true) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(new Runnable() {
                EnemiesFactory factory = new EnemiesFactory(enemyPath, 1);
                ImageView enemyImageView = factory.sendEnemies(myGridPane);
                int enemyHealth = factory.getEnemy1().getBaseHealth();
                int enemyDamage = factory.getEnemy1().getBaseDamage();
                @Override
                public void run() {
                    while (true) {
                        int currentColIndex = GridPane.getColumnIndex(enemyImageView);
                        if (currentColIndex < 10) {
                            Platform.runLater(() -> GridPane.setColumnIndex(enemyImageView, currentColIndex + 1));
                            if(trackedPositionsByArcherTowers[GridPane.getRowIndex(enemyImageView)][currentColIndex][0] == 1){
                                int posx = GridPane.getColumnIndex(enemyImageView);
                                int posy = GridPane.getRowIndex(enemyImageView);
                                if (posx <= 1){
                                    posx=2;
                                }
                                else if(posx >=9){
                                    posx=8;
                                }
                                if (posy <= 1){
                                    posy=2;
                                }
                                else if(posy >=8){
                                    posy=7;
                                }
                                for (int u= posx-2; u<=posx+2;u++){
                                    for(int z= posy-2; z<=posy+2;z++){
                                        if(TowersPosition[z][u] == 1){

                                            int finalU = u;
                                            int finalZ = z;
                                            /*Node x = myGridPane.getChildren().get(((finalZ)*11)+finalU+1);
                                            String imagePath = "C:\\Uni\\Java\\TowerDefense\\src\\main\\resources\\hr\\tvz\\defense\\tower\\towerdefense\\towerdefense\\images\\tower.jpg";



                                                    ImageView tempImageView = (ImageView) x;
                                                    Image tempImage = tempImageView.getImage();
                                                    if (tempImage != null && imagePath.equals(tempImage.getUrl())) {
                                                        System.out.println("SUCCESSSSS");


                                            //Node t = x.get(10);
                                            //FilteredList<Node> cell = myGridPane.getChildren().filtered(node -> node.getLayoutX() == finalU && node.getLayoutY() == finalZ);

                                            ImageView PreShootingTowerImageView = (ImageView) x;
                                            //get the tower PreShootingTowerImageView


                                            //Broken
                                            Platform.runLater(() -> myGridPane.getChildren().remove(PreShootingTowerImageView));*/
                                            ImageView shottingTowerImageView = new ImageView(archerTower.getShootingPicture());
                                            shottingTowerImageView.setFitWidth(90.909);
                                            shottingTowerImageView.setFitHeight(70);
                                            Platform.runLater(() -> myGridPane.add(shottingTowerImageView, finalU, finalZ));

                                            try {
                                                Thread.sleep(200);
                                            } catch (InterruptedException e) {
                                                // Handle the exception if necessary
                                            }
                                            //Platform.runLater(() -> myGridPane.getChildren().remove(shottingTowerImageView));
                                            //shottingTowerImageView.setImage(archerTower.getPicture().getImage());
                                            ImageView notShooting = new ImageView(archerTower.getPicture().getImage());
                                            notShooting.setFitWidth(90.909);
                                            notShooting.setFitHeight(70);
                                            Platform.runLater(() -> myGridPane.add(notShooting, finalU, finalZ));
                                                    //}

                                        }
                                    }
                                }

                                int counter = 0;
                                for(int depth = 0; depth <= 5; depth++){
                                    if(trackedPositionsByArcherTowers[GridPane.getRowIndex(enemyImageView)][currentColIndex][depth]==1){
                                        counter++;
                                    }
                                }
                                for(int i= 0; i<counter;i++){
                                    enemyHealth = enemyHealth - (archerTower.getDamagePerShot()* archerTower.getAttackSpeed());
                                    System.out.println("SHOT");
                                }

                                if(enemyHealth <=0){
                                    Platform.runLater(() -> myGridPane.getChildren().remove(enemyImageView));
                                    executor.close();
                                }
                            }
                        } else {
                            Platform.runLater(() -> myGridPane.getChildren().remove(enemyImageView));
                            String o="000";
                            String[] v= health.getText().split(" ");
                            int oo = Integer.parseInt(v[1]);
                            oo = oo-enemyDamage;

                            int finalOo = oo;
                            Platform.runLater(() -> health.setText("\u2764 "+ finalOo));
                            //health.setText("\u2764 "+ finalOo);
                            executor.close();
                        }
                        try {
                            Thread.sleep(500); //Move balloons
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);

            try {
                Thread.sleep(1000); //spawning enemies
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}