package steps.demoqa;

import api_assured.ApiUtilities;
import demoqa.pages.LandingPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

public class LandingPageSteps extends ApiUtilities {

    LandingPage landingPage = new LandingPage();

    @Given("Click card named {} in the landing page")
    public void clickCard(String cardName){

        WebElement elementGroupButton = landingPage.getElementFromList(cardName, landingPage.cards);
        landingPage.elementClick(elementGroupButton, true);
        log.info("Clicking card named " + cardName + " on the landing page");
    }

}
