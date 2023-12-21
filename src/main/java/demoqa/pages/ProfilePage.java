package demoqa.pages;

import bookstore.models.Book;
import demoqa.pages.components.BookDescriptionRow;
import demoqa.pages.components.ProfileInventoryRow;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReflectionUtilities;
import utils.Utilities;
import java.util.*;
import java.util.stream.Collectors;

public class ProfilePage extends Utilities {
    @FindBy(css = ".rt-tr-group")
    public List<ProfileInventoryRow> inventoryRows;

    @FindBy(css = ".select-wrap.-pageSizeOptions select")
    public WebElement rowNumberContainer;

    @FindBy(css = ".select-wrap.-pageSizeOptions option")
    public List<WebElement> rowNumbers;

    @FindBy(css = ".rt-resizable-header-content")
    public List<WebElement> gridCellTitles;

    @FindBy(css = ".profile-wrapper .mt-2.row")
    public List<BookDescriptionRow> bookDescriptionRows;

    @FindBy(css = "#addNewRecordButton")
    public WebElement backToBookStoreButton;

    public String getValue(BookDescriptionRow.Specification specification){
        return bookDescriptionRows
                .stream()
                .filter(
                        bookDescriptionRow -> bookDescriptionRow
                                .getLabel()
                                .equals(specification.getWebKeyName())
                )
                .findAny()
                .orElseThrow()
                .getValue();
    }

    public Map<BookDescriptionRow.Specification, String> getSpecifications(){
        Map<BookDescriptionRow.Specification, String> specificationMap = new HashMap<>();
        for (BookDescriptionRow bookDescriptionRow : bookDescriptionRows){
            for (BookDescriptionRow.Specification specification : BookDescriptionRow.Specification.values()){
                if (bookDescriptionRow.getLabel().equals(specification.getWebKeyName())){
                    specificationMap.put(specification, bookDescriptionRow.getValue());
                    break;
                }
            }
        }
        return specificationMap;
    }

    public Map<BookDescriptionRow.Specification, String> getSpecifications2(){
         return Arrays.stream(BookDescriptionRow.Specification.values())
                 .collect(Collectors.toMap(
                         specification -> specification,
                         specification -> bookDescriptionRows.stream()
                                 .filter(bookDescriptionRow -> bookDescriptionRow.getLabel().equals(specification.getWebKeyName()))
                                 .findAny()
                                 .orElseThrow()
                                 .getValue()
                 ));
    }

    public void bookVerification(Book book){
        elementClick(getBookLinkFor(book.getTitle()), true);
        for (BookDescriptionRow.Specification specification : BookDescriptionRow.Specification.values()){
            String expectedValue = String.valueOf(ReflectionUtilities.getField(specification.name(), book)).trim();
            String actualValue = getValue(specification).trim();
            Assert.assertEquals("Specifications do not match!\nExpected : " + expectedValue + "\nActual : " + actualValue,
                    expectedValue, actualValue
            );
            log.success(specification.name() + " of the book is verified as: " + actualValue);
        }
        elementClick(backToBookStoreButton, true);
    }

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

    public WebElement getBookLinkFor(String bookTitle){
        return inventoryRows
                .stream()
                .filter(inventoryRow -> inventoryRow.getBookLink().getText().equals(bookTitle))
                .findFirst()
                .orElseThrow()
                .getBookLink();
    }

}
