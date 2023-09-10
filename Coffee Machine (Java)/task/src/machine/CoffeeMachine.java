package machine;

import java.util.Scanner;
import java.util.function.Supplier;
import machine.command.CommandBuy;
import machine.command.CommandFill;
import machine.command.CommandRemaining;
import machine.command.CommandTake;

public class CoffeeMachine {
    int water, milk, coffee, cups, money = 0;
    CoffeeMachineState currentState = CoffeeMachineState.MAIN;
    private CoffeeMachineState subState;

    CoffeeMachine() {
        water = 400;
        milk = 540;
        coffee = 120;
        cups = 9;
        money = 550;
    }

    public static void main(String[] args) {
        run(new CoffeeMachine());
    }

    private static void run(CoffeeMachine machine) {
        Scanner scanner = new Scanner(System.in);
        Supplier<String> inputSupplier = () -> scanner.nextLine();

        while(true) {
            if(machine.currentState == CoffeeMachineState.MAIN) {
                System.out.println("==========================");
                System.out.println("Write userInput (buy, fill, take, remaining, exit): ");
                setMachineStateFromAction(machine, inputSupplier.get());
            }

            if (machine.currentState == CoffeeMachineState.ORDERING) {
                new CommandBuy(machine).run(inputSupplier);

            } else if (machine.currentState == CoffeeMachineState.FILLING) {
                new CommandFill(machine).run(inputSupplier);

            } else if (machine.currentState == CoffeeMachineState.TAKING_MONEY) {
                new CommandTake(machine).run(inputSupplier);

            } else if (machine.currentState == CoffeeMachineState.REMAINING_CHECKING) {
                new CommandRemaining(machine).run(inputSupplier);

            } else if (machine.currentState == CoffeeMachineState.EXIT) {
                return;
            }
        }
    }

    private static void setMachineStateFromAction(CoffeeMachine machine, String action) {
        switch (action) {
            case "buy":
                machine.setState(CoffeeMachineState.ORDERING);
                return;

            case "fill":
                machine.setState(CoffeeMachineState.FILLING);
                return;

            case "take":
                machine.setState(CoffeeMachineState.TAKING_MONEY);
                return;

            case "remaining":
                machine.setState(CoffeeMachineState.REMAINING_CHECKING);
                return;

            case "exit":
                machine.setState(CoffeeMachineState.EXIT);
                return;

            default:
                machine.setState(CoffeeMachineState.MAIN);
                System.out.println("Unknown command!");
        }
    }

    public void addWater(int amount) {
        water += amount;
    }

    public void addMilk(int amount) {
        milk += amount;
    }

    public void addCoffee(int amount) {
        coffee += amount;
    }

    public void addCups(int amount) {
        cups += amount;
    }

    public int takeMoney() {
        int temp = this.money;
        this.money = 0;
        return temp;
    }

    public void printStatus() {

        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("The coffee machine has:\n")
                .append(this.water).append(" ml of water\n")
                .append(this.milk).append(" ml of milk\n")
                .append(this.coffee).append(" g of coffee beans\n")
                .append(this.cups).append(" disposable cups\n")
                .append("$" + this.money).append(" of money");

        System.out.println(strBuilder.toString());
    }

    public void setState(CoffeeMachineState state) {
        this.currentState = state;
    }

    public CoffeeMachineState getSubState() {
        return this.subState;
    }

    public void setSubState(CoffeeMachineState subState) {
        this.subState = subState;
    }
}
