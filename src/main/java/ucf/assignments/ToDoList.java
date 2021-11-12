package ucf.assignments;
/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Daniel Guzman
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoList {

    public static int checkText(String year, String description){
        if(year.matches("\\d{4}-\\d{2}-\\d{2}") && !description.equals(""))                                       //if(year is correct format and description is not empty)
            return 1;                                                                                                   //return 1
        else                                                                                                            //else
            return 0;                                                                                                   //return 0
    }

    public static ObservableList<String> removeItem(ObservableList<String> list, int temp){
        for(int i = temp; i < list.size(); i++){                                                                        //for(loop through list size starting at temp)
            String newNum = String.valueOf(i + 1 + ".");                                                                //create newNum string
            String oldNum = String.valueOf(i + 2 + ".");                                                                //create oldNum string
            String tempItem = list.get(i).replace(oldNum, newNum);                                                      //replace oldNum with newNum is the tempItem
            list.set(i, tempItem);                                                                                      //set the tempItem in the list
        }

        return list;                                                                                                    //return list
    }

    public static ObservableList<String> completeOrIncomplete(ObservableList<String> list, int temp){
        String tempItem = " ";                                                                                          //create tempItem variable
        if(list.get(temp).contains("Incomplete"))                                                                       //if(list at temp index contains Incomplete)
            tempItem = list.get(temp).replace("Incomplete", "Complete");                                //change Incomplete to Complete
        else if(list.get(temp).contains("Complete"))                                                                    //if(list at temp index contains Complete)
            tempItem = list.get(temp).replace("Complete", "Incomplete");                                //change Complete to InComplete
        list.set(temp, tempItem);                                                                                       //set tempItem to list

        return list;                                                                                                    //return list
    }

    public static ObservableList<String> showComplete(ObservableList<String> list){
        ObservableList<String> temp = FXCollections.observableArrayList();                                              //create temp ObservableList
        for(int i = 0; i < list.size(); i++)                                                                            //for(loop through list size)
            if(list.get(i).contains("Complete"))                                                                        //if(list at index i contains Complete)
                temp.add(list.get(i));                                                                                  //add list at i to temp list

        return temp;                                                                                                    //return temp list
    }

    public static ObservableList<String> showIncomplete(ObservableList<String> list){
        ObservableList<String> temp = FXCollections.observableArrayList();                                              //create temp ObservableList
        for(int i = 0; i < list.size(); i++)                                                                            //for(loop through list size)
            if(list.get(i).contains("Incomplete"))                                                                      //if(list at index i contains Incomplete)
                temp.add(list.get(i));                                                                                  //add list at i to temp list

        return temp;                                                                                                    //return temp list
    }
}
