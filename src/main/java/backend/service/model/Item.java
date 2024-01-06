package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.ISpecialization;

import java.util.Optional;

public final class Item {

    private final int id;
    private final String name;
    private final String description;
    private final int weight;
    private final String image;
    private final SpecializationComposite specialization;
    private ICoordinate coordinate;

    Item(int id, String name, String description, int weight, String image, SpecializationComposite specialization, ICoordinate coordinate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.image = image;
        this.specialization = specialization;
        this.coordinate = coordinate;
    }

    public String getImage() {
        return this.image;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getWeight() {
        return this.weight;
    }

    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setCoordinate(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
    }

    public boolean isType(TypeItem type) {
        return this.specialization.isType(type);
    }

    public Optional<ISpecialization> getSpecialization(TypeItem type) {
        return this.specialization.getSpecialization(type);
    }

    public boolean isRemove() {
        return this.specialization.isRemove();
    }

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "name": "%s",
                    "description": "%s",
                    "weight": %d,
                    "coordinate": %s,
                    "image": "%s",
                    "specializations": %s
                }
                """.formatted(this.id, this.name, this.description, this.weight, this.coordinate.toString(),
                this.image, this.specialization);
    }

}
