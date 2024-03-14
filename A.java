public class A {
    private int money;

    public A() {
        this.money = 100;
        System.out.println(this.money);
    }

    public void test() {
        B b = new B();
        b.Bshop(this);
    }

    public int getmoney() { return this.money; }
    public void setmoney(int money) { this.money = money; }
}
