package org.example.tp3.monitoresImpl;

import org.example.tp3.monitores.AerosillaMonitor;
import org.example.tp3.monitores.ColaEsquiadoresMonitor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class ColaEsquiadoresMonitorImpl implements ColaEsquiadoresMonitor {
    private Queue<Object> cola = new LinkedList<>();
    private ArrayList<AerosillaMonitor> sillas;

    public ColaEsquiadoresMonitorImpl(ArrayList<AerosillaMonitor> sillas) {
        this.sillas = sillas;

    }

    public synchronized void agregarEsquiador() throws InterruptedException {
        cola.add(new Object());
        System.out.println("Llego un esquiador a la fila.");

    }

    public synchronized AerosillaMonitor tomarSilla() throws InterruptedException {
        while (true) {
            for(AerosillaMonitor aerosilla : sillas) {
                if (aerosilla.estaLibre()) {
                    aerosilla.tomarSilla();
                    return aerosilla;
                }
            }
            System.out.println("No hay sillas disponibles, esperar a que llegue una.");
            wait();
        }

    }

    public synchronized void esperarSilla(AerosillaMonitor aerosilla) throws InterruptedException {
        while (!aerosilla.estaLibre()) {
            wait();
        }

    }

}