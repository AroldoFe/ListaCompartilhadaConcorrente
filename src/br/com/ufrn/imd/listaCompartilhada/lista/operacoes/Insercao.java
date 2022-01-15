package br.com.ufrn.imd.listaCompartilhada.lista.operacoes;

import br.com.ufrn.imd.listaCompartilhada.lista.ListaCompartilhada;

import java.util.Random;

public class Insercao extends Thread {
    private final ListaCompartilhada listaCompartilhada;
    private final Random random;

    public Insercao(String name, ListaCompartilhada listaCompartilhada) {
        super(name);
        this.listaCompartilhada = listaCompartilhada;
        this.random = new Random();
    }

    @Override
    public void run() {
        final var numeroInserir = this.random.nextInt(50);
        this.listaCompartilhada.inserir(numeroInserir);
    }
}
