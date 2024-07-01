import java.util.ArrayList;
import java.util.List;

public abstract class Personagem {
    protected String nome;
    protected int pontosVida;
    protected int pontosVidaMax;
    protected int forca;
    protected int defesa;
    protected int destreza;
    protected int nivel;
    protected int experiencia;
    protected int experienciaProximoNivel;
    protected int zenny;
    protected Inventario inventario;

    protected boolean envenenado;
    protected boolean atordoado;
    protected boolean queimado;
    protected boolean dormindo;
    protected int turnosEnvenenado;
    protected int turnosQueimado;
    protected int turnosDormindo;

    public Personagem(String nome, int pontosVida, int forca, int defesa, int destreza) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.pontosVidaMax = pontosVida;
        this.forca = forca;
        this.defesa = defesa;
        this.destreza = destreza;
        this.nivel = 1;
        this.experiencia = 0;
        this.experienciaProximoNivel = 100;
        this.zenny = 0;
        this.inventario = new Inventario();
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public int getPontosVidaMax() {
        return pontosVidaMax;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getZenny() {
        return zenny;
    }

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public abstract void atacar(Personagem alvo);

    public void receberDano(int dano) {
        this.pontosVida -= dano;
    }

    public void receberCura(int cura) {
        this.pontosVida += cura;
        if (this.pontosVida > this.pontosVidaMax) {
            this.pontosVida = this.pontosVidaMax;
        }
    }

    public void adicionarZenny(int quantidade) {
        this.zenny += quantidade;
    }

    public void gastarZenny(int quantidade) {
        if (this.zenny >= quantidade) {
            this.zenny -= quantidade;
        } else {
            System.out.println("Zenny insuficiente.");
        }
    }

    public void adicionarItem(Item item) {
        this.inventario.adicionarItem(item);
    }

    public void mostrarInventario() {
        this.inventario.mostrarItens();
    }

    public void usarItem(int index) {
        this.inventario.usarItem(index, this);
    }

    public void equiparItem(Item item) {
        if (item.getNome().contains("Espada")) {
            this.forca += 10;
        } else if (item.getNome().contains("Armadura")) {
            this.defesa += 10;
        }
    }

    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        while (this.experiencia >= this.experienciaProximoNivel) {
            this.experiencia -= this.experienciaProximoNivel;
            subirNivel();
        }
    }

    private void subirNivel() {
        this.nivel++;
        this.experienciaProximoNivel += 100;
        this.pontosVidaMax += 10;
        this.pontosVida = this.pontosVidaMax;
        this.forca += 2;
        this.defesa += 2;
        this.destreza += 1;
        System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");
    }

    public boolean isEnvenenado() {
        return envenenado;
    }

    public void setEnvenenado(boolean envenenado) {
        this.envenenado = envenenado;
        if (envenenado) {
            this.turnosEnvenenado = 3;
        }
    }

    public boolean isAtordoado() {
        return atordoado;
    }

    public void setAtordoado(boolean atordoado) {
        this.atordoado = atordoado;
    }

    public boolean isQueimado() {
        return queimado;
    }

    public void setQueimado(boolean queimado) {
        this.queimado = queimado;
        if (queimado) {
            this.turnosQueimado = 3;
        }
    }

    public boolean isDormindo() {
        return dormindo;
    }

    public void setDormindo(boolean dormindo) {
        this.dormindo = dormindo;
        if (dormindo) {
            this.turnosDormindo = 2;
        }
    }

    public void aplicarEfeitos() {
        if (envenenado) {
            this.pontosVida -= 5;
            turnosEnvenenado--;
            if (turnosEnvenenado <= 0) {
                envenenado = false;
            }
        }
        if (queimado) {
            this.pontosVida -= 5;
            turnosQueimado--;
            if (turnosQueimado <= 0) {
                queimado = false;
            }
        }
        if (dormindo) {
            turnosDormindo--;
            if (turnosDormindo <= 0) {
                dormindo = false;
            }
        }
        if (atordoado) {
            atordoado = false;
        }
    }
}
