# 목차
- 1. 팀원
- 2. 기능 구현 - 1차 요구사항 (코드)
- 3. 기능 구현 - 1차 요구사항 (화면)

# 1. 팀원

> 광주 5반 6팀 예준성

>광주 5반 6팀 조자영

# 2. 기능 구현 - 1차 요구사항 (코드)


### 01
<details>
<summary>TripInfoView.java</summary>

    package com.ssafy.trip.view;

    import java.awt.BorderLayout;
    import java.awt.GridLayout;
    import java.awt.Image;
    import java.awt.Label;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.io.File;
    import java.util.List;

    import javax.swing.BorderFactory;
    import javax.swing.ImageIcon;
    import javax.swing.JButton;
    import javax.swing.JComboBox;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    import javax.swing.JScrollPane;
    import javax.swing.JTable;
    import javax.swing.JTextField;
    import javax.swing.table.DefaultTableModel;

    import com.ssafy.trip.EnjoyTripException;
    import com.ssafy.trip.model.dto.TripDto;
    import com.ssafy.trip.model.dto.TripSearchDto;
    import com.ssafy.trip.model.service.TripService;
    import com.ssafy.trip.model.service.TripServiceImpl;

    public class TripInfoView {

        /** model들 */
        private TripService tripService;

        /** main 화면 */
        private JFrame frame;

        /** 관광지 이미지 표시 Panel */
        private JLabel imgL;
        private JLabel[] tripInfoL;

        /** 조회 조건 */
        private JComboBox<String> findC;
        private JTextField wordTf;
        private JButton searchBt;

        /** 조회 내용 표시할 table */
        private DefaultTableModel tripModel;
        private JTable tripTable;
        private JScrollPane tripPan;
        private String[] title = { "번호", "관광지명", "도로명주소", "지번주소", "전화번호" };

        /** 검색 조건 */
        private String key;
        private String[] choice = { "검색조건선택", "관광지명", "주소" };
        /** 검색할 단어 */
        private String word;

        /** 화면에 표시하고 있는 주택 */
        private TripDto curTrip;

        /** 축제 기능 구현 필드 ---------------------------------------------------------------------*/
        private JButton detailBt;

	
    public TripInfoView() {
            /* Service들 생성 */
            tripService = new TripServiceImpl();

            /* 메인 화면 설정 */
            frame = new JFrame("Enjoy! Trip - 즐거운 여행");
    //		frame.addWindowListener(new WindowAdapter() {
    //			public void windowClosing(WindowEvent e){
    //				frame.dispose();
    //			}
    //		});

            setMain();

            frame.setSize(1200, 800);
            frame.setResizable(true);
            frame.setVisible(true);
            showTripInfo(0);
        }

        private void showTripInfo(int num) {
            try {
                curTrip = tripService.search(num);
            } catch (EnjoyTripException e) {
                e.printStackTrace();
            }
            tripInfoL[0].setText("");
            tripInfoL[1].setText("");
            tripInfoL[2].setText(curTrip.getTouristDestination());
            tripInfoL[3].setText(curTrip.getStreetAddress());
            tripInfoL[4].setText(curTrip.getLotAddress());
            tripInfoL[5].setText(curTrip.getLat() + "");
            tripInfoL[6].setText(curTrip.getLng() + "");
            tripInfoL[7].setText(curTrip.getTel());
            tripInfoL[8].setText(curTrip.getInfo());
            tripInfoL[9].setText("");

            ImageIcon icon = null;
            if (curTrip.getImg() != null && curTrip.getImg().trim().length() != 0) {
                String img = curTrip.getImg();
                File file = new File("img", img);

                if (!file.exists())
                    img = "no_image.jpg";
                icon = new ImageIcon("img/" + img);

            } else {
                icon = new ImageIcon("img/no_image.jpg");
            }
            Image image = icon.getImage();
            Image changeImage = image.getScaledInstance(570, 470, Image.SCALE_SMOOTH);
            ImageIcon changeIcon = new ImageIcon(changeImage);
            imgL.setIcon(changeIcon);
        }

        /** 메인 화면인 관광지 목록을 위한 화면 셋팅하는 메서드 */
        public void setMain() {

            /* 왼쪽 화면을 위한 설정 */
            JPanel left = new JPanel(new BorderLayout());
            JPanel leftCenter = new JPanel(new BorderLayout(0, 10));
            JPanel leftR = new JPanel(new GridLayout(10, 2));
            leftR.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

            //******************** 축제 정보 출력을 위한 버든
            detailBt = new JButton("지역 축제 정보");

            String[] info = { "", "", "관광지명", "도로명주소", "지번주소", "위도", "경도", "전화번호", "관광지정보", "" };
            int size = info.length;
            JLabel infoL[] = new JLabel[size];
            tripInfoL = new JLabel[size];
            for (int i = 0; i < size; i++) {
                infoL[i] = new JLabel(info[i]);
                tripInfoL[i] = new JLabel("");
                leftR.add(infoL[i]);
                leftR.add(tripInfoL[i]);
            }
            imgL = new JLabel();
            leftCenter.add(imgL, "Center");
            leftCenter.add(leftR, "South");

            left.add(new JLabel("관광지 정보", JLabel.CENTER), "North");
            left.add(leftCenter, "Center");

            //**************** 축제 정보 출력을 위한 버든
            left.add(detailBt, "South");

            /* 오른쪽 화면을 위한 설정 */
            JPanel right = new JPanel(new BorderLayout());
            JPanel rightTop = new JPanel(new GridLayout(4, 2));

            JPanel rightTop2 = new JPanel(new GridLayout(1, 3));
            String[] item = { "검색조건선택", "관광지명", "주소" };
            findC = new JComboBox<String>(item);
            wordTf = new JTextField();
            searchBt = new JButton("검색");

            rightTop2.add(findC);
            rightTop2.add(wordTf);
            rightTop2.add(searchBt);

            rightTop.add(new Label(""));
            rightTop.add(new Label(""));
            rightTop.add(rightTop2);
            rightTop.add(new Label(""));

            JPanel rightCenter = new JPanel(new BorderLayout());
            tripModel = new DefaultTableModel(title, 20);
            tripTable = new JTable(tripModel);
            tripPan = new JScrollPane(tripTable);
            tripTable.setColumnSelectionAllowed(true);
            rightCenter.add(new JLabel("관광지 정보", JLabel.CENTER), "North");
            rightCenter.add(tripPan, "Center");

            right.add(rightTop, "North");
            right.add(rightCenter, "Center");

            JPanel mainP = new JPanel(new GridLayout(1, 2));

            mainP.add(left);
            mainP.add(right);

            mainP.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
            frame.add(mainP, "Center");

            tripTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    int row = tripTable.getSelectedRow();
                    int code = Integer.parseInt(((String) tripModel.getValueAt(row, 0)).trim());
                    showTripInfo(code);
                }
            });

            // complete code #01
            // 아래의 코드를 참조하여 아래 라인을 uncomment 하고 searchBt.addActionList() 를 Lambda 표현식으로 바꾸세요.
            searchBt.addActionListener(e -> searchTrips());

            // 참조코드 시작 - 위 코드를 완성 후 삭제 또는 comment 처리하세요.
            /*ActionListener buttonHandler = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchTrips();
                }
            };

            searchBt.addActionListener( buttonHandler );*/
            // 참조코드 종료

            showTrips();

            detailBt.addActionListener(e -> new FestivalView());
        }

        /** 검색 조건에 맞는 관광지 검색 */
        private void searchTrips() {
            word = wordTf.getText().trim();
            key = choice[findC.getSelectedIndex()];
            showTrips();
        }

        /**
         * 관광지 목록을 갱신하기 위한 메서드
         */
        public void showTrips() {
            TripSearchDto tripSearchDto = new TripSearchDto();
            if (key != null) {
                if (key.equals("관광지명")) {
                    tripSearchDto.setTouristDestination(word);
                } else if (key.equals("주소")) {
                    tripSearchDto.setSido(word);
                }
            }

            if (word == null || word.trim().length() == 0)
                findC.setSelectedIndex(0);

            List<TripDto> trips = tripService.searchAll(tripSearchDto);
            if (trips != null) {
                int i = 0;
                String[][] data = new String[trips.size()][5];
                for (TripDto trip : trips) {
                    data[i][0] = "" + (trip.getNum()+1);
                    data[i][1] = trip.getTouristDestination();
                    data[i][2] = trip.getStreetAddress();
                    data[i][3] = trip.getLotAddress();
                    data[i++][4] = trip.getTel();
                }
                tripModel.setDataVector(data, title);
            }
        }

    //	public static void main(String[] args) {
    //		new TripInfoView();
    //	}
    }

    
    
    
