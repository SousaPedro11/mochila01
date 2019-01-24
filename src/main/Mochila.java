package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import estrategias.DinamicaProg;
import estrategias.Gulosa;
import modelo.Item;

public class Mochila {

    public static void main(final String[] args) {

        new Mochila();

    }

    private int indice = 0;

    private int capacidade;

    public Mochila() {

        final List<Item> objetos = this.lerItens();

        final StringBuilder sb1 = new StringBuilder();
        Instant now1 = Instant.now();
        final String resultado1 = new Gulosa(objetos, this.capacidade).resolver();
        Instant now2 = Instant.now();
        final String tempo1 = "# Tempo: " + Duration.between(now1, now2).toString().replaceAll("PT", "");
        // System.out.println(tempo);

        sb1.append(resultado1)
                        .append("\n")
                        .append(tempo1)
                        .append("\n");

        System.out.println();

        now1 = Instant.now();
        final String resultado2 = new DinamicaProg(this.capacidade, objetos).resolver();
        now2 = Instant.now();
        // System.out.println(tempo);
        final String tempo2 = "# Tempo: " + Duration.between(now1, now2).toString().replaceAll("PT", "");

        sb1.append("\n")
                        .append(resultado2)
                        .append("\n")
                        .append(tempo2)
                        .append("\n");

        JOptionPane.showMessageDialog(null, sb1.toString(), "Resultado Problema da Mochila", JOptionPane.PLAIN_MESSAGE);
    }

    private List<Item> lerItens() {

        final List<Item> itens = new ArrayList<>();
        try (final BufferedReader leitor = new BufferedReader(new InputStreamReader(
                        new FileInputStream("entrada-mochila.txt"),
                        "UTF-8"))) {

            // ler cabecalho
            /* final String linha1 = */leitor.readLine();
            this.setCapacidade(Integer.parseInt(leitor.readLine()));
            String linha = null;
            while ((linha = leitor.readLine()) != null) {

                this.indice++;
                // separa linha em colunas delimitadas por virgula
                final String[] campos = linha.split(" ", -1);

                final Item item = new Item(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]), this.indice);

                itens.add(item);
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public int getCapacidade() {

        return this.capacidade;
    }

    public void setCapacidade(final int capacidade) {

        this.capacidade = capacidade;
    }

}
