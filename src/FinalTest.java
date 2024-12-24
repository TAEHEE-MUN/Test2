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

public class FinalTest extends JFrame {
    private ArrayList<String> scheduleList; // 시간 계획을 저장할 리스트
    private HashMap<String, String> taskDetails;
    public FinalTest() {
        scheduleList = new ArrayList<>(); // 리스트 초기화
        taskDetails = new HashMap<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320, 440); // 프레임 크기 조정
        this.setTitle("과제 자동 스케쥴링");

        // 입력 패널 생성
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout 사용

        // 과제/시험
        inputPanel.add(new JLabel("과제 :"));
        JTextField assignmentField = new JTextField(20); // 너비를 20으로 설정
        inputPanel.add(assignmentField);

        // 내용
        inputPanel.add(new JLabel("내용 :"));
        JTextArea contentArea = new JTextArea(); // 내용 입력 필드
        contentArea.setLineWrap(true); // 줄 바꿈 허용
        contentArea.setWrapStyleWord(true); // 단어 단위로 줄 바꿈
        JScrollPane scrollPane = new JScrollPane(contentArea); // 스크롤 가능하게 감싸기
        scrollPane.setPreferredSize(new Dimension(250, 120)); // 스크롤 패널 크기 설정
        inputPanel.add(scrollPane); // 스크롤 패널 추가

        // 제출기한
        inputPanel.add(new JLabel("제출기한 :"));
        JPanel datePanel = new JPanel(); // 제출기한 필드를 위한 패널
        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬

        // 월, 일 텍스트 필드 추가
        JTextField startMonthField = new JTextField(2); // 시작 월 필드
        JTextField startDayField = new JTextField(2); // 시작 일 필드
        JTextField endMonthField = new JTextField(2); // 종료 월 필드
        JTextField endDayField = new JTextField(2); // 종료 일 필드

        datePanel.add(startMonthField);
        datePanel.add(new JLabel("월")); // 월 라벨 추가
        datePanel.add(startDayField);
        datePanel.add(new JLabel("일")); // 일 라벨 추가
        datePanel.add(new JLabel("~")); // 구분자
        datePanel.add(endMonthField);
        datePanel.add(new JLabel("월")); // 종료 월 라벨 추가
        datePanel.add(endDayField);
        datePanel.add(new JLabel("일")); // 종료 일 라벨 추가

        inputPanel.add(datePanel);

        // 난이도
        inputPanel.add(new JLabel("난이도 :"));
        JPanel difficultyPanel = new JPanel(); // 난이도 라디오 버튼을 위한 패널
        JRadioButton easyButton = new JRadioButton("쉬움");
        JRadioButton normalButton = new JRadioButton("보통");
        JRadioButton hardButton = new JRadioButton("어려움");

