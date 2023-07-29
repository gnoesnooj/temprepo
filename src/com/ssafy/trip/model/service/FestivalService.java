package com.ssafy.trip.model.service;

import com.ssafy.trip.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import java.util.List;

public interface FestivalService {

    public List<FestivalDto> search(TripDto tripDto);
}
