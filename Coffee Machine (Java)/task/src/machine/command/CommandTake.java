package machine.command;

import machine.CoffeeMachine;
import machine.CoffeeMachineCommand;
import machine.CoffeeMachineState;

import java.util.function.Supplier;

public class CommandTake extends CoffeeMachineCommand {

    public CommandTake(CoffeeMachine machine) {
        super(machine);
    }

    @Override
    public void run(Supplier<String> optionSupplier) {
        int takenMoney = this.machine.takeMoney();
        System.out.println("I gave you $" + takenMoney);
        this.machine.setState(CoffeeMachineState.MAIN);
    }
}
