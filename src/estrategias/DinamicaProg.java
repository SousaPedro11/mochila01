package estrategias;

import java.util.ArrayList;
import java.util.List;

import modelo.Item;
import modelo.Solucao;
import util.Utilitario;

public class DinamicaProg extends Utilitario {

    private int capacidade;

    private int c = 0;

    private List<Item> itens;

    List<String> solucoesPossiveis = new ArrayList<>();

    public DinamicaProg(final int capacidade, final List<Item> itens) {

        this.capacidade = capacidade;
        this.itens = itens;
    }

    public int getCapacidade() {

        return this.capacidade;
    }

    public void setCapacidade(final int capacidade) {

        this.capacidade = capacidade;
    }

    public List<Item> getItens() {

        return this.itens;
    }

    public void setItens(final List<Item> itens) {

        this.itens = itens;
    }

    @Override
    public String resolver() {

        final StringBuilder sb = new StringBuilder();
        final Solucao solucao = new Solucao("Dinâmico");
        int i, w;
        final int n = this.itens.size();
        final int K[][] = new int[n + 1][this.capacidade + 1];

        // Cria tabela K[][] bottom-up
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= this.capacidade; w++) {
                this.c++;
                if ((i == 0) || (w == 0)) {
                    K[i][w] = 0;
                } else if (this.itens.get(i - 1).getPeso() <= w) {
                    K[i][w] = this.maximo(this.itens.get(i - 1).getValor() + K[i - 1][w - this.itens.get(i - 1).getPeso()], K[i - 1][w]);
                } else {
                    K[i][w] = K[i - 1][w];
                }
            }
        }
        solucao.setValor(K[n][this.capacidade]);

        // System.out.println(solucao.mostrar());
        final String numeroInstrucoes = "# Número de instruções executadas: " + this.c;
        // System.out.println(numeroInstrucoes);
        sb.append(solucao.mostrar())
                        .append("\n")
                        .append(numeroInstrucoes);
        return sb.toString();
    }

    private int maximo(final int a, final int b) {

        return (a > b) ? a : b;
    }
}
