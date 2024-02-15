public abstract class Enemy {
    private String name;    // モンスター名
    private int hp;         // 体力
    private int mp;         // 魔力
    private int attack;     // 攻撃力
    private int defense;    // 防御力
    private int agility;    // すばやさ
    private int point;      // 経験値
    private int money;      // 落とすお金
    private int enemyCount; // 敵の数、今回は使わない予定

    public void attack(Brave b) {
        System.out.println(this.name + "の攻撃！");
        int damage = b.getDefense() - this.attack;
        System.out.println(b.getName() + "は" + damage + "ポイントのダメージを受けた！");
        b.setHp(b.getHp() - damage);
    }

    public void run() {
        System.out.println(this.name + "はにげだした！");
        // 敵の戦闘フラグをNOにするようなプログラム
    }

    // アクセサ
    public String getName() { return this.name; }
    public int getHp() { return this.hp; }
    public int getMp() { return this.mp; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getAgility() { return this.agility; }
    public int getPoint() { return this.point; }
    public int getMoney() { return this.money; }
    public int getEnemyCount() { return this.enemyCount; }

    public void setName(String name) { this.name = name; }
    public void setHp(int hp) { this.hp = hp; }
    public void setMp(int mp) { this.mp = mp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setAgility(int agility) { this.agility = agility; }
    public void setPoint(int point) { this.point = point; }
    public void setMoney(int money) { this.money = money; }
    public void setEnemyCount(int enemyCount) { this.enemyCount = enemyCount; }
}