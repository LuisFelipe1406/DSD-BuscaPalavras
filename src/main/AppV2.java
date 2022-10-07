package main;

import java.util.ArrayList;
import java.util.List;

import main.control.thread.buscaPalavrasThread;

public class AppV2 {
    
    //Versao com quantas threads quisermos
    public static void main(String args[]) throws InterruptedException {
        //Para facilitar os testes
        for (int n = 0; n < 5; n++) {
            //Para marcar o tempo
            long tempoInicial = System.currentTimeMillis();
                
            int threads = 250;                          //A quantidade de threads a serem usadas
            String palavra = "developing";              //A palavra a ser procurada
            int qtdPalavras = 0;                        //Quantidade de ocorrencias da palavra procurada        
            int qtdArquivos = 250;                      //A quantidade de arquivos
            int diferenca = qtdArquivos % threads;      //A diferença entre a divisao dos arquivos pelo numero de threads
            int intervaloI = 0;                         //Intervalo inicial da busca
            int intervaloF = 0;                         //Intervalo final da busca
            List<buscaPalavrasThread> listaThreads = new ArrayList<buscaPalavrasThread>();  //Lista das threads usadas

            System.out.println("Buscando..."); 
            
            for (int i =  1; i <= threads; i++) {
                //Define os intervalos de busca para cada thread
                //O intervalo inicial seria intervalo final da thread anterior (ja que estamos usando a expressao < dentro do for da thread)
                //O intervalo final seria a porcao de arquivos delimitada aquela thread
                intervaloI = intervaloF;
                intervaloF = (qtdArquivos / threads) * i;
                
                //Compensa a diferença caso a divisao dos arquivos entre as threads nao seja exata
                if (i == threads) {
                    intervaloF += diferenca;
                }

                //Cria uma nova thread para buscar as palavras e adiciona ela na lista de threads
                buscaPalavrasThread thread = new buscaPalavrasThread(palavra, intervaloI, intervaloF);
                listaThreads.add(thread);
                thread.run();
            }

            //Aguarda o termino da execucao de todas as threads para obter quantas palavras cada uma recuperou
            for (buscaPalavrasThread t : listaThreads) {
                t.join();
                qtdPalavras += t.getQtdPalavras();
            }

            System.out.println("A palavra " + palavra + " aparece " + qtdPalavras + " vezes nos " + qtdArquivos + " arquivos.");
            System.out.println("O método foi executado em " + ((System.currentTimeMillis() - tempoInicial) / 1000.00) + " segundos");
            System.out.println("--");
        }
    }

}