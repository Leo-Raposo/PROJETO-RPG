import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private List<Classes.Personagem> personagens;
    private List<Inimigo> todosInimigos;
    private Inventario inventario;
    private Scanner scanner;
    private Random random;

    public Jogo() {
        this.personagens = new ArrayList<>();
        this.todosInimigos = new ArrayList<>();
        this.inventario = new Inventario();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void iniciar() {
        criarPersonagens();
        criarInimigos();

        for (int i = 0; i < todosInimigos.size(); i++) {
            Inimigo inimigo = todosInimigos.get(i);
            System.out.println("\nUm " + inimigo.getNome() + " apareceu!");
            List<Inimigo> inimigos = new ArrayList<>();
            inimigos.add(inimigo);

            Batalha batalha = new Batalha(personagens, inimigos);
            batalha.iniciar();

            if (!algumVivo(personagens)) {
                System.out.println("Os personagens foram derrotados...");
                break;
            }

            for (Classes.Personagem personagem : personagens) {
                personagem.ganharExperiencia(inimigo.getRecompensaXP());
                Item drop = inimigo.droparItem();
                if (drop != null) {
                    inventario.adicionarItem(drop);
                    System.out.println(personagem.getNome() + " recebeu " + drop.getNome() + " como drop.");
                }
                //personagem.adicionarZenny(random.nextInt(100) + 50);
            }
        }

        if (algumVivo(personagens)) {
            System.out.println("Os personagens venceram todos os desafios!");
        }
    }

    private void criarPersonagens() {
        System.out.println("Escolha seu personagem:");
        System.out.println("1. Cavaleiro");
        System.out.println("2. Templario");
        System.out.println("3. Bruxo");
        System.out.println("4. Sabio");
        System.out.println("5. Mercenario");
        System.out.println("6. Arruaceiro");
        System.out.println("7. Ferreiro");
        System.out.println("8. Alquimista");
        System.out.println("9. Sacerdote");
        System.out.println("10. Monge");
        System.out.println("11. Cacador");
        System.out.println("12. Bardo");
        System.out.println("13. Odalisca");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                personagens.add(new Classes.Cavaleiro("Cavaleiro"));
                break;
            case 2:
                personagens.add(new Classes.Templario("Templario"));
                break;
            case 3:
                personagens.add(new Classes.Bruxo("Bruxo"));
                break;
            case 4:
                personagens.add(new Classes.Sabio("Sabio"));
                break;
            case 5:
                personagens.add(new Classes.Mercenario("Mercenario"));
                break;
            case 6:
                personagens.add(new Classes.Arruaceiro("Arruaceiro"));
                break;
            case 7:
                personagens.add(new Classes.Ferreiro("Ferreiro"));
                break;
            case 8:
                personagens.add(new Classes.Alquimista("Alquimista"));
                break;
            case 9:
                personagens.add(new Classes.Sacerdote("Sacerdote"));
                break;
            case 10:
                personagens.add(new Classes.Monge("Monge"));
                break;
            case 11:
                personagens.add(new Classes.Cacador("Cacador"));
                break;
            case 12:
                personagens.add(new Classes.Bardo("Bardo"));
                break;
            case 13:
                personagens.add(new Classes.Odalisca("Odalisca"));
                break;
            default:
                System.out.println("Escolha inválida.");
                criarPersonagens();
                break;
        }

        for (Classes.Personagem personagem : personagens) {
            System.out.println(personagem.getNome() + " criado com " + formatarVida(personagem));
        }
    }

    private void criarInimigos() {
        todosInimigos.add(new Inimigo("Poring", 10, 5, 2, 1, "Monstro", 10));
        todosInimigos.add(new Inimigo("Fabre", 15, 7, 3, 2, "Monstro", 15));
        todosInimigos.add(new Inimigo("Drops", 20, 10, 4, 3, "Monstro", 20));
        todosInimigos.add(new Inimigo("Tarou", 25, 12, 5, 4, "Monstro", 25));
        todosInimigos.add(new Inimigo("Thief Bug", 30, 15, 6, 5, "Monstro", 30));
        todosInimigos.add(new Inimigo("Hornet", 35, 18, 7, 6, "Monstro", 35));
        todosInimigos.add(new Inimigo("Mandragora", 40, 20, 8, 7, "Monstro", 40));
        todosInimigos.add(new Inimigo("Wolf", 45, 22, 9, 8, "Monstro", 45));
        todosInimigos.add(new Inimigo("Elder Willow", 50, 25, 10, 9, "Monstro", 50));
        todosInimigos.add(new Inimigo("Tarou", 55, 28, 11, 10, "Monstro", 55));
        todosInimigos.add(new Inimigo("Bigfoot", 60, 30, 12, 11, "Monstro", 60));
        todosInimigos.add(new Inimigo("Goblin", 65, 35, 13, 12, "Monstro", 65));
        todosInimigos.add(new Inimigo("Orc Warrior", 70, 40, 14, 13, "Monstro", 70));
        todosInimigos.add(new Inimigo("Minorous", 75, 45, 15, 14, "Monstro", 75));
        todosInimigos.add(new Inimigo("Baphomet", 100, 60, 20, 15, "MVP", 100));

        for (Inimigo inimigo : todosInimigos) {
            System.out.println(inimigo.getNome() + " criado com " + formatarVida(inimigo));
        }
    }

    private String formatarVida(Classes.Personagem personagem) {
        return personagem.getPontosVida() + "/" + personagem.getPontosVidaMax();
    }

    private boolean algumVivo(List<Classes.Personagem> lista) {
        for (Classes.Personagem p : lista) {
            if (p.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciar();
    }
}
