package estrategias;

import java.util.ArrayList;
import java.util.List;

import modelo.Item;
import modelo.Solucao;
import util.Utilitario;

public class DinamicaRec extends Utilitario {

    private int W;

    private int c = 0;

    private List<Item> itens;

    List<String> solucoesPossiveis = new ArrayList<>();

    public DinamicaRec(final int w, final List<Item> itens) {

        this.W = w;
        this.itens = itens;
    }

    public int getW() {

        return this.W;
    }

    public void setW(final int w) {

        this.W = w;
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
        solucao.setValor(this.recursivo(this.getItens(), this.getW(), this.getItens().size(), false, ""));
        solucao.setItens(new ArrayList<Item>());

        final List<List<Item>> conjunto = new ArrayList<>();
        int valorMax = 0;

        for (int i = 0; i < this.solucoesPossiveis.size(); i++) {
            conjunto.add(new ArrayList<Item>());
            final String[] subconjunto = this.solucoesPossiveis.get(i).split(" ");

            for (final String sc : subconjunto) {
                if (sc.trim().length() > 0) {
                    conjunto.get(i).add(this.getItens().get(Integer.parseInt(sc)));
                }
            }

            final int valTmp = this.calculaBeneficio(conjunto.get(i));
            if (valTmp > valorMax) {
                valorMax = valTmp;
                solucao.setValor(valorMax);
                // solucao.setPeso(this.calculaPeso(conjunto.get(i)));
                solucao.setItens(conjunto.get(i));
            }
        }

        System.out.println(solucao.mostrar());
        final String numeroInstrucoes = "# Número de instruções executadas: " + this.c;
        System.out.println(numeroInstrucoes);
        sb.append(solucao.mostrar())
                        .append("\n")
                        .append(numeroInstrucoes);
        return sb.toString();
    }

    private int recursivo(final List<Item> itens, final int W, final int n, final boolean add, String s) {

        this.c++;
        if (add) {
            s += (n) + " ";
        }

        if ((n == 0) || (W == 0)) {
            this.solucoesPossiveis.add(s);
            return 0;
        }

        if (itens.get(n - 1).getPeso() > W) {
            return this.recursivo(itens, W, n - 1, false, s);
        } else {
            final int casoA = itens.get(n - 1).getValor() + this.recursivo(itens, W - itens.get(n - 1).getPeso(), n - 1, true, s);
            final int casoB = this.recursivo(itens, W, n - 1, false, s);
            return this.maximo(casoA, casoB);
        }
    }

    private int maximo(final int a, final int b) {

        return (a > b) ? a : b;
    }
}
