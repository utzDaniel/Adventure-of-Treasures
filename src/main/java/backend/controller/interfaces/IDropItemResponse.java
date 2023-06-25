package backend.controller.interfaces;

public interface IDropItemResponse extends IResponse {

    int capacity();

    int maxCapacity();

    IItemDTO item();

    int indexItem();

}