접은 내용(ex 소스 코드)
</details>

### 02
<details>
<summary> TripServiceImpl.java </summary>
    
    package com.ssafy.trip.model.service;

    import java.util.List;

    import com.ssafy.trip.EnjoyTripException;
    import com.ssafy.trip.model.dao.TripDao;
    import com.ssafy.trip.model.dao.TripDaoImpl;
    import com.ssafy.trip.model.dto.TripDto;
    import com.ssafy.trip.model.dto.TripSearchDto;

    public class TripServiceImpl implements TripService {

        private TripDao tripDao;

        public TripServiceImpl() {
            tripDao = new TripDaoImpl();
        }

        /**
         * 검색 조건(key) 검색 단어(word)에 해당하는 관광지 정보(TripDto)를 검색해서 반환.
         * 
         * @param tripSearchDto 검색 조건과 검색 단어가 있는 객체
         * @return 조회한 관광지 목록
         */
        @Override
        public List<TripDto> searchAll(TripSearchDto tripSearchDto) {
            return tripDao.searchAll(tripSearchDto);
        }

        /**
         * 관광지 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환.
         * 
         * @param num 검색할 관광지 식별 번호
         * @return 관광지 식별 번호에 해당하는 관광지 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
         * @throws EnjoyTripException 
         */
        @Override
        public TripDto search(int num) throws EnjoyTripException {

            // complete code #02
            // null 을 return 하면 안됩니다. Dao Layer 의 적절한 method를 호출하여 Business Logic 을 완성하세요.
            if(tripDao.search(num) != null) {
                return tripDao.search(num); // 예외처리
            } else {
                throw new EnjoyTripException();
            }
        }

    }

