package com.itsmamme.utils;

public final class Message {

    private Message() {
    }

    public static String success(String message) {
        return Text.color.green(Terminal.symbol.CHECK_MARK) + " " + message;
    }

    public static String error(String message) {
        return Text.color.red(Terminal.symbol.CROSS_MARK) + " " + message;
    }

    public static String info(String message) {
        return Text.color.blue(Terminal.symbol.TILDE) + " " + message;
    }

    public static String request(String message) {
        return Text.style.bold(Text.color.blue(Terminal.symbol.QUESTION_MARK)) + " " + message + " "
                + Terminal.symbol.PROMPT_MARK + " ";
    }

    public static String process(String message) {
        return Terminal.symbol.RIGHT_ARROW + Text.color.mute(" " + message + Terminal.symbol.PROGRESS_MARK);
    }
}