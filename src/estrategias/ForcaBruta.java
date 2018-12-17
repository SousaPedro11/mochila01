package estrategias;

import java.util.ArrayList;
import java.util.List;

import modelo.Item;
import modelo.Solucao;
import util.Utilitario;

public class ForcaBruta extends Utilitario {

    private int W;

    private int c = 0;

    private List<Item> itens;

    public ForcaBruta(final int w, final List<Item> itens) {

        this.W = w;
        this.itens = itens;
    }

    @Override
    public void resolver() {

        final List<List<Item>> permutacoes = this.subconjuntos(this.itens);
        int melhorValor = 0;

        final Solucao solucao = new Solucao("força bruta");

        for (final List<Item> solucaopossivel : permutacoes) {
            this.c++;
            final int peso = this.calculaPeso(solucaopossivel);
            if (peso <= this.W) {
                final int valor = this.calculaBeneficio(solucaopossivel);
                if (valor > melhorValor) {
                    melhorValor = valor;
                    solucao.setValor(valor);
                    // solucao.setPeso(peso);
                    solucao.setItens(solucaopossivel);
                }
            }
        }
        System.out.println(solucao.mostrar());
        System.out.println("# Número de instruções executadas: " + this.c);
    }

    private List<List<Item>> subconjuntos(final List<Item> lista) {

        final List<List<Item>> matriz_subconjuntos = new ArrayList<>();

        if (lista.isEmpty()) {
            matriz_subconjuntos.add(new ArrayList<Item>());
            return matriz_subconjuntos;
        }

        final Item primer_elemento = lista.get(0);

        final List<List<Item>> subconjuntos = this.subconjuntos(lista.subList(1, lista.size()));

        for (final List<Item> subconjunto : subconjuntos) {
            matriz_subconjuntos.add(subconjunto);
            final List<Item> subsubconjunto = new ArrayList<>(subconjunto);
            subsubconjunto.add(0, primer_elemento);
            matriz_subconjuntos.add(subsubconjunto);
        }

        return matriz_subconjuntos;
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

}
