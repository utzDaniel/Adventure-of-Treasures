package backend.service.model.builder;

import backend.service.component.RemoveItem;
import backend.service.component.use.Use;
import backend.service.interfaces.IUsable;
import backend.service.model.Player;

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
    public void use() {
        new Use(this).run();
        new RemoveItem(Player.getInstance().getInventory(), this).run();
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
