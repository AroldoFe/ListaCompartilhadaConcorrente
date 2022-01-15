# Lista Compartilhada com Acessos Concorrentes

## Autor

* Aroldo Felix Pereira Junior (junioraroldo37@gmail.com)

## Objetivos

* O objetivo deste trabalho é estimular o projeto, implementação e avaliação de soluções para problemas
por meio de programação concorrente, em especial colocando em prática os conceitos e mecanismos
de sincronização de threads.
* Implementar soluções de acesso concorrente a uma lista compartilhada utilizando Threads

## Metodologia

* Foram criados operações de Inserção, Busca e Remoção concorrentes que acessam a lista compartilhada
* Foi criada uma lista compartilhada que tem operações de inserção e remoção que possuem LOCK e de busca

### Definições

* Remoção remove de itens a partir de qualquer posição da lista
* Inserção realiza operações de inserção de itens no final da lista
* Threads do tipo Busca meramente realizam operações de leitura sobre a lista e, portanto, podem ser executadas de forma simultânea com as outras
* Threads do tipo Inserção devem ser mutuamente exclusivas a fim de impedir que duas threads estejam inserindo itens no final da lista ao mesmo tempo
* Uma thread do tipo R pode acessar a lista por vez para realizar remoção de itens e essa operação deve ser mutuamente exclusiva com relação às demais (busca e inserção).

## Execução

O programa possui um único método Main.
Este MAIN possui um atributo NUM_MIN_ITERACOES que define o numéro mínimo de quantidade de threads de cada tipo

* Cada operação tem sua saída no console
* Ao final das operações de inserção e remoção, a lista é impressa no console.
