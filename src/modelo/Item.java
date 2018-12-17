package modelo;

public class Item {

    private int valor;

    private int peso;

    private int id;

    public Item(final int valor, final int peso, final int id) {

        this.valor = valor;
        this.peso = peso;
        this.id = id;
    }

    public int getValor() {

        return this.valor;
    }

    public void setValor(final int valor) {

        this.valor = valor;
    }

    public int getPeso() {

        return this.peso;
    }

    public void setPeso(final int peso) {

        this.peso = peso;
    }

    public int getId() {

        return this.id;
    }

    public void setId(final int id) {

        this.id = id;
    }

}
