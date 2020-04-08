package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account = new Account();

    @Test
    void addLunch() {
        //Arrange
//        Lunch lunch = new Lunch();
//
//        //Act
//        account.addLunch(lunch);
//        int actualSize = account.getAllLunch().size();
//        int expected = 1;
//
//        //Assert
//        assertEquals(expected, actualSize);
    }

    @Test
    void getLunchByValidID() {
        //Arrange
        Account account = new Account();
        Lunch lunch = new Lunch();
        account.addLunch(lunch);
        long lunchid = lunch.getLunchID();
        //Act
       Lunch lunch1 = account.getLunchByID(lunchid);
        //Assert
        assertEquals(lunch1, lunch);
    }

    @Test
    void getLunchByInvalidID() {
        //Arrange
        Account account = new Account();
        Lunch lunch = new Lunch();
        account.addLunch(lunch);
        //Act
        Lunch lunch1 = account.getLunchByID(lunch.getLunchID());
        //Assert
        assertEquals(1, lunch.getLunchID());
    }

    @Test
    void removeLunchDayByID() {
        //Arrange
//        Account account = new Account();
//        Lunch lunch = new Lunch();
//        account.addLunch(lunch);
//        long lunchid = lunch.getLunchID();
//        //Act
//        account.removeLunchDayByID(lunchid);
//        int actualsize = account.getAllLunch().size();
//        int expectedsize = 0;
//
//
//        //Assert
//        assertEquals(expectedsize, actualsize);
    }

    @Test
    void getAllLunch() {
        //Arrange
//        Account account = new Account();
//        Lunch lunch = new Lunch();
//        Lunch lunch1 = new Lunch();
//        account.addLunch(lunch);
//        account.addLunch(lunch1);
//        Lunch lunch2 = new Lunch();
//
//        //Act
//        int actualsize = account.getAllLunch().size();
//        int expectedsize = 2;
//
//
//        //Assert
//        assertEquals(expectedsize, actualsize);
    }
}
