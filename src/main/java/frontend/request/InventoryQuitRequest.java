package frontend.request;

import backend.controller.interfaces.IInventoryQuitRequest;

public record InventoryQuitRequest(String action) implements IInventoryQuitRequest {
}