        // ButtonGroup을 사용하여 라디오 버튼 그룹화
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyButton);
        difficultyGroup.add(normalButton);
        difficultyGroup.add(hardButton);

        difficultyPanel.add(easyButton);
        difficultyPanel.add(normalButton);
        difficultyPanel.add(hardButton);
        inputPanel.add(difficultyPanel);

        // 알람 라디오 버튼 추가
        JPanel alarmPanel = new JPanel(); // 알람 버튼을 위한 패널
        alarmPanel.add(new JLabel("알람 :")); // 알람 라벨 추가
        JRadioButton alarmOnButton = new JRadioButton("0");
        JRadioButton alarmOffButton = new JRadioButton("X");
        ButtonGroup alarmGroup = new ButtonGroup(); // 라디오 버튼 그룹화
        alarmGroup.add(alarmOnButton);
        alarmGroup.add(alarmOffButton);

        alarmPanel.add(alarmOnButton);
        alarmPanel.add(alarmOffButton);
        inputPanel.add(alarmPanel); // 입력 패널에 추가

        // 출력하기 버튼 추가
        JButton submitButton = new JButton("출력하기");
        submitButton.setPreferredSize(new Dimension(150, 60)); // 버튼 크기 설정

        // 버튼 클릭 시 새 창 열기
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 새 JFrame 생성
                JFrame newFrame = new JFrame("출력 내용");
                newFrame.setSize(350, 600); // 새 창 크기 설정
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 새 창 닫기 설정

                // 내용 표시 패널 생성
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS)); // 수직 방향으로 레이아웃 설정

                // 설정값 레이블 추가
                JLabel settingsLabel = new JLabel("설정값 : ");
                newPanel.add(settingsLabel); // 레이블 추가

                // 출력 내용 표시
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
                outputArea.setEditable(false); // 처음에는 편집 불가
                JScrollPane outputScrollPane = new JScrollPane(outputArea);
                outputScrollPane.setPreferredSize(new Dimension(250, 120)); // 크기 조정
                newPanel.add(outputScrollPane); // 스크롤 가능하게 추가

                // 시간계획표 레이블 추가
                JLabel scheduleLabel = new JLabel("\n시간계획표 : ");
                newPanel.add(scheduleLabel); // 레이블 추가

                // 사용자 입력용 JTextArea 추가
                JTextArea userInputArea = new JTextArea();
                userInputArea.setLineWrap(true);
                userInputArea.setWrapStyleWord(true);
                userInputArea.setEditable(false); // 처음에는 편집 불가
                JScrollPane userInputScrollPane = new JScrollPane(userInputArea);
                userInputScrollPane.setPreferredSize(new Dimension(250, 120)); // 크기 조정
                newPanel.add(userInputScrollPane); // 스크롤 가능하게 추가

                // 버튼 패널 생성
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬

                JButton changeButton = new JButton("변경하기");
                JButton saveButton = new JButton("파일 저장하기");

                // 변경하기 버튼 클릭 시 편집 가능하게 설정
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        userInputArea.setEditable(true); // 편집 가능
                    }
                });

                // 파일 저장하기 버튼 클릭 시 파일 저장 기능 추가
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
                        String timePlan = userInputArea.getText(); // 시간계획표

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
                            // HashMap에 저장된 모든 항목을 파일에 저장
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

                buttonPanel.add(changeButton); // 변경하기 버튼 추가
                buttonPanel.add(saveButton); // 파일 저장하기 버튼 추가

                newPanel.add(buttonPanel); // 버튼 패널 추가

                newFrame.add(newPanel); // 새 패널을 새 창에 추가
                newFrame.setVisible(true); // 새 창 보이기

                // 난이도 체크 및 시간계획표 생성
                int endDay = Integer.parseInt(endDayField.getText());
                int endMonth = Integer.parseInt(endMonthField.getText());
                int calculatedDay1;

