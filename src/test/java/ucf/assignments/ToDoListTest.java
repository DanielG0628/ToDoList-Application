package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    @Test
    void checkText() {
        assertEquals(1, ToDoList.checkText("2002-02-20", "My Birthday"));
        assertEquals(1, ToDoList.checkText("2002-08-22", "Mary's Birthday"));
        assertEquals(0, ToDoList.checkText("1234", "Birthday"));
        assertEquals(0, ToDoList.checkText("2002-02-20", ""));
    }

    @Test
    void removeItem() {
        ObservableList<String> temp1 = FXCollections.observableArrayList();
        temp1.add("1. 2002-02-28 | Daniel Guzman | Incomplete");
        temp1.add("2. 2008-03-28 | Mary Jane | Incomplete");
        ObservableList<String> temp2 = FXCollections.observableArrayList();
        temp2.add("1. 2008-03-28 | Mary Jane | Incomplete");
        assertEquals(temp1, ToDoList.removeItem(temp1, 1));
    }

    @Test
    void completeOrIncomplete() {
        ObservableList<String> temp1 = FXCollections.observableArrayList();
        ObservableList<String> temp2 = FXCollections.observableArrayList();
        temp1.add("1. 2002-02-28 | Daniel Guzman | Incomplete");
        temp2.add("1. 2002-02-28 | Daniel Guzman | Complete");
        assertEquals(temp2, ToDoList.completeOrIncomplete(temp1, 0));
    }

    @Test
    void showComplete() {
        ObservableList<String> temp1 = FXCollections.observableArrayList();
        ObservableList<String> temp2 = FXCollections.observableArrayList();
        temp1.add("1. 2002-02-28 | Daniel Guzman | Incomplete");
        temp1.add("1. 2002-02-28 | Daniel Guzman | Complete");
        temp2.add("1. 2002-02-28 | Daniel Guzman | Complete");
        assertEquals(temp2, ToDoList.showComplete(temp1));
    }

    @Test
    void showIncomplete() {
        ObservableList<String> temp1 = FXCollections.observableArrayList();
        ObservableList<String> temp2 = FXCollections.observableArrayList();
        temp1.add("1. 2002-02-28 | Daniel Guzman | Incomplete");
        temp1.add("1. 2002-02-28 | Daniel Guzman | Complete");
        temp2.add("1. 2002-02-28 | Daniel Guzman | Incomplete");
        assertEquals(temp2, ToDoList.showIncomplete(temp1));
    }
}