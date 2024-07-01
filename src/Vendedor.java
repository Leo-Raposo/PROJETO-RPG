import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vendedor {
    private List<Item> itensVenda;
    private Random random;

    public Vendedor() {
        this.itensVenda = new ArrayList<>();
        this.random = new Random();
        gerarItensParaVenda();
    }

    private void gerarItensParaVenda() {
        itensVenda.add(new Item("Poção Vermelha", "Comum"));
        itensVenda.add(new Item("Poção Laranja", "Comum"));
        itensVenda.add(new Item("Poção Amarela", "Rara"));
        itensVenda.add(new Item("Poção Branca", "Épica"));
        itensVenda.add(new Item("Espada", "Comum"));
        itensVenda.add(new Item("Armadura de Couro", "Comum"));
        itensVenda.add(new Item("Espada Lendária", "Lendária"));
        itensVenda.add(new Item("Armadura de Dragão", "Lendária"));
    }

    public void mostrarItens() {
        System.out.println("Itens à venda:");
        for (int i = 0; i < itensVenda.size(); i++) {
            Item item = itensVenda.get(i);
            System.out.println((i + 1) + ". " + item.getNome() + " - " + item.getRaridade());
        }
    }

    public Item comprarItem(int index, int zenny) {
        if (index < 1 || index > itensVenda.size()) {
            System.out.println("Índice inválido.");
            return null;
        }

        Item item = itensVenda.get(index - 1);
        if (zenny >= item.getPreco()) {
            System.out.println("Item comprado: " + item.getNome());
            return item;
        } else {
            System.out.println("Zenny insuficiente.");
            return null;
        }
    }
}
