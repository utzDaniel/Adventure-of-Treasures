package backend.service.interfaces;

public interface IUsable extends ISpecialization {

    int getIdMap();

    ICoordinate getCoordinate();

    boolean isEnabled();

    void setEnabled(boolean enabled);

}
