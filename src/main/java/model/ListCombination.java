package model;

import java.util.ArrayList;
import java.util.List;

public final class ListCombination {

    private ListCombination(){}

    private final static List<CounterCombine> amountCombine = new ArrayList<>();

    public static int getAmountCombine(int combine) {
        for (CounterCombine counterCombine : amountCombine) {
            if (counterCombine.getCombine() == combine) {
                return counterCombine.getCounter();
            }
        }
        return -1;
    }

    public static void setAmountCombine(UtilCombinable utilCombinable) {
        int combine = utilCombinable.getCombine();
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
}
class CounterCombine {

    private final int combine;
    private int counter;

    protected CounterCombine(int combine){
        this.combine = combine;
        this.counter = 1;
    }
    protected int getCombine() {
        return this.combine;
    }

    protected int getCounter() {
        return this.counter;
    }

    protected void updateCounter() {
        this.counter++;
    }
}
