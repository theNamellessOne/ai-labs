package lab3;

import java.util.Arrays;
import java.util.function.Predicate;

public class Car {
    public Model model;
    public boolean isBroken;
    public boolean isStarted = false;

    public Car(Model model, boolean isBroken) {
        this.model = model;
        this.isBroken = isBroken;
    }

    public Car(Model model, boolean isBroken, boolean isStarted) {
        this.model = model;
        this.isBroken = isBroken;
        this.isStarted = isStarted;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void destroy() {
        isBroken = true;
        isStarted = false;
    }

    public void fix() {
        isBroken = false;
        isStarted = false;
        System.out.println(model.name + " is fixed.");
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void start() {
        if (!this.isBroken) {
            isStarted = true;
            System.out.println(this.model.name + " is started.");
        }
        else {
            System.out.println(this.model.name + " cannot be started, because it's broken.");
        }
    }

    public void turnOff() {
        if (!this.isBroken) {
            isStarted = false;
            System.out.println(model.name + " is turned off.");
        }

    }

    @SafeVarargs
    public final void isAllowedToRace(Predicate<Car>... predicates) {
        var predicate = Arrays.stream(predicates).reduce(x -> true, Predicate::and);

        System.out.println(predicate.test(this)
                ? model.name + " is allowed to Race."
                : model.name + " is not allowed to Race.");
    }

    @Override
    public String toString() {
        return "Car{" +
                "model=" + model +
                ", isBroken=" + isBroken +
                ", isStarted=" + isStarted +
                '}';
    }
}
