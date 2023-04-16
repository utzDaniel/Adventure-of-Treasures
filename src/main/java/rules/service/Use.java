package rules.service;

import rules.exception.ItemUsableException;
import backend.model.Player;
import rules.enums.ItemsUsable;
import backend.model.interfaces.IUsable;

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