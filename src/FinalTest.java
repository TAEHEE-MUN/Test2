import javax.swing.*;
import java.awt.*;

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
        contentArea.setPreferredSize(new Dimension(250, 120)); // 너비 250, 높이 120으로 설정
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
