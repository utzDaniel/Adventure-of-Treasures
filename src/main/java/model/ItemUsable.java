package model;

import model.interfaces.IUsable;

import javax.swing.*;
import java.util.List;

public class ItemUsable extends Item implements IUsable {

    /*
     * separa em duas classe pois, posso ter item usavel no player (poção)
     * e item usavel no mapa (chave) getLocalUse().
     *
     * */

    private final String localUse;

    public ItemUsable(String name, String description, int weight, String localUse, int positionItemX, int positionItemY, ImageIcon imagemIcon){
        super(name, description, weight, positionItemX, positionItemY, imagemIcon);
        this.localUse = localUse;
    }

    public String getLocalUse() {
        return this.localUse;
    }

    @Override
    public boolean action(Item item) {
        return this.use(item);
    }

    @Override
    public boolean action(List<Item> itens) {
        return action(itens.get(0));
    }
}
