package ru.otus;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.monitor.PingPong;

public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(PingPong.class);
    private static final String ADDITION = "addition";
    private static final String SUBTRACTION = "subtraction";
    private static final int MAX_NUMBER = 10;
    private static final int MIN_NUMBER = 0;

    private String last = "PONG";

    private synchronized void action(String message) {
        int number = 0;
        String action = ADDITION;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                //spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                //поэтому не if
                while (last.equals(message)) {
                    this.wait();
                }

                //logger.info(message);
                last = message;
                sleep();
                notifyAll();
                if (action.equals(ADDITION)) number++;
                if (action.equals(SUBTRACTION)) number--;

                if (number == MAX_NUMBER) action = ADDITION;
                if (number == MIN_NUMBER) action = SUBTRACTION;

                logger.info("{}",number);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new HomeWork.NotInterestingException(ex);
            }
        }
    }

    public static void main(String[] args) {
        HomeWork homeWork = new HomeWork();
        new Thread(() -> homeWork.action("ping")).start();
        new Thread(() -> homeWork.action("PONG")).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
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
