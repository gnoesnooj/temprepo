package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dao.FestivalDao;
import com.ssafy.trip.model.dao.FestivalDaoImpl;
import com.ssafy.trip.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import java.util.List;

public class FestivalServiceImpl implements FestivalService{

    private FestivalDao festivalDao;

    public FestivalServiceImpl(){
        festivalDao = new FestivalDaoImpl();
    }

    @Override
    public List<FestivalDto> search(TripDto tripDto) {
        return festivalDao.search(tripDto.getLotAddress(), tripDto.getStreetAddress());
    }
}
