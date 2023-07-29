package com.ssafy.trip.util;

import com.ssafy.trip.model.dto.FestivalDto;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class FestivalSAXHandler extends DefaultHandler {

    private int num;

    private List<FestivalDto> dtos;

    private FestivalDto festivalDto;

    private String temp;

    public FestivalSAXHandler() {
        dtos = new ArrayList<>();
    }

    /**
     * text address name date place
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes att) {
        temp = "";
        if (qName.equals("text")) {
            // complete code #04
            // tripDto 객체를 생성(이미지 정보 세팅)하고 trips List에 추가하세요.
            festivalDto = new FestivalDto(num++);
            dtos.add(festivalDto);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("address")) {
            festivalDto.setAddress(temp);
        } else if (qName.equals("date")) {
            festivalDto.setDate(temp);
        } else if (qName.equals("name")) {
            festivalDto.setName(temp);
        } else if (qName.equals("place")) {
            festivalDto.setPlace(temp);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        temp = new String(ch, start, length);
    }

    public List<FestivalDto> getFestivals(){
        return dtos;
    }
}
