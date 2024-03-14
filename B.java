

public class B {
    private boolean leaveFlag;

    public void Bshop(A a) {
        while(!this.leaveFlag) {
            System.out.println("50mのやくそうをかいますか？");
            if (a.getmoney() < 50) {
                System.out.println("おかねがたりません。でなおしてきなさい");
                this.leaveFlag = true;
                continue;
            }
            System.out.println("かう：0　かわない：－1");
            if (new java.util.Scanner(System.in).nextInt() == -1) {
                break;
            } else {
                a.setmoney(a.getmoney() - 50);
            }
            System.out.println("かいものをつづけますか？");
            System.out.println("つづける：0　つづけない：－1");
            if (new java.util.Scanner(System.in).nextInt() == -1) {
                this.leaveFlag = true;
                continue;
            }
        }
    }
}
