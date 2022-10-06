package main.control.thread;

import java.io.IOException;

import main.control.BuscaPalavras;

public class buscaPalavrasThread extends Thread {
    
    private String palavra;
    private int limiteInferior;
    private int limiteSuperior;
    private int qtdPalavras;

    public buscaPalavrasThread(String palavra, int limiteInferior, int limiteSuperior) {
        this.palavra = palavra;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.qtdPalavras = 0;
    }

    @Override
    public void run() {
        BuscaPalavras busca = new BuscaPalavras();
        
        for (int i = limiteInferior; i < limiteSuperior; i++) {
            try {
                busca.buscar(this.palavra, "/dataset/" + i +".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.qtdPalavras += busca.getQtdPalavras();
    }

    public int getQtdPalavras() {
        return this.qtdPalavras;
    }

}
