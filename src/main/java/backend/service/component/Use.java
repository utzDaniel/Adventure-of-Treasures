package backend.service.component;

import backend.exception.ItemUsableException;
import backend.service.model.Player;
import backend.enums.ItemsUsable;
import backend.service.interfaces.IUsable;

import java.util.Arrays;

public final class Use {

    private final Player player;
    private final IUsable item;

    public Use(IUsable item) {
        this.player = Player.getInstance();
        this.item = item;
    }

    public boolean run() {
        checkCurrentMap();
        useItem();
        return true;
    }

    private void checkCurrentMap() {
        if (!(this.item.getLocalUse().equals(this.player.getCurrentMap().getName())))
            throw new ItemUsableException("Esse item não pode ser usado nesse mapa!");
    }

    private void useItem() {
        var item = Arrays.stream(ItemsUsable.values())
                .filter(itemsUsable -> itemsUsable.getLabel().equals(this.item.getName()))
                .findFirst().orElseThrow(() -> new ItemUsableException("Não foi possível usar esse item!"));
        item.use();
    }
}