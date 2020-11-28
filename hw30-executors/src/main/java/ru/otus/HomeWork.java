package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);
    private static final String ADDITION = "addition";
    private static final String SUBTRACTION = "subtraction";
    private static final int MAX_NUMBER = 10;
    private static final int MIN_NUMBER = 1;
    private static final int SLEEP_TIME = 100;

    private String last = "SECOND";

    private synchronized void action(String message) {
        int number = MIN_NUMBER - 1;
        String action = ADDITION;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                while (last.equals(message)) {
                    this.wait();
                }

                last = message;
                sleep(SLEEP_TIME);
                notifyAll();

                if (action.equals(ADDITION)) number++;
                if (action.equals(SUBTRACTION)) number--;

                if (number == MAX_NUMBER) action = SUBTRACTION;
                if (number == MIN_NUMBER) action = ADDITION;

                logger.info("{}", number);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new HomeWork.NotInterestingException(ex);
            }
        }
    }

    public static void main(String[] args) {
        HomeWork homeWork = new HomeWork();
        new Thread(() -> homeWork.action("FIRST")).start();
        sleep(1);
        new Thread(() -> homeWork.action("SECOND")).start();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static class NotInterestingException extends RuntimeException {
        NotInterestingException(InterruptedException ex) {
            super(ex);
        }
    }
}
