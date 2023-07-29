package com.ssafy.trip.view;

import com.ssafy.trip.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchDto;
import com.ssafy.trip.model.service.FestivalService;
import com.ssafy.trip.model.service.FestivalServiceImpl;
import com.ssafy.trip.model.service.TripService;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FestivalView {

	private FestivalService festivalService;
	private JFrame frame;
	private TripDto curTrip;

	/** 축제 정보를 출력해줄 테이블 전체*/
	private DefaultTableModel tripModel;
	private JTable tripTable;
	private JScrollPane tripPan;

	private String[] title = { "지역", "축제이름", "위치", "기간"};

	/** */

	public FestivalView(TripDto curTrip) {
		this.curTrip = curTrip;
		festivalService = new FestivalServiceImpl();

		/* 메인 화면 설정 */
		frame = new JFrame("지역 축제 정보");
		frame.setSize(600, 600);
		frame.setResizable(true);
		frame.setVisible(true);
		setFestivalView(curTrip);

		/** 필요 : 1. xml 파싱 2. 주소 파싱 3. 위치 넣어서 화면 출력  */
		/* Service들 생성 */

	}

	public void setFestivalView(TripDto curTrip){
		tripModel = new DefaultTableModel(title, 10);
//		model 설정
		findFestivals(curTrip);

		tripTable = new JTable(tripModel);
		tripPan = new JScrollPane(tripTable);
		tripTable.setColumnSelectionAllowed(true);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel(" ", JLabel.CENTER), "North");
		panel.add(tripPan, "Center");
		frame.add(tripPan);
	}

	private void findFestivals(TripDto tripDto){
		// FestivalService 작성 후 tripDto를 통해서 주소값으로 찾는 메소드 구현하기
		List<FestivalDto> festivalDtos = festivalService.search(tripDto);
		if (festivalDtos != null) {
			int i = 0;
			String[][] data = new String[festivalDtos.size()][4];
			for (FestivalDto festival : festivalDtos) {
				data[i][0] = festival.getAddress();
				data[i][1] = festival.getName();
				data[i][2] = festival.getPlace();
				data[i++][3] = festival.getDate();
			}
			tripModel.setDataVector(data, title);
		}
	}
}
