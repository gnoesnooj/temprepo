package com.ssafy.trip.view;

import com.ssafy.trip.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.service.FestivalService;
import com.ssafy.trip.model.service.FestivalServiceImpl;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FestivalView {

	private FestivalService festivalService;
	private JFrame frame;
	private TripDto curTrip;

	private DefaultTableModel tripModel;
	private JTable tripTable;
	private JScrollPane tripPan;

	private String[] title = { "지역", "축제이름", "위치", "기간"};

	public FestivalView(TripDto curTrip) {
		this.curTrip = curTrip;
		festivalService = new FestivalServiceImpl();

		frame = new JFrame("지역 축제 정보");
		frame.setSize(600, 600);
		frame.setResizable(true);
		frame.setVisible(true);
		setFestivalView(curTrip);
	}

	public void setFestivalView(TripDto curTrip){
		tripModel = new DefaultTableModel(title, 10);
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
