package main.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuscaPalavras {
    private int qtdPalavra;

    public BuscaPalavras() {
        this.qtdPalavra = 0;
    }

    public int getQtdPalavras() {
        return this.qtdPalavra;
    }

    public void buscar(String palavra, String file) throws IOException {
        //Abre o arquivo
        BufferedReader arquivo = new BufferedReader(new FileReader(new File(this.getClass().getResource("").getPath() + file)));
        String str = arquivo.readLine();

        //Enquanto houver algo a ser lido
        while (str != null) {
            if (str.contentEquals(palavra)) {
                this.qtdPalavra++;
            }

            str = arquivo.readLine();
        }
    }
}
