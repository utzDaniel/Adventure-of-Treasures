package frontend.request;

import backend.controller.interfaces.ICombinationItemRequest;

import java.util.List;

public record CombinationItemRequest(
        String action,
        List<String> name) implements ICombinationItemRequest {
}
