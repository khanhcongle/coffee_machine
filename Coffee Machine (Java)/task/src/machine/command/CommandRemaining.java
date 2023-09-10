package machine.command;

import machine.CoffeeMachine;
import machine.CoffeeMachineCommand;
import machine.CoffeeMachineState;

import java.util.function.Supplier;

public class CommandRemaining extends CoffeeMachineCommand {
    public CommandRemaining(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void run(Supplier<String> optionSupplier) {
        this.machine.printStatus();
        this.machine.setState(CoffeeMachineState.MAIN);
    }

}
