package com.itsmamme.utils;

public final class Text {

    private Text() {
    }

    private final static String RESET = "\033[0m";

    public class color {

        private final static String RED = "\033[31m";
        private final static String GREEN = "\033[32m";
        private final static String BLUE = "\033[34m";
        private final static String MUTE = "\033[90m";

        public static String red(String text) {
            return RED + text + RESET;
        }

        public static String green(String text) {
            return GREEN + text + RESET;
        }

        public static String blue(String text) {
            return BLUE + text + RESET;
        }

        public static String mute(String text) {
            return MUTE + text + RESET;
        }
    }

    public class style {

        private final static String BOLD = "\033[1m";
        private final static String UNDERLINE = "\033[4m";

        public static String bold(String text) {
            return BOLD + text + RESET;
        }

        public static String underline(String text) {
            return UNDERLINE + text + RESET;
        }
    }
}
