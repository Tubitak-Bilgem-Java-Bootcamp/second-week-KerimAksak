package java102.one;

import java102.one.locations.*;

import java.util.Scanner;
import java.util.function.Supplier;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    Supplier<String> dash = () -> "---------------------------------";

    public void start(){
        System.out.print("Hoşgeldiniz, lütfen karakterin ismini giriniz..: ");
        String playerName = scanner.nextLine();
        while (playerName.isEmpty()){
            System.out.println(ConsoleColor.ANSI_RED+"Boş bir karakter ismi olamaz!"+ConsoleColor.ANSI_RESET);
            System.out.print("Lüfen karakter ismini giriniz..:");
            playerName = scanner.nextLine();
        }

        Player player = new Player(playerName.substring(0,1).toUpperCase() + playerName.substring(1));
        System.out.println("Oyuna hoşgeldin " + player.getName() +"!");
        player.selectCharacter();

        while (true) {
            Location location = null;
            System.out.println(dash.get());
            System.out.println("Gideceğin yeri belirle");
            System.out.println("Lokasyonlar;");
            System.out.println("1- SafeHouse  -> Güvenli bölge, canın yenilenir.");
            System.out.println("2- ToolStore  -> Kendine zırh ve silah alırsın.");
            System.out.println("3- Cave       -> İçerisinde zombilerin yaşadığı savaş alanı.");
            System.out.println("4- Forest     -> İçerisinde vampirlerin yaşadığı savaş alanı.");
            System.out.println("5- River      -> İçerisinde ayıların yaşadığı savaş alanı.");
            System.out.println("6- Mine       -> İçerisinde yılanların yaşadığı savaş alanı.");
            System.out.println("7- Exit");
            System.out.print("Lütfen gideceğin yeri seç..:");
            int selectedLocation = scanner.nextInt();
            System.out.println(dash.get());
            boolean notExit = true;
            switch (selectedLocation){
                case 1 -> location = new SafeHouse(player);
                case 2 -> location = new ToolStore(player);
                case 3 -> {
                    if(player.getInventory().isFood()){
                        System.out.println(ConsoleColor.ANSI_PURPLE+"Yemek(food) daha önce kazanılmış, SafeHouse yönlendiriliyorsun!"+ConsoleColor.ANSI_RESET);
                        location = new SafeHouse(player);
                    }else
                        location = new Cave(player);
                }
                case 4 -> {
                    if(player.getInventory().isFirewood()){
                        System.out.println(ConsoleColor.ANSI_PURPLE+"Odun(firewood) daha önce kazanılmış, SafeHouse yönlendiriliyorsun!"+ConsoleColor.ANSI_RESET);
                        location = new SafeHouse(player);
                    }else
                        location = new Forest(player);
                }
                case 5 -> {
                    if(player.getInventory().isWater()){
                        System.out.println(ConsoleColor.ANSI_PURPLE+"Su(water) daha önce kazanılmış, SafeHouse yönlendiriliyorsun!"+ConsoleColor.ANSI_RESET);
                        location = new SafeHouse(player);
                    }else
                        location = new River(player);
                }
                case 6 -> location = new Mine(player);
                case 7 -> location = null;
                default -> location = new SafeHouse(player);
            }

            if(location == null) {
                break;
            }
            if (!location.onLocation()) {
                break;
            }
        }

    }
}
