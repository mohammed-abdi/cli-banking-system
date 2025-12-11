package com.itsmamme.utils;

public final class Terminal {

    private static final long DEFAULT_DELAY = 1500;
    private static final int FALLBACK_LINES = 50;

    private Terminal() {
    }

    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < FALLBACK_LINES; i++) {
                System.out.println();
            }
        }
    }

    public static void clearScreenAfterDelay() {
        clearScreenAfterDelay(DEFAULT_DELAY);
    }

    public static void clearScreenAfterDelay(long ms) {
        delay(ms);
        clearScreen();
    }

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}