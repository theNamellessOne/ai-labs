package lab3;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Car> isNotBroken = car -> !car.isBroken;
        Predicate<Car> isFastEnough = car -> car.model.maxSpeed >= 200;

        Model Dacia = new Model("Dacia Logan", 155);
        Model Porsche = new Model("Porsche 911", 330);

        Car car1 = new Car(Dacia, false);
        Car car2 = new Car(Porsche, true);

        System.out.println(car1.isStarted);
        car1.start();
        System.out.println(car1.isStarted);

        System.out.println();

        System.out.println(car2.isStarted);
        car2.start();
        System.out.println(car2.isStarted);

        car1.isAllowedToRace(isNotBroken, isFastEnough);

        car2.isAllowedToRace(isNotBroken, isFastEnough);
        car2.fix();
        car2.isAllowedToRace(isNotBroken, isFastEnough);
    }
}
