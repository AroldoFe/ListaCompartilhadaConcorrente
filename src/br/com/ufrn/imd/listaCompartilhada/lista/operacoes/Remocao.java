package br.com.ufrn.imd.listaCompartilhada.lista.operacoes;

import br.com.ufrn.imd.listaCompartilhada.lista.ListaCompartilhada;

import java.util.Random;

public class Remocao extends Thread {
    private final ListaCompartilhada listaCompartilhada;
    private final Random random;

    public Remocao(String name, ListaCompartilhada listaCompartilhada) {
        super(name);
        this.listaCompartilhada = listaCompartilhada;
        this.random = new Random();
    }

    @Override
    public void run() {
        final var indiceRemover = this.random.nextInt(25);
        this.listaCompartilhada.remover(indiceRemover);
    }
}
