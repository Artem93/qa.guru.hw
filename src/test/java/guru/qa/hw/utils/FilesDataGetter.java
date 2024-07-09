package guru.qa.hw.utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import guru.qa.hw.models.ExampleJson;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesDataGetter {
    private final ClassLoader cl = FilesDataGetter.class.getClassLoader();

    public String getTextFromPdf(String zipName, String fileName) throws Exception {
        var unzippedFile = unzipFile(zipName, fileName);
        return getTextFromPdf(unzippedFile);
    }

    String getTextFromPdf(File file) throws IOException {
        PDF pdf = new PDF(file);
        return pdf.text;
    }

    public String getTextFromXlsx(String zipName, String fileName, int sheet, int row, int column) throws Exception {
        var unzippedFile = unzipFile(zipName, fileName);
        return getTextFromXlsx(unzippedFile, sheet, row, column);
    }

    String getTextFromXlsx(File file, int sheet, int row, int column) {
        XLS xls = new XLS(file);
        return xls.excel.getSheetAt(sheet).getRow(row).getCell(column).getStringCellValue();
    }

    public String getTextFromCsv(String zipName, String fileName, int row, int column) throws Exception {
        var unzippedFile = unzipFile(zipName, fileName);
        return getTextFromCsv(unzippedFile, row, column);
    }

    String getTextFromCsv(File file, int row, int column) throws Exception {
        try (CSVReader cr = new CSVReader(new InputStreamReader(new FileInputStream(file)))) {
            List<String[]> lines = cr.readAll();
            return lines.get(row)[column];
        }
    }

    public ExampleJson getXManJson() throws Exception {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(cl.getResourceAsStream("xMan.json")))) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, ExampleJson.class);
        }
    }

    private File unzipFile(String zipName, String fileName) throws Exception {
        File tempFile = File.createTempFile("unzipped_", fileName);
        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream(zipName)))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals(fileName)) {
                    try (FileOutputStream file = new FileOutputStream(tempFile)) {
                        file.write(zis.readAllBytes());
                    }
                }
            }
        }
        return tempFile;
    }
}
