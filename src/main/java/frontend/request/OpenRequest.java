package frontend.request;

import backend.controller.interfaces.IOpenRequest;

public record OpenRequest(String action) implements IOpenRequest {
}
