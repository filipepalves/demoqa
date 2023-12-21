package test.java.com.demoqa;

import main.java.base.TabSwitcher;
import main.java.base.TableVerifier;
import main.java.base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ElementsTest extends TestUtilities {


    @Test
    public void elementstest() {

        log.info("Starting Elements Test");

        String url = "https://demoqa.com/";
        driver.get(url);

        // Click on elements button and verify all the elements
        By elementsclick = By.cssSelector(".category-cards [class='card mt-4 top-card']:nth-of-type(1)");
        waitUtility.waitAndClick(elementsclick);

        String title = driver.findElement(By.cssSelector(".main-header")).getText();
        Assert.assertEquals(title, "Elements");

        String pleaseselect = driver.findElement(By.cssSelector(".col-12.mt-4.col-md-6")).getText();
        Assert.assertEquals(pleaseselect, "Please select an item from left to start practice.");

        WebElement textboxbutton = driver.findElement(By.id("item-0"));
        String textboxbuttonText = textboxbutton.getText();
        Assert.assertEquals(textboxbuttonText, "Text Box");

        WebElement checkboxbutton = driver.findElement(By.id("item-1"));
        String checkboxbuttonText = checkboxbutton.getText();
        Assert.assertEquals(checkboxbuttonText, "Check Box");

        WebElement radiobuttonbutton = driver.findElement(By.id("item-2"));
        String radiobuttonbuttonText = radiobuttonbutton.getText();
        Assert.assertEquals(radiobuttonbuttonText, "Radio Button");

        WebElement webtablebutton = driver.findElement(By.id("item-3"));
        String webtablebuttonText = webtablebutton.getText();
        Assert.assertEquals(webtablebuttonText, "Web Tables");

        WebElement buttonsbutton = driver.findElement(By.id("item-4"));
        String buttonsbuttonText = buttonsbutton.getText();
        Assert.assertEquals(buttonsbuttonText, "Buttons");

        WebElement linksbutton = driver.findElement(By.id("item-5"));
        String linksbuttonText = linksbutton.getText();
        Assert.assertEquals(linksbuttonText, "Links");

        WebElement brokenlinksbutton = driver.findElement(By.id("item-6"));
        String brokenlinksbuttonText = brokenlinksbutton.getText();
        Assert.assertEquals(brokenlinksbuttonText, "Broken Links - Images");

        WebElement uploadanddownloadbutton = driver.findElement(By.id("item-7"));
        String uploadanddownloadbuttonText = uploadanddownloadbutton.getText();
        Assert.assertEquals(uploadanddownloadbuttonText, "Upload and Download");

        WebElement dynamicpropertiesbutton = driver.findElement(By.id("item-8"));
        String dynamicpropertiesbuttonText = dynamicpropertiesbutton.getText();
        Assert.assertEquals(dynamicpropertiesbuttonText, "Dynamic Properties");

        log.info("All the labels are verified. Ending Elements Test");

    }

    @Test(dependsOnMethods = "elementstest")
    public void textboxtest() {

        log.info("Starting Textbox Test");

        // Click on Text Box button and verify all the elements

        By textboxclick = By.id("item-0");
        waitUtility.waitAndClick(textboxclick);

        String title = driver.findElement(By.cssSelector(".main-header")).getText();
        Assert.assertEquals(title, "Text Box");

        String fullname = driver.findElement(By.id("userName-label")).getText();
        Assert.assertEquals(fullname, "Full Name");

        WebElement fullnametextbox = driver.findElement(By.id("userName"));
        String fullnametextboxText = fullnametextbox.getAttribute("placeholder");
        Assert.assertEquals(fullnametextboxText, "Full Name");

        String email = driver.findElement(By.id("userEmail-label")).getText();
        Assert.assertEquals(email, "Email");

        WebElement emailtextbox = driver.findElement(By.id("userEmail"));
        String emailtextboxText = emailtextbox.getAttribute("placeholder");
        Assert.assertEquals(emailtextboxText, "name@example.com");

        String currentaddress = driver.findElement(By.id("currentAddress-label")).getText();
        Assert.assertEquals(currentaddress, "Current Address");

        WebElement currentaddresstextbox = driver.findElement(By.id("currentAddress"));
        String currentaddresstextboxText = currentaddresstextbox.getAttribute("placeholder");
        Assert.assertEquals(currentaddresstextboxText, "Current Address");

        String permanentaddress = driver.findElement(By.id("permanentAddress-label")).getText();
        Assert.assertEquals(permanentaddress, "Permanent Address");

        WebElement permanentaddresstextbox = driver.findElement(By.id("permanentAddress"));
        String permanentaddresstextboxText = permanentaddresstextbox.getAttribute("placeholder");
        Assert.assertEquals(permanentaddresstextboxText, "");

        log.info("All the labels are verified.");

        // Send keys to each element and verify the results

        fullnametextbox.sendKeys("Test Name");
        emailtextbox.sendKeys("Test Email");
        currentaddresstextbox.sendKeys("Test Current Address");
        permanentaddresstextbox.sendKeys("Test Permanent Address");

        String fullnamevalue = fullnametextbox.getAttribute("value");
        Assert.assertEquals(fullnamevalue, "Test Name");

        String emailvalue = emailtextbox.getAttribute("value");
        Assert.assertEquals(emailvalue, "Test Email");

        String currentaddressvalue = currentaddresstextbox.getAttribute("value");
        Assert.assertEquals(currentaddressvalue, "Test Current Address");

        String permanentaddressvalue = permanentaddresstextbox.getAttribute("value");
        Assert.assertEquals(permanentaddressvalue, "Test Permanent Address");

        log.info("All the values typed are verified and the same as expected.");

        // Clear all the values and verify the results

        fullnametextbox.clear();
        emailtextbox.clear();
        currentaddresstextbox.clear();
        permanentaddresstextbox.clear();

        fullnamevalue = fullnametextbox.getAttribute("value");
        Assert.assertEquals(fullnamevalue, "");

        emailvalue = emailtextbox.getAttribute("value");
        Assert.assertEquals(emailvalue, "");

        currentaddressvalue = currentaddresstextbox.getAttribute("value");
        Assert.assertEquals(currentaddressvalue, "");

        permanentaddressvalue = permanentaddresstextbox.getAttribute("value");
        Assert.assertEquals(permanentaddressvalue, "");

        log.info("All values are cleared.");

        // Click on submit button and verify if nothing is displayed

        WebElement submitbutton = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].click();", submitbutton);

        log.info("Submit button is clicked. Nothing is displayed.");

        // Type all the value again and click on submit button

        fullnametextbox.sendKeys("Test Name");
        emailtextbox.sendKeys("Test Email");
        currentaddresstextbox.sendKeys("Test Current Address");
        permanentaddresstextbox.sendKeys("Test Permanent Address");

        js.executeScript("arguments[0].click();", submitbutton);

        String output = driver.findElement(By.id("output")).getText();
        Assert.assertEquals(output, "");

        emailtextbox.clear();
        emailtextbox.sendKeys("email@example.com");
        js.executeScript("arguments[0].click();", submitbutton);

        output = driver.findElement(By.id("output")).getText();
        Assert.assertEquals(output, """
                Name:Test Name
                Email:email@example.com
                Current Address :Test Current Address
                Permananet Address :Test Permanent Address""");

        log.info("All the values are typed and submit button is clicked. The output is verified and it's as expected.");
    }

    @Test(dependsOnMethods = "textboxtest")
    public void checkboxtest() {

        log.info("Starting Checkbox Test");

        // Click on Check Box button, click on each dropdown and verify all the elements

        By checkboxclick = By.id("item-1");
        String checkboxclickText = driver.findElement(checkboxclick).getText();
        Assert.assertEquals(checkboxclickText, "Check Box");
        waitUtility.waitAndClick(checkboxclick);

        WebElement expandAllButton = driver.findElement(By.cssSelector(".rct-option-expand-all"));
        expandAllButton.click();

        // Find and click specific checkboxes by their IDs

        WebElement notesCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Notes')]"));
        notesCheckbox.click();

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You have selected :\n" +
                "notes");

        WebElement commandsCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Commands')]"));
        commandsCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                desktop
                notes
                commands""");

        WebElement desktopCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Desktop')]"));
        desktopCheckbox.click();

        WebElement reactCheckbox = driver.findElement(By.xpath("//span[contains(text(),'React')]"));
        reactCheckbox.click();

        WebElement angularCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Angular')]"));
        angularCheckbox.click();

        WebElement veuCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Veu')]"));
        veuCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                workspace
                react
                angular
                veu""");

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement workspaceCheckbox = driver.findElement(By.xpath("//span[contains(text(),'WorkSpace')]"));
        workspaceCheckbox.click();

        WebElement privateCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Private')]"));
        privateCheckbox.click();

        WebElement publicCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Public')]"));
        publicCheckbox.click();

        WebElement classifiedCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Classified')]"));
        classifiedCheckbox.click();

        WebElement generalCheckbox = driver.findElement(By.xpath("//span[contains(text(),'General')]"));
        generalCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                office
                public
                private
                classified
                general""");

        WebElement officeCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Office')]"));
        officeCheckbox.click();

        WebElement wordfileCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Word File.doc')]"));
        wordfileCheckbox.click();

        WebElement excelfileCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Excel File.doc')]"));
        excelfileCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                downloads
                wordFile
                excelFile""");

        WebElement homeCheckbox = driver.findElement(By.xpath("//span[contains(text(),'Home')]"));
        homeCheckbox.click();

        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, """
                You have selected :
                home
                desktop
                notes
                commands
                documents
                workspace
                react
                angular
                veu
                office
                public
                private
                classified
                general
                downloads
                wordFile
                excelFile""");

        log.info("All the checkboxes are working as expected and the feedback is displayed correctly.");

        // Click on some dropdown buttons

        WebElement dropdowndesktop = driver.findElement(By.xpath("(//button[@title='Toggle'])[2]"));
        dropdowndesktop.click();

        WebElement dropdowndocuments = driver.findElement(By.xpath("(//button[@title='Toggle'])[3]"));
        dropdowndocuments.click();

        WebElement dropdownworkspace = driver.findElement(By.xpath("(//button[@title='Toggle'])[4]"));
        dropdownworkspace.click();

    }

    @Test(dependsOnMethods = "checkboxtest")
    public void radiobuttontest() {

        log.info("Starting Radio Button Test");

        // Click on Radio button and verify all the elements

        WebElement radiobuttonbutton = driver.findElement(By.id("item-2"));
        String radiobuttonbuttonText = radiobuttonbutton.getText();
        Assert.assertEquals(radiobuttonbuttonText, "Radio Button");
        radiobuttonbutton.click();

        String title = driver.findElement(By.cssSelector(".mb-3")).getText();
        Assert.assertEquals(title, "Do you like the site?");

        String yes = driver.findElement(By.cssSelector("label[for='yesRadio']")).getText();
        Assert.assertEquals(yes, "Yes");

        String impressive = driver.findElement(By.cssSelector("label[for='impressiveRadio']")).getText();
        Assert.assertEquals(impressive, "Impressive");

        String no = driver.findElement(By.cssSelector("label[for='noRadio']")).getText();
        Assert.assertEquals(no, "No");

        // Click on each radio button and verify the feedback

        By yesRadio = By.cssSelector("label[for='yesRadio']");
        waitUtility.waitAndClick(yesRadio);

        String feedback = driver.findElement(By.cssSelector(".mt-3")).getText();
        Assert.assertEquals(feedback, "You have selected Yes");

        WebElement impressiveRadio = driver.findElement(By.cssSelector("label[for='impressiveRadio']"));
        impressiveRadio.click();

        feedback = driver.findElement(By.cssSelector(".mt-3")).getText();
        Assert.assertEquals(feedback, "You have selected Impressive");

        WebElement noRadio = driver.findElement(By.id("noRadio"));

        boolean isClickable = noRadio.isEnabled();

        if (!isClickable) {
            log.info("The element is not clickable.");
        } else {
            log.info("The element is clickable.");
        }

        feedback = driver.findElement(By.cssSelector(".mt-3")).getText();
        Assert.assertEquals(feedback, "You have selected Impressive");

    }

    @Test(dependsOnMethods = "radiobuttontest")
    public void webtables() throws InterruptedException {

        WaitUtility waitUtility = new WaitUtility(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        log.info("Starting Web Tables Test");

        WebElement webtablebutton = driver.findElement(By.id("item-3"));
        String webtablebuttonText = webtablebutton.getText();
        Assert.assertEquals(webtablebuttonText, "Web Tables");
        webtablebutton.click();

        WebElement addbutton = driver.findElement(By.id("addNewRecordButton"));
        addbutton.isDisplayed();

        WebElement textbox = driver.findElement(By.id("searchBox"));
        String searchboxText = textbox.getAttribute("placeholder");
        Assert.assertEquals(searchboxText, "Type to search");

        WebElement firstname = driver.findElement(By.xpath("//div[@class='rt-resizable-header-content' and text()='First Name']"));
        String firstnameText = firstname.getText();
        Assert.assertEquals(firstnameText, "First Name");

        WebElement lastname = driver.findElement(By.xpath("//div[@class='rt-resizable-header-content' and text()='Last Name']"));
        String lastnameText = lastname.getText();
        Assert.assertEquals(lastnameText, "Last Name");

        WebElement age = driver.findElement(By.xpath("//div[@class='rt-resizable-header-content' and text()='Age']"));
        String ageText = age.getText();
        Assert.assertEquals(ageText, "Age");

        WebElement email = driver.findElement(By.xpath("//div[@class='rt-resizable-header-content' and text()='Email']"));
        String emailText = email.getText();
        Assert.assertEquals(emailText, "Email");

        WebElement salary = driver.findElement(By.xpath("//div[@class='rt-resizable-header-content' and text()='Salary']"));
        String salaryText = salary.getText();
        Assert.assertEquals(salaryText, "Salary");

        WebElement department = driver.findElement(By.xpath("//div[@class='rt-resizable-header-content' and text()='Department']"));
        String departmentText = department.getText();
        Assert.assertEquals(departmentText, "Department");

        WebElement action = driver.findElement(By.xpath("//div[@class='rt-resizable-header-content' and text()='Action']"));
        String actionText = action.getText();
        Assert.assertEquals(actionText, "Action");

        log.info("All the labels are correctly displayed");

        // Verify all the headers and order

        WebElement table = driver.findElement(By.xpath("//div[@class='rt-table']"));
        List<WebElement> tableRows = table.findElements(By.xpath(".//div[@role='row' and contains(@class, 'rt-tr')]"));
        WebElement tableHeader = driver.findElement(By.xpath("//div[@class='rt-thead -header']"));
        List<WebElement> headers = tableHeader.findElements(By.xpath(".//div[@role='columnheader' and contains(@class, 'rt-th')]"));
        List<String> headerTexts = headers.stream().map(WebElement::getText).toList();

        log.info("Table Headers: " + headerTexts);

        TableVerifier.verifyColumnSorting(headers.get(0), tableRows, 1);

        TableVerifier.verifyColumnSorting(headers.get(1), tableRows, 2);

        TableVerifier.verifyColumnSorting(headers.get(3), tableRows, 4);

        TableVerifier.verifyColumnSorting(headers.get(4), tableRows, 5);

        log.info("All the order buttons are working correctly");

        WebElement previousbutton = driver.findElement(By.cssSelector(".-previous"));
        String previous = previousbutton.getText();
        Assert.assertEquals(previous, "Previous");

        WebElement nextbutton = driver.findElement(By.cssSelector(".-next"));
        String next = nextbutton.getText();
        Assert.assertEquals(next, "Next");

        // Verify the edit one row

        WebElement editButton = driver.findElement(By.id("edit-record-1"));
        editButton.click();

        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        WebElement ageInput = driver.findElement(By.id("age"));
        WebElement salaryInput = driver.findElement(By.id("salary"));
        WebElement departmentInput = driver.findElement(By.id("department"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        firstNameInput.clear();
        firstNameInput.sendKeys("John");
        lastNameInput.clear();
        lastNameInput.sendKeys("Doe");
        emailInput.clear();
        emailInput.sendKeys("L5kzA@example.com");
        ageInput.clear();
        ageInput.sendKeys("25");
        salaryInput.clear();
        salaryInput.sendKeys("5000");
        departmentInput.clear();
        departmentInput.sendKeys("QA");
        submitButton.click();

        String rowtable = driver.findElement(By.xpath("(//div[@role='rowgroup'])[2]")).getText();
        Assert.assertEquals(rowtable, "John\nDoe\n25\nL5kzA@example.com\n5000\nQA");

        log.info("Edit button works correctly");

        // Verify the dropdown option to select the number of rows to display

        WebElement dropdownElement = driver.findElement(By.cssSelector("span.select-wrap.-pageSizeOptions > select[aria-label='rows per page']"));

        Select dropdown = new Select(dropdownElement);

        boolean isMultiple = dropdown.isMultiple();
        log.info("Is the dropdown allowing multiple selection? " + isMultiple);

        List<WebElement> options = dropdown.getOptions();

        StringBuilder optionsText = new StringBuilder("Available options in the dropdown: ");
        for (WebElement option : options) {
            optionsText.append(option.getText()).append(", ");
        }

        log.info(optionsText.toString());
        dropdown.selectByVisibleText("5 rows");

        WebElement selectedOption = dropdown.getFirstSelectedOption();
        table = driver.findElement(By.xpath("//div[@class='rt-tbody']"));
        tableRows = table.findElements(By.xpath(".//div[@role='row' and contains(@class, 'rt-tr')]"));
        int rowsnumber = tableRows.size();
        Assert.assertEquals(rowsnumber, 5);
        log.info("The selected option is: " + selectedOption.getText() + " And the table has: " + rowsnumber + " rows");

        // Add some rows to the table

        for (int i = 1; i <= 90; i++) {

            addbutton.click();

            firstNameInput = driver.findElement(By.id("firstName"));
            lastNameInput = driver.findElement(By.id("lastName"));
            emailInput = driver.findElement(By.id("userEmail"));
            ageInput = driver.findElement(By.id("age"));
            salaryInput = driver.findElement(By.id("salary"));
            departmentInput = driver.findElement(By.id("department"));
            submitButton = driver.findElement(By.id("submit"));

            firstNameInput.sendKeys("Filipe" + i);
            lastNameInput.sendKeys("Alves" + i);
            emailInput.sendKeys("filipepretoalves" + i + "@gmail.com");
            ageInput.sendKeys("36");
            salaryInput.sendKeys("30000");
            departmentInput.sendKeys("QA Engineer");
            submitButton.click();

        }

        // Verify the number of rows in the table

        dropdown.selectByVisibleText("100 rows");

        table = driver.findElement(By.xpath("//div[@class='rt-tbody']"));
        tableRows = table.findElements(By.xpath(".//div[@role='row' and contains(@class, 'rt-tr')]"));
        List<WebElement> nonEmptyRows = new ArrayList<>();
        for (WebElement row : tableRows) {
            String rowText = row.getText().trim();
            if (!rowText.isEmpty()) {
                nonEmptyRows.add(row);
            }
        }
        rowsnumber = nonEmptyRows.size();
        Assert.assertEquals(rowsnumber, 93, "The table should have 93 rows, but it has: " + rowsnumber + " rows");

        log.info("The table has: " + rowsnumber + " rows. Were added 90 rows successfully.");

        //Verify the delete button

        String rowtablePre = driver.findElement(By.xpath("(//div[@role='rowgroup'])[1]")).getText();

        WebElement deleteButton = driver.findElement(By.id("delete-record-3"));
        deleteButton.click();

        String rowtablePost = driver.findElement(By.xpath("(//div[@role='rowgroup'])[1]")).getText();

        Assert.assertNotEquals(rowtablePre, rowtablePost);

        log.info("Delete button is working correctly. The 1st row was deleted.");

        // Verify the pagination

        dropdown.selectByVisibleText("5 rows");

        String pagination = driver.findElement(By.cssSelector("[aria-label='jump to page']")).getAttribute("value");
        Assert.assertEquals(pagination, "1");

        nextbutton.click();

        pagination = driver.findElement(By.cssSelector("[aria-label='jump to page']")).getAttribute("value");
        Assert.assertEquals(pagination, "2");

        nextbutton.click();

        pagination = driver.findElement(By.cssSelector("[aria-label='jump to page']")).getAttribute("value");
        Assert.assertEquals(pagination, "3");

        previousbutton.click();

        pagination = driver.findElement(By.cssSelector("[aria-label='jump to page']")).getAttribute("value");
        Assert.assertEquals(pagination, "2");

        log.info("The pagination works as expected after clicking next and previous buttons.");

    }

    @Test(dependsOnMethods = "webtables")
    public void buttons() throws InterruptedException {

        log.info("Starting Buttons Test");

        // Click on Buttons button and verify all the elements

        WebElement buttons = driver.findElement(By.id("item-4"));
        String buttonsText = buttons.getText();
        Assert.assertEquals(buttonsText, "Buttons");
        buttons.click();

        WebElement doubleclick = driver.findElement(By.id("doubleClickBtn"));
        String doubleclickText = doubleclick.getText();
        Assert.assertEquals(doubleclickText, "Double Click Me");


        WebElement rightclick = driver.findElement(By.id("rightClickBtn"));
        String rightclickText = rightclick.getText();
        Assert.assertEquals(rightclickText, "Right Click Me");

        WebElement click = driver.findElement(By.xpath("//button[@class='btn btn-primary' and text()='Click Me']\n"));
        String clickText = click.getText();
        Assert.assertEquals(clickText, "Click Me");

        log.info("All the buttons are displayed.");

        // Click on each button and verify the feedback

        actions.doubleClick(doubleclick).perform();
        String doubleclickfeedback = driver.findElement(By.id("doubleClickMessage")).getText();
        Assert.assertEquals(doubleclickfeedback, "You have done a double click");

        actions.contextClick(rightclick).perform();
        String rightclickfeedback = driver.findElement(By.id("rightClickMessage")).getText();
        Assert.assertEquals(rightclickfeedback, "You have done a right click");

        click.click();
        String clickfeedback = driver.findElement(By.id("dynamicClickMessage")).getText();
        Assert.assertEquals(clickfeedback, "You have done a dynamic click");

        log.info("All the buttons are working correctly.");

    }

    @Test(dependsOnMethods = "buttons")
    public void linkstest() throws InterruptedException {

        String firstWindowHandle = driver.getWindowHandle();
        tabSwitcher = new TabSwitcher(driver);


        log.info("Starting Links Test");

        // Click on Buttons button and verify all the elements

        js.executeScript("window.scrollBy(0, 300)");
        WebElement links = driver.findElement(By.id("item-5"));
        String linksText = links.getText();
        Assert.assertEquals(linksText, "Links");
        links.click();

        WebElement simplelink = driver.findElement(By.id("simpleLink"));
        String simplelinkText = simplelink.getText();
        Assert.assertEquals(simplelinkText, "Home");

        WebElement dynamiclink = driver.findElement(By.id("dynamicLink"));
        String dynamiclinkText = dynamiclink.getText();
        Assert.assertTrue(dynamiclinkText.contains("Home"));

        WebElement createdlink = driver.findElement(By.id("created"));
        String createdlinkText = createdlink.getText();
        Assert.assertEquals(createdlinkText, "Created");

        WebElement nocontent = driver.findElement(By.id("no-content"));
        String nocontentText = nocontent.getText();
        Assert.assertEquals(nocontentText, "No Content");

        WebElement moved = driver.findElement(By.id("moved"));
        String movedText = moved.getText();
        Assert.assertEquals(movedText, "Moved");

        WebElement badrequest = driver.findElement(By.id("bad-request"));
        String badrequestText = badrequest.getText();
        Assert.assertEquals(badrequestText, "Bad Request");


        WebElement unauthorized = driver.findElement(By.id("unauthorized"));
        String unauthorizedText = unauthorized.getText();
        Assert.assertEquals(unauthorizedText, "Unauthorized");

        WebElement forbidden = driver.findElement(By.id("forbidden"));
        String forbiddenText = forbidden.getText();
        Assert.assertEquals(forbiddenText, "Forbidden");

        WebElement notfound = driver.findElement(By.id("invalid-url"));
        String notfoundText = notfound.getText();
        Assert.assertEquals(notfoundText, "Not Found");

        log.info("All the links are as expected.");

        // Click on Home link

        simplelink.click();

        tabSwitcher.switchToNewTabAndAssertURL("https://demoqa.com/");

        // Click on Dynamic link

        dynamiclink.click();

        tabSwitcher.switchToNewTabAndAssertURL("https://demoqa.com/");

        // Click on Created link

        By waitforcreatedLink = By.id("created");
        waitUtility.waitAndClick(waitforcreatedLink);

        String createdlinkclicked = driver.findElement(By.id("linkResponse")).getText();
        Assert.assertEquals(createdlinkclicked, "Link has responded with staus 201 and status text Created");

        // Click on No Content link

        nocontent.click();

        String nocontentclicked = driver.findElement(By.id("linkResponse")).getText();
        Assert.assertEquals(nocontentclicked, "Link has responded with staus 204 and status text No Content");

        // Click on Moved link

        moved.click();

        String movedclicked = driver.findElement(By.id("linkResponse")).getText();
        Assert.assertEquals(movedclicked, "Link has responded with staus 301 and status text Moved Permanently");

        // Click on Bad Request link

        badrequest.click();

        String badrequestclicked = driver.findElement(By.id("linkResponse")).getText();
        Assert.assertEquals(badrequestclicked, "Link has responded with staus 400 and status text Bad Request");

        // Click on Unauthorized link

        unauthorized.click();

        String unauthorizedclicked = driver.findElement(By.id("linkResponse")).getText();
        Assert.assertEquals(unauthorizedclicked, "Link has responded with staus 401 and status text Unauthorized");

        // Click on Forbidden link

        forbidden.click();

        String forbiddenclicked = driver.findElement(By.id("linkResponse")).getText();
        Assert.assertEquals(forbiddenclicked, "Link has responded with staus 403 and status text Forbidden");

        // Click on Not Found link

        notfound.click();

        String notfoundclicked = driver.findElement(By.id("linkResponse")).getText();
        Assert.assertEquals(notfoundclicked, "Link has responded with staus 404 and status text Not Found");



    }


}

