package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.*;
import backend.service.memento.SpecializationMemento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SpecializationComposite implements ISpecialization, IMemento<SpecializationMemento> {

    private final List<ISpecialization> specializations;

    public SpecializationComposite() {
        this.specializations = new ArrayList<>();
    }

    public void add(ISpecialization specialization) {
        this.specializations.add(specialization);
    }

    public Optional<ISpecialization> getSpecialization(TypeItem type) {
        return this.specializations.stream().filter(s -> s.isType(type)).findFirst();
    }

    @Override
    public int id() {
        return this.specializations.isEmpty() ? -1 : this.specializations.get(0).id();
    }

    @Override
    public boolean isRemove() {
        return this.specializations.stream().allMatch(IRemovable::isRemove);
    }

    @Override
    public boolean isType(TypeItem type) {
        return this.specializations.stream().anyMatch(s -> s.isType(type));
    }

    @Override
    public String toString() {
        return """
                [
                    "%s"
                ]
                """.formatted(this.specializations.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

    @Override
    public SpecializationMemento save() {
        Boolean equip = null;
        var equippable = getSpecialization(TypeItem.EQUIPPABLE);
        if (equippable.isPresent()) {
            equip = ((IEquippable) equippable.get()).isEquip();
        }

        Boolean enabled = null;
        var usable = getSpecialization(TypeItem.USABLE);
        if (usable.isPresent()) {
            enabled = ((IUsable) usable.get()).isEnabled();
        }

        return new SpecializationMemento(equip, enabled);
    }

    @Override
    public void restore(SpecializationMemento memento) {
        getSpecialization(TypeItem.EQUIPPABLE)
                .ifPresent(s -> ((IEquippable) s).setEquip(memento.equip()));

        getSpecialization(TypeItem.USABLE)
                .ifPresent(s -> ((IUsable) s).setEnabled(memento.enabled()));
    }
}
