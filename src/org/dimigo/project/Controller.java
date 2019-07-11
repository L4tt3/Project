package org.dimigo.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {
    @FXML private Button btn_ptt;
    @FXML private Button btn_splt;
    @FXML private Button btn_gtm;
    @FXML private Label lbl_err;
    @FXML private TextField pageSize;
    @FXML private Button btn_quit;

    @FXML
    public void gotoParser(ActionEvent event){
        try {
            Stage stage = (Stage) btn_ptt.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Parser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ie){
            lbl_err.setText("File Read Error");
        }
    }
    @FXML
    public void gotoMain(ActionEvent event){
        try {
            Stage stage = (Stage) btn_gtm.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ie){
            lbl_err.setText("Error");
        }
    }
    @FXML
    public void gotoSplitter(ActionEvent event){
        try {
            Stage stage = (Stage) btn_splt.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Splitter.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ie){
            lbl_err.setText("Error");
        }
    }
    @FXML
    public void chooseFile_txt(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String filepath = selectedFile.getAbsolutePath();
        try {
            OutputStream output = new FileOutputStream(filepath.substring(0,filepath.length()-3) + "txt");
            String str = new PdfParser().PdfFileParser(filepath);
            byte[] by=str.getBytes();
            output.write(by);
            lbl_err.setText("Success. If your txt is blank, I can't do...");
        } catch (FileNotFoundException fne){
            lbl_err.setText("No File");
        } catch (IOException ie) {
            lbl_err.setText("File Read Error");
        } catch (Exception e){
            lbl_err.setText("Error");
        }
    }
    @FXML
    public void chooseFile_split(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String filepath = selectedFile.getAbsolutePath();
        try {
            PdfSplitter.splitPDFFile(filepath,Integer.parseInt(pageSize.getText()));
            lbl_err.setText("Success!");
        } catch (Exception e){
            lbl_err.setText("Error");
        }
    }
    @FXML
    public void quitFile(ActionEvent event){
        Stage stage = (Stage) btn_quit.getScene().getWindow();
        stage.close();
    }
}
