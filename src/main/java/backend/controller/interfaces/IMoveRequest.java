package backend.controller.interfaces;

public interface IMoveRequest extends IRequest {

    String direction();

    int width();

    int height();

}
