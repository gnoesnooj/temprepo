package com.ssafy.trip.util;

import com.ssafy.trip.model.dto.FestivalDto;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FestivalSAXParser {

    private List<FestivalDto> festivalDtos;

    private int size;

    public int num;

    public FestivalSAXParser(){
        loadData();
    }
    private void loadData() {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        String tripInfoFilePath = "res/festival.xml";

        try {
            SAXParser parser = factory.newSAXParser();
            FestivalSAXHandler handler = new FestivalSAXHandler();
            parser.parse(tripInfoFilePath, handler);
            festivalDtos = handler.getFestivals();

            size = festivalDtos.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FestivalDto> getFestivalDtos(){
        return festivalDtos;
    }
}
