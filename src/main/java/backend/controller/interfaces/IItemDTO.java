package backend.controller.interfaces;

public interface IItemDTO {

    String name();

    String description();

    String icon();

    int weight();

    ICoordinateDTO coordinate();

    String effect();

    String specialization();

    boolean isEquipped();

}
