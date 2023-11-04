package org.example.tp3;

import org.example.tp3.modelo.Esquiador;
import org.example.tp3.monitores.AerosillaMonitor;
import org.example.tp3.monitoresImpl.AerosillaMonitorImpl;
import org.example.tp3.monitoresImpl.ColaEsquiadoresMonitorImpl;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        int numEsquiadores = 12;
        int numSillas = 2;
        int capacidadMaxima = 4;


        ArrayList<AerosillaMonitor> sillas = new ArrayList<>();
        for(int i = 0; i < numSillas; i++) {
            sillas.add(new AerosillaMonitorImpl(capacidadMaxima));
        }

        ColaEsquiadoresMonitorImpl colaEsquiadores = new ColaEsquiadoresMonitorImpl(sillas);

        for(int i = 0; i < numEsquiadores; i++) {
            new Esquiador(colaEsquiadores).start();
        }
    }
}