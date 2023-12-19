package steps.demoqa;

import api_assured.ApiUtilities;
import context.ContextStore;
import demoqa.pages.LoginPage;
import io.cucumber.java.en.Given;

public class LoginPageSteps extends ApiUtilities {

    LoginPage loginPage = new LoginPage();

    @Given("Fill in {} and {} text boxes with the corresponding user information stored before")
    public void fillLoginContainers(String userNameString, String passwordString){


        String userName = ContextStore.get("username");
        String attributeName = "placeholder";
        String password = ContextStore.get("password");

        loginPage.fillInputInTextBoxesWrtAttributeName(attributeName, userNameString, loginPage.loginContainers, userName);

        loginPage.fillInputInTextBoxesWrtAttributeName(attributeName, passwordString, loginPage.loginContainers, password);

        log.info("Filling username and password text box with the info stored in context store");
    }

    @Given("Click login button")
    public void clickLoginButton(){
        loginPage.elementClick(loginPage.loginButton, true);
        log.info("Clicking login button");

    }


}
