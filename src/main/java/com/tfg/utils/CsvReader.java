package com.tfg.utils;

import com.tfg.models.Csv;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvReader {

    private CSVReader reader;

    public CsvReader(File file) throws FileNotFoundException {
        this.reader = new CSVReader(new FileReader(file));
    }

    public Csv convertFileToCsv(){
        String[] next;
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        int i = 0;
        try {
            while ((next = reader.readNext()) != null) {
                list.add(new ArrayList<>());
                for(String cell : next){
                    list.get(i).add(cell);
                }
                i+=1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return convertArrayListCsvObject(list);
    }

    private Csv convertArrayListCsvObject(ArrayList<ArrayList<String>> list) {
        int length = list.get(0).size();
        String[] headers = new String[length];
        for(int j = 0, i = 0; j < length; j++){
            headers[j] = list.get(i).get(j);
        }
        list.remove(0);
        String[][] lines = list.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);

        return new Csv(headers, lines);
    }

}
