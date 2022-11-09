package com.example.hellofx;

import javafx.application.Application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class GridBasicTutorial extends Application {

    @Override
    public void start(Stage stage){
        try{
            BorderPane root = new BorderPane();
            GridPane grid = new GridPane();

            grid.setGridLinesVisible(true);

            // create java fx controls (nodes) to add to the grid pane
            Label lbFName = new Label("First Name");
            Label lbLName = new Label("Last Name");
            TextField txtFName = new TextField();
            TextField txtLName = new TextField();
            Button btnSave = new Button("save");
            Button btnCancel = new Button("cancel");
            ButtonBar btnBar = new ButtonBar();

            btnBar.getButtons().addAll(btnSave,btnCancel);


            grid.add(lbFName, 0,0 ,1,1);
            grid.add(txtFName,1,0,1,1);
            grid.add(lbLName,0 ,1,1,1 );
            grid.add(txtLName,1,1,1,1);
            grid.add(btnBar,0,2,2,1);

            // set colum and row gap

            grid.setHgap(10);
            grid.setVgap(5);

            grid.setPadding(new Insets(10,10,10,10));

            // col constraints

            ColumnConstraints column1 = new ColumnConstraints();
            ColumnConstraints column2 = new ColumnConstraints();

            grid.getColumnConstraints().add(column1);
            grid.getColumnConstraints().add(column2);

            column1.setPrefWidth(100);
            column2.setPrefWidth(200);

            column1.setPercentWidth(33);
            column2.setPercentWidth(67);

            // horizontal alignment
            GridPane.setHalignment(lbFName, HPos.RIGHT);
            GridPane.setHalignment(lbLName, HPos.RIGHT);

            GridPane.setHgrow(txtFName, Priority.ALWAYS);
            GridPane.setHgrow(txtLName, Priority.ALWAYS);





            root.setCenter(grid);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Grid pane");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
