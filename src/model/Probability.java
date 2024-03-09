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
}
