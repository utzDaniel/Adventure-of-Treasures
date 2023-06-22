package frontend.request;

import backend.controller.interfaces.IMoveRequest;

public record MoveRequest(
        String action,

        String direction,

        int width,

        int height

) implements IMoveRequest {
}
