package org.kadam;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SignInPage{

    WebDriver driver;

    //locators for textual presence
    @FindBy(xpath = "//h3[contains(text(),'Kadam Admin')]")
    public WebElement titleCheck;
    @FindBy(xpath = "//p[contains(text(),'Admin Sign-In')]")
    public WebElement platformTitle;
    @FindBy(xpath = "//label[@for = 'email']")
    public WebElement emailFieldTitle;
    @FindBy(xpath = "//label[@for= 'password']")
    public WebElement passwordFieldTitle;
    @FindBy(xpath = "//p[contains(text(),'Forgot your Password?')]")
    public WebElement forgot_passText;
    @FindBy(xpath = "//div[contains(text(),'Please fill the required field')]")
    public WebElement errorMessageEmail;

    //SignIn & Reset
    @FindBy(xpath = "//input[@placeholder = 'Enter here']")
    public WebElement emailField;
    @FindBy(xpath = "//input[@name = 'password']")
    public WebElement passwordField;
    @FindBy(xpath = "//*[local-name()='svg' and @data-icon = 'eye']")
    public WebElement viewPass;
    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    public WebElement signIn_Button;
    @FindBy(xpath = "//a[contains(text(),'Reset Password')]")
    public WebElement resetPass_button;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void uiSignin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Check title
            assert wait.until(ExpectedConditions.textToBePresentInElement(titleCheck, "Kadam Admin")) : "Title mismatch";

            // Check platform title
            assert wait.until(ExpectedConditions.textToBePresentInElement(platformTitle, "Admin Sign-In")) : "Platform title mismatch";

            // Check email field title
            assert wait.until(ExpectedConditions.textToBePresentInElement(emailFieldTitle, "Email Id")) : "Email field title mismatch";

            // Check password field title
            assert wait.until(ExpectedConditions.textToBePresentInElement(passwordFieldTitle, "Password")) : "Password field title mismatch";

            // Check forgot password text
            assert wait.until(ExpectedConditions.textToBePresentInElement(forgot_passText, "Forgot your Password?")) : "Forgot password text mismatch";

            // Check if email and password fields are present and enabled
            assert wait.until(ExpectedConditions.elementToBeClickable(emailField)).isEnabled() : "Email field not clickable";
            assert wait.until(ExpectedConditions.elementToBeClickable(passwordField)).isEnabled() : "Password field not clickable";

            // Check if view password icon is present
            assert wait.until(ExpectedConditions.visibilityOf(viewPass)).isDisplayed() : "View password icon not visible";

            // Check if sign in button is present and enabled
            assert wait.until(ExpectedConditions.elementToBeClickable(signIn_Button)).isEnabled() : "Sign in button not clickable";

            // Check if reset password button is present
            assert wait.until(ExpectedConditions.visibilityOf(resetPass_button)).isDisplayed() : "Reset password button not visible";

            System.out.println("SignIn Page UI verification passed.");
        } catch (AssertionError e) {
            System.out.println("SignIn Page UI verification failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to fail the test
        } catch (Exception e) {
            System.out.println("Exception during UI verification: " + e.getMessage());
            throw new AssertionError("UI verification failed due to exception", e);
        }
    }

    public void loginTestForm(String username, String password){

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //Entering Login details
        if(wait.until(ExpectedConditions.elementToBeClickable(emailField)).isDisplayed()) {
            actions.click(emailField)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE)
                    .build()
                    .perform();
            emailField.sendKeys(username);
        }
        actions.click(passwordField)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();

        passwordField.sendKeys(password);
//        viewPass.click();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        viewPass.click();

        wait.until(ExpectedConditions.elementToBeClickable(signIn_Button)).click();

//        try{
//            if(wait.until(ExpectedConditions.elementToBeClickable(closeButton)).isEnabled())
//                wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
//        }
//        catch (Exception e){
//            System.out.println("Close Button not found !!!"+e.getMessage());
//        }
    }

}