</details>
    

### 03
    
<details>
<summary>TripDaoImpl.java</summary>  
    
    package com.ssafy.trip.model.dao;

    import java.util.LinkedList;
    import java.util.List;

    import com.ssafy.trip.model.dto.TripDto;
    import com.ssafy.trip.model.dto.TripSearchDto;
    import com.ssafy.trip.util.TouristDestinationSAXParser;

    public class TripDaoImpl implements TripDao {

        private List<TripDto> tripInfo;

        public TripDaoImpl() {
            loadData();
        }

        /**
         * 관광지 정보를 xml 파일에서 읽어온다.
         */
        @Override
        public void loadData() {
            TouristDestinationSAXParser parser = new TouristDestinationSAXParser();
            tripInfo = parser.getTripInfo();
        }

        @Override
        public List<TripDto> searchAll(TripSearchDto tripSearchDto) {
            List<TripDto> finds = new LinkedList<TripDto>();

            String touristDestination = tripSearchDto.getTouristDestination(); // 관광지
            String sido = tripSearchDto.getSido(); // 주소
            if (touristDestination != null) {
                for (TripDto trip : tripInfo) {
                    if (trip.getTouristDestination().contains(touristDestination)) {
                        finds.add(trip);
                    }
                }
            } else if (sido != null) {
                for (TripDto trip : tripInfo) {
                    if (trip.getStreetAddress().contains(sido)) {
                        finds.add(trip);
                    }
                }
            } else {
                finds = tripInfo;
            }
            return finds;
        }

        @Override
        public TripDto search(int num) {

            // complete code #03
            // List<TripDto> tripInfo 로부터 num 에 해당하는 TripDto 정보를 검색하여 return 하도록 코드를 작성하세요.
            // 해당하는 num이 없을 경우 null을 리턴하세요.
            for(TripDto tripDto : tripInfo) {
                if(tripDto.getNum() == num) {
                    return tripDto;
                }
            }
            return null;
        }

        public static void print(List<TripDto> trips) {
            for (TripDto trip : trips) {
                System.out.println(trip);
            }
        }
    }

</details>
    

### 04~07

