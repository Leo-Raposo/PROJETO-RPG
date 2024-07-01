import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public void mostrarItens() {
        System.out.println("Itens no inventário:");
        for (Item item : itens) {
            System.out.println("- " + item.getNome());
        }
    }

    public List<Item> getItens() {
        return itens;
    }

    public void usarItem(int index, Personagem personagem) {
        if (index < 1 || index > itens.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Item item = itens.get(index - 1);
        if (item.getNome().contains("Poção")) {
            int cura = 0;
            switch (item.getNome()) {
                case "Poção Vermelha":
                    cura = 20;
                    break;
                case "Poção Laranja":
                    cura = 50;
                    break;
                case "Poção Amarela":
                    cura = 100;
                    break;
                case "Poção Branca":
                    cura = 200;
                    break;
            }
            personagem.receberCura(cura);
            System.out.println(personagem.getNome() + " usou " + item.getNome() + " e recuperou " + cura + " HP.");
            itens.remove(item);
        } else if (item.getNome().contains("Espada") || item.getNome().contains("Armadura")) {
            personagem.equiparItem(item);
            System.out.println(personagem.getNome() + " equipou " + item.getNome() + ".");
            itens.remove(item);
        } else {
            System.out.println("Não é possível usar este item.");
        }
    }
}
