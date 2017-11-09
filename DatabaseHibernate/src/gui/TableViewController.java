/*
 * Copyright (C) 2017 Szysz
 */
package gui;

import crud.UsersCRUD;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import others.Users;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class TableViewController implements Initializable {

    private ObservableList<Users> usersList;
    @FXML
    private TableView<Users> tableView;
    @FXML
    private TableColumn<Users, String> user_id;
    @FXML
    private TableColumn<Users, String> username;
    @FXML
    private TableColumn<Users, String> role;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usersList = FXCollections.observableArrayList(UsersCRUD.getAllUsers());

        tableView.setItems(usersList);

        user_id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        username.setCellValueFactory(new PropertyValueFactory<>("name"));
        role.setCellValueFactory(new PropertyValueFactory<>("myRole"));

        user_id.prefWidthProperty().bind(tableView.widthProperty().multiply(0.24));
        username.prefWidthProperty().bind(tableView.widthProperty().multiply(0.40));
        role.prefWidthProperty().bind(tableView.widthProperty().multiply(0.35));
    }

    public void addUser(Users user){
        usersList.add(user);
    }
    
    public void removeUser(Users user){
        usersList.remove(user);
    }
    
    public ObservableList<Users> getUsersList() {
        return usersList;
    }

}
