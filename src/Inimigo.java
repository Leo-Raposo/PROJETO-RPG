import java.util.Random;

public class Inimigo extends Classes.Personagem {
    private String tipo;
    private int recompensaXP;
    private Random random;

    public Inimigo(String nome, int pontosVida, int forca, int defesa, int destreza, String tipo, int recompensaXP) {
        super(nome, pontosVida, forca, defesa, destreza);
        this.tipo = tipo;
        this.recompensaXP = recompensaXP;
        this.random = new Random();
    }

    public String getTipo() {
        return tipo;
    }

    public int getRecompensaXP() {
        return recompensaXP;
    }

    public Item droparItem() {
        int chance = random.nextInt(100);
        if (chance < 40) {
            return new Item("Poção Vermelha", "Comum");
        } else if (chance < 60) {
            return new Item("Poção Laranja", "Comum");
        } else if (chance < 75) {
            return new Item("Poção Amarela", "Rara");
        } else if (chance < 85) {
            return new Item("Poção Branca", "Épica");
        } else if (chance < 95) {
            return new Item("Espada", "Comum");
        } else {
            return new Item("Espada Lendária", "Lendária");
        }
    }

    @Override
    public void atacar(Classes.Personagem alvo) {
        int dano = this.forca - alvo.getDefesa();
        dano = dano < 0 ? 0 : dano;
        alvo.receberDano(dano);
        System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
    }
}
