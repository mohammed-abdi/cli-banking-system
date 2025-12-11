package com.itsmamme.utils;

import java.util.Arrays;

import com.itsmamme.enums.Side;

public final class Terminal {

    private static final long DEFAULT_DELAY = 1500;
    private static final int FALLBACK_LINES = 50;

    private Terminal() {
    }

    public class symbol {
        public static final String CHECK_MARK = "✔";
        public static final String CROSS_MARK = "✘";
        public static final String TILDE = "~";
        public static final String SLASH = "/";
        public static final String PROMPT_MARK = "›";
        public static final String QUESTION_MARK = "?";
        public static final String PROGRESS_MARK = "...";
        public static final String RIGHT_ARROW = "→";
        public static final String LEFT_ARROW = "←";
        public static final String UPWARD_ARROW = "↑";
        public static final String DOWNWARD_ARROW = "↓";
        public static final String[] spinner = { "⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏" };
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

    public class decorator {
        private static final long DEFAULT_SEPARATOR_LENGTH = 25;

        public static String line(long length) {
            String line = "─";

            for (int i = 0; i < length - 1; i++) {
                line = line + "─";
            }

            return Text.color.mute(line);
        }

        public static String separator() {
            return Text.color.mute(line(DEFAULT_SEPARATOR_LENGTH));
        }

        public static String tableCorner(Side side) {
            switch (side) {
                case TOP_RIGHT:
                    return "┐";
                case TOP_LEFT:
                    return "┌";
                case TOP_CENTER:
                    return "┬";
                case BOTTOM_CENTER:
                    return "┴";
                case CENTER:
                    return "│";
                case LEFT_CENTER:
                    return "├";
                case RIGHT_CENTER:
                    return "┤";
                case BOTTOM_RIGHT:
                    return "┘";
                case BOTTOM_LEFT:
                    return "└";
                default:
                    return "?";
            }
        }

    }

    public static String path(String[] path) {
        int pathLength = path.length;
        String parentDirectoryPath = String.join(" " + symbol.SLASH + " ", Arrays.copyOf(path, pathLength - 1));
        String targetElement = path[pathLength - 1];
        return Text.color.mute(symbol.TILDE + " " + parentDirectoryPath) + " " + symbol.SLASH + " "
                + Text.color.blue(targetElement);
    }
}