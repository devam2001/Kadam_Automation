package org.kadam;

import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class SignInPageTest extends TestBase {

    SignInPage signIn;
    ResourceBundle creds = ResourceBundle.getBundle("testcreds");

    @Test(priority = 1)
    public void signInpageUI(){
        signIn = new SignInPage(driver);
        signIn.uiSignin();
    }
    @Test(priority = 2)
    public void loginTestFlow(){
        signIn = new SignInPage(driver);
        int cnt=0;
        for(int i = 1; i<=15; i++){
            String email = creds.getString("newemail.testcase"+i);
            String password = creds.getString("newpassword.testcase"+i);
            signIn.loginTestForm(email, password);
            System.out.println(cnt++);
        }
    }
    @Test(priority = 3)
    public void loginRealFlow(){
        signIn = new SignInPage(driver);
        String username = creds.getString("email.real");
        String password = creds.getString("password.real");
        signIn.loginTestForm(username,password);
    }
}