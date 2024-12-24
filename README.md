# 과제 관리 프로그램

## 1. 개요



### 1.1. 목적

본 프로그램의 목적은 사용자가 과제를 효율적으로 관리하고, 제출 기한에 맞추어 과제를 완료할 수 있도록 돕는 것입니다. 이를 위해 과제 정보를 입력받고, 난이도 및 제출 기한을 기준으로 자동으로 시간 계획을 수립합니다. 또한 사용자가 시간 계획을 쉽게 출력하고 수정할 수 있도록 GUI 인터페이스를 제공합니다. 시간계획은 자율적으로 변경해 본인의 스케쥴에 맞도록 수정하여 조절할 수 있습니다. 파일을 만들어 휴대폰 연동을 통해 공유 수단으로 사용하며 시간 계획에 맞춰 알람 시스템을 작동 시킵니다. 마지막으로 제출 및 공지 확인을 쉽게 만드는 경로도 포함되어 있습니다

### 1.2. 대상

이 프로그램은 과제를 관리하고자 하는 모든 사용자를 대상으로 합니다. 특히 다수의 과제를 동시에 처리해야 하거나, 과제 제출 기한을 맞추기 위해 계획적으로 작업을 수행해야 하는 사람들이 유용하게 사용할 수 있습니다.

## 2. 프로그램의 중요성 및 필요성

### 2.1. 중요성

이 프로그램은 과제 관리 및 시간 계획의 중요성을 강조합니다. 다양한 과제를 동시에 처리해야 하는 상황에서 시간을 효율적으로 배분하고 관리하는 능력은 매우 중요합니다. 이 프로그램은 과제를 체계적으로 정리하고, 제출 기한을 놓치지 않도록 돕는 중요한 도구입니다.

### 2.2. 필요성

많은 사람들은 과제나 업무를 할 때, 시간을 제대로 배분하지 못해 제출 기한을 놓치거나, 너무 많은 업무를 한 번에 처리하려다가 과제를 제때 완료하지 못하는 경우가 많습니다. 이 프로그램은 이러한 문제를 해결할 수 있도록 도와줍니다. 사용자에게 필요한 정보를 입력받고, 자동으로 계획을 세워 주므로 과제나 업무에 대한 스트레스를 줄여주는 도구입니다.

## 3. 프로그램 수행 절차

### 3.1. 다이어그램

![자바 순서도 (1)](https://github.com/user-attachments/assets/8eba4c25-fdb2-4d11-9199-541bbff59f4d)

### 3.2. 클래스 다이어그램

![자바 다이어그램](https://github.com/user-attachments/assets/687456e9-97e8-45e0-9641-8a9461f72692)


### 3.3. 절차 설명

1. 사용자 입력: 사용자는 과제 내용, 마감일, 난이도 및 필요한 작업을 입력합니다.

![메인화면](https://github.com/user-attachments/assets/5d74fb60-14ad-4417-b8f1-6bca3d06e5b6)
 
2. 시간 계획 생성: 입력된 정보를 바탕으로 프로그램이 자동으로 시간 계획을 생성합니다.

### 난이도 쉬움 선택
 ![쉬움](https://github.com/user-attachments/assets/77da8adf-dbaa-4c14-b5bd-d8b2f87a6f69)
### 난이도 보통 선택
 ![보통](https://github.com/user-attachments/assets/e0a96cb8-b4a5-4a29-af11-e55855612b73)
### 난이도 어려움 선택
 ![어려움](https://github.com/user-attachments/assets/524a630c-b887-44d4-86e4-b2b7a49aa71d)

3. 수정 기능: 사용자는 생성된 계획을 수정할 수 있으며, 필요에 따라 작업을 추가하거나 삭제할 수 있습니다.
### 자율적 계획 변경
![변경하기](https://github.com/user-attachments/assets/cf3c7188-13e8-4918-8753-7d9c21630cf4)

4. 최종 출력: 최종적으로 수정된 계획이 화면에 출력되며, 파일로 저장할 수 있는 기능도 제공합니다.
![파일 저장완료](https://github.com/user-attachments/assets/746f3e64-c438-4c96-bd49-3b2eec3c55c3)
### 파일에 저장
![변경된 파일](https://github.com/user-attachments/assets/5e0c4530-f60e-4e44-9ec3-f4efca0459d9)

5. 연동 기능: 휴대폰과 연동을 하여 알람기능과 공유기능 사용하여 원활한 작업 수행이 가능합니다.(미구현)

6. URL 이동: 공지사항으로 이동되며 제출 및 과제 재검토 확인할 수 있습니다.
### 공지사항 이동
![공지사항](https://github.com/user-attachments/assets/7405821d-5e89-4afe-9503-492eda4ab0c7)


## 4. 느낀점

첫째, 사용자 중심의 설계가 얼마나 중요한지 실감하게 되었습니다. 사용자들이 과제 관리와 시간 계획을 세울 때, 프로그램의 인터페이스와 흐름이 직관적이고 사용하기 쉬워야 한다는 점을 명확히 깨달았습니다. 이를 위해 GUI 설계에 신경을 많이 썼고, 사용자가 프로그램을 편리하게 활용할 수 있도록 피드백을 주는 요소들을 추가했습니다.

둘째, 자동화의 힘을 다시 한 번 확인할 수 있었습니다. 사용자가 입력한 과제 정보에 따라 자동으로 시간 계획을 생성해주는 기능은 단순하지만 매우 중요한 역할을 합니다. 이 과정을 통해, 자동화된 시간 관리 시스템이 얼마나 사용자에게 실질적인 도움을 줄 수 있는지를 깨달았습니다. 이 기능 덕분에 과제 관리에 드는 시간과 노력을 줄이고, 중요한 기한을 놓치지 않게 됩니다.

셋째, 테스트와 디버깅의 중요성을 깊이 이해했습니다. 프로그램을 개발하는 과정에서 발생하는 작은 오류나 버그는 예상보다 더 큰 문제로 확산될 수 있음을 경험했습니다. 따라서 개발 초기부터 철저한 테스트와 디버깅을 거쳐야 함을 깨달았습니다. 코드의 각 부분이 어떻게 상호작용하는지, 사용자가 입력하는 다양한 데이터를 처리할 수 있도록 준비하는 과정에서 많은 시간을 투자했지만, 그만큼 프로그램의 안정성도 크게 향상되었습니다.

마지막으로, 프로젝트 관리의 중요성도 큰 깨달음을 얻었습니다. 처음엔 나에게 필요한 과제 관리 프로그램을 만들었습니다. 이를 통해, 실제로 이 프로그램을 사용하면 얼마나 효율적으로 시간과 과제를 관리할 수 있을지 궁금합니다. 개발과정에서 느낀 것은, 기술적인 구현뿐만 아니라 프로그램의 실제 사용성을 고려한 설계가 얼마나 중요한지에 대한 인식이었습니다.

다만 아쉬웠던 점은 휴대폰 연동을 통해 알람기능과 공유기능을 사용해 유용한 어플이 되는 욕심을 부렸지만 시간과 부족한 실력으로 개발에 대한 목표을 완전히 도달하지못해 아쉽습니다. 이 프로그램 개발을 통해 얻은 경험은 앞으로도 많은 프로젝트에서 유용하게 활용될 것입니다. 
