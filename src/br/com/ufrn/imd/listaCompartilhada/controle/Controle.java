package br.com.ufrn.imd.listaCompartilhada.controle;

public class Controle {
    private int buscando = 0;
    private int removendo = 0;

    public synchronized void lockBuscar() throws InterruptedException {
        while (this.removendo > 0) {
            wait();
        }
        this.buscando++;
    }

    public synchronized void unlockBuscar() throws InterruptedException {
        this.buscando--;
        notifyAll();
    }

    public synchronized void lockRemover() throws InterruptedException {
        while (this.buscando > 0) {
            wait();
        }

        this.removendo++;
    }

    public synchronized void unlockRemover() throws InterruptedException {
        this.removendo--;
        notifyAll();
    }
}
