import com.sun.nio.sctp.AbstractNotificationHandler;

import java.awt.font.MultipleMaster;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    // [PCCP 기출문제] 1번 / 동영상 재생기
    public String solution_1(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int videoLength = Integer.parseInt(video_len.substring(0,2))*60 + Integer.parseInt(video_len.substring(3,5));
        System.out.println(videoLength);
        int currentPos = Integer.parseInt(pos.substring(0,2))*60 + Integer.parseInt(pos.substring(3,5));
        System.out.println(currentPos);
        int videoEnd = Integer.parseInt(op_end.substring(0,2))*60 + Integer.parseInt(op_end.substring(3,5));
        int videoStart = Integer.parseInt(op_start.substring(0,2))*60 + Integer.parseInt(op_start.substring(3,5));

        for (int i = 0; i < commands.length; i++) {
            if(videoStart <= currentPos && currentPos <= videoEnd){
                currentPos = videoEnd;
            }
            if(commands[i].equals("next")){
                if(videoLength-currentPos < 10) {
                    currentPos = videoLength;
                    continue;
                }
                currentPos += 10;
            }
            if(commands[i].equals("prev")){
                if(currentPos < 10) {
                    currentPos = 0;
                    continue;
                }
                currentPos -= 10;
            }
        }

        System.out.println(currentPos);
        int minutes = currentPos / 60;
        int remainingSeconds = currentPos % 60;

        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    // 올바른 괄호
    public boolean solution_2(String s){
        Deque<String> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            // 최초 ")" 입력 시 리턴
            if(s.substring(0,1).equals(")")){
                return false;
            }

            String current = s.substring(i, i+1);
            if(current.equals("(")){
                deque.add(current);
            }
            else{
                // ")" 입력 시 리턴
                if(deque.isEmpty()){
                    return false;
                }
                deque.pop();
            }
        }
        if (deque.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    // 숫자의 표현
    public int solution_3(int n){
        int answer = 0;
        // 자연수 배열 초기화
        int[] number = new int[10000];
        for (int i = 0; i < number.length; i++) {
            number[i] = i+1;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        // 로직 시작
        while (right <= number.length){
            sum = 0;
            for (int i = left; i < right; i++) {
                sum += number[i];
            }
            if (sum < n){
                right++;
            } else if (sum > n) {
                left++;
            }
            if (sum == n){
                answer++;
                left++;
            }
        }

        return answer;
    }

    // 문자열 다루기 기본
    public boolean solution_4(String s){
        boolean answer = true;
        if(s.length()==4 || s.length()==6){
            for (int i = 0; i < s.length(); i++) {
                char currentChar = s.charAt(i);
                if((currentChar >= 65 && currentChar <= 97) || (currentChar >= 97 && currentChar <= 122)){
                    answer = false;
                }
            }
        } else{
            answer = false;
        }
        return answer;
    }

    // 나머지가 1이 되는 수 찾기
    public int solution_5(int n){
        int answer = 0;
        int x = n-1;

        while(true){
            if(n%x==1){
                answer=x;
                for (int i = 2; i <= x/2; i++) {
                    if(x%i==0){
                        answer = i;
                        break;
                    }
                }
            }
            break;
        }

        return answer;
    }

    // x만큼 간격이 있는 n개의 숫자
    public long[] solution_6(int x, int n){
        long[] answer = new long[n];
        long number = x;
        for (int i = 0; i < n; i++) {
            answer[i] = number;
            number = answer[i]+x;
        }
        return answer;
    }

    // 문자열 내 p와 y의 개수
    public boolean solution_7(String s){
        long p = 0;
        long y = 0;

        p = s.chars().filter(c -> c == 'p' || c == 'P').count();
        y = s.chars().filter(c -> c == 'y' || c == 'Y').count();

        if(p!=y){
            return false;
        } else {
            return true;
        }
    }

    // 자연수 뒤집어 배열로 만들기
    public int[] solution_8(long n){
        Long number = n;
        String numberStr = number.toString();
        int[] answer = new int[numberStr.length()];
        int numberStrIndex = numberStr.length()-1;
        for (int i = 0; i < numberStr.length(); i++) {
            answer[i] = Character.getNumericValue(numberStr.charAt(numberStrIndex));
            numberStrIndex--;
        }
        // 모범답안
        // return new StringBuilder().append(n).reverse().chars().map(Character::getNumericValue).toArray();
        return answer;
    }

    // 자릿수 더하기
    public int solution_9(int n){
        return new StringBuilder().append(n).chars().map(Character::getNumericValue).sum();
    }

    // 정수 내림차순으로 배치하기
    public long solution_10(long n){
        return Long.parseLong(
                String.valueOf(n).chars()
                        .map(Character::getNumericValue)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .map(String::valueOf)
                        .collect(Collectors.joining()));
    }

    // 정수 제곱근 판별
    public long solution_11(long n){

        double number = Math.sqrt(Double.parseDouble(String.valueOf(n)));

        if(number-Math.floor(number)>0){
            return -1;
        } else{
            return (long) ((number+1)*(number+1));
        }
    }

    // 하샤드 수
    public boolean solution_12(int x){
        int num = new StringBuilder().append(x).chars().map(Character::getNumericValue).sum();
        if(x%num==0){
            return true;
        } else {
            return false;
        }
    }

    // 두 정수 사이의 합
    public long solution_13(int a, int b){
        long answer = 0;
        int sum = 0;
        if(a==b) return a;
        if(a>b){
            answer=b;
            for (int i = 0; i<=a-b; i++) {
                sum += answer;
                answer++;
            }
        } else if (a<b) {
            answer=a;
            for (int i = 0; i<=b-a; i++){
                sum += answer;
                answer++;
            }
        }
        return sum;
    }

    // 서울에서 김서방 찾기
    public String solution_14(String[] seoul){
        int pos = 0;
        for (int i = 0; i < seoul.length; i++) {
            if(seoul[i].equals("Kim")){
                pos = i;
            };
        }
        return "김서방은 " + pos + "에 있다";
    }

    // 콜라츠 추측
    public int solution_15(long num){
        int time = 0;
        if(num==1)return 0;
        while(time<500){
            time++;
            if(num%2==0){
                num/=2;
            } else if (num % 2 != 0) {
                num=num*3+1;
            }
            if(num==1) return time;
        }
        return -1;
    }

    // 없는 숫자 더하기
    public int solution_16(int[] numbers){
        int[] answer = new int[10];
        int sum = 0;

        for (int i = 0; i < answer.length; i++) {
            answer[i] = 0;
        }
        for (int i = 0; i < numbers.length; i++) {
            answer[numbers[i]] = 1;
        }
        for (int i = 0; i < answer.length; i++) {
            if(answer[i]==0){
                sum += i;
            }
        }
        return sum;
    }

    // 나누어 떨어지는 숫자 배열
    public ArrayList<Integer> solution_17(int[] arr, int divisor){
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]%divisor==0){
                answer.add(arr[i]);
            }
        }
        if(answer.isEmpty()){
            answer.add(-1);
        } else{
            answer.sort(Comparator.naturalOrder());
        }
        return answer;
    }

    // 제일 작은 수 제거하기
    public int[] solution_18(int[] arr){
//        if(arr.length==1) {
//            List<Integer> answer = new ArrayList<>();
//            answer.add(-1);
//            return answer;
//        }
//        int min = Arrays.stream(arr).min().getAsInt();
//        List<Integer> answer = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        answer.remove(answer.indexOf(min));
//        return answer;

        if(arr.length==1) return new int[] {-1};
        int min = Arrays.stream(arr).min().getAsInt();
        return Arrays.stream(arr).filter(i -> i != min).toArray();
    }

    // 핸드폰 번호 가리기
    public String solution_19(String phone_number){
        String answer = "";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < phone_number.length()-4; i++) {
            stringBuffer.append("*");
        }
        answer = stringBuffer.toString();
        return answer + phone_number.substring(phone_number.length()-4, phone_number.length());
    }

    // 행렬의 덧셈
    public int[][] solution_20(int[][] arr1, int[][] arr2){
        int[][] answer = {};
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return answer;
    }

    // 직사각형 별찍기
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            stringBuilder.append('*');
        }
        for (int i = 0; i < cols; i++) {
            System.out.println(stringBuilder);
        }
    }

    // 최대공약수와 최소공배수
    public int[] solution_21(int n, int m){
        int[] answer = new int[2];

        int a,b;

        if(n>m){
            a=n;
            b=m;
        } else{
            a=m;
            b=n;
        }

        while (b!=0){
            int temp = b;
            b = a % b;
            a = temp;
        }

        int gcd = a;
        int lcm = (n*m)/gcd;

        answer[0] = gcd;
        answer[1] = lcm;

        return answer;
    }

    // 예산
    public int solution_22(int[] d, int budget){
        Arrays.sort(d);
        int sum = 0;
        int count = 0;

        for(int amount : d){
            if ( sum + amount > budget) break;
            sum += amount;
            count++;
        }
        return count;
    }

    // 크기가 작은 부분 문자열
    public long solution_23(String t, String p){
        long count = 0;
        for (int i = 0; i <= t.length()-p.length(); i++) {
            if(Long.parseLong(t.substring(i, p.length()+i))<=Long.parseLong(p)){
                count++;
            };
        }
        return count;
    }

    // 이상한 문자 만들기
    public String solution_24(String s){
        String[] stringArr = s.split(" ", -1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringArr.length; i++) {
            char[] newString = stringArr[i].toCharArray();
            for (int j = 0; j < stringArr[i].length(); j++) {
                if(j%2==0){
                    newString[j] = Character.toUpperCase(newString[j]);
                } else if (j%2==1){
                    newString[j] = Character.toLowerCase(newString[j]);
                }
            }
            sb.append(newString);
            if(i<stringArr.length+1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // 삼총사
    public static int  n,r, count;
    public static int[] output;
    public int solution_25(int[] number){
        n = number.length;
        r = 3;
        output = new int[r];
        combi(0, 0, number);
        return count;
    }

    public static void combi(int cnt, int start, int[] number){
        if (cnt == r){
            if(Arrays.stream(output).sum() == 0){
                count++;
            }
            return;
        }
        for (int i = start; i < n; i++) {
            output[cnt] = number[i];
            combi(cnt+1, i+1, number);
        }
    }
    // 최소직사각형
    public int solution_26(int[][] sizes) {
        int maxWidth = 0;
        int maxHeigth = 0;
        for (int i = 0; i < sizes.length; i++) {
            int max = Math.max(sizes[i][0], sizes[i][1]);
            int min = Math.min(sizes[i][0], sizes[i][1]);

            sizes[i][0] = max;
            sizes[i][1] = min;

            maxWidth = Math.max(maxWidth, sizes[i][0]);
            maxHeigth = Math.max(maxHeigth, sizes[i][1]);
        }
        return maxWidth * maxHeigth;
    }

    public List<Integer> solution_27(int[] answers){
        int[] student1 = new int[]{1,2,3,4,5};
        int[] student2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] student3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] result = new int[3];
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == student1[i%5]){
                result[0]++;
            }
            if(answers[i] == student2[i%8]){
                result[1]++;
            }
            if(answers[i] == student3[i%10]){
                result[2]++;
            }
        }
        int max = Arrays.stream(result).max().getAsInt();
        for (int i = 0; i < result.length; i++) {
            if(result[i]==max){
                answer.add(i);
            }
        }

        return answer;
    }

    public String solution_28(String s, int n){
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]==' ') continue;
            if(chars[i]>=65 && chars[i]<=90){
                if(chars[i]+n>90){
                    chars[i] = (char) (chars[i]+n-26);
                } else{
                    chars[i] = (char) (chars[i]+n);
                }
            } else if (chars[i]>=97 && chars[i]<=122) {
                if(chars[i]+n>122){
                    chars[i] = (char) (chars[i]+n-26);
                } else{
                    chars[i] = (char) (chars[i]+n);
                }
            }
        }
        return String.valueOf(chars);
    }

    // 카카오 비밀지도
    public List<String> solution_29(int n, int[] arr1, int[] arr2) {
        List<String> answer = new ArrayList<>();
        List<Integer> intArr1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        List<Integer> intArr2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            String prefix1 = "";
            String prefix2 = "";

            String binarys1 = Integer.toBinaryString(intArr1.get(i));
            if(binarys1.length()<n){
                for (int j = 0; j < n-binarys1.length(); j++) {
                    prefix1 += "0";
                }
                binarys1 = prefix1 + binarys1;
            }

            String binarys2 = Integer.toBinaryString(intArr2.get(i));
            if(binarys2.length()<n){
                for (int j = 0; j < n-binarys2.length(); j++) {
                    prefix2 += "0";
                }
                binarys2 = prefix2 + binarys2;
            }

            List<Integer> newNum = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                newNum.add(Integer.parseInt(String.valueOf(binarys1.charAt(j))) | Integer.parseInt(String.valueOf(binarys2.charAt(j))));
            }

            String newStr = new String();
            for (int j = 0; j < n; j++) {
                if(newNum.get(j)==1){
                    newStr += "#";
                } else{
                    newStr += " ";
                }
            }

            answer.add(newStr);
        }
        return answer;

        // 모범답안
