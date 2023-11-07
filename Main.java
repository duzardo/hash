/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetora3;

/**
 *
 * @author ZaiaPai
 */
import java.util.Random;

public class Main {
    public static void main(String[] args) {
       
        long seed = 123; 

        // tabelas
        TabelaHash tabela1 = new TabelaHash(10);
        TabelaHash tabela2 = new TabelaHash(100);
        TabelaHash tabela3 = new TabelaHash(1000);
        TabelaHash tabela4 = new TabelaHash(10000);
        TabelaHash tabela5 = new TabelaHash(1000000);
       
        //mudar tabela e quantidades
        //quantidades: 20 mil, 100 mil, 500 mil, 1 milhão e 5 milhões
        popularTabela(tabela5, seed, 20000, "dobramento");
        popularTabela(tabela5, seed, 20000, "multiplicacao");
        popularTabela(tabela5, seed, 20000, "resto");

        // realizar 5 buscas na tabela1 com valor fixo 123456789
        medirTempoDeBusca(tabela5, 5, 123456789, "dobramento");
        medirTempoDeBusca(tabela5, 5, 123456789, "multiplicacao");
        medirTempoDeBusca(tabela5, 5, 123456789, "resto");
    }

    public static void popularTabela(TabelaHash tabela, long seed, int quantidade, String metodo) {
        Random random = new Random(seed);
        int colisoesAntes = tabela.getColisoes();
        long startTime = System.nanoTime();

        for (int i = 0; i < quantidade; i++) {
            int numero = random.nextInt(1000000000);
            Registro registro = new Registro(numero);

            if (metodo.equals("dobramento")) {
                tabela.inserirDobramento(registro);
            } else if (metodo.equals("multiplicacao")) {
                tabela.inserirMultiplicacao(registro, 0.5);
            } else {
                tabela.inserirResto(registro);
            }
        }

        long endTime = System.nanoTime();
        int colisoesDepois = tabela.getColisoes() - colisoesAntes;

        System.out.println("Tamanho da tabela: " + tabela.getTamanho());
        System.out.println("Método de inserção: " + metodo);
        System.out.println("Tempo necessário (ns) para população: " + (endTime - startTime));
        System.out.println("Número de colisões durante a população: " + colisoesDepois);
        System.out.println();
    }

    public static void medirTempoDeBusca(TabelaHash tabela, int numBuscas, int valorBusca, String metodo) {
        long somaDosTempos = 0;

    for (int i = 0; i < numBuscas; i++) {
        long startTime = System.nanoTime();
        if (metodo.equals("resto")) {
            tabela.buscar(valorBusca);
        } else if (metodo.equals("dobramento")) {
            tabela.buscarDobramento(valorBusca);
        } else {
            tabela.buscarMultiplicacao(valorBusca);
        }
        long endTime = System.nanoTime();
        somaDosTempos += (endTime - startTime);
    }

    long mediaDosTempos = somaDosTempos / numBuscas;

    System.out.println("Média de tempo (ns) para realizar " + numBuscas + " buscas com método " + metodo + ": " + mediaDosTempos);
}
}
