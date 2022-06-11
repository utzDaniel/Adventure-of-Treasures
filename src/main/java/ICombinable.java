import java.util.ArrayList;

public interface ICombinable {

    default boolean combination(ArrayList<Item> itensCombination, Player player){
        return new Combination(player).execute(itensCombination);
    }

    int getCombine();
}
