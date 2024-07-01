import java.util.*;

public class Batalha {
    private List<Classes.Personagem> personagens;
    private List<Inimigo> inimigos;
    private Vendedor vendedor;

    public Batalha(List<Classes.Personagem> personagens, List<Inimigo> inimigos) {
        this.personagens = personagens;
        this.inimigos = inimigos;
        this.vendedor = new Vendedor();
    }

    public void iniciar() {
        List<Classes.Personagem> todos = new ArrayList<>();
        todos.addAll(personagens);
        todos.addAll(inimigos);

        todos.sort(Comparator.comparingInt(Classes.Personagem::getDestreza).reversed());

        while (algumVivo(personagens) && algumVivo(inimigos)) {
            for (Classes.Personagem p : todos) {
                if (p.estaVivo()) {
                    if (personagens.contains(p)) {
                        Classes.Personagem alvo = escolherAlvo(inimigos);
                        realizarAcao(p, alvo);
                    } else {
                        Classes.Personagem alvo = escolherAlvo(personagens);
                        realizarAcao(p, alvo);
                    }
                }
            }
        }

        if (algumVivo(personagens)) {
            System.out.println("Os personagens venceram a batalha!");
        } else {
            System.out.println("Os inimigos venceram a batalha...");
        }
    }

    private void realizarAcao(Classes.Personagem atacante, Classes.Personagem alvo) {
        Scanner scanner = new Scanner(System.in);
        if (atacante instanceof Inimigo) {
            atacar(atacante, alvo);
        } else {
            System.out.println(atacante.getNome() + ", escolha uma ação:");
            System.out.println("1. Atacar");
//            System.out.println("2. Ir ao Vendedor");
//            System.out.println("3. Abrir Inventário");
            System.out.println("4. Fugir");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    atacar(atacante, alvo);
                    break;
//                case 2:
//                    visitarVendedor(atacante);
//                    break;
//                case 3:
//                    abrirInventario(atacante);
//                    break;
                case 4:
                    if (fugir()) {
                        System.out.println(atacante.getNome() + " conseguiu fugir!");
                        return;
                    } else {
                        System.out.println(atacante.getNome() + " falhou em fugir.");
                    }
                    break;
                default:
                    System.out.println("Escolha inválida.");
                    realizarAcao(atacante, alvo);
                    break;
            }
        }
    }

//    private void visitarVendedor(Classes.Personagem personagem) {
//        vendedor.mostrarItens();
//        System.out.println("Digite o número do item que deseja comprar, ou 0 para sair:");
//        Scanner scanner = new Scanner(System.in);
//        int escolha = scanner.nextInt();
//        if (escolha != 0) {
//            Item item = vendedor.comprarItem(escolha, personagem.getZenny());
//            if (item != null) {
//                personagem.adicionarItem(item);
//                personagem.gastarZenny(item.getPreco());
//            }
//        }
//    }

//    private void abrirInventario(Classes.Personagem personagem) {
//        personagem.mostrarInventario();
//        System.out.println("Digite o número do item que deseja usar, ou 0 para sair:");
//        Scanner scanner = new Scanner(System.in);
//        int escolha = scanner.nextInt();
//        if (escolha != 0) {
//            personagem.usarItem(escolha);
//        }
//    }

    private void atacar(Classes.Personagem atacante, Classes.Personagem alvo) {
        int valorDado = rolarDado();
        System.out.println(atacante.getNome() + " rolou um " + valorDado + " no D20.");

        int dano;
        if (valorDado == 20) {
            System.out.println("Ataque Crítico!");
            dano = atacante.getForca() * 2 - alvo.getDefesa();
            alvo.setEnvenenado(true);
        } else if (valorDado >= 15) {
            System.out.println("Ataque Forte!");
            dano = atacante.getForca() - alvo.getDefesa();
        } else if (valorDado >= 10) {
            System.out.println("Ataque Médio!");
            dano = (int)(atacante.getForca() * 0.75) - alvo.getDefesa();
        } else if (valorDado >= 5) {
            System.out.println("Ataque Fraco!");
            dano = (int)(atacante.getForca() * 0.5) - alvo.getDefesa();
        } else {
            System.out.println("Errou o ataque!");
            dano = 0;
        }

        dano = dano < 0 ? 0 : dano;
        alvo.receberDano(dano);
        System.out.println(atacante.getNome() + " causou " + dano + " de dano em " + alvo.getNome() + ".");
        System.out.println("Vida de " + alvo.getNome() + ": " + formatarVida(alvo));
    }

    private boolean fugir() {
        int valorDado = rolarDado();
        System.out.println("Você rolou um " + valorDado + " no D20.");
        return valorDado >= 15;
    }

    private int rolarDado() {
        Random random = new Random();
        return random.nextInt(20) + 1;
    }

    private Classes.Personagem escolherAlvo(List<? extends Classes.Personagem> lista) {
        for (Classes.Personagem p : lista) {
            if (p.estaVivo()) {
                return p;
            }
        }
        return null;
    }

    private boolean algumVivo(List<? extends Classes.Personagem> lista) {
        for (Classes.Personagem p : lista) {
            if (p.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    private String formatarVida(Classes.Personagem personagem) {
        return personagem.getPontosVida() + "/" + personagem.getPontosVidaMax();
    }
}
