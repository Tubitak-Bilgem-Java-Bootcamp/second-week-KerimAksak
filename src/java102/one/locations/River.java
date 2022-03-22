package java102.one.locations;

import java102.one.Player;
import java102.one.monster.Bear;

public class River extends BattleLocation{
    public River(Player player) {
        super(player, "River", new Bear(), "Water",3);
    }
}
