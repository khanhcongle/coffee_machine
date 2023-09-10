package machine.command;

import machine.CoffeeMachine;
import machine.CoffeeMachineCommand;
import machine.CoffeeMachineState;

import java.util.function.Supplier;

public class CommandFill extends CoffeeMachineCommand {
    public CommandFill(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void run(Supplier<String> amountStrSupplier) {
        if (machine.getSubState() == null) {
            machine.setSubState(CoffeeMachineState.FILLING_WATER);
        }

        final Supplier<Integer> amountSupplier = () -> Integer.parseInt(amountStrSupplier.get());

        if (machine.getSubState() == CoffeeMachineState.FILLING_WATER) {
            System.out.println("Write how many ml of water you want to add: ");
            machine.addWater(amountSupplier.get());
            machine.setSubState(CoffeeMachineState.FILLING_MILK);
            return;
        }

        if (machine.getSubState() == CoffeeMachineState.FILLING_MILK) {
            System.out.println("Write how many ml of milk you want to add: ");
            machine.addMilk(amountSupplier.get());
            machine.setSubState(CoffeeMachineState.FILLING_COFFEE_BEAN);
            return;
        }

        if (machine.getSubState() == CoffeeMachineState.FILLING_COFFEE_BEAN) {
            System.out.println("Write how many grams of coffee beans you want to add: ");
            machine.addCoffee(amountSupplier.get());
            machine.setSubState(CoffeeMachineState.FILLING_CUP);
            return;
        }

        if (machine.getSubState() == CoffeeMachineState.FILLING_CUP) {
            System.out.println("Write how many disposable cups you want to add: ");
            machine.addCups(amountSupplier.get());
            this.machine.setSubState(null);
            this.machine.setState(CoffeeMachineState.MAIN);
            return;
        }
    }
}
