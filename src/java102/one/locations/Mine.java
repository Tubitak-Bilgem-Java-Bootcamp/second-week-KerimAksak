package java102.one.locations;

import java102.one.Player;
import java102.one.monster.Snake;

public class Mine extends BattleLocation{
    public Mine(Player player) {
        super(player, "Mine", new Snake(), "", 3);
    }
}
