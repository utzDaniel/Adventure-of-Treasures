package backend.service.enums;

import backend.service.interfaces.ICoordinate;
import backend.service.model.Area;

public enum MoveCommand2 {

    // TODO refatorando com um command
    OESTE {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.y() <= Area.minY();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = coordinate.y() - Movement.STEP;
            return ICoordinate.getInstance(x, y);
        }

        @Override
        public ICoordinate coordinateByNextScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = Area.maxY();
            return ICoordinate.getInstance(x, y);
        }
    },
    NORTE {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.x() <= Area.minX();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x() - Movement.STEP;
            var y = coordinate.y();
            return ICoordinate.getInstance(x, y);
        }

        @Override
        public ICoordinate coordinateByNextScenery(ICoordinate coordinate) {
            var x = Area.maxX();
            var y = coordinate.y();
            return ICoordinate.getInstance(x, y);
        }
    },
    LESTE {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.y() >= Area.maxY();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = coordinate.y() + Movement.STEP;
            return ICoordinate.getInstance(x, y);
        }

        @Override
        public ICoordinate coordinateByNextScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = Area.minY();
            return ICoordinate.getInstance(x, y);
        }
    },
    SUL {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.x() >= Area.maxX();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x() + Movement.STEP;
            var y = coordinate.y();
            return ICoordinate.getInstance(x, y);
        }

        @Override
        public ICoordinate coordinateByNextScenery(ICoordinate coordinate) {
            var x = Area.minX();
            var y = coordinate.y();
            return ICoordinate.getInstance(x, y);
        }
    };


    public abstract boolean isNextScenery(ICoordinate coordinate);

    public abstract ICoordinate coordinateByScenery(ICoordinate coordinate);

    public abstract ICoordinate coordinateByNextScenery(ICoordinate coordinate);

}
