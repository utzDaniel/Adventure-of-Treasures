package backend.controller.factory;

import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IResponse;
import backend.controller.interfaces.IServiceResponse;
import backend.controller.response.Response;
import backend.service.dto.Message;

public final class ResponseFactory {
    private ResponseFactory() {
    }

    public static IResponse create(TypeMessage msg) {
        return new Response(createMessage(msg), null);
    }

    public static IResponse create(IServiceResponse rsp) {
        return new Response(createMessage(rsp.message()), rsp.object());
    }

    private static IMessage createMessage(TypeMessage msg) {
        return new Message(msg.isSuccess(), msg.getText(), msg.getEffect());
    }

}
