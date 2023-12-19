package demoqa.pages;

import demoqa.pages.components.BookDescriptionRow;
import demoqa.pages.components.ProfileInventoryRow;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends Utilities {
    @FindBy(css = ".rt-tr-group")
    public List<ProfileInventoryRow> inventoryRows;

    @FindBy(css = ".rt-resizable-header-content")
    public List<WebElement> gridCellTitles;

    @FindBy(css = ".profile-wrapper .mt-2.row")
    public List<BookDescriptionRow> bookDescriptionRows;

    @FindBy(css = "#addNewRecordButton")
    public WebElement backToBookStoreButton;

    public WebElement getBookLink(){return gridCellTitles.get(1);}

    public String getAuthor(){return gridCellTitles.get(2).getText();}

    public String getPublisher(){return gridCellTitles.get(3).getText();}

    public List<String> getAuthorNames(){
        List<String> authorNames = new ArrayList<>();
        for (ProfileInventoryRow inventoryRow : inventoryRows){
            String authorName = inventoryRow.getGridCells().get(2).getText();
            if (!authorName.equals(" ")){
                authorNames.add(authorName);
            }
        }
        return authorNames;
    }

    public BookDescriptionRow getBookDescriptionRows(String label){
        for (BookDescriptionRow row : bookDescriptionRows)
            if (row.getLabel().equals(label)) return row;
        throw new RuntimeException("Row not found");
    }

    public List<String> getLabels(){
        List<String> labels = new ArrayList<>();
        for (BookDescriptionRow row : bookDescriptionRows)
            labels.add(row.getLabel());
        return labels;
    }

}
