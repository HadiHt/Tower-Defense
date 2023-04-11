package hr.tvz.defense.tower.towerdefense.towerdefense.Controllers;

import hr.tvz.defense.tower.towerdefense.towerdefense.LevelOneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TowersController implements Initializable {

    private LevelOneController parent = new LevelOneController();
    private GridPane myGridPane;
    @FXML
    private Label money;
    @FXML
    private Label health;
    @FXML
    private GridPane myGridPaneTower;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i = 0; i < myGridPaneTower.getChildren().size(); i++) {
            myGridPaneTower.getChildren().get(i).toFront();
        }
        money.setText("$ 150");
        parent.setAttributes(money, health);
        health.setText("\u2764 100");
    }

    @FXML
    private void onClick(MouseEvent event){
        ImageView im = (ImageView) event.getSource();
        parent.childImage = im.getImage();
    }
        @FXML
        private void onDragDetected(MouseEvent event) {
            Dragboard db = myGridPaneTower.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(((ImageView)event.getSource()).getImage());
            db.setContent(content);
            event.consume();
        }
        @FXML
        private void onDragOver(DragEvent event) {
            if (event.getGestureSource() != event.getSource() &&
                    event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        }
        @FXML
        private void onDragDropped(DragEvent event) {
        try {
            ImageView draggedImage = imageView;
            int col;
            int row = myGridPane.getRowIndex((Node) event.getSource());
            try {
                col = myGridPane.getColumnIndex((Node) event.getSource());
            } catch (Exception e) {
                col = 0;
            }
            myGridPane.add(draggedImage, col, row);
            event.consume();
        }
        catch(Exception e){
            e.printStackTrace();
        }
            };
}

