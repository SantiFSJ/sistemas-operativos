package org.example.tp3.modelo;

import org.example.tp3.monitores.AerosillaMonitor;
import org.example.tp3.monitores.ColaEsquiadoresMonitor;
import org.example.tp3.monitoresImpl.AerosillaMonitorImpl;

import java.util.Random;

public class Esquiador extends Thread {
    private final ColaEsquiadoresMonitor colaEsquiadores;
    private final Random random;
    private boolean subio;

    public Esquiador(ColaEsquiadoresMonitor colaEsquiadores) {
        this.colaEsquiadores = colaEsquiadores;
        this.random = new Random();
        this.subio = false;
    }

    @Override
    public void run() {
        while (!this.subio) {
            try {
                Thread.sleep(random.nextInt(6000));
                colaEsquiadores.agregarEsquiador();
                AerosillaMonitor aerosilla = colaEsquiadores.tomarSilla();
                colaEsquiadores.esperarSilla(aerosilla);
                aerosilla.liberarSilla();
                Thread.sleep(random.nextInt(3000));
                this.subio = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

    }

}