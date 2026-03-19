import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Random gerador = new Random();
        Scanner leia = new Scanner(System.in);

        String[] opcoes = {"Pedra", "Papel", "Tesoura"};

        boasvindas();
        int com = gerador.nextInt(3);
        int player = leia.nextInt();

        if (player == 0) {
            System.out.println("Saindo...");
            return;
        }

        player -= 1;
        rodada(opcoes, player, com);

        if (player == com) {
            mensagemDeEmpate(gerador);
        } else if ((player == 0 && com == 2) || (player == 1 && com == 0) || (player == 2 && com == 1)) {
            mensagemDoGanhador(gerador);
        } else {
            mensagemDePerda(gerador);
        }

        leia.close();

    }

    public static void boasvindas() {
        System.out.println("""
                ================================
                Bem-vindo ao Jogo de JOKENPO!
                Você Irá jogar contra a máquina.
                ================================
                Escolha sua opção:
                [0] Sair
                [1] Pedra
                [2] Papel
                [3] Tesoura
                ================================
                Digite a opção:""");
    }

    public static void rodada (String[] opcoes, int player, int com) {
        System.out.println("Você escolheu: " + opcoes[player]);
        System.out.println("O computador escolheu: " + opcoes[com]);
    }

    public static void mensagemDePerda(Random gerador) {
        String[] mensagem = {
                "Fim de Jogo. O computador venceu a rodada.",
                "Game Over! A máquina leu seus movimentos.",
                "Não foi dessa vez! A sorte está do lado dos circuitos.",
                "Você foi superado! Tentar novamente?"
        };

        // Seleciona um índice aleatório de 0 até o tamanho do array - 1
        String rodada = mensagem[gerador.nextInt(mensagem.length)];
        System.out.println(rodada);
    }

    public static void mensagemDeEmpate(Random gerador) {
        String[] mensagem = {
                "Empate! Mentes brilhantes pensam igual.",
                "Sincronia total! Ninguém pontuou.",
                "De novo! Escolham sua melhor tática novamente."
        };

        // Seleciona um índice aleatório de 0 até o tamanho do array - 1
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

        // Seleciona um índice aleatório de 0 até o tamanho do array - 1
        String rodada = mensagem[gerador.nextInt(mensagem.length)];
        System.out.println(rodada);
    }
}
