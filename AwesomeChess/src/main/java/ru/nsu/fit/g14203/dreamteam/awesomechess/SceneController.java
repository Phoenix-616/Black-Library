/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nsu.fit.g14203.dreamteam.awesomechess;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.nsu.fit.g14203.dreamteam.awesomechess.creatures.ICreature;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.Cell;
import ru.nsu.fit.g14203.dreamteam.awesomechess.field.FieldCoord;
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

    private class ActionHand implements EventHandler {

        @Override
        public void handle(Event event) {
            Node obj = (Node) event.getTarget();
            int x = GridPane.getColumnIndex(obj);
            int y = 7 - GridPane.getRowIndex(obj);
            System.out.println( x+" "+y);
            model.cellClicked(new FieldCoord(x, y));
            showField();
            showCreatures();
            updateLogs();
            if (model.blackWin()) {
                addInfo("ПОБЕДА РАБОВ!!!");
            }
            if (model.whiteWin()) {
                addInfo("ПОБЕДА ХОЗЯЕВ!!!");
            }
        }

    }
    
    private void updateLogs() {
        List<String> l = model.getLog();
        for (String str : l) {
            addInfo(str);
        }
    }

    private void showCreatures() {
        List<ICreature> l = model.getSelectedCreatures();
        ICreature c;
        try {
            c = l.get(1);
            NameChar1.setText(c.getName());
            DescChar1.setText(c.getDescription());
            String imgName = "resources/" + c.getImgFileName();
            Image img = new Image(new File(imgName).toURI().toString());
            ImgChar1.setImage(img);
        } catch (Exception ex) {
            NameChar1.setText("");
            DescChar1.setText("");
            ImgChar1.setImage(null);
        }
        try {
            c = l.get(0);
            NameChar2.setText(c.getName());
            DescChar2.setText(c.getDescription());
            String imgName = "resources/" + c.getImgFileName();
            Image img = new Image(new File(imgName).toURI().toString());
            ImgChar2.setImage(img);
        } catch (Exception ex) {
            NameChar2.setText("");
            DescChar2.setText("");
            ImgChar2.setImage(null);
        }

    }

    private void addInfo(String str) {
        StringBuilder b = new StringBuilder();
        //b.append(LogTextArea.getText());
        b.append('\n');
        b.append(str);
        b.append('\n');
        LogTextArea.appendText(b.toString());
    }

    private void showField() {
        Cell[][] cur = model.getState();
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                int j = 7 - k;
                try {
                    String imgName = "resources/" + cur[i][k].getFigure().getCreature().getIconFileNameBlack();
                    Image img = new Image(new File(imgName).toURI().toString());
                    FieldBack[i][j].setImage(img);
                } catch (Exception ex) {
                    FieldBack[i][j].setImage(null);
                }
            }
        }
    }

    private void initFieldBack() {
        FieldBack = new ImageView[8][8];
        boolean white = true;
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                int j = 7 - k;
                FieldBack[i][j] = new ImageView();
                FieldBack[i][j].setFitWidth(50);
                FieldBack[i][j].setFitHeight(50);
                FieldBack[i][j].setOnMouseClicked(new ActionHand());
                Rectangle rect = new Rectangle(50, 50);
                if (white) {
                    rect.setFill(Color.WHITE);
                } else {
                    rect.setFill(Color.BROWN);
                }
                rect.setOnMouseClicked(new ActionHand());
                MainField.add(rect, i, j);
                MainField.add(FieldBack[i][j], i, j);
                white = !white;
            }
            white = !white;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Model();
        initFieldBack();
        showField();
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
