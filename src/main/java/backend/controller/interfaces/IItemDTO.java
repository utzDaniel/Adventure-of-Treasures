package backend.controller.interfaces;

public interface IItemDTO {
    int id();

    String name();

    int weight();

    String description();

    String image();

    boolean combinable();

}
