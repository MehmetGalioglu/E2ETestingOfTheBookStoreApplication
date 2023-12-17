package steps.demoqa;

import api_assured.ApiUtilities;
import demoqapages.LandingPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPageSteps extends ApiUtilities {

    LandingPage landingPage = new LandingPage();

    @Given("Click card named {} in the landing page")
    public void clickCard(String cardName){
        List<WebElement> cardBodies = landingPage.cardBodies();
        WebElement elementGroupButton = landingPage.getElementFromList(cardName, cardBodies);
        log.info("Click card named " + cardName + " on the landing page");
    }

}
