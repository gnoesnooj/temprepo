package com.ssafy.trip.model.dao;

import com.ssafy.trip.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import java.util.List;

public interface FestivalDao {
    public void loadData();
    public List<FestivalDto> search(String addr1, String addr2);
}
