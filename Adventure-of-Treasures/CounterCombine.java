public class CounterCombine {

    private final int combine;
    private int counter;

    public CounterCombine(int combine){
        this.combine = combine;
        this.counter = 1;
    }
    public int getCombine() {
        return this.combine;
    }

    public int getCounter() {
        return this.counter;
    }

    public void updateCounter() {
        this.counter++;
    }

}
