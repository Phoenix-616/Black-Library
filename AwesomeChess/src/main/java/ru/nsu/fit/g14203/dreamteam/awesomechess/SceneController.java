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
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
                    String imgName = "resources/" + cur[i][k].getFigure().getIconFileName();
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
                FieldBack[i][j].setOnMouseClicked(new ActionHand());
                FieldBack[i][j].fitHeightProperty().bind(MainField.heightProperty().divide(8));
                FieldBack[i][j].fitWidthProperty().bind(MainField.widthProperty().divide(8));
                StackPane pane = new StackPane();
                if (white) {
                    pane.setStyle("-fx-background-color: grey;");
                } else {
                    pane.setStyle("-fx-background-color: darkgrey;");
                }
                pane.setOnMouseClicked(new ActionHand());
                MainField.add(pane, i, j);
                MainField.add(FieldBack[i][j], i, j);
                white = !white;
            }
            white = !white;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainField.minWidthProperty().bind(((GridPane)MainField.getParent()).widthProperty());
        MainField.minHeightProperty().bind(((GridPane)MainField.getParent()).widthProperty());
        ImgChar1.fitWidthProperty().bind(((GridPane)ImgChar1.getParent()).widthProperty());
        ImgChar1.fitHeightProperty().bind(((GridPane)ImgChar1.getParent()).heightProperty().multiply(0.35));
        ImgChar2.fitWidthProperty().bind(((GridPane)ImgChar1.getParent()).widthProperty());
        ImgChar2.fitHeightProperty().bind(((GridPane)ImgChar1.getParent()).heightProperty().multiply(0.35));
        model = new Model();
        initFieldBack();
        showField();
    }

    @FXML
    private void NewGamePressed(ActionEvent event) {
        model = new Model();
        showField();
        showCreatures();
        LogTextArea.setText("");
    }

    @FXML
    private void AboutPressed(ActionEvent event) {
        showAlert("Об игре", null, "    Игра AwesomeChess представляет из себя модифицированный вариант шахмат. Игра рассчитана на 2х игроков и проводится на одном компьютере. Есть общее шахматное поле. Каждый игрок имеет свой набор фигур - белых или черных. Белые ходят первыми. Все фигуры кроме пешек ходят в соответствии с правилами классических шахмат. Пешки изначально могут ходить только на один шаг вперед, но достигая противоположного края доски, превращаются в ферзя(королеву).\n" +
"    Каждая фигура олицетворяет некое существо из славянской мифологии и имеет карточку, на которой указано имя существа, его сила, изображение, а также дано небольшое описание. Когда игрок выбирает свою фигуру, карточка соответствующего существа отображается в области справа. Когда он кликает на фигуру противника, которую хочет (и может!) срубить, в области справа также отображается карточка вражеского существа. Между ними происходит \"битва\", исход которой заранее не предопределен: победить может как \"атакующее\" существо, так и \"обороняющееся\". Чем выше сила существа, тем выше его шансы на победу. Это делает исход игры абсолютно не предсказуемым.\n" +
"    Дополнительный хаос вносит наличие зловредного бога, который в начале каждого хода (кроме первого) совершает \"шалость\" на случайно выбранной клетке: если на ней стояло существо, то бог либо меняет его форму (высшая ↔ низшая), либо радостно его убивает. Убийство совершается не чаще, чем раз в 5 ходов. Что касается форм - все существа находятся в низшей форме в начале игры. Высшая форма отличается повышенным показателем силы. Иконка фигуры существа в высшей форме выделяется цветным контуром.\n" +
"    Победа в отличие от классических шахмат достигается тогда, когда все фигуры противника повержены.\n" +
"    Вся информация по игровому процессу описывается в области под шахматным полем - чей сейчас ход, кто куда сходил, божественные влияния, исходы битв, уведомление об окончании игры.\n" +
"    Новую игру можно начать, нажав соответствующую кнопку на панели меню. Там же можно ознакомиться с правилами игры и информацией о создателях приложения.\n" +
"    Наслаждайтесь. И да победит... любимчик бога рандома!\n");
    }

    @FXML
    private void CopyrightsPressed(ActionEvent event) {
        showAlert("Копирайт", "Разработчики", "Дробот Алёна"
                + "\nТрофимова Екатерина"
                + "\nНайденов Александр"
                + "\nГруппа \"DreamTeam\"(14203)");
    }

    private void showAlert(String tit, String header, String content) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle(tit);
        al.setHeaderText(header);
        al.setContentText(content);
        al.getDialogPane().setMinWidth(500);
        al.showAndWait();
    }

}
