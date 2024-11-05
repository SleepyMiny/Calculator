import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        calclass cal = new calclass();
        saveclass save = new saveclass();       //불러오기

        System.out.print("숫자를 입력하세요: ");    //시작코드
        double num1 = sc.nextDouble();
        System.out.print("기호를 입력하세요: ");
        String op = sc.next();
        System.out.print("숫자를 입력하세요: ");
        double num2 = sc.nextDouble();


        double result = cal.oper(num1, op, num2);
        System.out.println("결과 : "+result);

        save.addsave(result);

        System.out.println("1, 이어 계산하기 / 2, 저장 불러오기 / (아무키를 눌러 종료)");
        int check = sc.nextInt();

        for (;check == 1 || check == 2;) {                  //이어계산 저장불러오기 루프시작

            for (;check == 1;) {                            //이어 계산한다면

                System.out.println("결과 : "+result);        //   ---입력받기---
                num1 = result;
                System.out.print("기호를 입력하세요: ");
                op = sc.next();
                System.out.print("숫자를 입력하세요: ");
                num2 = sc.nextDouble();                      //   ---입력받기완료---

                result = cal.oper(num1, op, num2);          // 결과계산
                System.out.println("결과 : "+result);

                save.addsave(result);                       //저장

                System.out.println("1, 이어 계산하기 / 2, 저장 불러오기 / (아무키를 눌러 종료)");
                check = sc.nextInt();

            }

            if (check == 2) {                                //불러온다면

                save.printsave();

                System.out.println();
                System.out.print("몇번을 불러올까요 : ");
                int savenum = sc.nextInt();
                result = save.savenum(savenum);

                System.out.println("결과 : " + result);
                System.out.println("1, 이어 계산하기 / 2, 저장 불러오기 / (아무키를 눌러 종료)");
                check = sc.nextInt();

            } else {
                break;
            }

        }


    }
}


class calclass {


    double oper (double num1,String op, double num2) {
        double result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("0으로 나눌 수 없습니다");
                }
                break;
            default:
                System.out.println("잘못된 연산자입니다");
        }
        return result;
    }

}

class saveclass {

    double[] save = new double[10];
    int savecount = 0;

    void addsave (double result) {
        if (savecount < 10) {
            save[savecount] = result;
            savecount++;
        } else {
            for (int i = 0; i < 9; i++) {
                save[i] = save[i + 1];
            }
            save[9] = result;
            savecount = 10;
        }
    }

    void printsave(){
         for (int i = 0; i < savecount ; i++) {
            System.out.print(i+1 + " : " + save[i] + " / ");
         }
    }

    double savenum(int savenum) {
        if (savenum > 0 && savenum <= savecount) {
            return save[savenum-1];
        } else {
            System.out.println("잘못된 번호입니다.");
            return save[savecount-1];
        }
    }

}