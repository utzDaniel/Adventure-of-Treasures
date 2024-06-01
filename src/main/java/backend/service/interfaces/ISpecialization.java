package backend.service.interfaces;

import backend.repository.interfaces.IEntity;
import backend.service.enums.TypeItem;

public interface ISpecialization extends IRemovable, IEntity {

    boolean isType(TypeItem type);

}
