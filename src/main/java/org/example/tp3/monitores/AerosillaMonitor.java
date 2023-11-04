package org.example.tp3.monitores;

public interface AerosillaMonitor {
    void tomarSilla() throws InterruptedException;
    boolean estaLibre();
    void liberarSilla() throws InterruptedException;

}
