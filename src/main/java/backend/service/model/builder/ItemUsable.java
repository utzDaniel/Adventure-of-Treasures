package backend.service.model.builder;

import backend.controller.exception.ItemUsableException;
import backend.service.component.RemoveItem;
import backend.service.component.Use;
import backend.service.interfaces.ICombinable;
import backend.service.interfaces.IUsable;
import backend.service.model.Player;

import java.util.List;

public final class ItemUsable extends Item implements IUsable {

    /*
     * separa em duas classe pois, posso ter item usavel no player (poção)
     * e item usavel no mapa (chave) getLocalUse().
     *
     * */

    private String localUse;
    private String effect;

    ItemUsable() {
    }

    void setLocalUse(String localUse) {
        this.localUse = localUse;
    }

    @Override
    public boolean use() {
        try {
            new Use(this).run();
            new RemoveItem(Player.getInstance().getInventory(), this).run();
            return true;
        } catch (ItemUsableException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getLocalUse() {
        return this.localUse;
    }

    @Override
    public String getEffect() {
        return this.effect;
    }

    void setEffect(String filename) {
        this.effect = filename;
    }

    @Override
    public boolean action() {
        return this.use();
    }

    @Override
    public boolean action(List<ICombinable> itens) {
        return action();
    }

    @Override
    public String toString() {
        return """
                Item: %s
                {
                    "localUse": "%s",
                    "effect": "%s"
                }
                """.formatted(super.toString(), this.localUse, this.effect);
    }
}
