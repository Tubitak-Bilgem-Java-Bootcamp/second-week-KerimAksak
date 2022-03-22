package java102.one;

import java102.one.characters.Archer;
import java102.one.characters.Knight;
import java102.one.characters.Samurai;
import java102.one.characters.Character;
import java102.one.weapons.Weapon;

import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private static final Scanner scanner = new Scanner(System.in);

    private String name;
    private String characterName;
    private int healthy;
    private int money;
    private int damage;
    private int baseHealth;

    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectCharacter(){
        String leftAlignFormat = "| %-6d | %-14s | %-6d | %-6d | %-7d |%n";
        System.out.format("+--------+----------------+--------+--------+---------+%n");
        System.out.format("| ID     | Character Name | Damage | Health | Money   |%n");
        System.out.format("+--------+----------------+--------+--------+---------+%n");
        Arrays.asList(new Samurai(), new Archer(), new Knight())
                .forEach(character -> System.out.format(leftAlignFormat
                        ,character.getId()
                        ,character.getName()
                        ,character.getDamage()
                        ,character.getHealth()
                        ,character.getMoney()));
        System.out.format("+--------+----------------+--------+--------+---------+%n");
        System.out.print("Lütfen oynayacağın karakteri seç..(1,2,3): ");
        int selectCharacter = scanner.nextInt();
        switch (selectCharacter){
            case 1 -> initPlayer(new Samurai());
            case 2 -> initPlayer(new Archer());
            case 3 -> initPlayer(new Knight());
            default -> throw new IllegalArgumentException();
        }
        System.out.println(ConsoleColor.CYAN+"Seçilen karakter..: "+ this.characterName+ConsoleColor.ANSI_RESET);
    }

    public void initPlayer(Character character){
        this.setDamage(character.getDamage());
        this.setMoney(character.getMoney());
        this.setHealthy(character.getHealth());
        this.setCharacterName(character.getName());
        this.setBaseHealth(character.getHealth());
    }

    public void playerStats(){
        System.out.println(
                "Silah..:\t" + this.getInventory().getWeapon().getName() +
                "\nZırh...:\t" + this.getInventory().getArmor().getName() +
                "\nBloklama..:\t" + this.getInventory().getArmor().getBlock() +
                "\nHasar..:\t"+ this.getTotalDamage() +
                "\nSağlık..:\t"+ this.getHealthy() +
                "\nBakiye..:\t"+this.getMoney());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getHealthy() {
        return healthy;
    }

    public void setHealthy(int healthy) {
        if(healthy < 0)
            healthy = 0;
        this.healthy = healthy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTotalDamage(){
        return damage + this.inventory.getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }
}
