package model;

public class Probability {
    // Returns true with the given probability
    public static boolean isTrue(double probability) {
        return Math.random() < probability;
    }

    // Returns a random integer between min (include) and max (include)
    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    // Returns a random Direction
    public static Direction randomDirection() {
        int random = randomInt(0, 3);
        return switch (random) {
            case 0 -> Direction.UP;
            case 1 -> Direction.DOWN;
            case 2 -> Direction.LEFT;
            case 3 -> Direction.RIGHT;
            default -> null;
        };
    }
}
