package login;

import javafx.application.Application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;


public class Main extends Application {

    Label  userLabel,passLabel,regUserLabel,regPassLabel,emailLabel;
    TextField userField,regUserField,regEmailField,regPassField;
    Button loginButton,clearButton,createButton,canceledButton;
    PasswordField passField;






    @Override
    public void start(Stage primaryStage) throws Exception{
        File file = new File("src/sample/form.txt");
     
        PrintWriter writes = new PrintWriter(new FileWriter(file,true));
        Scanner scan = new Scanner(file);

        GridPane pane2 = new GridPane();
        GridPane mainPane = new GridPane();

        Scene mainScene = new Scene(mainPane);
        Scene scene2 = new Scene(pane2,500,300);


        Hyperlink link = new Hyperlink("Don't have an account?");
        link.setOnAction(e -> primaryStage.setScene(scene2));


        userLabel = new Label("Username");
        passLabel = new Label("Password");

        userField = new TextField();
        passField = new PasswordField();




        Alert alert = new Alert(Alert.AlertType.NONE);


        loginButton = new Button("Login");
        loginButton.setPrefSize(100,20);
        loginButton.setOnAction(actionEvent -> {
            String userN = userField.getText();
            String passW = passField.getText();

            String email = "";
            String user = "";
            String pass = "";

            while((!user.equals(userN)) && (!email.equals(userN)) && !(pass.equals(passW))) {
                email = scan.nextLine();
                user = scan.nextLine();
                pass = scan.nextLine();

                if (user.equals(userN) && pass.equals(passW)) {
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("you got in");
                    alert.setContentText("Correct");
                    primaryStage.close();
                } else if (email.equals(userN) && pass.equals(passW)) {
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("you got in");
                    alert.setContentText("Correct");
                    primaryStage.close();
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("nope");
                    alert.setContentText("Incorrect");
                }

            }
            alert.show();

        });

        clearButton = new Button("Clear");
        clearButton.setOnAction(actionEvent -> {
            userField.setText("");
            passField.setText("");
        });
        clearButton.setPrefSize(100,20);





        mainPane.setAlignment(Pos.CENTER);
        mainPane.setMinSize(400,200);
        mainPane.setVgap(5);
        mainPane.setHgap(5);
        mainPane.setPadding(new Insets(10,10,10,10));

        mainPane.add(userLabel,0,1);
        mainPane.add(userField,1,1);
        mainPane.add(passLabel,0,2);
        mainPane.add(passField,1,2);
        mainPane.add(loginButton,0,3);
        mainPane.add(clearButton,1,3);
        mainPane.add(link,1,4);

        Label headerLabel = new Label("Wakanda Forever");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        mainPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));






        regUserLabel = new Label("username");
        regPassLabel = new Label("password");
        emailLabel = new Label("Email");

        regUserField = new TextField();
        regEmailField = new TextField();
        regPassField = new TextField();

        createButton = new Button("create");
        createButton.setPrefSize(100,20);
        createButton.setOnAction(actionEvent -> {

            String storedEmail = regEmailField.getText();
            String storedUser = regUserField.getText();
            String storedPass = regPassField.getText();


            if(regPassField.getText().equals("")&& regUserField.getText().equals("")&&regEmailField.getText().equals("")){

                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("nothing was written");
                alert.show();
            }else{

                try{
                    writes.write(storedEmail+"\n");
                    writes.write(storedUser+"\n");
                    writes.write(storedPass+"\n");
                    writes.close();
                }catch (Exception e){
                    e.printStackTrace();

                }

                regEmailField.setText("");
                regUserField.setText("");
                regPassField.setText("");
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("account created");
                alert.show();
                primaryStage.setScene(mainScene);
            }

        });

        canceledButton = new Button("cancel");
        canceledButton.setPrefSize(100,20);
        canceledButton.setOnAction(actionEvent -> primaryStage.setScene(mainScene));



        pane2.setAlignment(Pos.CENTER);
        pane2.setMinSize(400,200);
        pane2.setVgap(5);
        pane2.setHgap(5);
        pane2.setPadding(new Insets(10,10,10,10));



        pane2.add(emailLabel,0,0);
        pane2.add(regEmailField,0,1);
        pane2.add(regUserLabel,0,2);
        pane2.add(regUserField,0,3);
        pane2.add(regPassLabel,0,4);
        pane2.add(regPassField,0,5);
        pane2.add(createButton,0,6);
        pane2.add(canceledButton,1,6);



        link.setOnAction(e -> primaryStage.setScene(scene2));







        primaryStage.setTitle("jfxLoginForm");
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