// 난이도에 따른 시간계획표 생성
                if (easyButton.isSelected() || normalButton.isSelected() || hardButton.isSelected()) {
                    int days = easyButton.isSelected() ? 3 : normalButton.isSelected() ? 5 : 7;
                    StringBuilder schedule = new StringBuilder();

                    for (int i = 0; i < days; i++) {
                        // 날짜 계산 로직 추가
                        int day = endDay - (days - i - 1); // 현재 날짜에서 차감
                        if (day < 1) { // 날짜가 1보다 작으면
                            if (endMonth % 2 == 0) { // 짝수 월
                                // 2일 차감
                                day += 30; // 30일로 순환
                            } else { // 홀수 월
                                // 2일 차감
                                day += 31; // 31일로 순환

                            }
                        }
                        schedule.append(day).append("일: ")
                                .append(getTaskDescription(i, easyButton.isSelected(), normalButton.isSelected(), hardButton.isSelected())).append("\n");
                    }
                    userInputArea.setText(schedule.toString()); // 리스트 내용을 JTextArea에 추가
                }

                newFrame.add(newPanel); // 새 패널을 새 창에 추가
                newFrame.setVisible(true); // 새 창 보이기
            }
        });

        inputPanel.add(submitButton);
        // 입력 패널을 프레임에 추가
        this.add(inputPanel, BorderLayout.CENTER);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // 오른쪽 정렬

        JButton noticeButton = new JButton("공지사항"); // 이름 변경
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 지정된 URL 열기
                    Desktop.getDesktop().browse(new URI("https://hive.cju.ac.kr"));
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(FinalTest.this, "페이지를 열 수 없습니다: " + ex.getMessage());
                }
            }
        });

        JButton phoneLinkButton = new JButton("휴대폰 연동"); // 이름 변경
        // 휴대폰 연동 버튼 클릭 시 처리
        phoneLinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 새 JFrame 생성
                JFrame phoneLinkFrame = new JFrame("휴대폰 연동 안내");
                phoneLinkFrame.setSize(400, 200); // 새 창 크기 설정
                phoneLinkFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 새 창 닫기 설정

                // 내용 표시 패널 생성
                JPanel messagePanel = new JPanel();
                messagePanel.setLayout(new BorderLayout()); // 레이아웃 설정

                // 안내 문구 추가
                String message = "휴대폰 앱과 연동하는 시스템을 만들어서\n" +
                        "휴대폰 앱으로 알람기능과 공유시스템 등을 넣고 싶었지만\n" +
                        "아직 시간과 실력이 부족했습니다.";
                JTextArea messageArea = new JTextArea(message);
                messageArea.setWrapStyleWord(true); // 단어 단위로 줄 바꿈
                messageArea.setLineWrap(true); // 줄 바꿈 허용
                messageArea.setEditable(false); // 편집 불가
                messageArea.setBackground(null); // 배경 투명

                messagePanel.add(messageArea, BorderLayout.CENTER); // 패널에 문구 추가

                phoneLinkFrame.add(messagePanel); // 새 창에 패널 추가
                phoneLinkFrame.setVisible(true); // 새 창 보이기
            }
        });

        buttonPanel.add(noticeButton);
        buttonPanel.add(phoneLinkButton);

        // 버튼 패널을 프레임에 추가
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    // 각 난이도에 따른 작업 설명을 반환하는 메소드
    private String getTaskDescription(int dayIndex, boolean isEasy, boolean isNormal, boolean isHard) {
        if (isEasy) {
            switch (dayIndex) {
                case 0: return "과제의 주제 분석, 관련 자료 조사";
                case 1: return "첫 번째 부분 작성 (예: 서론, 일부 본문)";
                case 2: return "나머지 부분 작성 및 최종 검토";
            }
        } else if (isNormal) {
            switch (dayIndex) {
                case 0: return "과제의 주제 및 요구사항 분석, 필요한 자료 수집";
                case 1: return "첫 번째 부분 작성 (예: 서론과 첫 번째 섹션)";
                case 2: return "두 번째 섹션 작성 및 중간 점검";
                case 3: return "세 번째 섹션 작성, 자료 정리 및 참고문헌 추가";
                case 4: return "전체 작업 검토 및 수정, 최종 제출 준비";
            }
        } else if (isHard) {
            switch (dayIndex) {
                case 0: return "과제의 주제 선정 및 요구사항 분석, 필요한 자료 수집";
                case 1: return "자료 조사 및 분석, 주제에 대한 첫 번째 아이디어 구상";
                case 2: return "서론 및 첫 번째 섹션 작성";
                case 3: return "두 번째 섹션 작성, 중간 점검 및 내용 보강";
                case 4: return "세 번째 섹션 작성, 추가 자료 정리 및 분석";
                case 5: return "네 번째 섹션 작성 및 수정, 참고문헌 추가";
                case 6: return "전체 작업 검토 및 최종 수정, 제출 준비";
            }
        }
        return ""; // 기본값
    }

    public static void main(String[] args) {
        new FinalTest();
    }
}
