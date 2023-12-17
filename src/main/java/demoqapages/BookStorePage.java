package demoqapages;

import demoqapages.components.ElementGroup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class BookStorePage extends Utilities {

    @FindBy(css = ".element-group")
    public List<ElementGroup> elementGroups;

    public List<WebElement> elementGroupButtons(){
        List<WebElement> elementGroupButtons = new ArrayList<>();
        for (ElementGroup elementGroup : elementGroups){
            elementGroupButtons.add(elementGroup.getElementGroupButton());
        }
        return elementGroupButtons;
    }

}
