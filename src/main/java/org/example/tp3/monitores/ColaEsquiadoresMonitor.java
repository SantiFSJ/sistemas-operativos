package org.example.tp3.monitores;

public interface ColaEsquiadoresMonitor {
    void agregarEsquiador() throws InterruptedException;
     AerosillaMonitor tomarSilla() throws InterruptedException ;
     void esperarSilla(AerosillaMonitor aerosilla) throws InterruptedException;

}
