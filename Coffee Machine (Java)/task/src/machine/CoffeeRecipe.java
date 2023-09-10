package machine;

abstract public class CoffeeRecipe {
    CoffeeMachine machine;

    protected int waterConsumption;
    protected int milkConsumption;
    protected int coffeeConsumption;
    protected int price;

    CoffeeRecipe(CoffeeMachine machine) {
        this.machine = machine;
    }

    boolean isEnough() {
        return isEnoughWater()
                && isEnoughMilk()
                && isEnoughCoffee()
                && isEnoughCup();
    }

    public void process() {
        if (!isEnough()) {
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");

        this.machine.water -= waterConsumption;
        this.machine.milk -= milkConsumption;
        this.machine.coffee -= coffeeConsumption;
        this.machine.cups -= 1;

        this.machine.money += price;
    }

    public boolean isEnoughWater() {
        boolean isEnough = waterConsumption <= this.machine.water;
        if (!isEnough) {
            System.out.println("Sorry, not enough water!");
        }
        return isEnough;
    }

    public boolean isEnoughMilk() {
        boolean isEnoughMilk = milkConsumption <= this.machine.milk;
        if (!isEnoughMilk) {
            System.out.println("Sorry, not enough milk!");
        }
        return isEnoughMilk;
    }

    public boolean isEnoughCoffee() {
        boolean isEnoughCoffee = this.coffeeConsumption <= this.machine.coffee;
        if (!isEnoughCoffee) {
            System.out.println("Sorry, not enough coffee!");
        }
        return isEnoughCoffee;
    }

    public boolean isEnoughCup() {
        boolean isEnoughCups = this.machine.cups >= 1;
        if (!isEnoughCups) {
            System.out.println("Sorry, not enough cup!");
        }
        return isEnoughCups;
    }

    public static class EspressoRecipe extends CoffeeRecipe {
        public EspressoRecipe(CoffeeMachine machine) {
            super(machine);
            this.waterConsumption = 250;
            this.milkConsumption = 0;
            this.coffeeConsumption = 16;
            this.price = 4;
        }
    }

    public static class LatteRecipe extends CoffeeRecipe {
        public LatteRecipe(CoffeeMachine machine) {
            super(machine);
            this.waterConsumption = 350;
            this.milkConsumption = 75;
            this.coffeeConsumption = 20;
            this.price = 7;
        }
    }

    public static class CappuccinoRecipe extends CoffeeRecipe {
        public CappuccinoRecipe(CoffeeMachine machine) {
            super(machine);
            this.waterConsumption = 200;
            this.milkConsumption = 100;
            this.coffeeConsumption = 12;
            this.price = 6;
        }
    }
}
