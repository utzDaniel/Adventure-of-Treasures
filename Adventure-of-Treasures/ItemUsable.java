import javax.swing.*;

public class ItemUsable extends Item implements Usable{

    private final String localUse;

    public ItemUsable(String name, String description, int weight, String localUse, int positionItemX, int positionItemY, ImageIcon imagemIcon){
        super(name, description, weight, positionItemX, positionItemY, imagemIcon);
        this.localUse = localUse;
    }

    @Override
    public boolean use(Item item, Player player) {
        Use utilize = new Use(player);
        return utilize.validItemUsable(item);
    }

    public String getLocalUse() {
        return this.localUse;
    }

}
