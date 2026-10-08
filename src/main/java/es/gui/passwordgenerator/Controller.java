package es.gui.passwordgenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private TextArea passwordTextArea;
    @FXML
    private TextField passwordLength;
    @FXML
    private TextField keyword;
    @FXML
    private CheckBox lower;
    @FXML
    private CheckBox upper;
    @FXML
    private CheckBox number;
    @FXML
    private CheckBox special;

    @FXML
    public void initialize() {
        // initialize is executed once when the program begins to run
        // Here we can intialize everything from the beginning like listeners...
        keyword.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.replace(" ", "");
            passwordLength.setText(String.valueOf(newValue.length()));
        });
    }

    @FXML
    void onGeneratePasswordClick(MouseEvent event) {
        Password password = new Password();
        password.setHasLowerCase(lower.isSelected());
        password.setHasUpperCase(upper.isSelected());
        password.setHasNumber(number.isSelected());
        password.setHasSpecialChars(special.isSelected());
        password.setKeyword(keyword.getText());

        try{
            password.setLength(Integer.parseInt(passwordLength.getText()));
        }
        catch (NumberFormatException e) {
            // Nothing happens
        }
        password.generateMainPassword(); // Generates password with options selected by user

        passwordTextArea.setEditable(false);
        passwordTextArea.setText(password.getPassword());
        passwordLength.setText(String.valueOf(password.getLength()));
    }

    @FXML
    void onCopyButton(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(passwordTextArea.getText());
        clipboard.setContent(content);
    }
}
