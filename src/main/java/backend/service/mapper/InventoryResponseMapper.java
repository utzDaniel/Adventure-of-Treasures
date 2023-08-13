package backend.service.mapper;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.ISpecialization;
import backend.repository.interfaces.IEntity;
import backend.service.dto.SpecializationDTO;
import backend.service.dto.response.InventoryResponse;
import backend.service.enums.TypeItem;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class InventoryResponseMapper implements Function<Inventory, IInventoryResponse> {

    @Override
    public IInventoryResponse apply(Inventory inventory) {
        
        var map = inventory.getItens().stream()
                .collect(Collectors.toMap(Item::getId, item -> createListSpecialization(item, inventory)));



        var itensDTO = inventory.getItens().stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .toList();

        return new InventoryResponse(map, createListInformation(inventory), itensDTO);
    }

    private List<String> createListInformation(Inventory inventory) {
        var list = new ArrayList<String>();
        int capacity = inventory.getCapacity();
        int maxCapacity = inventory.getMaxCapacity();
        list.add(String.format("Capacidade do inventario %d/%d", capacity, maxCapacity));
        list.add("Nome");
        list.add("Peso");
        list.add("Descrição");
        return list;
    }

    private List<ISpecialization> createListSpecialization(Item item, Inventory inventory) {
        var list = new ArrayList<ISpecialization>();
        list.add(new SpecializationDTO("USAR", item.isType(TypeItem.USABLE)));
        var key = inventory.isAtivo(item) ? "DESEQUIPAR" : "EQUIPAR";
        list.add(new SpecializationDTO(key, item.isType(TypeItem.EQUIPABLE)));
        list.add(new SpecializationDTO("COMBINAR", item.isType(TypeItem.COMBINABLE)));
        list.add(new SpecializationDTO("REMOVER", !item.isType(TypeItem.MISSION) && key.equals("EQUIPAR")));
        return list;
    }
}
