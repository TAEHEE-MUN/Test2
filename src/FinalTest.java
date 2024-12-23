import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FinalTest extends JFrame {

    public FinalTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 440); // 프레임 크기 조정
        this.setTitle("과제 및 시험 자동 스케쥴링");

        // 입력 패널 생성
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout 사용

        // 과제/시험
        inputPanel.add(new JLabel("과제/시험 :"));
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
        JTextField startDateField = new JTextField(9); // 시작 날짜 필드
        startDateField.setHorizontalAlignment(JTextField.CENTER); // 가운데 정렬
        JTextField endDateField = new JTextField(9); // 종료 날짜 필드
        endDateField.setHorizontalAlignment(JTextField.CENTER); // 가운데 정렬
        datePanel.add(startDateField);
        datePanel.add(new JLabel("~")); // 구분자
        datePanel.add(endDateField);
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
                newFrame.setSize(300, 400); // 새 창 크기 설정
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
                        "제출기한: " + startDateField.getText() + " ~ " + endDateField.getText() + "\n" +
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
                JLabel scheduleLabel = new JLabel("시간계획표 : ");
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
                        String filePath = "C:\\Users\\tea00\\OneDrive\\바탕 화면\\AutoScheduling.txt";
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                            writer.write("과제/시험: " + assignmentField.getText() + "\n");
                            writer.write("내용: " + contentArea.getText() + "\n");
                            writer.write("제출기한: " + startDateField.getText() + " ~ " + endDateField.getText() + "\n");
                            writer.write("난이도: " + (easyButton.isSelected() ? "쉬움" :
                                    normalButton.isSelected() ? "보통" : "어려움") + "\n");
                            writer.write("알람: " + (alarmOnButton.isSelected() ? "0" : "X") + "\n");
                            writer.write("사용자 입력: " + userInputArea.getText() + "\n");
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