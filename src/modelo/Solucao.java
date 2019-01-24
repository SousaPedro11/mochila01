package modelo;

import java.util.List;

public class Solucao {

    // private int peso;

    private int valor;

    private List<Item> itens;

    private String estrategia;

    public Solucao(final String estrategia) {

        this.estrategia = estrategia.toUpperCase();
    }

    public String mostrar() {

        final StringBuilder sb = new StringBuilder();
        sb.append("# DADOS USANDO O ALGORITMO ")
                        .append(this.getEstrategia())
                        .append("\n");
        sb.append("# Valor: ").append(this.getValor());
        // sb.append("# Peso: ").append(this.getPeso()).append("\n");
        // String obj = "# Objetos: [";
        //
        // for (int i = 0; i < this.getItens().size(); i++) {
        // if (i == (this.getItens().size() - 1)) {
        // obj += this.getItens().get(i).getId() + "].";
        // } else {
        // obj += this.getItens().get(i).getId() + ", ";
        // }
        // }

        return sb.toString() /* + obj */;
    }

    // public int getPeso() {
    //
    // return this.peso;
    // }
    //
    // public void setPeso(final int peso) {
    //
    // this.peso = peso;
    // }

    public int getValor() {

        return this.valor;
    }

    public void setValor(final int valor) {

        this.valor = valor;
    }

    public List<Item> getItens() {

        return this.itens;
    }

    public void setItens(final List<Item> itens) {

        this.itens = itens;
    }

    public String getEstrategia() {

        return this.estrategia;
    }

    public void setEstrategia(final String estrategia) {

        this.estrategia = estrategia;
    }

}
