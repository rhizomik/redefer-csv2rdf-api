package com.tfg.utils;

import com.tfg.models.Csv;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static CSVReader reader;

    public CsvReader() {
    }

    public static Csv convertFileToCsv(File file) throws IOException {
        reader = new CSVReader(new FileReader(file));
        List<String[]> list = reader.readAll();
        int numLines = (int) reader.getLinesRead();
        String [] headers = new String[list.get(0).length];
        String [][] lines = new String[numLines - 1][headers.length]; //calculate array size

        // read headers
        setHeadersArray(list, headers);

        // read lines
        setLinesArray(list, lines);

        return new Csv(headers, lines);
    }

    private static void setHeadersArray(List<String[]> list, String[] headers) {
        int j = 0;
        for(String cell : list.get(0)) {
            headers[j] = cell;
            j += 1;
        }
    }

    private static void setLinesArray(List<String[]> list, String[][] lines) {
        int j, k = 0;
        for(int i = 1; i < list.size(); i++) {
            String[] row = list.get(i);
            j = 0;
            for (String cell : row) {
                lines[k][j] = cell;
                j += 1;
            }
            k+=1;
        }
    }
}
