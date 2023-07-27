package backend.controller.interfaces;

import backend.controller.enums.TypeMessage;

public interface IServiceResponse {
    TypeMessage message();

    Object object();

}
