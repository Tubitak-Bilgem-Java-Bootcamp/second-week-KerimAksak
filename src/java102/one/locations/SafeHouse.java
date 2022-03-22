package java102.one.locations;

import java102.one.ConsoleColor;
import java102.one.Player;

public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "SafeHouse");
    }

    @Override
    public boolean onLocation() {
        if(this.getPlayer().getInventory().isFirewood() &
            this.getPlayer().getInventory().isWater() &
            this.getPlayer().getInventory().isFood()){
            System.out.println(ConsoleColor.ANSI_YELLOW+"TÜM EŞYALAR ELDE EDİLMİŞ.\nOYUN BİTTİ, KAZANDIN!"+ConsoleColor.ANSI_RESET);
            return false;
        }
        System.out.println("Şu an SafeHouse içerisindesin. \n"+ConsoleColor.ANSI_GREEN+"Canın yenilendi!");
        this.getPlayer().setHealthy(this.getPlayer().getBaseHealth());
        System.out.println("Mevcut can değerin: "+this.getPlayer().getHealthy()+ConsoleColor.ANSI_RESET);
        return true;
    }
}
