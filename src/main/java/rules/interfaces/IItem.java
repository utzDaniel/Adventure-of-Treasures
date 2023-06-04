package rules.interfaces;

public interface IItem {

    String getIcon();
    ICoordinate getCoordinate();
    String getName();
    String getDescription();
    String getEffect();
    int getWeight();

    String getSpecialization();

    boolean isEquipped();

}
