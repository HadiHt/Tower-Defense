module hr.tvz.defense.tower.towerdefense.towerdefense {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires com.almasb.fxgl.all;

    opens hr.tvz.defense.tower.towerdefense.towerdefense to javafx.fxml;
    exports hr.tvz.defense.tower.towerdefense.towerdefense;

    opens hr.tvz.defense.tower.towerdefense.towerdefense.Controllers to javafx.fxml;
    exports hr.tvz.defense.tower.towerdefense.towerdefense.Controllers;
}