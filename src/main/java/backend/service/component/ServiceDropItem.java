package backend.service.component;

import backend.service.model.Player;
import backend.service.model.builder.Item;

public class ServiceDropItem {

    public boolean run(Player player, String name) {
        Item item = player.getInventory().getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name)).findFirst().get();
        return new DropItem(player, item).run();
    }
}
