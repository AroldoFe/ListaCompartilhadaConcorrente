package br.com.ufrn.imd.listaCompartilhada.lista;

import br.com.ufrn.imd.listaCompartilhada.controle.Controle;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ListaCompartilhada {
    // Defininco uma única instância
    private static ListaCompartilhada instance;

    // Buffer
    private final LinkedList<Integer> lista;

    // Controles de exclusão
    private final Lock lock;
    private final Controle controle;

    private ListaCompartilhada() {
        this.lista = new LinkedList<>();

        this.lock = new ReentrantLock(true);
        this.controle = new Controle();
    }

    public static ListaCompartilhada getInstance() {
        if (instance == null) {
            instance = new ListaCompartilhada();
        }

        return instance;
    }

    public void buscar(Integer numero) {
        try {
            this.controle.lockBuscar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder mensagens = new StringBuilder();

        mensagens.append("--> Buscando elemento [")
                .append(numero)
                .append("]... ");

        final var indice = this.lista.indexOf(numero);

        if (indice < 0) {
            mensagens
                    .append("[FAIL]")
                    .append("\n\tElemento não encontrado");
        } else {
            mensagens
                    .append("[OK]")
                    .append("\n\tElemento encontrado na posição ")
                    .append(indice);
        }

        println(mensagens);

        try {
            this.controle.unlockBuscar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Integer numero) {
        // Bloqueando seção crítica
        this.lock.lock();

        StringBuilder mensagens = new StringBuilder();

        mensagens
                .append("--> Inserindo elemento [")
                .append(numero)
                .append("]... ");

        this.lista.addLast(numero);

        mensagens
                .append("[OK]")
                .append("\n\tElemento ")
                .append(numero).append(" inserido");

        println(mensagens);

        println(this);

        this.lock.unlock();
    }

    public void remover(int indice) {
        // Bloqueando seção crítica
        this.lock.lock();

        try {
            this.controle.lockRemover();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final var mensagens = new StringBuilder();

        mensagens
                .append("--> Removendo elemento da posição #")
                .append(indice)
                .append("... ");

        if (indice < 0 || indice >= this.lista.size()) {
            mensagens
                    .append("[FAIL]")
                    .append("\n\tPosição não encontrada");
        } else {
            final var valorRemovido = this.lista.remove(indice);
            mensagens
                    .append("[OK]")
                    .append("\n\tElemento ")
                    .append(valorRemovido)
                    .append(" foi removido da lista");
        }

        try {
            this.controle.unlockRemover();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println(mensagens);
        println(this);

        this.lock.unlock();
    }

    private void println(Object object) {
        System.out.println(object.toString());
    }

    @Override
    public String toString() {
        return "\n*** Estado Atual da Lista: [" +
                this.lista
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("] -> [")) +
                "]\n";
    }
}
