package backend.controller.interfaces;

public interface IEquipItemResponse extends IResponse {

    int capacity();

    int maxCapacity();

    IItemDTO item();

    int indexItem();

}
