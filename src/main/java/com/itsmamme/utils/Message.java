package com.itsmamme.utils;

public final class Message {

    private Message() {
    }

    public static String success(String message) {
        return Text.color.green("✔ ") + message;
    }

    public static String error(String message) {
        return Text.color.red("✘ ") + message;
    }

    public static String info(String message) {
        return Text.color.blue("❯ ") + message;
    }

    public static String request(String message) {
        return Text.style.bold(Text.color.blue("? ")) + message + " › ";
    }

    public static String process(String message) {
        return Text.color.mute("→ " + message + "...");
    }
}