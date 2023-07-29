package com.ssafy.trip.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommerceCSVLoader {

    private String filePath;
    private BufferedReader bufferedReader;
    private List<String[]> commerces;

    public List<String[]> getCommerces() {
        return commerces;
    }

    public void setCommerces(List<String[]> commerces) {
        this.commerces = commerces;
    }

    private int index;

    public CommerceCSVLoader() throws IOException {
        this.filePath = "res/경기도상권.csv";
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), "UTF-8"));
        commerces = new ArrayList<>();

        makeList(bufferedReader);
        this.index = 0;
    }

    private void makeList(BufferedReader bufferedReader) throws IOException {
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] lineContents = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            commerces.add(lineContents);
        }
    }
}