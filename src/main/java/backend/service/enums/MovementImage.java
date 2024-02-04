package backend.service.enums;

public enum MovementImage {

    RIGHT_FOOT_TOGETHER('C') {
        @Override
        public MovementImage next() {
            return RIGHT_FOOT_FORWARD;
        }
    },
    RIGHT_FOOT_FORWARD('D') {
        @Override
        public MovementImage next() {
            return LEFT_FOOT_TOGETHER;
        }
    },
    LEFT_FOOT_TOGETHER('C') {
        @Override
        public MovementImage next() {
            return LEFT_FOOT_FORWARD;
        }
    },
    LEFT_FOOT_FORWARD('E') {
        @Override
        public MovementImage next() {
            return RIGHT_FOOT_TOGETHER;
        }
    };

    private final char code;

    MovementImage(char code) {
        this.code = code;
    }

    public abstract MovementImage next();

    public static MovementImage reset() {
        return RIGHT_FOOT_TOGETHER;
    }

    public char getCode() {
        return this.code;
    }
}
