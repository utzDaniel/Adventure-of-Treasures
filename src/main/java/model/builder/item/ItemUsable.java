package model.builder.item;

import exception.ItemUsableException;
import model.Player;
import model.interfaces.ICombinable;
import model.interfaces.IUsable;
import service.Use;

import java.util.List;

public class ItemUsable extends Item implements IUsable {

    /*
     * separa em duas classe pois, posso ter item usavel no player (poção)
     * e item usavel no mapa (chave) getLocalUse().
     *
     * */

    private String localUse;
    private String effect;

    protected ItemUsable() {
    }

    protected void setLocalUse(String localUse) {
        this.localUse = localUse;
    }

    @Override
    public boolean use() {
        try {
            new Use(this).run();
            Player.getInstance().getInventory().removeItem(this);
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

    protected void setEffect(String filename) {
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
        return "ItemUsable{" +
                "localUse='" + this.localUse +
                ", effect='" + this.effect +"} "+ super.toString();
    }
}
