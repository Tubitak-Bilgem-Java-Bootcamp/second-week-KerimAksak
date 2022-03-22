package java102.one.locations;

import java102.one.ConsoleColor;
import java102.one.Player;
import java102.one.armor.HeavyArmor;
import java102.one.armor.LightArmor;
import java102.one.armor.MediumArmor;
import java102.one.monster.Monster;
import java102.one.weapons.Gun;
import java102.one.weapons.Shotgun;
import java102.one.weapons.Sword;

import java.util.Random;

public abstract class BattleLocation extends Location{
    private Monster monster;
    private String award;
    private int maxNumberOfMonster;


    public BattleLocation(Player player, String name, Monster monster, String award, int maxNumberOfMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxNumberOfMonster = maxNumberOfMonster;
    }

    @Override
    public boolean onLocation() {
        int numberOfMonster = this.randomNumberOfMonster();
        System.out.println("Şu an savaş alanındasınız..:"+ this.getName());
        System.out.println("Burada "+numberOfMonster +" adet " + this.getMonster().getName() +" yaşıyor");
        System.out.println("<S>avaş ya da <K>aç");
        String selectBattleCase = scanner.nextLine().toUpperCase();
        while (true){
            if(selectBattleCase.equals("S") || selectBattleCase.equals("K"))
                break;
            System.out.println("Düzgün harf gir!");
            selectBattleCase = scanner.nextLine().toUpperCase();
        }
        if(selectBattleCase.equals("S")){
            boolean fightResult = combat(numberOfMonster);
            if(fightResult){
                System.out.println("Savaşı kazandın!");
                switch (this.getName()){
                    case "Cave" -> {
                        this.getPlayer().getInventory().setFood(true);
                        this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getMonster().getCoin());
                        System.out.println("Ganimetiniz "+this.getPlayer().getMoney() + " altın ve Ekmek(" + this.getPlayer().getInventory().isFood() + ")");
                    }
                    case "Forest" -> {
                        this.getPlayer().getInventory().setFirewood(true);
                        this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getMonster().getCoin());
                        System.out.println("Ganimetiniz "+this.getPlayer().getMoney() + " altın ve Odun(" + this.getPlayer().getInventory().isFirewood() + ")");
                    }
                    case "River" -> {
                        this.getPlayer().getInventory().setWater(true);
                        this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getMonster().getCoin());
                        System.out.println("Ganimetiniz "+this.getPlayer().getMoney() + " altın ve Su(" + this.getPlayer().getInventory().isWater() + ")");
                    }
                    case "Mine" -> {
                        double random = Math.random();
                        if( random <= 0.15 ) {
                            double randomWeapon = Math.random();
                            if(randomWeapon <= 0.20){
                                if(this.getPlayer().getInventory().getWeapon().getName().equals("Shotgun"))
                                    System.out.println("Envanterde var olan eşya düştü.(Tüfek) :(");
                                this.getPlayer().getInventory().setWeapon(new Shotgun());
                                System.out.println("Yılandan tüfek elde edildi.");
                            }

                            if(0.20 <= randomWeapon & randomWeapon <= 0.50){
                                if(this.getPlayer().getInventory().getWeapon().getName().equals("Sword"))
                                    System.out.println("Envanterde var olan eşya düştü.(Tüfek) :(");
                                this.getPlayer().getInventory().setWeapon(new Sword());
                                System.out.println("Yılandan kılıç elde edildi.");
                            }

                            if(0.50 <= randomWeapon){
                                if(this.getPlayer().getInventory().getWeapon().getName().equals("Sword"))
                                    System.out.println("Envanterde var olan eşya düştü.(Silah) :(");
                                this.getPlayer().getInventory().setWeapon(new Gun());
                                System.out.println("Yılandan silah elde edildi.");
                            }

                        }
                        if( 0.15 <= random & random <= 0.30 ) {
                            double randomArmor = Math.random();
                            if(randomArmor <= 0.20){
                                if(this.getPlayer().getInventory().getArmor().getName().equals("HeavyArmor"))
                                    System.out.println("Envanterde var olan eşya düştü.(Ağır Zırh) :(");
                                this.getPlayer().getInventory().setArmor(new HeavyArmor());
                                System.out.println("Yılandan ağır zırh elde edildi.");
                            }

                            if(0.20 <= randomArmor & randomArmor <= 0.50){
                                if(this.getPlayer().getInventory().getArmor().getName().equals("MediumArmor"))
                                    System.out.println("Envanterde var olan eşya düştü.(Orta Zırh) :(");
                                this.getPlayer().getInventory().setArmor(new MediumArmor());
                                System.out.println("Yılandan hafif zırh elde edildi.");
                            }

                            if(0.50 <= randomArmor){
                                if(this.getPlayer().getInventory().getArmor().getName().equals("LightArmor"))
                                    System.out.println("Envanterde var olan eşya düştü.(Orta Zırh) :(");
                                this.getPlayer().getInventory().setArmor(new LightArmor());
                                System.out.println("Yılandan hafif zırh elde edildi.");
                            }

                        }
                        if( 0.30 <= random & random <= 0.55 ) {
                            double randomCoin = Math.random();
                            if(randomCoin <= 0.20){
                                this.getPlayer().setMoney(this.getPlayer().getMoney()+10);
                                System.out.println("Elde edilen ganimet: "+10+" altın.");
                            }

                            if(0.20 <= randomCoin & randomCoin <= 0.50){
                                this.getPlayer().setMoney(this.getPlayer().getMoney()+5);
                                System.out.println("Elde edilen ganimet: "+5+" altın.");
                            }

                            if(0.50 <= randomCoin){
                                this.getPlayer().setMoney(this.getPlayer().getMoney()+1);
                                System.out.println("Elde edilen ganimet: "+1+" altın.");
                            }
                        }
                        if( 0.55 <= random) {
                            System.out.println("Yılandan bir şey elde edilemedi!");
                        }
                    }
                }
                return true;
            }
        }
        if(this.getPlayer().getHealthy() <= 0){
            System.out.println("Savaşı kaydettin!");
            System.out.println(ConsoleColor.ANSI_RED+"GAME OVER"+ConsoleColor.ANSI_RESET);
            return false;
        }
        return true;
    }

    private boolean combat(int numberOfMonster){
        int originalHealth = this.getMonster().getHealth();
        boolean combatTurn = Math.random() < 0.5;
        for(int i=0;i<numberOfMonster;i++){
            playerStats();
            monsterStats(originalHealth);
            this.getMonster().setHealth(originalHealth);
            while (this.getPlayer().getHealthy() > 0 && this.getMonster().getHealth() > 0){
                System.out.println("<V>ur ya da <K>aç");
                String selectCombat = scanner.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    if(combatTurn){
                        System.out.println(ConsoleColor.ANSI_BLUE+this.getPlayer().getName() + " atak yapıyor!"+ConsoleColor.ANSI_RESET);
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                    if(this.getMonster().getHealth() > 0){
                        System.out.println(ConsoleColor.ANSI_RED+"\nCanavar atak yapıyor!"+ConsoleColor.ANSI_RESET);
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(monsterDamage < 0)
                            monsterDamage = 0;
                        this.getPlayer().setHealthy(this.getPlayer().getHealthy()-monsterDamage);
                        afterHit();
                        if(this.getPlayer().getHealthy()<=0){
                            return false;
                        }
                        if(this.getMonster().getHealth()<=0){
                            System.out.println(this.getMonster().getCoin());
                            if(Integer.valueOf(i).equals(numberOfMonster-1))
                                return true;
                        }
                    }
                    if(!combatTurn){
                        System.out.println(ConsoleColor.CYAN+this.getPlayer().getName() + " atak yapıyor!"+ConsoleColor.ANSI_RESET);
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }

                }else{
                    return false;
                }
                combatTurn = false;
            }
            if(this.getMonster().getHealth() < this.getPlayer().getHealthy()){
                System.out.println(ConsoleColor.ANSI_PURPLE+"Düşmanı yendiniz!\nBir sonraki canavar geliyor!"+ConsoleColor.ANSI_RESET);
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getMonster().getCoin());
            }
        }
        return true;
    }

    private void afterHit(){
        System.out.println(ConsoleColor.ANSI_GREEN+"Kalan can değerleri..:\n" +
                this.getPlayer().getName()+"..:"+this.getPlayer().getHealthy()+"\n"+
                this.getMonster().getName()+"..:"+this.getMonster().getHealth()+ConsoleColor.ANSI_RESET+"\n"
                );
    }

    private void playerStats(){
        System.out.println(ConsoleColor.CYAN+"Şu anki özelliklerin: \n" +
                        "Canın..:" + this.getPlayer().getHealthy() +
                        "\nHasarın..:" + this.getPlayer().getTotalDamage() +
                        "\nBloklaman..:" + this.getPlayer().getInventory().getArmor().getBlock() +
                        "\nSilah...:" + this.getPlayer().getInventory().getWeapon().getName() +
                        "\nZırh...:" + this.getPlayer().getInventory().getArmor().getName() + "\n"+ConsoleColor.ANSI_RESET
                );
    }

    private void monsterStats(int originalHealth){
        System.out.println(ConsoleColor.RED+"Canavarın özellikleri:"+
                "\nİsmi..:"+this.getMonster().getName()+
                "\nCanı..:"+originalHealth+
                "\nHasarı..:"+this.getMonster().getDamage()+
                "\nDüşen para..:"+this.getMonster().getCoin()+ConsoleColor.ANSI_RESET+"\n"
        );
    }

    private int randomNumberOfMonster(){
        Random random = new Random();
        return random.nextInt(maxNumberOfMonster) +1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxNumberOfMonster() {
        return maxNumberOfMonster;
    }

    public void setMaxNumberOfMonster(int maxNumberOfMonster) {
        this.maxNumberOfMonster = maxNumberOfMonster;
    }
}
