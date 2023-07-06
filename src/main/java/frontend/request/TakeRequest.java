package frontend.request;

import backend.controller.interfaces.ITakeRequest;

public record TakeRequest(String action) implements ITakeRequest {
}
