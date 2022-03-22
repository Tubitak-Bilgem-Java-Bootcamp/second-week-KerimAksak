package java102.one.locations;

import java102.one.Player;
import java102.one.monster.Zombie;

public class Cave extends BattleLocation{
    public Cave(Player player) {
        super(player, "Cave", new Zombie(), "Food",3);
    }
}
