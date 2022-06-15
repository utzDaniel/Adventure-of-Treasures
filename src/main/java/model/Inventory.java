package model;

public class Inventory {

    private int capacity;
    private int maxCapacity;
    private boolean isInventory;

    public Inventory() {
        this.capacity = 0;
        this.maxCapacity = 10;
        this.isInventory = false;
    }

    public boolean isInventory() {
        return isInventory;
    }

    public void setIsInventory() {
        this.isInventory = !isInventory;
    }
    public void setCapacity(int weight) {
        this.capacity += weight;
    }

    public void setMaxCapacity(int upgradeCapacity) {
        this.maxCapacity += upgradeCapacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }
}
