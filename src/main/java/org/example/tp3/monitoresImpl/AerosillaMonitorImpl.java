package org.example.tp3.monitoresImpl;

import org.example.tp3.monitores.AerosillaMonitor;

public class AerosillaMonitorImpl implements AerosillaMonitor {
    private int capacidadMaxima;
    private int esquiadoresEsperando;

    public AerosillaMonitorImpl(int capacidadMaxima){
        this.capacidadMaxima = capacidadMaxima;
        this.esquiadoresEsperando = 0;
    }

    public synchronized void tomarSilla() throws InterruptedException {
        while(this.esquiadoresEsperando >= this.capacidadMaxima) {
            System.out.println("La silla esta llena. Toca esperar otra.");
            wait();
        }

        this.esquiadoresEsperando++;
        if ((esquiadoresEsperando == 1))
            System.out.println("Se sento un esquiador. Hay " + this.esquiadoresEsperando + " esquiador esperando.");
        else
            System.out.println("Se sento un esquiador. Son " + this.esquiadoresEsperando + " esquiadores esperando.");

        ;
        if(this.esquiadoresEsperando == this.capacidadMaxima)
            notifyAll();

    }
    public boolean estaLibre(){
        return (esquiadoresEsperando < capacidadMaxima);

    }
    public synchronized void liberarSilla() throws InterruptedException {
        while (esquiadoresEsperando < capacidadMaxima) {
            wait();
        }
        System.out.println("La aerosilla se lleno y esta subiendo");
        Thread.sleep(8000);
        System.out.println("Volvio la aerosilla");
        esquiadoresEsperando = 0;
        notifyAll();

    }

}