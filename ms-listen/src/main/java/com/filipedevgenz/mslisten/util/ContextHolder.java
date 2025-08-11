package com.filipedevgenz.mslisten.util;

public class ContextHolder {
    private static final ThreadLocal<String> numeroRemetente = new ThreadLocal<>();

    public static void setNumero(String numero) {
        numeroRemetente.set(numero);
    }

    public static String getNumero() {
        return numeroRemetente.get();
    }

    public static void clear() {
        numeroRemetente.remove();
    }
}
