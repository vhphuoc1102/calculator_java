package controller;

import com.jfoenix.controls.JFXButton;
import feature.BaseConverter;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ConverterInterfaceController implements Initializable {
    private Scene sceneOfStandard;
    @FXML
    private Label baseBin;

    @FXML
    private Label baseDec;

    @FXML
    private Label baseHex;

    @FXML
    private Label baseOct;
    @FXML
    private Label result;

    @FXML
    private JFXButton converter;

    @FXML
    private Label resBin;

    @FXML
    private Label resDec;

    @FXML
    private Label resHex;

    @FXML
    private Label resOct;

    @FXML
    private VBox slider;

    private boolean menuDisplayed;
    private String base;
    private String temp;
    private int tempLen;
    private final ArrayList<String> decLock = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
    private final ArrayList<String> octLock = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "9", "8"));
    private final ArrayList<String> binLock = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "9", "8", "7", "6", "5", "4", "3", "2"));

    public void setFirstScene(Scene scene) {
        this.sceneOfStandard = scene;
    }

    public void switchToStandard(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneOfStandard);
        stage.show();
    }

    public void menuDisplay() {
        if (menuDisplayed) {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(slider);

            slide.setToX(-179);
            slide.play();

            slider.setTranslateX(0);
            menuDisplayed = false;
        } else {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-179);
            menuDisplayed = true;
        }
    }

    public void chooseMode(ActionEvent event) {
        if (event.getSource() == converter) {
            menuDisplay();
        } else {
            switchToStandard(event);
            menuDisplay();
        }
    }

    public void clickOnHex() {
        baseHex.setStyle("-fx-background-color: #f2f2f2;-fx-border-color: black black black black;");
        baseDec.setStyle(null);
        baseOct.setStyle(null);
        baseBin.setStyle(null);
        base = "HEX";
        result.setText(resHex.getText());
    }

    public void clickOnDec() {
        baseHex.setStyle(null);
        baseDec.setStyle("-fx-background-color: #f2f2f2;-fx-border-color: black black black black;");
        baseOct.setStyle(null);
        baseBin.setStyle(null);
        base = "DEC";
        result.setText(resDec.getText());
    }

    public void clickOnOct() {
        baseHex.setStyle(null);
        baseDec.setStyle(null);
        baseOct.setStyle("-fx-background-color: #f2f2f2;-fx-border-color: black black black black;");
        baseBin.setStyle(null);
        base = "OCT";
        result.setText(resOct.getText());
    }

    public void clickOnBin() {
        baseHex.setStyle(null);
        baseDec.setStyle(null);
        baseOct.setStyle(null);
        baseBin.setStyle("-fx-background-color: #f2f2f2;-fx-border-color: black black black black;");
        base = "BIN";
        result.setText(resBin.getText());
    }

    public void number(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();
        BaseConverter baseConverter = new BaseConverter();
        switch (base) {
            case "HEX":
                if (resHex.getText().equals("0")) {
                    resHex.setText(value);
                    result.setText(value);
                } else {
                    resHex.setText(resHex.getText() + value);
                    result.setText(resHex.getText());
                }
                resDec.setText(BaseConverter.baseConversion(result.getText(), 16, 10));
                resOct.setText(BaseConverter.baseConversion(result.getText(), 16, 8));
                resBin.setText(BaseConverter.baseConversion(result.getText(), 16, 2));
                break;
            case "DEC":
                if (decLock.contains(value)) return;
                if (resDec.getText().equals("0")) {
                    resDec.setText(value);
                    result.setText(value);
                    System.out.println(20);
                } else {
                    resDec.setText(resDec.getText() + value);
                    result.setText(resDec.getText());
                    System.out.println(21);
                }
                resHex.setText(BaseConverter.baseConversion(result.getText(), 10, 16));
                resOct.setText(BaseConverter.baseConversion(result.getText(), 10, 8));
                resBin.setText(BaseConverter.baseConversion(result.getText(), 10, 2));
                break;
            case "OCT":
                if (octLock.contains(value)) return;
                if (resOct.getText().equals("0")) {
                    resOct.setText(value);
                    result.setText(value);
                } else {
                    resOct.setText(resOct.getText() + value);
                    result.setText(resOct.getText());
                }
                resHex.setText(BaseConverter.baseConversion(result.getText(), 8, 16));
                resDec.setText(BaseConverter.baseConversion(result.getText(), 8, 10));
                resBin.setText(BaseConverter.baseConversion(result.getText(), 8, 2));
                break;
            case "BIN":
                if (binLock.contains(value)) return;
                if (resBin.getText().equals("0")) {
                    resBin.setText(value);
                    result.setText(value);
                } else {
                    resBin.setText(resBin.getText() + value);
                    result.setText(resBin.getText());
                }
                resHex.setText(BaseConverter.baseConversion(result.getText(), 2, 16));
                resDec.setText(BaseConverter.baseConversion(result.getText(), 2, 10));
                resOct.setText(BaseConverter.baseConversion(result.getText(), 2, 8));
                break;
            default:
                break;
        }
    }

    public void delete() {
        BaseConverter baseConverter = new BaseConverter();
        switch (base) {
            case "HEX":
                temp = resHex.getText();
                tempLen = temp.length();
                if (tempLen == 1) {
                    clearEntry();
                    return;
                }
                temp = temp.substring(0, tempLen - 1);
                resHex.setText(temp);
                resDec.setText(BaseConverter.baseConversion(temp, 16, 10));
                resOct.setText(BaseConverter.baseConversion(temp, 16, 8));
                resBin.setText(BaseConverter.baseConversion(temp, 16, 2));
                break;
            case "DEC":
                temp = resDec.getText();
                tempLen = temp.length();
                if (tempLen == 1) {
                    clearEntry();
                    return;
                }
                temp = temp.substring(0, tempLen - 1);
                resDec.setText(temp);
                resHex.setText(BaseConverter.baseConversion(temp, 10, 16));
                resOct.setText(BaseConverter.baseConversion(temp, 10, 8));
                resBin.setText(BaseConverter.baseConversion(temp, 10, 2));
                break;
            case "OCT":
                temp = resOct.getText();
                tempLen = temp.length();
                if (tempLen == 1) {
                    clearEntry();
                    return;
                }
                temp = temp.substring(0, tempLen - 1);
                resOct.setText(temp);
                resHex.setText(BaseConverter.baseConversion(temp, 8, 16));
                resDec.setText(BaseConverter.baseConversion(temp, 8, 10));
                resBin.setText(BaseConverter.baseConversion(temp, 8, 2));
                break;
            case "BIN":
                temp = resBin.getText();
                tempLen = temp.length();
                if (tempLen == 1) {
                    clearEntry();
                    return;
                }
                temp = temp.substring(0, tempLen - 1);
                resBin.setText(temp);
                resHex.setText(BaseConverter.baseConversion(temp, 2, 16));
                resDec.setText(BaseConverter.baseConversion(temp, 2, 10));
                resOct.setText(BaseConverter.baseConversion(temp, 2, 8));
                break;
            default:
                break;
        }
    }

    public void clearEntry() {
        result.setText("0");
        resHex.setText("0");
        resDec.setText("0");
        resOct.setText("0");
        resBin.setText("0");
        result.setText("0");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuDisplayed = false;
        slider.setTranslateX(-179);
        base = "DEC";
        result.setText("0");
        resHex.setText("0");
        resDec.setText("0");
        resOct.setText("0");
        resBin.setText("0");
    }
}
