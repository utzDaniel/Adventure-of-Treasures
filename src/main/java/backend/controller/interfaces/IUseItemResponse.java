package backend.controller.interfaces;

public interface IUseItemResponse extends IResponse {

    int capacity();

    int maxCapacity();

    IItemDTO item();

    int indexItem();

    String iconMap();

}
