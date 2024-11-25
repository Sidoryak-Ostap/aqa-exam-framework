package ui;

import bo.DeletePostBO;
import bo.LoginBO;
import driver.DriverPool;
import listeners.AllureListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({
        AllureListener.class
})
public class DeletePostTest {

    @BeforeMethod
    public void setup(){
        String login = "admin";
        String password = "admin";
        new LoginBO().login(login, password);
    }


    @Test
    public void DeletePostTest(){
        // 1. Verify post to delete exists
        new DeletePostBO().verifyPostToDeleteExists()
        // 2 Click on delete button
                .clickDeleteBtn()
        // 3 Confirm delete
                .confirmDelete()
        // 4 Verify post was deleted
                .verifyPostDeleted();
    }

    @AfterMethod
    void closeBrowser(){
        DriverPool.quitDriver();
    }
}
