package machine.command;

import machine.CoffeeMachine;
import machine.CoffeeMachineCommand;
import machine.CoffeeMachineState;
import machine.CoffeeRecipe;

import java.util.function.Supplier;


public class CommandBuy extends CoffeeMachineCommand {

    public CommandBuy(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void run(Supplier<String> optionSupplier) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String option = optionSupplier.get();

        if ("back".equals(option)) {
            this.machine.setState(CoffeeMachineState.MAIN);
            return;
        }

        CoffeeRecipe recipe = switch (option) {
            case "1" -> new CoffeeRecipe.EspressoRecipe(this.machine);
            case "2" -> new CoffeeRecipe.LatteRecipe(this.machine);
            case "3" -> new CoffeeRecipe.CappuccinoRecipe(this.machine);
            default -> null;
        };

        if (recipe == null) {
            System.out.println("Sorry, you should input 1, 2, 3, or back");
            return;
        }

        recipe.process();
        this.machine.setState(CoffeeMachineState.MAIN);
    }
}

