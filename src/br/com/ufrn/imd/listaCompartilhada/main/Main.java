package br.com.ufrn.imd.listaCompartilhada.main;

import br.com.ufrn.imd.listaCompartilhada.lista.ListaCompartilhada;
import br.com.ufrn.imd.listaCompartilhada.lista.operacoes.Busca;
import br.com.ufrn.imd.listaCompartilhada.lista.operacoes.Insercao;
import br.com.ufrn.imd.listaCompartilhada.lista.operacoes.Remocao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final int NUM_MIN_ITERACOES = 50;

    public static void main(String[] args) {
        ListaCompartilhada listaCompartilhada = ListaCompartilhada.getInstance();

        final Collection<Insercao> inserirList = new ArrayList<>();
        final Collection<Remocao> removerList = new ArrayList<>();
        final Collection<Busca> buscarList = new ArrayList<>();

        for (int i = 0; i < NUM_MIN_ITERACOES; i++) {
            inserirList.add(new Insercao("INSERIR #" + i, listaCompartilhada));
            removerList.add(new Remocao("REMOVER #" + i, listaCompartilhada));
        }

        final var FATOR_CRESCIMENTO = 1.5;
        for (int i = 0; i < NUM_MIN_ITERACOES * FATOR_CRESCIMENTO; i++) {
            buscarList.add(new Busca("BUSCAR #" + i, listaCompartilhada));
        }

        // Juntando todas as threads em uma só collection
        final List<Thread> threads = Stream.concat(
                Stream.concat(
                        inserirList.stream(),
                        buscarList.stream()
                ),
                removerList.stream()
        ).collect(Collectors.toList());

        Collections.shuffle(threads);

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
