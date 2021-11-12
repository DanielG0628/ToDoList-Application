package ucf.assignments;
/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Daniel Guzman
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class ToDoController {
    @FXML
    Button addButton;                                                                                                   //create addButton

    @FXML
    Button removeButton;                                                                                                //create removeButton

    @FXML
    Button clearButton;                                                                                                 //create clearButton

    @FXML
    Button editButton;                                                                                                  //create editButton

    @FXML
    Button completeOrIncompleteButton;                                                                                  //create completeOrIncompleteButton

    @FXML
    Button showCompleteButton;                                                                                          //create showCompleteButton

    @FXML
    Button showIncompleteButton;                                                                                        //create showIncompleteButton

    @FXML
    Button showExisting;                                                                                                //create showExistingButton

    @FXML
    Button helpButton;                                                                                                  //create helpButton

    @FXML
    Button saveButton;                                                                                                  //create saveButton

    @FXML
    Button loadButton;                                                                                                  //create loadButton

    @FXML
    ListView<String> listView;                                                                                          //create listView

    @FXML
    TextField yearText;                                                                                                 //create yearText

    @FXML
    TextField descriptionText;                                                                                          //create descriptionText

    @FXML
    TextField removeOrEditText;                                                                                         //create removeOrEditText

    @FXML
    TextField completeOrIncompleteText;                                                                                 //create completedOrIncompleteText

    ObservableList<String> list = FXCollections.observableArrayList();                                                  //create ObservableList that is connected to the ListView
    int itemCount = 0;                                                                                                  //create item count variable
    ToDoList t = new ToDoList();                                                                                        //create ToDoList class variable

    @FXML
    //Function to add an item to the list
    private void addItem(){
        if(t.checkText(yearText.getText(), descriptionText.getText()) == 1) {                                           //if(call checkText function == 1)
            itemCount++;                                                                                                //increment item count
            list.add(itemCount + ". " + yearText.getText() + " | " + descriptionText.getText() + " | Incomplete");      //add item to list
            listView.setItems(list);                                                                                    //set items to listView
            yearText.setText("");                                                                                       //make yearText empty
            descriptionText.setText("");                                                                                //make descriptionText empty
        }
    }

    @FXML
    //Function to remove an item from the list
    private void removeItem(){
        int temp = Integer.parseInt(removeOrEditText.getText()) - 1;                                                    //create temp variable that holds index
        list.remove(temp);                                                                                              //remove element in list at temp index
        list = t.removeItem(list, temp);                                                                                //call removeItem function
        listView.setItems(list);                                                                                        //set items to listView
        itemCount--;                                                                                                    //decrement item count
        removeOrEditText.setText("");                                                                                   //make removeOrEditText empty
    }

    @FXML
    //Function to clear all items in list
    private void clearList(){
        list.clear();                                                                                                   //clear list
        listView.setItems(list);                                                                                        //set items to listView
        itemCount = 0;                                                                                                  //set itemCount to zero
    }

    @FXML
    //Function to edit an item in the list
    private void editItem(){
        if(t.checkText(yearText.getText(), descriptionText.getText()) == 1) {                                           //if(call checkText function == 1)
            int temp = Integer.parseInt((removeOrEditText.getText())) - 1;                                              //create temp variable that holds index
            list.set(temp, removeOrEditText.getText() + ". " + yearText.getText() + " | " + descriptionText.getText() + //set edited item to index in list
                    " | Incomplete");
            listView.setItems(list);                                                                                    //set items to listView
            removeOrEditText.setText("");                                                                               //make removeOrEditText empty
            yearText.setText("");                                                                                       //make yearText empty
            descriptionText.setText("");                                                                                //make descriptionText empty
        }
    }

    @FXML
    //Function to change item to complete or incomplete
    private void completedOrIncompleteItem(){
        int temp = Integer.parseInt((completeOrIncompleteText.getText())) - 1;                                          //create temp variable that holds index
        list = t.completeOrIncomplete(list, temp);                                                                      //call completeOrIncomplete function
        listView.setItems(list);                                                                                        //set items to listView
        completeOrIncompleteText.setText("");                                                                           //make completeOrIncompleteText empty
    }

    @FXML
    //Function to show help screen
    private void help(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpView.fxml"));                          //create new FXMLLoader to load HelpView.fxml
            Parent root1 = (Parent) loader.load();                                                                      //
            Stage stage = new Stage();                                                                                  //
            stage.setTitle("Help");                                                                                     //Load help screen
            stage.setScene(new Scene(root1));                                                                           //
            stage.show();                                                                                               //
        }
        catch (Exception e){
            System.out.println("Can't load new window");
        }
    }

    @FXML
    //Function to show completed items
    private void showCompleted(){
        listView.setItems(t.showComplete(list));                                                                        //set items to ListView after calling showComplete function
    }

    @FXML
    //Function to show existing items
    private void showExisting(){
        listView.setItems(list);                                                                                        //set items to ListView
    }

    @FXML
    //Function to show the incomplete items
    private void showIncomplete(){;
        listView.setItems(t.showIncomplete(list));                                                                      //set items to ListView after calling showIncomplete function
    }

    @FXML
    //Function to save list
    private void saveList() throws Exception{
        try {
            FileWriter writer = new FileWriter("src/main/java/ucf/assignments/List.txt");                       //create new file to write in
            for(int i = 0; i < list.size(); i++)                                                                        //
                writer.write(list.get(i) + "\n");                                                                   //write all elements in list into new file
            writer.close();                                                                                             //close writer
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    //Function to load list
    private void loadList() throws Exception{
        try {
            File loadToDoList = new File("src/main/java/ucf/assignments/List.txt");                            //create new file to read from
            if (loadToDoList.exists()) {                                                                                //if(the file exists)
                list.clear();                                                                                           //clear the list
                Scanner sc = new Scanner(loadToDoList);                                                                 //
                while(sc.hasNextLine())                                                                                 //
                    list.add(sc.nextLine());                                                                            //read file into list
            }
            itemCount = list.size();                                                                                    //set itemCount to size of loaded list
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}