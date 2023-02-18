package model.builder.item;

import exception.ItemUsableException;
import model.Player;
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

    protected ItemUsable(){}

    protected void setLocalUse(String localUse) {
        this.localUse = localUse;
    }

    @Override
    public boolean use(Item item) {
        try {
            new Use(this).run();
            Player.getInstance().getInventory().removeItem(item);
            return true;
        }catch (ItemUsableException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getLocalUse() {
        return this.localUse;
    }

    @Override
    public boolean action(Item item) {
        return this.use(this);
    }

    @Override
    public boolean action(List<Item> itens) {
        return action(itens.get(0));
    }

    @Override
    public String toString() {
        return "ItemUsable{" +
                "localUse='" + localUse + '\'' +
                "} " + super.toString();
    }
}
