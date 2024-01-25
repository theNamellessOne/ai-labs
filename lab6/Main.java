package lab6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static List<Double> fuzzySpeedControl(double currentSpeed, HashMap<String, Integer> speedRules) {
        return List.of(
                1 / (1 + Math.pow((currentSpeed - speedRules.get("low")) / 15.0, 2)),
                1 / (1 + Math.pow((currentSpeed - speedRules.get("medium")) / 15.0, 2)),
                1 / (1 + Math.pow((currentSpeed - speedRules.get("high")) / 15.0, 2))
        );
    }

    public static void determineSpeedAction(List<Double> speedLevels) {
        int maxIndex = speedLevels.indexOf(speedLevels.stream().max(Double::compare).orElse(-1.0));

        if (maxIndex == 0) {
            System.out.println("Можна збільшити - ризик низький");
        } else if (maxIndex == 1) {
            System.out.println("Підтримуємо поточну швидкість - оптимально");
        } else if (maxIndex == 2) {
            System.out.println("Зменшуємо швидкість - ризик великий");
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> speedRules = new HashMap<>();
        speedRules.put("low", 40);
        speedRules.put("medium", 100);
        speedRules.put("high", 160);

        for (int i = 0; i < 200; i+=20) {
            System.out.print(i + "km/h ");
            List<Double> speedLevels = new ArrayList<>(fuzzySpeedControl(i, speedRules));
            determineSpeedAction(speedLevels);
        }
    }
}
