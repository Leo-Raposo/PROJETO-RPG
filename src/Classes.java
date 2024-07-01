public class Classes {

    public static abstract class Personagem {
        protected String nome;
        protected int pontosVida;
        protected int pontosVidaMax;
        protected int forca;
        protected int defesa;
        protected int destreza;
        protected int nivel;
        protected int experiencia;
        protected int experienciaProximoNivel;

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

        public boolean estaVivo() {
            return pontosVida > 0;
        }

        public abstract void atacar(Personagem alvo);

        public void receberCura(int cura) {
            this.pontosVida += cura;
            if (this.pontosVida > this.pontosVidaMax) {
                this.pontosVida = this.pontosVidaMax;
            }
        }

        public void receberDano(int dano) {
            this.pontosVida -= dano;
        }

        public void equiparItem(Item item) {
            if (item.getNome().contains("Espada")) {
                this.forca += 10;
            } else if (item.getNome().contains("Armadura")) {
                this.defesa += 10;
            }
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
    }

    public static class Cavaleiro extends Personagem {
        public Cavaleiro(String nome) {
            super(nome, 100, 20, 10, 5);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Templario extends Personagem {
        public Templario(String nome) {
            super(nome, 100, 18, 12, 5);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Bruxo extends Personagem {
        private int pontosMagia;

        public Bruxo(String nome) {
            super(nome, 80, 25, 5, 7);
            this.pontosMagia = 10;
        }

        public int getPontosMagia() {
            return pontosMagia;
        }

        public void usarMagia(Personagem alvo) {
            if (pontosMagia > 0) {
                int danoMagia = this.forca * 2 - alvo.getDefesa();
                pontosMagia--;
                alvo.receberDano(danoMagia);
                System.out.println(this.nome + " usou magia em " + alvo.getNome() + " e causou " + danoMagia + " de dano.");
            } else {
                System.out.println(this.nome + " não tem pontos de magia suficientes.");
            }
        }

        @Override
        public void atacar(Personagem alvo) {
            usarMagia(alvo);
        }
    }

    public static class Sabio extends Personagem {
        private int pontosMagia;

        public Sabio(String nome) {
            super(nome, 85, 23, 6, 6);
            this.pontosMagia = 12;
        }

        public int getPontosMagia() {
            return pontosMagia;
        }

        public void usarMagia(Personagem alvo) {
            if (pontosMagia > 0) {
                int danoMagia = this.forca * 2 - alvo.getDefesa();
                pontosMagia--;
                alvo.receberDano(danoMagia);
                System.out.println(this.nome + " usou magia em " + alvo.getNome() + " e causou " + danoMagia + " de dano.");
            } else {
                System.out.println(this.nome + " não tem pontos de magia suficientes.");
            }
        }

        @Override
        public void atacar(Personagem alvo) {
            usarMagia(alvo);
        }
    }

    public static class Mercenario extends Personagem {
        public Mercenario(String nome) {
            super(nome, 95, 20, 8, 6);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Arruaceiro extends Personagem {
        public Arruaceiro(String nome) {
            super(nome, 90, 18, 10, 7);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Ferreiro extends Personagem {
        public Ferreiro(String nome) {
            super(nome, 110, 22, 8, 4);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Alquimista extends Personagem {
        public Alquimista(String nome) {
            super(nome, 85, 20, 7, 5);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Sacerdote extends Personagem {
        private int pontosMagia;

        public Sacerdote(String nome) {
            super(nome, 80, 15, 10, 6);
            this.pontosMagia = 15;
        }

        public int getPontosMagia() {
            return pontosMagia;
        }

        public void usarMagia(Personagem alvo) {
            if (pontosMagia > 0) {
                int danoMagia = this.forca * 2 - alvo.getDefesa();
                pontosMagia--;
                alvo.receberDano(danoMagia);
                System.out.println(this.nome + " usou magia em " + alvo.getNome() + " e causou " + danoMagia + " de dano.");
            } else {
                System.out.println(this.nome + " não tem pontos de magia suficientes.");
            }
        }

        @Override
        public void atacar(Personagem alvo) {
            usarMagia(alvo);
        }
    }

    public static class Monge extends Personagem {
        public Monge(String nome) {
            super(nome, 95, 21, 9, 6);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Cacador extends Personagem {
        public Cacador(String nome) {
            super(nome, 90, 19, 8, 8);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Bardo extends Personagem {
        public Bardo(String nome) {
            super(nome, 85, 18, 7, 7);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }

    public static class Odalisca extends Personagem {
        public Odalisca(String nome) {
            super(nome, 85, 17, 8, 8);
        }

        @Override
        public void atacar(Personagem alvo) {
            int dano = this.forca - alvo.getDefesa();
            dano = dano < 0 ? 0 : dano;
            alvo.receberDano(dano);
            System.out.println(this.nome + " atacou " + alvo.getNome() + " e causou " + dano + " de dano.");
        }
    }
}
