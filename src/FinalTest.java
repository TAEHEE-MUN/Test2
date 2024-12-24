import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * FinalTest 클래스는 사용자가 과제를 계획하고 스케줄을 자동으로 생성할 수 있는 GUI 프로그램입니다.
 * 사용자는 과제 제목, 내용, 제출 기한, 난이도 등을 입력하고,
 * 시간 계획표를 생성한 후 출력 및 저장할 수 있습니다.
 * 또한, 알람 설정 기능과 외부 웹 링크를 제공하는 버튼도 포함되어 있습니다.
 *
 * @author MunTaeHee
 * @version 2.0
 * @since 1.0
 *
 * @created 2024-12-23
 * @lastModified 2024-12-25
 *
 * @changelog
 * <ul>
 * <li>2024-12-23: 최초 생성 </li>
 * <li>2024-12-23: 인터페이스 생성 </li>
 * <li>2024-12-23: 이벤트 사용시 새프레임 생성 </li>
 * <li>2024-12-23: 입출력파일 추가 </li>
 * <li>2024-12-24: 난이도 버튼의 기능추가 </li>
 * <li>2024-12-24: 공지사항 버튼 기능추가 </li>
 * <li>2024-12-24: 연동 버튼 기능추가 </li>
 * <li>2024-12-24: 리스트, 해시맵 추가사용 </li>
 * <li>2024-12-25: 자바독 추가 </li>
 * </ul>
 */
public class FinalTest extends JFrame {

    /**
     * 시간 계획을 저장할 ArrayList입니다. 사용자가 입력한 시간 계획을 순차적으로 저장합니다.
     */
    private ArrayList<String> scheduleList;

    /**
     * 과제 제목을 키로, 과제의 세부 사항을 값으로 저장하는 HashMap입니다.
     * 사용자가 입력한 과제 제목을 통해 관련 세부 사항을 쉽게 검색할 수 있습니다.
     */
    private HashMap<String, String> taskDetails;

    /**
     * FinalTest 클래스의 생성자입니다.
     * JFrame을 초기화하고, GUI 컴포넌트를 설정하고, 이벤트 리스너를 연결합니다.
     */
    public FinalTest() {
        scheduleList = new ArrayList<>(); // 시간 계획을 저장할 리스트 초기화
        taskDetails = new HashMap<>(); // 과제 세부 사항을 저장할 HashMap 초기화

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 시 애플리케이션 종료
        this.setSize(320, 440); // 프레임 크기 설정
        this.setTitle("과제 자동 스케쥴링"); // 프레임 제목 설정

        // 입력 패널 생성
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // 컴포넌트를 왼쪽 정렬

        // 과제 제목 텍스트 필드 생성
        inputPanel.add(new JLabel("과제 :"));
        JTextField assignmentField = new JTextField(20); // 과제 제목 입력 필드
        inputPanel.add(assignmentField);

        // 과제 내용 입력 필드 생성
        inputPanel.add(new JLabel("내용 :"));
        JTextArea contentArea = new JTextArea(); // 내용 입력 필드
        contentArea.setLineWrap(true); // 줄 바꿈 허용
        contentArea.setWrapStyleWord(true); // 단어 단위로 줄 바꿈
        JScrollPane scrollPane = new JScrollPane(contentArea); // 텍스트 영역을 스크롤 가능하게
        scrollPane.setPreferredSize(new Dimension(250, 120)); // 스크롤 패널 크기 설정
        inputPanel.add(scrollPane); // 내용 입력 필드 패널에 추가

        // 제출 기한 입력 필드 생성
        inputPanel.add(new JLabel("제출기한 :"));
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬

        JTextField startMonthField = new JTextField(2); // 시작 월 필드
        JTextField startDayField = new JTextField(2); // 시작 일 필드
        JTextField endMonthField = new JTextField(2); // 종료 월 필드
        JTextField endDayField = new JTextField(2); // 종료 일 필드

        // 월, 일 필드와 라벨 추가
        datePanel.add(startMonthField);
        datePanel.add(new JLabel("월"));
        datePanel.add(startDayField);
        datePanel.add(new JLabel("일"));
        datePanel.add(new JLabel("~"));
        datePanel.add(endMonthField);
        datePanel.add(new JLabel("월"));
        datePanel.add(endDayField);
        datePanel.add(new JLabel("일"));
        inputPanel.add(datePanel);

        // 난이도 설정을 위한 라디오 버튼 생성
        inputPanel.add(new JLabel("난이도 :"));
        JPanel difficultyPanel = new JPanel();
        JRadioButton easyButton = new JRadioButton("쉬움");
        JRadioButton normalButton = new JRadioButton("보통");
        JRadioButton hardButton = new JRadioButton("어려움");

        // 난이도 라디오 버튼을 그룹화하여 한 번에 하나만 선택되도록 설정
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyButton);
        difficultyGroup.add(normalButton);
        difficultyGroup.add(hardButton);

