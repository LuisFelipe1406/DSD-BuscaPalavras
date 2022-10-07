package main;

import java.io.IOException;

import main.control.BuscaPalavras;

public class AppV1 {
    
    //Versao sem threads
    public static void main(String args[]) throws IOException {
        //Executando todas as vezes para facilitar o teste
        for (int n = 0; n < 5; n++) {
            //Para marcar o tempo
            long tempoInicial = System.currentTimeMillis();

            BuscaPalavras busca = new BuscaPalavras();
            String palavra = "developing";
            int qtdArquivos = 250;
            
            System.out.println("Buscando...");

            for (int i = 0; i < qtdArquivos; i++) {
                busca.buscar(palavra, "../recursos/dataset/" + i +".txt");
            }

            System.out.println("A palavra " + palavra + " aparece " + busca.getQtdPalavras() + " vezes nos " + qtdArquivos + " arquivos.");
            System.out.println("O método foi executado em " + ((System.currentTimeMillis() - tempoInicial) / 1000.00) + " segundos");

            System.out.println("--");
        }
    }

}
