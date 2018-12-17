package estrategias;

import java.util.ArrayList;
import java.util.List;

import modelo.Item;
import modelo.Solucao;
import util.Utilitario;

public class Gulosa extends Utilitario {

    private final List<Item> itens;

    private final int capacidade;

    private int c = 0;

    public Gulosa(final List<Item> itens, final int capacidade) {

        this.itens = itens;
        this.capacidade = capacidade;
    }

    @Override
    public void resolver() {

        final Solucao solucao = new Solucao("Guloso");
        solucao.setItens(new ArrayList<Item>());

        // Ordena o valor em ordewm decrescente
        this.insertionSort();

        int pesoAcumulado = 0;

        // busca o valor máximo possível
        for (final Item item : this.itens) {
            this.c++;
            if ((pesoAcumulado + item.getPeso()) <= this.getCapacidade()) {
                pesoAcumulado += item.getPeso();
                solucao.setValor(solucao.getValor() + item.getValor());
                // solucao.setPeso(solucao.getPeso() + item.getPeso());
                solucao.getItens().add(item);
            } else if (pesoAcumulado > this.getCapacidade()) {
                break;
            }
        }

        System.out.println(solucao.mostrar());
        System.out.println("# Número de instruções executadas: " + this.c);
    }

    private void insertionSort() {

        for (int i = 1; i < this.getItens().size(); i++) {
            final Item candidato = this.getItens().get(i);
            int j = i - 1;
            while ((j >= 0) && (candidato.getValor() > this.getItens().get(j).getValor())) {
                this.c++;
                this.getItens().set(j + 1, this.getItens().get(j));
                j = j - 1;
            }
            this.getItens().set(j + 1, candidato);
        }
    }

    public List<Item> getItens() {

        return this.itens;
    }

    public int getCapacidade() {

        return this.capacidade;
    }

}
