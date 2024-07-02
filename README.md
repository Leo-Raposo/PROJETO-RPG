# RPG de Turno em Java

Bem-vindo ao projeto de RPG de Turno em Java! Este projeto é uma implementação básica de um jogo de RPG de turno utilizando os fundamentos de Programação Orientada a Objetos (POO) e outras estruturas essenciais da linguagem Java, como laços de repetição e condicionais. O jogo apresenta personagens jogáveis, inimigos, combate, inventário, sistema de vendedor e progressão de personagens.

## Funcionalidades

### Criação de Personagens

- **Classes de Personagens**: Cavaleiro, Templário, Bruxo, Sábio, Mercenário, Arruaceiro, Ferreiro, Alquimista, Sacerdote, Monge, Caçador, Bardo e Odalisca.
- **Atributos**: Cada personagem possui atributos como pontos de vida, força, defesa, destreza, nível, experiência e zenny (moeda do jogo).

### Combate

- **Sistema de Turnos**: Cada personagem e inimigo realiza uma ação por turno.
- **Ações Disponíveis**: Atacar, Ir ao Vendedor, Abrir Inventário, Fugir.
- **Rolagem de Dado**: Utiliza um D20 para determinar o sucesso e a intensidade dos ataques.

### Inimigos

- **Variedade de Inimigos**: 15 tipos de inimigos baseados no jogo Ragnarok Online, incluindo um chefe final (MVP).
- **Drops**: Inimigos podem dropar itens e zenny ao serem derrotados.

### Inventário

- **Gerenciamento de Itens**: Jogadores podem adicionar, visualizar e usar itens do inventário.
- **Tipos de Itens**: Poções, armas, armaduras e itens valiosos com diferentes raridades (Comum, Rara, Épica, Lendária).

### Vendedor

- **Sistema de Compra**: Jogadores podem comprar itens do vendedor usando zenny.
- **Itens à Venda**: Diversos itens, incluindo poções e equipamentos, disponíveis para compra.

### Progressão de Personagem

- **Ganho de Experiência**: Personagens ganham experiência ao derrotar inimigos.
- **Subida de Nível**: Ao atingir um determinado valor de experiência, os personagens sobem de nível, melhorando seus atributos.

## Como Jogar

1. **Escolha de Personagem**: Ao iniciar o jogo, escolha uma das classes disponíveis.
2. **Início da Batalha**: Enfrente uma sequência de inimigos, um por vez, até enfrentar o chefe final.
3. **Combate**: Durante o combate, escolha entre atacar, visitar o vendedor, abrir o inventário ou fugir.
4. **Gerenciamento de Inventário**: Use itens para curar ou melhorar seu personagem.
5. **Visitar o Vendedor**: Compre novos itens para fortalecer seu personagem.
6. **Progredir no Jogo**: Derrote inimigos, ganhe experiência, suba de nível e colete itens valiosos.

## Estrutura do Projeto

- `Jogo.java`: Classe principal que controla o fluxo do jogo.
- `Personagem.java`: Classe abstrata que define os atributos e métodos comuns a todos os personagens.
- `Classes.java`: Contém todas as subclasses específicas de personagens.
- `Inimigo.java`: Classe que define os atributos e comportamentos dos inimigos.
- `Batalha.java`: Classe que gerencia o sistema de combate.
- `Inventario.java`: Classe que gerencia os itens do inventário dos personagens.
- `Vendedor.java`: Classe que gerencia a loja de itens.

## Requisitos

- Java JDK 8 ou superior
- IDE Java (IntelliJ, Eclipse, NetBeans, etc.)

## Como Executar

1. Clone o repositório:
    ```sh
    git clone https://github.com/Leo-Raposo/projeto-RPG.git
    ```
2. Navegue até o diretório do projeto:
    ```sh
    cd seu-repositorio
    ```
3. Compile o projeto:
    ```sh
    javac *.java
    ```
4. Execute o jogo:
    ```sh
    java Jogo
    ```

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues para discutir novas funcionalidades ou reportar bugs. Pull requests também são bem-vindos.

---

Desenvolvido como projeto acadêmico para demonstração de conceitos de Programação Orientada a Objetos e estruturas de controle em Java.
