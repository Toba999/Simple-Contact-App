package contactdbapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class FXMLDocumentBase extends AnchorPane {

    protected final Button btnNew;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Button btnUpdate;
    protected final Button btnDelete;
    protected final Button btnFirst;
    protected final Button btnPrev;
    protected final Button btnNext;
    protected final Button btnLast;
    protected final TextField txtId;
    protected final TextField txtName;
    protected final TextField txtEmail;
    protected final TextField txtPhone;
    protected final Label label3;
    DBHandler dbHandler;

    public FXMLDocumentBase() {

        dbHandler = DBHandler.getInstance();
        btnNew = new Button();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        btnUpdate = new Button();
        btnDelete = new Button();
        btnFirst = new Button();
        btnPrev = new Button();
        btnNext = new Button();
        btnLast = new Button();
        txtId = new TextField();
        txtName = new TextField();
        txtEmail = new TextField();
        txtPhone = new TextField();
        label3 = new Label();
        txtName.setEditable(false);
        txtPhone.setEditable(false);
        txtEmail.setEditable(false);
        getStarted();
        
        

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        btnNew.setLayoutX(102.0);
        btnNew.setLayoutY(309.0);
        btnNew.setMnemonicParsing(false);
        btnNew.setText("New...");
        btnNew.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(btnNew.getText().equals("New...")){
                    txtId.clear();
                    txtName.clear();
                    txtPhone.clear();
                    txtEmail.clear();
                    btnNew.setText("Save");
                    txtName.setEditable(true);
                    txtPhone.setEditable(true);
                    txtEmail.setEditable(true);
                }else{
                    Contact c = new Contact();
                    c.setName(txtName.getText());
                    c.setPhone(txtPhone.getText());
                    c.setEmail(txtEmail.getText());
                    
                    if(txtName.getText().isEmpty()|| txtEmail.getText().isEmpty() ||
                            txtPhone.getText().isEmpty() ){
                    showAlertMessage("Error", "You must fill all fields ", Alert.AlertType.ERROR);
                    }else{
                    dbHandler.addNew(c);
                     Contact c1 =dbHandler. getFirst();
                     btnNew.setText("New...");
                     System.out.println("Saved");
                    if (c1 != null){
                     txtId.setText(String.valueOf(c1.getID()));
                     txtName.setText(c1.getName());
                     txtPhone.setText(c1.getPhone());
                     txtEmail.setText(c1.getEmail());
                
                       txtId.setEditable(true);
                      txtName.setEditable(true);
                      txtPhone.setEditable(true);
                      txtEmail.setEditable(true);
                    }else{
                   showAlertMessage("Error", "There is no contact show  ", Alert.AlertType.ERROR);
                        txtId.clear();
                        txtName.clear();
                       txtPhone.clear();
                        txtEmail.clear();
                    }                                 
                }
 
                }
            }            
        });

        label.setLayoutX(72.0);
        label.setLayoutY(79.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(61.0);
        label.setText("ID");

        label0.setLayoutX(72.0);
        label0.setLayoutY(118.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(61.0);
        label0.setText("Name");

        label1.setLayoutX(72.0);
        label1.setLayoutY(160.0);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(61.0);
        label1.setText("Email");

        label2.setLayoutX(72.0);
        label2.setLayoutY(208.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(61.0);
        label2.setText("Phone");

        btnUpdate.setLayoutX(158.0);
        btnUpdate.setLayoutY(309.0);
        btnUpdate.setMnemonicParsing(false);
        btnUpdate.setText("Update");
        btnUpdate.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                 if(btnUpdate.getText().equals("Update")){
                   
                    btnUpdate.setText("Save");
                    
                    txtEmail.setEditable(true);
                    txtName.setEditable(true);
                    txtPhone.setEditable(true);
                    txtEmail.setDisable(false);
                    txtName.setDisable(false);
                    txtPhone.setDisable(false);
                    
                }
                else{
                    Contact c = new Contact();
                   c.setID(Integer.valueOf(txtId.getText()));
                    c.setName(txtName.getText());
                    c.setEmail(txtEmail.getText());
                    c.setPhone(txtPhone.getText());
                    
                    dbHandler.updateContact(c);
                    txtEmail.setEditable(false);
                    txtName.setEditable(false);
                    txtPhone.setEditable(false);
                    txtEmail.setDisable(true);
                    txtName.setDisable(true);
                    txtPhone.setDisable(true);
                    
                    btnUpdate.setText("Update");

                }
                
            
            }            
        });

        btnDelete.setLayoutX(221.0);
        btnDelete.setLayoutY(309.0);
        btnDelete.setMnemonicParsing(false);
        btnDelete.setText("Delete");
        btnDelete.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               if(txtId.getText().isEmpty()||txtName.getText().isEmpty()|| txtEmail.getText().isEmpty() ||
                            txtPhone.getText().isEmpty() ){
                showAlertMessage("Error", "There is no contact to delete  ", Alert.AlertType.ERROR);
                }else{
                dbHandler. deleteContact(Integer.valueOf(txtId.getText()));
               
                txtId.setEditable(true);
                txtName.setEditable(true);
                txtPhone.setEditable(true);
                txtEmail.setEditable(true);
                Contact c = dbHandler.getNext(Integer.parseInt(txtId.getText()));
                
                
                if (c != null){
                    txtId.setText(c.getID()+"");
                     txtName.setText(c.getName());
                     txtEmail.setText(c.getEmail());
                     txtPhone.setText(c.getPhone());
                     dbHandler.updateContact(c);
               }else{
                  showAlertMessage("Error", "This is the first contact   ", Alert.AlertType.ERROR);
                 txtId.clear();
                txtName.clear();
                txtEmail.clear();
                txtPhone.clear();
                }
                }
            }            
        });

        btnFirst.setLayoutX(282.0);
        btnFirst.setLayoutY(309.0);
        btnFirst.setMnemonicParsing(false);
        btnFirst.setText("First");
        btnFirst.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Contact c =dbHandler. getFirst();
                 if (c != null){
                txtId.setText(String.valueOf(c.getID()));
                txtName.setText(c.getName());
                txtPhone.setText(c.getPhone());
                txtEmail.setText(c.getEmail());
                
                txtId.setEditable(true);
                txtName.setEditable(true);
                txtPhone.setEditable(true);
                txtEmail.setEditable(true);
                }else{
                  showAlertMessage("Error", "There is no contact show  ", Alert.AlertType.ERROR);
                 }
            }            
        });

        btnPrev.setLayoutX(329.0);
        btnPrev.setLayoutY(309.0);
        btnPrev.setMnemonicParsing(false);
        btnPrev.setText("Previous");
        btnPrev.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               Contact c = dbHandler.getPrev(Integer.parseInt(txtId.getText()));
         
                if (c != null){
                    txtId.setText(c.getID()+"");
                     txtName.setText(c.getName());
                     txtEmail.setText(c.getEmail());
                     txtPhone.setText(c.getPhone());
               }else{
                  showAlertMessage("Error", "This is the first contact   ", Alert.AlertType.ERROR);
                 }
            }            
        });

        btnNext.setLayoutX(400.0);
        btnNext.setLayoutY(309.0);
        btnNext.setMnemonicParsing(false);
        btnNext.setText("Next");
        btnNext.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               Contact c = dbHandler.getNext(Integer.parseInt(txtId.getText()));
                
               if (c != null){
                    txtId.setText(c.getID()+"");
                     txtName.setText(c.getName());
                     txtEmail.setText(c.getEmail());
                     txtPhone.setText(c.getPhone());
               }else{
                  showAlertMessage("Error", "This is the last contact   ", Alert.AlertType.ERROR);
                 }
                
            }            
        });

        btnLast.setLayoutX(452.0);
        btnLast.setLayoutY(309.0);
        btnLast.setMnemonicParsing(false);
        btnLast.setText("Last");
        btnLast.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Contact c =dbHandler. getLast();
                 if (c != null){
               txtId.setText(String.valueOf(c.getID()));
                txtName.setText(c.getName());
                txtPhone.setText(c.getPhone());
                txtEmail.setText(c.getEmail());
                
                txtId.setEditable(true);
                txtName.setEditable(true);
                txtPhone.setEditable(true);
                txtEmail.setEditable(true);
                }else{
                  showAlertMessage("Error", "There is no contact show  ", Alert.AlertType.ERROR);
                }
            }            
        });

        txtId.setLayoutX(126.0);
        txtId.setLayoutY(75.0);
        txtId.setPrefHeight(25.0);
        txtId.setPrefWidth(142.0);

        txtName.setLayoutX(126.0);
        txtName.setLayoutY(114.0);
        txtName.setPrefHeight(25.0);
        txtName.setPrefWidth(251.0);

        txtEmail.setLayoutX(123.0);
        txtEmail.setLayoutY(156.0);
        txtEmail.setPrefHeight(25.0);
        txtEmail.setPrefWidth(257.0);

        txtPhone.setLayoutX(123.0);
        txtPhone.setLayoutY(204.0);
        txtPhone.setPrefHeight(25.0);
        txtPhone.setPrefWidth(185.0);

        label3.setLayoutX(72.0);
        label3.setLayoutY(36.0);
        label3.setText("Person Details");
        label3.setFont(new Font(14.0));

        getChildren().add(btnNew);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(btnUpdate);
        getChildren().add(btnDelete);
        getChildren().add(btnFirst);
        getChildren().add(btnPrev);
        getChildren().add(btnNext);
        getChildren().add(btnLast);
        getChildren().add(txtId);
        getChildren().add(txtName);
        getChildren().add(txtEmail);
        getChildren().add(txtPhone);
        getChildren().add(label3);

    }
    
    private void showAlertMessage(String header, String msg, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(msg);
        a.show();
    }
    
    private void getStarted(){
        Contact c =dbHandler. getFirst();
                 if (c != null){
                txtId.setText(String.valueOf(c.getID()));
                txtName.setText(c.getName());
                txtPhone.setText(c.getPhone());
                txtEmail.setText(c.getEmail());
                
                txtId.setEditable(true);
                txtName.setEditable(true);
                txtPhone.setEditable(true);
                txtEmail.setEditable(true);
                }
    }
    
}
