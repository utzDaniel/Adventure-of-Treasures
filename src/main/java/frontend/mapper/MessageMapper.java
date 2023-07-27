package frontend.mapper;

import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IResponse;
import frontend.model.view.Message;

import java.util.function.Function;

public final class MessageMapper implements Function<Object, IMessage> {
    @Override
    public IMessage apply(Object response) {
        var resp = (IResponse) response;
        return new Message(resp.msg().sucess(),
                resp.msg().text(),
                resp.msg().effect());
    }

}