//        String[] answer = new String[n];
//        String temp = "";
//        for (int i = 0; i < n; i++) {
//            temp = String.format("%16s", Integer.toBinaryString(arr1[i] | arr2[i]));
//            temp = temp.substring(temp.length() - n);
//            temp = temp.replaceAll("1", "#");
//            temp = temp.replaceAll("0", " ");
//            answer[i] = temp;
//        }

    }

    //[PCCE 기출문제] 9번 / 지폐 접기
    public int solution_30(int[] wallet, int[] bill) {
        int answer = 0;

        while(Math.min(bill[1], bill[0]) > Math.min(wallet[1], wallet[0]) ||
                Math.max(bill[1], bill[0]) > Math.max(wallet[1], wallet[0])){
            if(bill[0] > bill[1]){
                bill[0] = bill[0] / 2;
            } else{
                bill[1] = bill[1] / 2;
            }
            answer++;
        }
        return answer;
    }

    // 카카오 다트게임
    public int solution_31(String dartResult) {
        int answer = 0;
        int idx = 0;
        String tmpStr = "";
        int[] score = new int[3];
        char[] chars = dartResult.toCharArray();

        for (int i = 0; i < dartResult.length(); i++) {
            if( chars[i] >= 48 && chars[i] <= 57){
                tmpStr += String.valueOf(chars[i]);
            } else if (chars[i] >= 65 && chars[i] <= 90) {
                if(chars[i]=='S'){
                    score[idx++] = (int) Math.pow(Integer.parseInt(tmpStr), 1);
                } else if (chars[i]=='D') {
                    score[idx++] = (int) Math.pow(Integer.parseInt(tmpStr), 2);
                } else if (chars[i]=='T'){
                    score[idx++] = (int) Math.pow(Integer.parseInt(tmpStr), 3);
                }
                tmpStr = "";
            } else{
                if(chars[i]=='*'){
                    score[idx-1] *= 2;
                    if(idx-2>=0) {
                        score[idx-2] *= 2;
                    }
                } else if (chars[i]=='#') {
                    score[idx-1] *= (-1);
                }
            }
        }
        answer = score[0] + score[1] + score[2];
        return answer;
    }

    public int[] solution_32(int[] lottos, int[] win_nums) {
        int[] answer = {0,0};
        int count = 0;
        int blank = 0;
        for (int i = 0; i < lottos.length; i++) {
            for (int j = 0; j < win_nums.length; j++) {
                if(lottos[i]==win_nums[j]){
                    count++;
                }
            }
            if(lottos[i]==0){
                blank++;
            }
        }
        int max = count+blank;
        int min = count;
        if(max<2){
            answer[0] = 6;
            answer[1] = 6;
        } else{
            answer[0] = 7-max;
            if(min<2){
                answer[1] = 6;
            } else{
                answer[1] = 7-min;
            }
        }
        return answer;
    }

    // [PCCE 기출문제] 9번 / 이웃한 칸
    public int solution_33(String[][] board, int h, int w) {
        int count = 0;
        int n = board.length;

        int[] dh = new int[] {0,1,-1,0};
        int[] dw = new int[] {1,0,0,-1};

        for (int i = 0; i < n; i++) {
            int h_check = h+dh[i];
            int w_check = w+dw[i];
            if(h_check>=0 && h_check<n && w_check>=0 && w_check<n){
                if(board[h][w]==board[h_check][w_check]){
                    count++;
                }
            }
        }
        return count;
    }

    // 2019 카카오 신입 크레인 인형뽑기 게임
    public int solution_34(int[][] board, int[] moves) {
        int answer = 0;

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < moves.length; i++) {
            int moveIdx = moves[i]-1;
            for (int j = 0; j < board.length; j++) {
                int checkValue = board[j][moveIdx];
                if(checkValue!=0){
                    board[j][moveIdx] = 0;
                    if(checkDollDeque(deque, checkValue)){
                        deque.removeFirst();
                        answer++;
                    } else{
                        deque.addFirst(checkValue);
                    }
                    break;
                }
            }
        }

        return answer*2;
    }
    public boolean checkDollDeque(Deque deque, Integer value){
        if (!deque.isEmpty()){
            if(deque.getFirst().equals(value)){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }

    // PCCE 10번 데이터 분석
    public ArrayList<int[]> solution_35(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] tmpAnswer = new int[500][4];
        ArrayList<int[]> answer = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int arrIdx = 0;
        int extColumnIdx = checkIdx(ext);
        int sortByColumnIdx = checkIdx(sort_by);
        // 데이터 추출
        for (int i = 0; i < data.length; i++) {
            if(data[i][extColumnIdx]<val_ext){
                tmpAnswer[arrIdx++] = data[i];
            }
        }
        // 정렬
        arrIdx = 0;
        for (int i = 0; i < tmpAnswer.length; i++) {
            map.put(i, tmpAnswer[i][sortByColumnIdx]);
        }
        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());

        entryList.sort(Comparator.comparingInt(Map.Entry::getValue));

        for(Map.Entry<Integer, Integer> entry : entryList) {
            if(entry.getValue()!=0){
                answer.add(tmpAnswer[entry.getKey()]);
            }
        }

        return answer;
    }
    public int checkIdx(String s){
        int extColumn;
        if(s.equals("code")){
            extColumn = 0;
        } else if (s.equals("date")) {
            extColumn = 1;
        } else if (s.equals("maximum")) {
            extColumn = 2;
        } else{
            extColumn = 3;
        }
        return extColumn;
    }

    // 2022 카카오 인턴 성격 유형 검사하기
    public String solution_36(String[] survey, int[] choices) {
        String answer = "";
        String[] type = new String[]{"R", "T", "C", "F", "J", "M", "A", "N"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            map.put(type[i], 0);
        }
        for (int i = 0; i < survey.length; i++) {
            String A = survey[i].substring(0,1);
            String B = survey[i].substring(1,2);
            if (choices[i] > 4) {
                map.put(B, map.get(B) + choices[i]-4);
            } else if (choices[i] < 4) {
                map.put(A, map.get(A) + 4-choices[i]);
            }
        }
        for (int i = 0; i <= 8; i+=2) {
            if(map.get(type[i])>=map.get(type[i+1])){
                answer += type[i];
            } else {
                answer += type[i+1];
            }
            if(answer.length()==4) break;
        }
        return answer;
    }

    // 2023 KAKAO 개인정보 수집 유효기간
    public List<Integer> solution_37(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String[] tmpPrivacies = new String[2];
        String[] tmpTerms;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate todayDate = LocalDate.parse(today, formatter);

        for (int i = 0; i < terms.length; i++) {
            tmpTerms = terms[i].split(" ");
            map.put(tmpTerms[0], Integer.parseInt(tmpTerms[1]));
        }
        for (int i = 0; i < privacies.length; i++) {
            tmpPrivacies[0] = privacies[i].substring(0,10);
            tmpPrivacies[1] = privacies[i].substring(11,12);
            LocalDate compareDate = LocalDate.parse(tmpPrivacies[0], formatter);

            int compare = todayDate.compareTo(compareDate.plusMonths(map.get(tmpPrivacies[1])));
            if(compare>=0){
                answer.add(i+1);
            }
            Collections.sort(answer);
        }
        return answer;
    }
    // 최솟값과 최댓값
    public String solution_38(String s) {
        String answer = "";
        String[] tmpStr = s.split(" ");
        int max = Arrays.stream(tmpStr).mapToInt(Integer::parseInt).max().orElseThrow();
        int min = Arrays.stream(tmpStr).mapToInt(Integer::parseInt).min().orElseThrow();
        answer = min + " " + max;
        return answer;
    }

    // JadenCase 문자열 만들기
    public String solution_39(String s) {
//        return Arrays.stream(s.split(" ", -1))  // 연속된 공백도 유지
//                .map(word -> {
//                    if (word.length() > 0) {
//                        // 첫 글자는 대문자, 나머지는 소문자로 변환
//                        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
//                    } else {
//                        return word;  // 공백은 그대로 유지
//                    }
//                })
//                .collect(Collectors.joining(" "));  // 변환된 단어들을 공백으로 연결

        String[] word = s.split(" ", -1);
        return Arrays.stream(word)
                .map( str -> {
                    if(str.length() > 0){
                        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
                    } else{
                        return str;
                    }
                }).collect(Collectors.joining(" "));
    }

    // 피보나치 수 ( 재귀 )
    public int solution_40(int n) {
        int answer = Fibonaci(n);
        return answer%1234567;
    }
    public int Fibonaci(int num) {
        if(num==0||num==1){
            return num;
        }
        return Fibonaci(num-1) + Fibonaci(num-2);
    }

    // 피보나치 수 ( DP )
    int[] numbers = new int[100000];

    public int solution_41(int n) {
        return Fibonaci2(n)%1234567;
    }
    public int Fibonaci2(int n){
        if (numbers[n] == 0) {
            if (n == 1 || n == 2) {
                numbers[n] = 1;
            } else {
                numbers[n] = Fibonaci2(n - 1) + Fibonaci(n - 2);
            }
        }
        return numbers[n];
    }

    // 2017 팁스타운 짝지어 제거하기
    public int solution_42(String s){
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()){
            if(!stack.isEmpty()){
                if(c==stack.peek()){
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else{
                stack.push(c);
            }
        }
        int answer = stack.isEmpty() ? 1 : 0;
        return answer;
    }

    // 카펫
    public int[] solution_43(int brown, int yellow) {
        int[] answer = {};
        return answer;
    }

    // 점프와 순간 이동
    public int solution_44(int n) {
        int ans = 0;
        while(n>=0){
            if(n%2==0){
                n/=2;
            } else{
                n-=1;
                ans+=1;
            }
        }

        return ans;
    }

    // Summer/Winter Coding(~2018) 영어 끝말잇기
    public int[] solution_45(int n, String[] words) {
        int[] answer = {0,0};
        List<List<String>> map = new ArrayList<>();
        Map<Integer, List<String>> multiValueMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            addValue(multiValueMap, i%n, words[i]);
        }
        for (int i = 0; i < n; i++) {
            map.add(multiValueMap.get(i));
        }
        System.out.println(map);
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if(!set.add(map.get(i).get(j))){
                    answer[0]=j+1;
                    answer[1]=i+1;
                    return answer;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if(i==0&&j==0)continue;
                String before = map.get(j-1).get(i);
                String current = map.get(j).get(i);
                System.out.println(before + " " + current + i + " " + j);
                if(before.charAt(before.length()-1)!=current.charAt(0)){
                    answer[0]=i;
                    answer[1]=j;
                    break;
                }
            }
        }

        return answer;
    }
    public static <K, V> void addValue(Map<K, List<V>> map, K key, V value) {
        // 기존 값 리스트를 가져오거나 새로 생성
        List<V> values = map.computeIfAbsent(key, k -> new ArrayList<>());
        // 값 추가
        values.add(value);
    }

    public int solution_46(int n, int a, int b)
    {
        int answer = 1;
        int[] nums = new int[n+1];
        nums[a]=1;
        nums[b]=1;
        for (int i = 0; i < Math.sqrt(n); i++) {
            double tmpA = a;
            double tmpB = b;
            nums[a]=0;
            nums[b]=0;
            a=(int) Math.ceil(tmpA/2);
            b=(int) Math.ceil(tmpB/2);
            nums[a]=1;
            nums[b]=1;
            System.out.println(Arrays.toString(nums));
            for (int j = 1; j < n+1; j+=2) {
                if(nums[j]==1 && nums[j+1]==1){
                    answer = i+2;
                    break;
                }
            }
        }
        return answer;
    }

    public int solution_47(String s) {
        int answer = 0;
        if(checkGwalho(s)){
            answer++;
        }
        for (int i = 1; i < s.length(); i++) {
            char[] tmpChars = s.toCharArray();
            char tmp = tmpChars[0];
            for (int j = 0; j < tmpChars.length-1; j++) {
                tmpChars[j] = tmpChars[j+1];
            }
            tmpChars[tmpChars.length-1] = tmp;
            s = String.valueOf(tmpChars);
            System.out.println(s);
            if(checkGwalho(s)){
                answer++;
            }
        }
        return answer;
    }

    public boolean checkGwalho(String s){
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars){
            if(c=='(' || c=='{' || c=='['){
                stack.push(c);
            }
            if(c==')'){
                if(stack.isEmpty()){
                    return false;
                }
                if(stack.peek()=='('){
                    stack.pop();
                }
            }
            if(c=='}'){
                if(stack.isEmpty()){
                    return false;
                }
                if(stack.peek()=='{'){
                    stack.pop();
                }
            }
            if(c==']'){
                if(stack.isEmpty()){
                    return false;
                }
                if(stack.peek()=='['){
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public List<Integer> solution_48(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();
        for (long i = left; i < right+1; i++) {
            answer.add((int) (Math.max(i/n, i%n)+1));
        }
        return answer;
    }
    public int solution_49(int cacheSize, String[] cities) {
        int answer=0;
        Deque<String> cache = new ArrayDeque<>();
        // early-return
        if(cacheSize==0){
            answer=cities.length*5;
            return answer;
        }
        for (int i = 0; i < cities.length; i++) {
            // cache hit
            if(cache.contains(cities[i].toLowerCase())){
                answer++;
                cache.remove(cities[i].toLowerCase());
                cache.push(cities[i].toLowerCase());
            } else{ // cache miss
                if(cache.size()==cacheSize){
                    answer+=5;
                    cache.removeLast();
                    cache.push(cities[i].toLowerCase());
                } else{
                    answer+=5;
                    cache.push(cities[i].toLowerCase());
                }
            }
        }
        return answer;
    }

    public int[] solution_50(String[] wallpaper){
        int lux=Integer.MAX_VALUE, luy=Integer.MAX_VALUE, rdx=Integer.MIN_VALUE, rdy=Integer.MIN_VALUE;
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if(wallpaper[i].charAt(j)=='#'){
                    luy = Math.min(luy,j);
                    lux = Math.min(lux,i);
                    rdx = Math.max(rdx,i+1);
                    rdy = Math.max(rdy,j+1);
                }
            }
        }
        return new int[] {lux, luy, rdx, rdy};
    }

    public List<Integer> solution_51(int k, int[] score) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            priorityQueue.offer(score[i]);
            if(priorityQueue.size()>k){
                priorityQueue.poll();
            }
            answer.add(priorityQueue.peek());
        }
        return answer;
    }
}