package backend.service.component.use;

import backend.service.enums.ItemsUsable;
import backend.service.interfaces.IUsable;
import backend.service.model.Player;

import java.util.Arrays;

public final class Use {
    // TODO apagar depois, quando refatorar o item
    private final Player player;
    private final IUsable item;

    public Use(IUsable item) {
        this.player = Player.getInstance();
        this.item = item;
    }

    public boolean run() {
        if (checkCurrentMap()) return false;
        useItem();
        return true;
    }

    private boolean checkCurrentMap() {
        return !(this.item.getLocalUse().equals(this.player.getCurrentMap().getName()));
    }

    private void useItem() {
        var item = Arrays.stream(ItemsUsable.values())
                .filter(itemsUsable -> itemsUsable.getLabel().equals(this.item.getName()))
                .findFirst().get();
        item.use();
    }
}
