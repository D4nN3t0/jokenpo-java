import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Random gerador = new Random();
        Scanner leia = new Scanner(System.in);

        String[] jokenpo = {"Pedra", "Papel", "Tesoura"};

        int com;
        int player;
        int scorePlayer = 0, scoreMachine = 0;
        boolean leave = false;

        int partida = 0;

        do {
            boasvindas();

            int opcao = leia.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    leave = true;
                    break;

                case 1:
                case 2:
                case 3:
                    player = opcao - 1;
                    com = gerador.nextInt(3);

                    rodada(jokenpo, player, com);

                    int resultado = iniciarRound(player, com, gerador);

                    if (resultado == 1) {
                        scorePlayer++;
                    } else if (resultado == -1) {
                        scoreMachine++;
                    }

                    partida++;

                    // lógica melhor de 3
                    if (partida == 3 || scorePlayer == 2 || scoreMachine == 2) {
                        roundScore(scoreMachine, scorePlayer);
                        System.out.println("\nTemos um ganhador..?");

                        if (scoreMachine > scorePlayer) {
                            System.out.println("A Máquina levou a vitória para casa dessa vez!");
                        } else if (scorePlayer > scoreMachine){
                            System.out.println("Parabéns pela sua vitória, você ganhou da máquina!");
                        } else {
                            System.out.println("Houve um Empate!!");
                            System.out.println("Não foi dessa vez, tente novamente.");
                        }

                        leave = true;
                    }
                    break;

                case 4:
                    roundScore(scoreMachine, scorePlayer);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente");
            }

        } while (!leave);

        leia.close();
    }

    public static void boasvindas() {
        System.out.println("""
                ================================
                Bem-vindo ao Jogo de JOKENPO!
                Você irá jogar contra a máquina.
                Modo de jogo: MELHOR DE 3
                ================================
                Escolha sua opção:
                [0] Sair
                [1] Pedra
                [2] Papel
                [3] Tesoura
                [4] Mostrar a pontuação
                ================================
                Digite a opção:""");
    }

    public static void rodada(String[] jokenpo, int player, int com) {
        System.out.println("Você escolheu: " + jokenpo[player]);
        System.out.println("O computador escolheu: " + jokenpo[com]);
    }

    public static int iniciarRound(int player, int com, Random gerador) {
        if (player == com) {
            mensagemDeEmpate(gerador);
            return 0; // empate
        } else if ((player == 0 && com == 2) ||
                (player == 1 && com == 0) ||
                (player == 2 && com == 1)) {
            mensagemDoGanhador(gerador);
            return 1; // player ganhou
        } else {
            mensagemDePerda(gerador);
            return -1; // máquina ganhou
        }
    }

    public static void mensagemDePerda(Random gerador) {
        String[] mensagem = {
                "Fim de Jogo. O computador venceu a rodada.",
                "Game Over! A máquina leu seus movimentos.",
                "Não foi dessa vez! A sorte está do lado dos circuitos.",
                "Você foi superado! Tentar novamente?"
        };

        String rodada = mensagem[gerador.nextInt(mensagem.length)];
        System.out.println(rodada);
    }

    public static void mensagemDeEmpate(Random gerador) {
        String[] mensagem = {
                "Empate! Mentes brilhantes pensam igual.",
                "Sincronia total! Ninguém pontuou.",
                "De novo! Escolham sua melhor tática novamente."
        };

        String rodada = mensagem[gerador.nextInt(mensagem.length)];
        System.out.println(rodada);
    }

    public static void mensagemDoGanhador(Random gerador) {
        String[] mensagem = {
                "Sensacional! Você previu o movimento inimigo.",
                "Mandou bem! O computador não teve chance.",
                "Vitória! Você superou a lógica da máquina.",
                "Vitória Real! A CPU foi completamente dominada."
        };

        String rodada = mensagem[gerador.nextInt(mensagem.length)];
        System.out.println(rodada);
    }

    public static void roundScore(int scoreMachine, int scorePlayer) {
        System.out.printf("Computador: %d Pontos\n", scoreMachine);
        System.out.printf("Player: %d Pontos\n", scorePlayer);
    }
}