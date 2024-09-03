package guru.qa.hw.tests.demoqa;

import guru.qa.hw.helpers.WithLogin;
import guru.qa.hw.pages.ProfilePage;
import guru.qa.hw.steps.BooksApiSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.hw.tests.demoqa.TestData.testIsbn;

public class DemoQaBookTests extends DemoQaTestBase {
    @Tag("hw18")
    @DisplayName("Удаление книги из списка в профиле")
    @WithLogin
    @Test
    void removeBookByUITest() {
        ProfilePage profilePage = new ProfilePage();
        BooksApiSteps booksSteps = new BooksApiSteps();

        booksSteps
                .removeAllBooks()
                .addBooks(testIsbn);
        profilePage
                .openPage()
                .clickOnDeleteFirstIcoInList()
                .pressOkButtonInModal()
                .acceptSystemWindow()
                .checkBooksRemoved(testIsbn);
    }
}
