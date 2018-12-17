package util;

import java.util.List;

import modelo.Item;

public abstract class Utilitario {

    public abstract void resolver();

    public int calculaBeneficio(final List<Item> itens) {

        int beneficio = 0;

        for (final Item item : itens) {
            beneficio += item.getValor();
        }
        return beneficio;
    }

    public int calculaPeso(final List<Item> itens) {

        int peso = 0;

        for (final Item item : itens) {
            peso += item.getPeso();
        }
        return peso;
    }
}
