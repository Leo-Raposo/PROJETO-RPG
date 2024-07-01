public class Item {
    private String nome;
    private String raridade;

    public Item(String nome, String raridade) {
        this.nome = nome;
        this.raridade = raridade;
    }

    public String getNome() {
        return nome;
    }

    public String getRaridade() {
        return raridade;
    }

    public int getPreco() {
        switch (raridade) {
            case "Comum":
                return 50;
            case "Rara":
                return 100;
            case "Épica":
                return 200;
            case "Lendária":
                return 500;
            default:
                return 0;
        }
    }
}
