import javax.swing.*;

public class ItemCombinable extends Item implements ICombinable {

    private final UtilCombinable utilCombinable;

    public ItemCombinable(String name, String description, int weight, int combine, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY,imagemIcon);
        this.utilCombinable = new UtilCombinable(this, combine);
    }

    @Override
    public int getCombine() {
        return this.utilCombinable.getCombine();
    }
}