<details>
<summary>TouristDestinationSAXHandler.java</summary>
    
    package com.ssafy.trip.util;

    import java.util.ArrayList;
    import java.util.List;

    import org.xml.sax.Attributes;
    import org.xml.sax.helpers.DefaultHandler;

    import com.ssafy.trip.model.dto.TripDto;

    /**
     * 전국관광지정보표준데이터.xml 파일에서 관광지 정보를 읽어 파싱하는 핸들러 클래스
     */
    public class TouristDestinationSAXHandler extends DefaultHandler {

        /**
         * 관광지 정보를 식별하기 위한 번호로 차후 DB에서는 primary key로 대체하지만 현재 버전에서는 0번부터 순차 부여한다.
         */
        private int num;
        /** 관광지 정보를 담는다 */
        private List<TripDto> trips;
        /** 파싱힌 관광지 정보 */
        private TripDto tripDto;
        /** 태그 바디 정보를 임시로 저장 */
        private String temp;

        public TouristDestinationSAXHandler() {
            trips = new ArrayList<TripDto>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes att) {
            temp = "";
            if (qName.equals("record")) {
                // complete code #04
                // tripDto 객체를 생성(이미지 정보 세팅)하고 trips List에 추가하세요.
                tripDto = new TripDto(num++);
                trips.add(tripDto);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (num < 13)
                tripDto.setImg("image"+findImg(num-1)+".jpg");
            if (qName.equals("관광지명")) {
                // complete code #05
                // 관광지명 항목을 처리하세요.
                tripDto.setTouristDestination(temp);
            } else if (qName.equals("소재지도로명주소")) {
                tripDto.setStreetAddress(temp);
            } else if (qName.equals("소재지지번주소")) {
                tripDto.setLotAddress(temp);
            } else if (qName.equals("위도")) {
                if (temp.length() != 0)
                    tripDto.setLat(Double.parseDouble(temp));
            } else if (qName.equals("경도")) {
                if (temp.length() != 0)
                    tripDto.setLng(Double.parseDouble(temp));
                // complete code #06
                // 경도 항목을 처리하세요.
            } else if (qName.equals("관광지소개")) {
                tripDto.setInfo(temp);
            } else if (qName.equals("관리기관전화번호")) {
                // complete code #07
                // 관리기관전화번호 항목을 처리하세요.
                tripDto.setTel(temp);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            temp = new String(ch, start, length);
        }

        public List<TripDto> getTrips() {
            return trips;
        }

        private String findImg(int num) {
            if(num < 10)
                return "0"+String.valueOf(num);
            else
                return String.valueOf(num);
        }
    }

</details>
    
### 08
    
<details>
<summary>TouristDestinationSAXParser</summary>

    package com.ssafy.trip.util;

    import java.util.List;

    import javax.xml.parsers.SAXParser;
    import javax.xml.parsers.SAXParserFactory;

    import com.ssafy.trip.model.dto.TripDto;

    /**
     * TouristDestinationSAXHandler를 이용해서 관광지 정보를 load하는 SAX Parser 프로 그램
     */
    public class TouristDestinationSAXParser {

    //	private Map<String, List<TripDto>> trips;
        private List<TripDto> tripInfo;
        private int size;
        /**
         * 관광지 정보를 식별하기 위한 번호로 차후 DB에서는 primary key로 대체하지만 현재 버전에서는 0번부터 순차 부여한다.
         */
        public int num;

        public TouristDestinationSAXParser() {
            // complete code #08
            // 전국관광지정보표준데이터.xml을 loading하도록 처리하세요.
            loadData();
        }

        /**
         * TouristDestinationSAXHandler를 이용 파싱한 관광지 정보를 추출한다.
         */
        private void loadData() {

            SAXParserFactory factory = SAXParserFactory.newInstance();

            String tripInfoFilePath = "res/전국관광지정보표준데이터.xml";

            try {
                SAXParser parser = factory.newSAXParser();
                TouristDestinationSAXHandler handler = new TouristDestinationSAXHandler();
                parser.parse(tripInfoFilePath, handler);
                tripInfo = handler.getTrips();

                size = tripInfo.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public List<TripDto> getTripInfo() {
            return tripInfo;
        }

        public void setTripInfo(List<TripDto> tripInfo) {
            this.tripInfo = tripInfo;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public static void main(String[] args) {
            new TouristDestinationSAXParser();
        }
    }

</details>

    
    
# 3. 기능 구현 - 1차 요구사항 (화면)


## 완료 


### 초기 화면
    
![처음화면](/uploads/4aa93aad9ac5182853e765d88bbe374f/처음화면.JPG)
    
### 상세 화면
![상세화면](/uploads/09df9161c99e505f1552f62e275bcf1b/상세화면.JPG)

### 검색 화면
![검색화면기능](/uploads/9e059dce951bcf8559c1c11ca46d1b80/검색화면기능.JPG)

### 이미지 파일 로드 화면
![사진기능구현](/uploads/30a2891630a2a61cf6793311d53e822e/사진기능구현.JPG)


## 추가 2 )
    
### 진행 중 - 지역 축제 정보 팝업 화면

![진행중-축제](/uploads/e34ce0af57b82bbc6b2af28a310f1555/진행중-축제.JPG)
