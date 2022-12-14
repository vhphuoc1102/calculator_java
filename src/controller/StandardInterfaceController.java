package controller;

import com.jfoenix.controls.JFXButton;
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
import java.util.ResourceBundle;

public class StandardInterfaceController implements Initializable {
    private Scene sceneOfConverter;

    @FXML
    private Label prev;

    @FXML
    private Label result;

    @FXML
    private VBox slider;
    @FXML
    private JFXButton standard;
    private boolean menuDisplayed;
    private boolean otherOperator;
    private boolean equalOperator;
    private boolean check; // Bien the hien vua an 1 dau phep tinh va chua thuc hien them bat ki mot hanh dong gi khac
    private double firstNumber;
    private double secondNumber;
    private double res;
    private String operator;
    private boolean checkPointOperator;

    public void setSecondScene(Scene scene) {
        this.sceneOfConverter = scene;
    }

    public void switchToConverter(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneOfConverter);
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
        if (event.getSource() == standard) {
            menuDisplay();
        } else {
            switchToConverter(event);
            menuDisplay();
        }
    }

    public void computeProcess(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();
        if (value.equals(".")) {
            if (otherOperator && equalOperator) {
                otherOperator = false;
                equalOperator = false;
                result.setText("0.");
                checkPointOperator = true;
                System.out.println("B0");
            } else if ((otherOperator && !equalOperator) || (!otherOperator && equalOperator)) {
                result.setText(result.getText() + ".");
                checkPointOperator = true;
                System.out.println("B1");
            } else {
                if (!checkPointOperator) {
                    result.setText(result.getText() + ".");
                    checkPointOperator = true;
                    System.out.println("B2");
                } else {
                    checkPointOperator = false;
                }
            }
            return;
        }
        if (otherOperator && equalOperator) {
            prev.setText("");
            result.setText(value);
            otherOperator = false;
            equalOperator = false;
            System.out.println("B3");
        } else if (otherOperator && !equalOperator) {
            if (!check) {
                result.setText(value);
                check = true;
                System.out.println("B4");
            } else {
                result.setText(result.getText() + value);
                check = false;
                System.out.println("B5");
            }
        } else if (!otherOperator && equalOperator) {
            if (!check) {
                result.setText(value);
                check = true;
                System.out.println("B6");
            } else {
                result.setText(result.getText() + value);
                System.out.println("B7");
            }
        } else {
            if (checkPointOperator) checkPointOperator = false;
            if (result.getText().equals("0")) result.setText(value);
            else result.setText(result.getText() + value);
            System.out.println("B8");
        }
    }

    public void operateProcess(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();
        check = false;
        if (otherOperator && equalOperator) {
            if (value.equals("=")) {
                firstNumber = Double.parseDouble(result.getText());
                res = basicCalculate(firstNumber, secondNumber, operator);
                prev.setText(DoubleOrInteger(firstNumber) + operator + DoubleOrInteger(secondNumber));
                result.setText(res + "");
                System.out.println("B9");
            } else {
                firstNumber = Double.parseDouble(result.getText());
                operator = value;
                prev.setText(DoubleOrInteger(firstNumber) + operator);
                equalOperator = false;
                System.out.println("B10");
            }
        } else if (otherOperator && !equalOperator) {
            if (value.equals("=")) {
                secondNumber = Double.parseDouble(result.getText());
                prev.setText(DoubleOrInteger(firstNumber) + operator + DoubleOrInteger(secondNumber) + "=");
                res = basicCalculate(firstNumber, secondNumber, operator);
                result.setText(DoubleOrInteger(res) + "");
                equalOperator = true;
                System.out.println("B11");
            } else {
                if (!value.equals(operator)) {
                    operator = value;
                    prev.setText(DoubleOrInteger(firstNumber) + operator);
                    System.out.println("B12");
                }
            }
        } else if (!otherOperator && equalOperator) {
            if (value.equals("=")) {
                System.out.println("B13");
            } else {
                operator = value;
                prev.setText(DoubleOrInteger(firstNumber) + operator);
                otherOperator = true;
                equalOperator = false;
                System.out.println("B14");
            }
        } else {
            if (value.equals("=")) {
                if (checkPointOperator) {
                    int len = result.getText().length();
                    firstNumber = Double.parseDouble(result.getText().substring(0, len - 1));
                    System.out.println(firstNumber);
                    result.setText(DoubleOrInteger(firstNumber) + "");
                    checkPointOperator = false;
                    System.out.println("B15");
                } else firstNumber = Double.parseDouble(result.getText());
                prev.setText(DoubleOrInteger(firstNumber) + "=");
                equalOperator = true;
                System.out.println("B16");
            } else {
                if (checkPointOperator) {
                    int len = result.getText().length();
                    firstNumber = Double.parseDouble(result.getText().substring(0, len - 1));
                    result.setText(DoubleOrInteger(firstNumber) + "");
                    checkPointOperator = false;
                    System.out.println("B17");
                } else firstNumber = Double.parseDouble(result.getText());
                operator = value;
                prev.setText(DoubleOrInteger(firstNumber) + operator);
                otherOperator = true;
                System.out.println("B18");
            }
        }
    }

    public void square() {
        if (checkPointOperator) {
            prev.setText("0");
            result.setText("0");
            checkPointOperator = false;
        } else {
            double temp = Double.parseDouble(result.getText());
            res = Math.pow(temp, 2);
            prev.setText("sqr(" + DoubleOrInteger(temp) + ")=");
            equalOperator = true;
            result.setText(DoubleOrInteger(res) + "");
        }
    }

    public void squareRoot() {
        if (checkPointOperator) {
            prev.setText("0");
            result.setText("0");
            checkPointOperator = false;
        } else {
            double temp = Double.parseDouble(result.getText());
            prev.setText("sqrt(" + DoubleOrInteger(temp) + ")=");
            equalOperator = true;
            if (temp < 0) {
                result.setText("Invalid input");
            } else {
                res = Math.sqrt(temp);
                result.setText(DoubleOrInteger(res));
            }
        }
    }

    public void inverse() {
        if (checkPointOperator) {
            prev.setText("0");
            result.setText("0");
            checkPointOperator = false;
        } else {
            double temp = Double.parseDouble(result.getText());
            prev.setText("sqrt(" + DoubleOrInteger(temp) + ")=");
            equalOperator = true;
            if (temp == 0) {
                result.setText("Invalid input");
            } else {
                res = 1 / temp;
                result.setText(res + "");
            }
        }
    }

    public void clear() {
        prev.setText("");
        result.setText("0");
        otherOperator = false;
        equalOperator = false;
        checkPointOperator = false;
    }

    public void delete() {
        if (otherOperator && equalOperator) {
            otherOperator = false;
            equalOperator = false;
            checkPointOperator = false;
            prev.setText("");
        } else if (!otherOperator && !equalOperator && !result.getText().equals("0")) {
            int len = result.getText().length();
            if (result.getText().charAt(len - 1) == '.') checkPointOperator = false;
            result.setText(result.getText().substring(0, len - 1));
        } else if (otherOperator && !equalOperator && !result.getText().equals("0")) {
            int len = result.getText().length();
            if (result.getText().charAt(len - 1) == '.') checkPointOperator = false;
            result.setText(result.getText().substring(0, len - 1));
        }
    }

    public void percent() {
        if (otherOperator && !equalOperator) {
            if (operator.equals("+") || operator.equals("-")) {
                double temp = Double.parseDouble(result.getText());
                res = temp / 100 * firstNumber;
            } else {
                res = firstNumber / 100;
            }
            secondNumber = res;
            prev.setText(DoubleOrInteger(firstNumber) + operator + DoubleOrInteger(secondNumber));
            result.setText(res + "");
        } else if (otherOperator && equalOperator) {
            double temp = Double.parseDouble(result.getText());
            if (operator.equals("+") || operator.equals("-")) {
                res = Math.pow(temp, 2) / 100;
            } else {
                res = temp / 100;
            }
            prev.setText(DoubleOrInteger(res) + "");
            result.setText(DoubleOrInteger(res) + "");
            equalOperator = false;
            otherOperator = false;
        } else {
            prev.setText("0");
            result.setText("0");
        }

    }

    public void negative() {
        res = Double.parseDouble(result.getText());
        if ((!otherOperator && equalOperator) || (otherOperator && equalOperator)) {
            prev.setText("negative(" + res + ")=");
            result.setText(DoubleOrInteger(-res) + "");
            otherOperator = false;
        } else if (!otherOperator && !equalOperator) {
            result.setText(DoubleOrInteger(-res) + "");
        } else {
            secondNumber = -res;
            result.setText(DoubleOrInteger(-res) + "");
            prev.setText(DoubleOrInteger(firstNumber) + operator + "negative(" + res + ")");
        }
    }

    public void clearEntry() {
        if (otherOperator && equalOperator) {
            otherOperator = false;
            equalOperator = false;
            prev.setText("");
        }
        result.setText("0");
        check = false;
    }

    public double basicCalculate(double number1, double number2, String operator) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "x":
                return number1 * number2;
            case "/":
                if (number2 != 0) return number1 / number2;
                else {
                    prev.setText("Cannot divide by zero");
                    return 0.0;
                }
            default:
                break;
        }
        return 0.0;
    }

    public String DoubleOrInteger(double number) {
        if (Math.ceil(number) == Math.floor(number)) {
            return "" + (int) number;
        } else {
            return "" + number;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuDisplayed = false;
        slider.setTranslateX(-179);
        prev.setText("");
        result.setText("0");
        otherOperator = false;
        equalOperator = false;
        check = false;
        firstNumber = 0;
        checkPointOperator = false;
    }
}
