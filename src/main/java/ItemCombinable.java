import javax.swing.*;
import java.util.ArrayList;

public class ItemCombinable extends Item implements Combinable {

    private final int combine;
    private final static ArrayList<CounterCombine> amountCombine = new ArrayList<>();

    public ItemCombinable(String name, String description, int weight, int combine, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY,imagemIcon);
        this.combine = combine;
        setAmountCombine(this.combine);
    }

    public static int getAmountCombine(int combine) {
        for (CounterCombine counterCombine : amountCombine) {
            if (counterCombine.getCombine() == combine) {
                return counterCombine.getCounter();
            }
        }
        return -1;
    }

    public static void setAmountCombine(int combine) {
        boolean newCombine = false;
        for (CounterCombine counterCombine : amountCombine) {
            if (counterCombine.getCombine() == combine) {
                counterCombine.updateCounter();
                newCombine = true;
            }
        }
        if(!newCombine){
            amountCombine.add(new CounterCombine(combine));
        }
    }

    @Override
    public boolean combination(ArrayList<Item> itensCombination, Player player) {
        Combination combination = new Combination(player);
        return combination.validItemCombinable(itensCombination);
    }

    public int getCombine() {
        return this.combine;
    }

}
