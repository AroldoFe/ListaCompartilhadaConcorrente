package br.com.ufrn.imd.listaCompartilhada.lista.operacoes;

import br.com.ufrn.imd.listaCompartilhada.lista.ListaCompartilhada;

import java.util.Random;

public class Busca extends Thread {
    private final ListaCompartilhada listaCompartilhada;
    private final Random random;

    public Busca(String name, ListaCompartilhada listaCompartilhada) {
        super(name);
        this.listaCompartilhada = listaCompartilhada;
        this.random = new Random();
    }

    @Override
    public void run() {
        final var numeroBuscar = this.random.nextInt(50);
        this.listaCompartilhada.buscar(numeroBuscar);
    }
}
