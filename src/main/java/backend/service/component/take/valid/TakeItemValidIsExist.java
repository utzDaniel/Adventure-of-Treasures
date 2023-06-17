package backend.service.component.take.valid;

import backend.controller.exception.ItemException;
import backend.service.interfaces.ITakeItemValid;
import backend.service.model.builder.Item;

import java.util.Objects;

public final class TakeItemValidIsExist implements ITakeItemValid {
    @Override
    public void valid(Item item) {
        if (Objects.isNull(item)) throw new ItemException("Item não encontrado!");
    }
}
