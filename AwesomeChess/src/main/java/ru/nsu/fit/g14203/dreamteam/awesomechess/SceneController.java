/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.event.HyperlinkEvent;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.IModel;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Model;

/**
 * FXML Controller class
 *
 * @author phoenix
 */
public class SceneController implements Initializable {

    @FXML
    private TextArea LogTextArea;
    @FXML
    private GridPane MainField;
    @FXML
    private TextField NameChar1;
    @FXML
    private TextField NameChar2;
    @FXML
    private TextArea DescChar1;
    @FXML
    private TextArea DescChar2;
    @FXML
    private ImageView ImgChar1;
    @FXML
    private ImageView ImgChar2;

    private IModel model = null;

    /**
     * Initializes the controller class.
     */
    private ImageView[][] FieldBack = null;

    private void initFieldBack() {
        FieldBack = new ImageView[8][8];
        boolean white = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                FieldBack[i][j] = new ImageView();
                FieldBack[i][j].setFitWidth(50);
                FieldBack[i][j].setFitHeight(50);
                Image img = new Image(new File("resources/LVolkolak.png").toURI().toString());
                FieldBack[i][j].setImage(img);
                FieldBack[i][j].setOnMouseClicked(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        Object obj = event.getTarget();
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                if (FieldBack[i][j] == obj) {
                                    System.out.println(i + " " + j);//переместить на квадраты
                                }
                            }
                        }
                    }
                });
                Rectangle rect = new Rectangle(50, 50);
                if (white) {
                    rect.setFill(Color.WHITE);
                } else {
                    rect.setFill(Color.BROWN);
                }
                MainField.add(rect, i, j);
                MainField.add(FieldBack[i][j], i, j);
                white = !white;
            }
            white = !white;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initFieldBack();
    }

    @FXML
    private void NewGamePressed(ActionEvent event) {
    }

    @FXML
    private void AboutPressed(ActionEvent event) {
    }

    @FXML
    private void CopyrightsPressed(ActionEvent event) {
    }

}
