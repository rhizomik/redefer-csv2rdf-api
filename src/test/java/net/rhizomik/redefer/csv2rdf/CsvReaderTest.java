package net.rhizomik.redefer.csv2rdf;

import net.rhizomik.redefer.csv2rdf.models.Csv;
import net.rhizomik.redefer.csv2rdf.utils.CsvReader;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class CsvReaderTest {

    File file = new File("./src/test/java/resources/test/hurricanes.csv");

    CsvReader reader = new CsvReader();

    public CsvReaderTest() throws FileNotFoundException {
    }

    @Test
    public void convertFileToCsv() throws Exception {
        Csv csv = reader.convertFileToCsv(file);
        assertEquals(13, csv.headers.length);
        assertEquals(8, csv.lines.length);
    }
}
