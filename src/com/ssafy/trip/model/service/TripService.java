package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.EnjoyTripException;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchDto;

public interface TripService {

	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 관광지 정보(TripDto)를 검색해서 반환.
	 * 
	 * @param tripSearchDto 검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 관광지 목록
	 */
	public List<TripDto> searchAll(TripSearchDto tripSearchDto);

	/**
	 * 관광지 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환.
	 * 
	 * @param num 검색할 관광지 식별 번호
	 * @return 관광지 식별 번호에 해당하는 관광지 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 * @throws EnjoyTripException 
	 */
	public TripDto search(int num) throws EnjoyTripException;

}
