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

public class TabelaHash {
    private int tamanho;
    private int colisoes;
    private Registro[] tabela;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
    }
    
    public void inserirResto(Registro chave) {
        int x = chave.getNumero() % tamanho;
        if (tabela[x] == null) {
            tabela[x] = chave;
        } else {
            colisoes++;
            Registro y = tabela[x];
            while (y.getProximo() != null) {
                y = y.getProximo();
                colisoes++;
            }
            y.setProximo(chave);
        }
    }
    public int getColisoes(){return this.colisoes;}
    public int getTamanho(){return this.tamanho;}

    public void inserirDobramento(Registro chave) {
        int x = 0;
        for (int i = 0; i < 9; i++) {
            x += chave.getNumero() % 10;
            chave.setNumero(chave.getNumero() / 10);
        }
        x = x % tamanho;
        if (tabela[x] == null) {
            tabela[x] = chave;
        } else {
            colisoes++;
            Registro y = tabela[x];
            while (y.getProximo() != null) {
                y = y.getProximo();
                colisoes++;
            }
            y.setProximo(chave);
        }
    }

    public void inserirMultiplicacao(Registro chave, double A) {
        int x = (int) (tamanho * (chave.getNumero() * A % 1));
        if (tabela[x] == null) {
            tabela[x] = chave;
        } else {
            colisoes++;
            Registro y = tabela[x];
            while (y.getProximo() != null) {
                y = y.getProximo();
                colisoes++;
            }
            y.setProximo(chave);
        }
    }

    public Registro buscar(int numero) {
        int x = numero % tamanho;
        Registro y = tabela[x];
        while (y != null) {
            if (y.getNumero() == numero) {
                return y;
            }
            y = y.getProximo();
        }
        return null;
    }
    
    public Registro buscarDobramento(int numero) {
    int x = dobramento(numero, tamanho);
    Registro y = tabela[x];
   
    while (y != null) {
        if (y.getNumero() == numero) {
            return y;
        }
        y = y.getProximo();
    }
    return null;
}

// dobramento
private int dobramento(int numero, int tamanho) {
    int indice = 0;
    int valor = numero;
    int d = 1; 
    
    while (valor > 0) {
        int resto = valor % 10;
        valor = valor / 10;
        indice += (resto * d);
        d *= 10;
    }
    
    return indice % tamanho;
}

public Registro buscarMultiplicacao(int numero) {
    int x = multiplicacao(numero, tamanho, 0.5);
    Registro y = tabela[x];
    
    while (y != null) {
        if (y.getNumero() == numero) {
            return y; 
        }
        y = y.getProximo();
    }
    return null;
}

private int multiplicacao(int numero, int tamanho, double A) {
    double valor = numero * A;
    double fracionaria = valor - (int) valor; 
    int arredondado = (int) valor;
    if (fracionaria < 0) {
        arredondado--;
    }
    return tamanho * arredondado;
}


}