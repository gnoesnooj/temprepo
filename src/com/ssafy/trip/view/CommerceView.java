package com.ssafy.trip.view;

import com.ssafy.trip.util.CommerceCSVLoader;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CommerceView {

    private JFrame frame;
    private DefaultTableModel tripModel;
    private JTable tripTable;
    private JScrollPane tripPan;
    private String[] title = {"업체명","대분류","소분류","주소"};

    public CommerceView() {
        frame = new JFrame("지역 축제 정보");
        frame.setSize(600, 600);
        frame.setResizable(true);
        frame.setVisible(true);
        setCommerceView();
    }

    public void setCommerceView(){
        tripModel = new DefaultTableModel(title, 10);
        printCommerce();

        tripTable = new JTable(tripModel);
        tripPan = new JScrollPane(tripTable);
        tripTable.setColumnSelectionAllowed(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(" ", JLabel.CENTER), "North");
        panel.add(tripPan, "Center");
        frame.add(tripPan);
    }

    private void printCommerce(){
        try {
            CommerceCSVLoader test = new CommerceCSVLoader();
            List<String[]> commerces = test.getCommerces();
            if(commerces != null){
                int i= 0;
                String [][] data = new String[commerces.size()][4];
                for(String[] commerce : commerces ){
                    data[i][0] = replace(commerce[1]);
                    data[i][1] = replace(commerce[4]);
                    data[i][2] = replace(commerce[6]);
                    data[i++][3] = replace(commerce[24]);
                }
                tripModel.setDataVector(data, title);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String replace(String str){
        return str.replace("\"", "");
    }
}
