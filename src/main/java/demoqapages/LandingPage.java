package demoqapages;

import demoqapages.components.Card;
import demoqapages.components.ElementGroup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class LandingPage extends Utilities {

    @FindBy(css = "[class=\"card mt-4 top-card\"]")
    public List<Card> cards;

    public List<WebElement> cardBodies(){
        List<WebElement> cardBodies = new ArrayList<>();
        for (Card card: cards){
            cardBodies.add(card.getCardBody());
        }
        return cardBodies;
    }

}
