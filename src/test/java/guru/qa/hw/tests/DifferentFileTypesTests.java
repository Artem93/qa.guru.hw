package guru.qa.hw.tests;

import guru.qa.hw.utils.FilesDataGetter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DifferentFileTypesTests {
    private final String zipName = "sample.zip";

    @CsvSource(value = {
            "PDF BOOKMARK SAMPLE",
            "The example bookmark file includes three distinct sections:",
    })
    @ParameterizedTest(name = "Проверка, что файле есть текст {0}")
    void checkDataFromPdf(String text) throws Exception {
        String pdfFileName = "sample.pdf";
        Assertions.assertTrue(
                new FilesDataGetter().getTextFromPdf(zipName, pdfFileName).contains(text));
    }

    @CsvSource(value = {
            "0, 4, 2, loss",
            "0, 1, 1, inspector",
    })
    @ParameterizedTest(name = "Проверка, что файле на листе {0} в строчке {1} и столбце {2} содержится текст {3}")
    void checkDataFromXlsx(int sheet, int row, int column, String text) throws Exception {
        String xlsxFileName = "sample.xlsx";
        Assertions.assertTrue(
                new FilesDataGetter().getTextFromXlsx(zipName, xlsxFileName, sheet, row, column).contains(text));
    }

    @CsvSource(value = {
            "0, 1, Doe",
            "3, 2, 7452 Terrace \"At the Plaza\" road",
    })
    @ParameterizedTest(name = "Проверка, что файле в строчке {0} и столбце {1} содержится текст {2}")
    void checkDataFromCsv(int row, int column, String text) throws Exception {
        String csvFileName = "sample.csv";
        Assertions.assertTrue(
                new FilesDataGetter().getTextFromCsv(zipName, csvFileName, row, column).contains(text));
    }


    @Test
    @DisplayName("Проверка информации в json")
    void checkJsonFile() throws Exception {
        var xMan = new FilesDataGetter().getXManJson();
        Assertions.assertEquals(xMan.getAge(), 29);
        Assertions.assertEquals(xMan.getName(), "Molecule Man");
        Assertions.assertEquals(xMan.getSecretIdentity(), "Dan Jukes");
        Assertions.assertEquals(xMan.getPowers().getFirst(), "Radiation resistance");
        Assertions.assertEquals(xMan.getPowers().getSecond(), "Turning tiny");
    }
}
