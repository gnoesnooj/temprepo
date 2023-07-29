package com.ssafy.trip.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommerceCSVLoader {
    /*
     * 작성 날짜 : 2021-07-31
     * 작성자    : 고석준
     * 프로그램 요약 :  CSV 파일을 읽거나 쓸 수 있다. jdk 1.6에서는 openCSV 라이브러리가 작동하지 않았기 때문에 내가 스스로 작성하였다.
     * 기능 : 파일 읽어서 리스트로 변환*/

    private String filePath;
    private BufferedReader bufferedReader;
    private List<String[]> commerces; // list 각각 1줄씩 요소 + 배열 위치가 data 값, list get0 은 title

    public List<String[]> getCommerces() {
        return commerces;
    }

    public void setCommerces(List<String[]> commerces) {
        this.commerces = commerces;
    }

    private int index;

    //This constructor is for read CSV File
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