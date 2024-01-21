package backend.service.enums;

import backend.service.interfaces.ICoordinate;
import backend.service.model.Area;

public enum Move {

    OESTE(ImagePlayer.ESQUERDA) {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.y() <= Area.minY();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = coordinate.y() - Move.STEP;
            return ICoordinate.getInstance(x, y);
        }

        @Override
        public ICoordinate coordinateByNextScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = Area.maxY();
            return ICoordinate.getInstance(x, y);
        }
    },
    NORTE(ImagePlayer.CIMA) {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.x() <= Area.minX();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x() - Move.STEP;
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
    LESTE(ImagePlayer.DIREITA) {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.y() >= Area.maxY();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = coordinate.y() + Move.STEP;
            return ICoordinate.getInstance(x, y);
        }

        @Override
        public ICoordinate coordinateByNextScenery(ICoordinate coordinate) {
            var x = coordinate.x();
            var y = Area.minY();
            return ICoordinate.getInstance(x, y);
        }
    },
    SUL(ImagePlayer.BAIXO) {
        @Override
        public boolean isNextScenery(ICoordinate coordinate) {
            return coordinate.x() >= Area.maxX();
        }

        @Override
        public ICoordinate coordinateByScenery(ICoordinate coordinate) {
            var x = coordinate.x() + Move.STEP;
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

    public static final int STEP = 1;
    private final ImagePlayer imagePlayer;

    Move(ImagePlayer imagePlayer) {
        this.imagePlayer = imagePlayer;
    }

    public abstract boolean isNextScenery(ICoordinate coordinate);

    public abstract ICoordinate coordinateByScenery(ICoordinate coordinate);

    public abstract ICoordinate coordinateByNextScenery(ICoordinate coordinate);

    public String getImage() {
        return this.imagePlayer.select();
    }

    public void run() {
        this.imagePlayer.run();
    }

}
