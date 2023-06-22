package frontend.request;

import backend.controller.interfaces.IRequest;

public record TakeRequest(String action) implements IRequest {
}
