package paystation.domain;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by tuvttran on 9/25/17.
 */
public class Main {

    public static void main(String[] args) throws IllegalCoinException {
        int state;
        Scanner kb = new Scanner(System.in);
        PayStationImpl ps = new PayStationImpl(
                new AlternatingRateStrategy(
                        new LinearRateStrategy(),
                        new ProgressiveRateStrategy(),
                        new ClockBasedDecisionStrategy()
                )
        );
        String menu = "Menu:\n" +
                        "1/ Deposit Coins\n" +
                        "2/ Display\n" +
                        "3/ Buy Ticket\n" +
                        "4/ Cancel\n" +
                        "5/ Change Rate Strategy\n" +
                        "6/ Quit";
        do {
            System.out.println(menu);
            System.out.print("Please choose your action: ");
            state = kb.nextInt();
            System.out.println();
            switch (state) {
                case 1:
                    System.out.print("How much do you want to deposit? ");
                    int deposit = kb.nextInt();
                    try {
                        ps.addPayment(deposit);
                    } catch (IllegalCoinException e) {
                        System.out.println("Invalid coin type. " +
                                "Please choose the next course of action.");
                    }
                    break;
                case 2:
                    System.out.println("Time bought: " + ps.readDisplay());
                    break;
                case 3:
                    Receipt receipt = ps.buy();
                    System.out.println("Thanks for your purchase. Please take the receipt " + receipt.value());
                    System.out.print("Do you want to continue? 1 for Yes and 0 for No: ");
                    int answer = kb.nextInt();
                    if (answer == 0)
                        state = 6;
                    break;
                case 4:
                    System.out.println("Canceling current transaction.");
                    Map<Integer, Integer> returnCoins = ps.cancel();
                    if (returnCoins.isEmpty()) {
                        System.out.println("No coins to return.");
                    } else {
                        System.out.println("Here's the returned coins");
                        for (Integer coin : returnCoins.keySet())
                            System.out.println("Type: " + coin + " cents. Quantity: " + returnCoins.get(coin));
                    }
                    break;
                case 5:
                    System.out.println("Please choose a Rate Strategy:");
                    System.out.println("1/ Linear Rate Strategy");
                    System.out.println("2/ Progressive Rate Strategy");
                    System.out.println("3/ Alternating Rate Strategy");
                    System.out.print("Strategy: ");
                    int strategy = kb.nextInt();
                    while (strategy > 3 || strategy < 1) {
                        System.out.println("Please enter a valid strategy!");
                        strategy = kb.nextInt();
                    }
                    switch (strategy) {
                        case 1:
                            ps.changeStrategy(new LinearRateStrategy());
                            break;
                        case 2:
                            ps.changeStrategy(new ProgressiveRateStrategy());
                            break;
                        case 3:
                            ps.changeStrategy(new AlternatingRateStrategy(
                                    new LinearRateStrategy(),
                                    new ProgressiveRateStrategy(),
                                    new ClockBasedDecisionStrategy()
                            ));
                            break;
                    }
                    break;
                case 6:
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (state != 6);
        System.out.println("Thanks for using the Pay Station!");
    }

}
