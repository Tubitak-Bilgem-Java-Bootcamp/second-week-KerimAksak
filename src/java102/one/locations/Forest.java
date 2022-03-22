package java102.one.locations;

import java102.one.Player;
import java102.one.monster.Vampire;

public class Forest extends BattleLocation{

    public Forest(Player player) {
        super(player, "Forest", new Vampire(), "Firewood",3);
    }
}
