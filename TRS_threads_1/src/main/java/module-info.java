module lr10.oop_lr10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens threads.lab_1 to javafx.fxml;
    exports threads.lab_1;
}