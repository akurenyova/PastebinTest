package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CreatePastePastebinPage;
import page.DetailsPastePastebinPage;


public class PastebinTest {

        private WebDriver driver;

        @BeforeMethod (alwaysRun = true)
        public void browserSetup() {
            driver = new ChromeDriver();
        }

        @DataProvider(name = "valuesPastebinTest")
        public Object[][] valuesForPastebinTest() {
            return new Object[][]{
                    {"Hello from WebDriver",
                            "None",
                            "10 Minutes",
                            "helloweb"},
                    {"git config --global user.name  \"New Sheriff in Town\"\n" +
                            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                            "git push origin master --force",
                            "Bash",
                            "10 Minutes",
                            "how to gain dominance among developers"}
            };
         }

        @Test (dataProvider = "valuesPastebinTest")
        public void createPastePastebinTest(String pasteText, String pasteSyntax, String pasteExpiraton, String pasteName) {

            DetailsPastePastebinPage detailsPastePastebinPage = new CreatePastePastebinPage(driver)
                    .openPage()
                    .inputValuesForPaste(pasteText, pasteSyntax, pasteExpiraton, pasteName)
                    .createNewPaste();

            Assert.assertTrue(detailsPastePastebinPage.pasteNameLabelText().contains(pasteName),
                    "Name is incorrect");
            Assert.assertTrue(detailsPastePastebinPage.pasteDataTextareaText().contains(pasteText),
                    "Text is incorrect");
            Assert.assertTrue(detailsPastePastebinPage.pasteSyntaxLabelText().contains(pasteSyntax),
                    "Syntax is incorrect");
        }

        @AfterMethod (alwaysRun = true)
        public void browserTearDown() {
            driver.quit();
            driver = null;
        }

    }
