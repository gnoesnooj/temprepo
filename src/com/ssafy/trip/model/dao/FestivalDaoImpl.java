package com.ssafy.trip.model.dao;

import com.ssafy.trip.model.dto.FestivalDto;
import com.ssafy.trip.util.FestivalSAXParser;
import java.util.LinkedList;
import java.util.List;

public class FestivalDaoImpl implements FestivalDao {
    List<FestivalDto> festivalDtos;

    public FestivalDaoImpl() {
        loadData();
    }

    @Override
    public void loadData() {
        FestivalSAXParser parser = new FestivalSAXParser();
        festivalDtos = parser.getFestivalDtos();
    }

    @Override
    public List<FestivalDto> search(String addr1, String addr2) {
        List<FestivalDto> finds = new LinkedList<>();
        String addr = (addr1.equals("") || addr1 == null) ? addr2 : addr1;
        for(FestivalDto dto : festivalDtos){
            String festivalAddress = dto.getAddress();
            String parsed1 = addr.substring(0,2);
            String parsed2 = "" + addr.charAt(0) + addr.charAt(2);

            if(festivalAddress.equals(parsed1) || festivalAddress.equals(parsed2)){
                finds.add(dto);
            }
        }
        return finds;
    }
}
