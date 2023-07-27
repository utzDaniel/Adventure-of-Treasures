package backend.controller.response;

import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IResponse;

public record Response(IMessage msg, Object obj) implements IResponse {

}
