package backend.service.interfaces;

import backend.service.enums.TypeItem;

public interface ISpecialization extends IRemovable {

    boolean isType(TypeItem type);

}
