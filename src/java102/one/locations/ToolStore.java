package java102.one.locations;

import java102.one.ConsoleColor;
import java102.one.Player;
import java102.one.armor.Armor;
import java102.one.armor.HeavyArmor;
import java102.one.armor.LightArmor;
import java102.one.armor.MediumArmor;
import java102.one.weapons.Gun;
import java102.one.weapons.Shotgun;
import java102.one.weapons.Sword;
import java102.one.weapons.Weapon;

import java.util.List;
import java.util.function.Supplier;

public class ToolStore extends NormalLocation {
    private final List<Weapon> weapons = List.of(new Gun(), new Sword(), new Shotgun());
    private final List<Armor> armors = List.of(new LightArmor(), new MediumArmor(), new HeavyArmor());

    Supplier<String> dash = () -> "---------------------------------";
    public ToolStore(Player player) {
        super(player, "ToolStore");
    }
    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu) {
            System.out.println(dash.get());
            System.out.println("Mağazaya hoş geldin!");
            System.out.println("1 - Weapons");
            System.out.println("2 - Armors");
            System.out.println("3 - Exit");
            System.out.print("Lütfen seçimini yap..: ");
            int selectCase = scanner.nextInt();
            System.out.println(dash.get());
            while (selectCase < 1 || selectCase > 3) {
                System.out.println(ConsoleColor.ANSI_RED+"Geçersiz Değer!"+ConsoleColor.ANSI_RESET);
                System.out.print("Lütfen seçimini yap..: ");
                selectCase = scanner.nextInt();
            }
            switch (selectCase) {
                case 1 -> {
                    listWeapon();
                    buyWeapon();
                }
                case 2 -> {
                    listArmour();
                    buyArmor();
                }
                case 3 -> {
                    exitStore();
                    showMenu = false;
                }
            }
        }
        return true;
    }

    public void listWeapon(){
        System.out.println("SİLAHLAR");
        String leftAlignFormat = "| %-3d | %-8s | %-5d | %-6d |%n";
        System.out.format("+-----+----------+-------+--------+%n");
        System.out.format("| ID  | Name     | Price | Damage |%n");
        System.out.format("+-----+----------+-------+--------+%n");
        weapons.forEach(weapon -> System.out.format(leftAlignFormat
                        ,weapon.getId()
                        ,weapon.getName()
                        ,weapon.getPrice()
                        ,weapon.getDamage()));
        System.out.format("+-----+----------+------+---------+%n");
        System.out.print("Alınacak silahı seç...veya çıkış yap(4)..:");
    }

    private void buyWeapon(){
        int selectWeaponID = scanner.nextInt();
        while (selectWeaponID < 1 || selectWeaponID > 4){
            System.out.println("Geçersiz değer");
            selectWeaponID = scanner.nextInt();
        }
        if(!Integer.valueOf(selectWeaponID).equals(4)) {
            Weapon selectedWeapon = weapons.get(selectWeaponID - 1);
            if (!selectedWeapon.getName().equals(this.getPlayer().getInventory().getWeapon().getName())) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Mevcut bakiyeniz yetmiyor." + this.getPlayer().getMoney());
                } else {
                    System.out.println(selectedWeapon.getName() + " silahını aldınız.");
                    System.out.println(dash.get());
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    System.out.println("Mevcut bakiyeniz...: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    //this.getPlayer().setDamage(this.getPlayer().getDamage() + selectedWeapon.getDamage());
                    this.getPlayer().playerStats();
                }
            } else {
                System.out.println("Aynı silahtan iki tane alamazsınız!");
                this.getPlayer().playerStats();
            }
        }
    }

    public void listArmour(){
        System.out.println("Zırhlar");
        String leftAlignFormat = "| %-3d | %-13s | %-5d | %-6d |%n";
        System.out.format("+-----+---------------+-------+--------+%n");
        System.out.format("| ID  | Name          | Price | Block  |%n");
        System.out.format("+-----+---------------+-------+--------+%n");
        armors.forEach(armor -> System.out.format(leftAlignFormat
                ,armor.getId()
                ,armor.getName()
                ,armor.getPrice()
                ,armor.getBlock()));
        System.out.format("+-----+---------------+------+---------+%n");
        System.out.print("Alacağın zırhı seç...veya çıkış yap(4)..:");
    }

    public void buyArmor(){
        int selectArmorId = scanner.nextInt();
        while (selectArmorId < 1 || selectArmorId > 4){
            System.out.println("Gerçersiz deger!");
            selectArmorId = scanner.nextInt();
        }
        if(!Integer.valueOf(selectArmorId).equals(4)) {
            Armor selectedArmor = armors.get(selectArmorId - 1);
            if (!selectedArmor.getName().equals(this.getPlayer().getInventory().getArmor().getName())) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Mevcut bakiyeniz yetmiyor." + this.getPlayer().getMoney());
                } else {
                    System.out.println(selectedArmor.getName() + " zırhını aldınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Mevcut bakiyeniz...: " + this.getPlayer().getMoney());
                    System.out.println(dash.get());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    this.getPlayer().playerStats();
                }
            } else {
                System.out.println("Aynı zırhtan iki tane alamazsınız!");
                this.getPlayer().playerStats();
            }
        }
    }

    public void exitStore(){
        System.out.println("Güle güle.");
    }
}
