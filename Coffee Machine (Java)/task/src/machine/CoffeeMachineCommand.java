package machine;

import java.util.function.Supplier;

abstract public class CoffeeMachineCommand {
    protected CoffeeMachine machine;

    protected CoffeeMachineCommand(CoffeeMachine machine) {
        this.machine = machine;
    }

    abstract protected void run(Supplier<String> optionSupplier);

}
