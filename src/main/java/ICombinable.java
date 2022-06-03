import java.util.ArrayList;

public interface ICombinable {

    default boolean combination(ArrayList<Item> itensCombination, Player player){
        Combination combination = new Combination(player);
        return combination.execute(itensCombination);
    }

    int getCombine();
}
