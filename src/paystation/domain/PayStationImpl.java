package paystation.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the pay station.
 * <p>
 * Responsibilities:
 * <p>
 * 1) Accept payment;
 * 2) Calculate parking time based on payment;
 * 3) Know earning, parking time bought;
 * 4) Issue receipts;
 * 5) Handle buy and cancel events.
 * <p>
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * <p>
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {

    private int insertedSoFar;
    private int timeBought;
    private Map<Integer, Integer> cancelMap;

    /*
    The strategy for rate calculations
     */
    private RateStrategy rateStrategy;

    public PayStationImpl(RateStrategy rs) {
        this.cancelMap = new HashMap<>();
        this.rateStrategy = rs;
    }

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                break;
            case 10:
                break;
            case 25:
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = rateStrategy.calculateTime(insertedSoFar);
        cancelMap.put(coinValue,
                cancelMap.getOrDefault(coinValue, 0) + 1);
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        Map<Integer, Integer> cloneMap = new HashMap<>(cancelMap);
        reset();
        return cloneMap;
    }

    @Override
    public int empty() {
        int totalCollected = insertedSoFar;
        reset();
        return totalCollected;
    }

    @Override
    public void changeStrategy(RateStrategy newStrategy) {
        this.rateStrategy = newStrategy;
    }

    private void reset() {
        timeBought = insertedSoFar = 0;
        cancelMap.clear();
    }
}
