package paystation.domain;

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
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Thanks for using the Pay Station!");
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (state != 6);
    }

}
