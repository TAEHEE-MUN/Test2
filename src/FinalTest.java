import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FinalTest extends JFrame {

    public FinalTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320, 440); // 프레임 크기 조정
        this.setTitle("과제 자동 스케쥴링");

        // 입력 패널 생성
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout 사용

        // 과제/시험
        inputPanel.add(new JLabel("과제 :"));
        JTextField assignmentField = new JTextField(20); // 너비를 20으로 설정
        assignmentField.setHorizontalAlignment(JTextField.CENTER); // 가운데 정렬
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

                // 난이도 체크 및 시간계획표 생성
                int endDay = Integer.parseInt(endDayField.getText());
                int endMonth = Integer.parseInt(endMonthField.getText());
                int calculatedDay1;

                // 쉬움 난이도 처리
                if (easyButton.isSelected()) {
                    if (endDay > 3) {
                        calculatedDay1 = endDay - 3;
                    } else {
                        if (endMonth % 2 == 1) { // 홀수일 경우
                            calculatedDay1 = (31 + endDay) - 3;
                        } else { // 짝수일 경우
                            calculatedDay1 = (30 + endDay) - 3;
                        }
                    }

                    // 시간계획표 내용 추가 (쉬움)
                    StringBuilder schedule = new StringBuilder();
                    for (int i = 0; i < 3; i++) {
                        int day = calculatedDay1 + i;
                        if (endMonth % 2 == 1) { // 홀수일 경우
                            day = (day - 1) % 31 + 1; // 1~31로 순환
                        } else { // 짝수일 경우
                            day = (day - 1) % 30 + 1; // 1~30으로 순환
                        }

                        schedule.append(day).append("일: ");
                        switch (i) {
                            case 0:
                                schedule.append("과제의 주제 분석, 관련 자료 조사\n");
                                break;
                            case 1:
                                schedule.append("첫 번째 부분 작성 (예: 서론, 일부 본문)\n");
                                break;
                            case 2:
                                schedule.append("나머지 부분 작성 및 최종 검토\n");
                                break;
                        }
                    }
                    userInputArea.setText(schedule.toString());
                }

                // 보통 난이도 처리
                else if (normalButton.isSelected()) {
                    if (endDay > 5) { // 5보다 클 경우
                        calculatedDay1 = endDay - 5;
                    } else {
                        // 5 이하일 경우 예외 처리
                        if (endMonth % 2 == 1) { // 홀수일 경우
                            calculatedDay1 = (31 + endDay) - 5;
                        } else { // 짝수일 경우
                            calculatedDay1 = (30 + endDay) - 5;
                        }
                    }

                    // 시간계획표 내용 추가 (보통)
                    StringBuilder schedule = new StringBuilder();
                    for (int i = 0; i < 5; i++) {
                        int day = calculatedDay1 + i;
                        if (endMonth % 2 == 1) { // 홀수일 경우
                            day = (day - 1) % 31 + 1; // 1~31로 순환
                        } else { // 짝수일 경우
                            day = (day - 1) % 30 + 1; // 1~30으로 순환
                        }

                        schedule.append(day).append("일: ");
                        switch (i) {
                            case 0:
                                schedule.append("과제의 주제 및 요구사항 분석, 필요한 자료 수집\n");
                                break;
                            case 1:
                                schedule.append("첫 번째 부분 작성 (예: 서론과 첫 번째 섹션)\n");
                                break;
                            case 2:
                                schedule.append("두 번째 섹션 작성 및 중간 점검\n");
                                break;
                            case 3:
                                schedule.append("세 번째 섹션 작성, 자료 정리 및 참고문헌 추가\n");
                                break;
                            case 4:
                                schedule.append("전체 작업 검토 및 수정, 최종 제출 준비\n");
                                break;
                        }
                    }
                    userInputArea.setText(schedule.toString());
                }

                // 어려움 난이도 처리
                else if (hardButton.isSelected()) {
                    if (endDay > 7) { // 7보다 클 경우
                        calculatedDay1 = endDay - 7;
                    } else {
                        // 7 이하일 경우 예외 처리
                        if (endMonth % 2 == 1) { // 홀수일 경우
                            calculatedDay1 = (31 + endDay) - 7;
                        } else { // 짝수일 경우
                            calculatedDay1 = (30 + endDay) - 7;
                        }
                    }

                    // 시간계획표 내용 추가 (어려움)
                    StringBuilder schedule = new StringBuilder();
                    for (int i = 0; i < 7; i++) {
                        int day = calculatedDay1 + i;
                        if (endMonth % 2 == 1) { // 홀수일 경우
                            day = (day - 1) % 31 + 1; // 1~31로 순환
                        } else { // 짝수일 경우
                            day = (day - 1) % 30 + 1; // 1~30으로 순환
                        }

                        schedule.append(day).append("일: ");
                        switch (i) {
                            case 0:
                                schedule.append("과제의 주제 선정 및 요구사항 분석, 필요한 자료 수집\n");
                                break;
                            case 1:
                                schedule.append("자료 조사 및 분석, 주제에 대한 첫 번째 아이디어 구상\n");
                                break;
                            case 2:
                                schedule.append("서론 및 첫 번째 섹션 작성\n");
                                break;
                            case 3:
                                schedule.append("두 번째 섹션 작성, 중간 점검 및 내용 보강\n");
                                break;
                            case 4:
                                schedule.append("세 번째 섹션 작성, 추가 자료 정리 및 분석\n");
                                break;
                            case 5:
                                schedule.append("네 번째 섹션 작성 및 수정, 참고문헌 추가\n");
                                break;
                            case 6:
                                schedule.append("전체 작업 검토 및 최종 수정, 제출 준비\n");
                                break;
                        }
                    }
                    userInputArea.setText(schedule.toString());
                }

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
                        String filePath = "C:\\Users\\tea00\\OneDrive\\바탕 화면\\AutoScheduling.txt";
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                            writer.write("과제/시험: " + assignmentField.getText() + "\n");
                            writer.write("내용: " + contentArea.getText() + "\n");
                            writer.write("제출기한: " + startMonthField.getText() + "월 " + startDayField.getText() + "일 ~ " +
                                    endMonthField.getText() + "월 " + endDayField.getText() + "일\n");
                            writer.write("난이도: " + (easyButton.isSelected() ? "쉬움" :
                                    normalButton.isSelected() ? "보통" : "어려움") + "\n");
                            writer.write("알람: " + (alarmOnButton.isSelected() ? "0" : "X") + "\n"+ "\n");
                            writer.write("시간계획표: \n" + userInputArea.getText() + "\n"); // 수정된 부분
                            JOptionPane.showMessageDialog(newFrame, "파일이 저장되었습니다: " + filePath);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(newFrame, "파일 저장 중 오류 발생: " + ex.getMessage());
                        }
                    }
                });

                buttonPanel.add(changeButton); // 변경하기 버튼 추가
                buttonPanel.add(saveButton); // 파일 저장하기 버튼 추가

                newPanel.add(buttonPanel); // 버튼 패널 추가

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

        buttonPanel.add(noticeButton);
        buttonPanel.add(phoneLinkButton);

        // 버튼 패널을 프레임에 추가
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FinalTest();
    }
}