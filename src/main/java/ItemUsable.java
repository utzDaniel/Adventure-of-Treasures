import javax.swing.*;

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

}