        difficultyPanel.add(easyButton);
        difficultyPanel.add(normalButton);
        difficultyPanel.add(hardButton);
        inputPanel.add(difficultyPanel);

        // 알람 설정을 위한 라디오 버튼 추가
        JPanel alarmPanel = new JPanel();
        alarmPanel.add(new JLabel("알람 :"));
        JRadioButton alarmOnButton = new JRadioButton("0");
        JRadioButton alarmOffButton = new JRadioButton("X");
        ButtonGroup alarmGroup = new ButtonGroup();
        alarmGroup.add(alarmOnButton);
        alarmGroup.add(alarmOffButton);

        alarmPanel.add(alarmOnButton);
        alarmPanel.add(alarmOffButton);
        inputPanel.add(alarmPanel);

        // 출력 버튼 추가
        JButton submitButton = new JButton("출력하기");
        submitButton.setPreferredSize(new Dimension(150, 60)); // 버튼 크기 설정

        /**
         * 출력 버튼 클릭 시 호출되는 액션 리스너입니다.
         * 사용자가 입력한 데이터를 출력 창에 보여주고, 시간 계획표를 생성합니다.
         * 또한, 변경 및 저장 기능을 제공합니다.
         */
        submitButton.addActionListener(new ActionListener() {
            /**
             * 출력 버튼을 클릭했을 때 실행되는 메서드입니다.
             * 사용자가 입력한 데이터를 기반으로 새로운 창에 정보를 출력하고,
             * 사용자가 작성한 시간 계획표를 생성합니다.
             * 또한, 변경 및 저장 버튼을 제공합니다.
             *
             * @param e 버튼 클릭 이벤트
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // 새 창을 만들어서 출력 내용을 표시
                JFrame newFrame = new JFrame("출력 내용");
                newFrame.setSize(350, 600);
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel newPanel = new JPanel();
                newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));

                // 과제 세부 내용 출력
                JLabel settingsLabel = new JLabel("설정값 : ");
                newPanel.add(settingsLabel);

                String outputText = "과제/시험: " + assignmentField.getText() + "\n" +
                        "내용: " + contentArea.getText() + "\n" +
                        "제출기한: " + startMonthField.getText() + "월 " + startDayField.getText() + "일 ~ " +
                        endMonthField.getText() + "월 " + endDayField.getText() + "일\n" +
                        "난이도: " + (easyButton.isSelected() ? "쉬움" :
                        normalButton.isSelected() ? "보통" : "어려움") + "\n" +
                        "알람: " + (alarmOnButton.isSelected() ? "0" : "X");

                JTextArea outputArea = new JTextArea(outputText);
                outputArea.setLineWrap(true);
                outputArea.setWrapStyleWord(true);
                outputArea.setEditable(false);
                JScrollPane outputScrollPane = new JScrollPane(outputArea);
                outputScrollPane.setPreferredSize(new Dimension(250, 120));
                newPanel.add(outputScrollPane);

                // 시간 계획표 레이블 및 입력 필드 추가
                JLabel scheduleLabel = new JLabel("\n시간계획표 : ");
                newPanel.add(scheduleLabel);

                JTextArea userInputArea = new JTextArea();
                userInputArea.setLineWrap(true);
                userInputArea.setWrapStyleWord(true);
                userInputArea.setEditable(false);
                JScrollPane userInputScrollPane = new JScrollPane(userInputArea);
                userInputScrollPane.setPreferredSize(new Dimension(250, 120));
                newPanel.add(userInputScrollPane);

                // 변경 및 저장 버튼 패널 추가
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                JButton changeButton = new JButton("변경하기");
                JButton saveButton = new JButton("파일 저장하기");

                // 변경 버튼 클릭 시 시간 계획 수정 가능하도록 설정
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        userInputArea.setEditable(true); // 편집 가능
                    }
                });

                // 저장 버튼 클릭 시 입력된 데이터를 파일로 저장
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 사용자 입력을 HashMap에 저장
                        String assignmentTitle = assignmentField.getText();
                        String assignmentContent = contentArea.getText();
                        String deadline = startMonthField.getText() + "월 " + startDayField.getText() + "일 ~ " +
                                endMonthField.getText() + "월 " + endDayField.getText() + "일";
                        String difficulty = easyButton.isSelected() ? "쉬움" :
                                normalButton.isSelected() ? "보통" : "어려움";
                        String alarm = alarmOnButton.isSelected() ? "0" : "X";
                        String timePlan = userInputArea.getText(); // 시간 계획표

                        // 과제 제목을 키로 하고, 과제의 세부 사항을 값으로 설정
                        String taskDetailsValue = "내용: " + assignmentContent + "\n" +
                                "제출기한: " + deadline + "\n" +
                                "난이도: " + difficulty + "\n" +
                                "알람: " + alarm + "\n" + "\n" +
                                "시간계획표: \n" + timePlan;

                        taskDetails.put(assignmentTitle, taskDetailsValue);

                        // 파일 저장
                        String filePath = "C:\\Users\\tea00\\OneDrive\\바탕 화면\\AutoScheduling.txt";
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                            for (String title : taskDetails.keySet()) {
                                writer.write("과제 제목: " + title + "\n");
                                writer.write(taskDetails.get(title) + "\n\n");
                            }
                            JOptionPane.showMessageDialog(FinalTest.this, "파일이 저장되었습니다: " + filePath);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(FinalTest.this, "파일 저장 중 오류 발생: " + ex.getMessage());
                        }
                    }
                });

                buttonPanel.add(changeButton); // 변경 버튼 추가
                buttonPanel.add(saveButton); // 저장 버튼 추가

                newPanel.add(buttonPanel);

                newFrame.add(newPanel); // 새 창에 내용 추가
                newFrame.setVisible(true); // 새 창을 보이도록 설정

                // 시간 계획표 생성
                int endDay = Integer.parseInt(endDayField.getText());
                int endMonth = Integer.parseInt(endMonthField.getText());
                int calculatedDay1;

                // 난이도에 따라 시간 계획표를 생성
                if (easyButton.isSelected() || normalButton.isSelected() || hardButton.isSelected()) {
                    int days = easyButton.isSelected() ? 3 : normalButton.isSelected() ? 5 : 7;
                    StringBuilder schedule = new StringBuilder();

                    for (int i = 0; i < days; i++) {
                        // 날짜 계산 로직 추가
                        int day = endDay - (days - i - 1); // 현재 날짜에서 차감
                        if (day < 1) { // 날짜가 1보다 작으면
                            if (endMonth % 2 == 0) { // 짝수 월
                                day += 30; // 30일로 순환
                            } else { // 홀수 월
                                day += 31; // 31일로 순환
                            }
                        }
                        schedule.append(day).append("일: ")
                                .append(getTaskDescription(i, easyButton.isSelected(), normalButton.isSelected(), hardButton.isSelected())).append("\n");
                    }
                    userInputArea.setText(schedule.toString()); // 생성된 시간 계획표를 텍스트 영역에 추가
                }

                newFrame.add(newPanel); // 새 창에 패널 추가
                newFrame.setVisible(true); // 새 창 보이기
            }
        });

        inputPanel.add(submitButton); // 제출 버튼 추가
        this.add(inputPanel, BorderLayout.CENTER); // 입력 패널을 프레임에 추가

        // 하단 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton noticeButton = new JButton("공지사항");
        noticeButton.addActionListener(new ActionListener() {
            /**
             * 공지사항 버튼 클릭 시 외부 웹사이트를 열기 위한 메서드입니다.
             * 사용자가 클릭하면 외부 브라우저를 통해 공지사항을 확인할 수 있습니다.
             *
             * @param e 버튼 클릭 이벤트
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://hive.cju.ac.kr"));
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(FinalTest.this, "페이지를 열 수 없습니다: " + ex.getMessage());
                }
            }
        });

        JButton phoneLinkButton = new JButton("휴대폰 연동");
        phoneLinkButton.addActionListener(new ActionListener() {
            /**
             * 휴대폰 연동 버튼 클릭 시 안내 창을 띄우는 메서드입니다.
             * 이 창에서는 사용자가 앱과 연동하는 방법에 대해 설명합니다.
             *
             * @param e 버튼 클릭 이벤트
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame phoneLinkFrame = new JFrame("휴대폰 연동 안내");
                phoneLinkFrame.setSize(400, 200);
                phoneLinkFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel messagePanel = new JPanel();
                messagePanel.setLayout(new BorderLayout());

                String message = "휴대폰 앱과 연동하는 시스템을 만들어서\n" +
                        "휴대폰 앱으로 알람기능과 공유시스템 등을 넣고 싶었지만\n" +
                        "아직 시간과 실력이 부족했습니다.";
                JTextArea messageArea = new JTextArea(message);
                messageArea.setWrapStyleWord(true);
                messageArea.setLineWrap(true);
                messageArea.setEditable(false);
                messageArea.setBackground(null);

                messagePanel.add(messageArea, BorderLayout.CENTER);

                phoneLinkFrame.add(messagePanel);
                phoneLinkFrame.setVisible(true);
            }
        });

        buttonPanel.add(noticeButton); // 공지사항 버튼 추가
        buttonPanel.add(phoneLinkButton); // 휴대폰 연동 버튼 추가

        this.add(buttonPanel, BorderLayout.SOUTH); // 하단 버튼 패널 추가

        this.setVisible(true); // GUI 창을 보이도록 설정
    }

    /**
     * 난이도에 따라 각 과제에 대한 설명을 반환하는 메서드입니다.
     *
     * @param taskIndex 과제의 순서 인덱스
     * @param isEasy 쉬운 난이도 여부
     * @param isNormal 보통 난이도 여부
     * @param isHard 어려운 난이도 여부
     * @return 각 과제에 대한 설명 텍스트
     */
    private String getTaskDescription(int taskIndex, boolean isEasy, boolean isNormal, boolean isHard) {
        switch (taskIndex) {
            case 0: return "과제 작성과 기획, 자료 조사";
            case 1: return "전체 설계 작성 및 이론적 배경 분석";
            case 2: return "본격적인 코드 작성 및 실험 계획 수립";
            case 3: return "실험 데이터 처리 및 코드 최적화";
            case 4: return "결과 분석 및 보고서 작성";
            case 5: return "문제 해결, 프로젝트 마무리 및 검토";
            case 6: return "최종 제출을 위한 전체 검토 및 수정";
            default: return "잘못된 난이도입니다.";
        }
    }

    /**
     * 프로그램 실행을 위한 main 메서드입니다.
     * @param args 명령 줄 인수
     */
    public static void main(String[] args) {
        new FinalTest(); // FinalTest 객체 생성하여 GUI 실행
    }
}
