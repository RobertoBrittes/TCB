package ifpr.edu.br;

import ifpr.edu.br.controllers.TreinadorController;
import ifpr.edu.br.controllers.AlunoController;
import ifpr.edu.br.model.Aluno;
import ifpr.edu.br.model.Treinador;

import java.util.Scanner;

public class Main {

    static TreinadorController treinadorController = new TreinadorController();
    static AlunoController alunoController = new AlunoController();

    public static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        imprimirCabecalho();
        switch (lerEscolha()) {
            case 1:
                efetuarCadastro();
                break;

            default:

                break;
        }
    }

    public static void efetuarCadastro() {
        limpaTerminal();
        System.out.println("""
                 ██████╗ █████╗ ██████╗  █████╗ ███████╗████████╗██████╗  ██████╗
                ██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██╔═══██╗
                ██║     ███████║██║  ██║███████║███████╗   ██║   ██████╔╝██║   ██║
                ██║     ██╔══██║██║  ██║██╔══██║╚════██║   ██║   ██╔══██╗██║   ██║
                ╚██████╗██║  ██║██████╔╝██║  ██║███████║   ██║   ██║  ██║╚██████╔╝
                 ╚═════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝


                ██████╗ ███████╗    ██╗   ██╗███████╗██╗   ██╗ █████╗ ██████╗ ██╗ ██████╗
                ██╔══██╗██╔════╝    ██║   ██║██╔════╝██║   ██║██╔══██╗██╔══██╗██║██╔═══██╗
                ██║  ██║█████╗      ██║   ██║███████╗██║   ██║███████║██████╔╝██║██║   ██║
                ██║  ██║██╔══╝      ██║   ██║╚════██║██║   ██║██╔══██║██╔══██╗██║██║   ██║
                ██████╔╝███████╗    ╚██████╔╝███████║╚██████╔╝██║  ██║██║  ██║██║╚██████╔╝
                ╚═════╝ ╚══════╝     ╚═════╝ ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝
                """);
        System.out.println("Digite seu nome:");
        SC.nextLine();
        String nome = SC.nextLine();
        System.out.println("Você é um(a):");
        System.out.println("1 - Aluno");
        System.out.println("2 - Treinador");
        int tipoUsuario = lerEscolha();
        System.out.println("");
        System.out.println("Digite seu email:");
        String email = SC.nextLine();
        System.out.println("Digite sua senha:");
        String senha = SC.nextLine();

    }

    public static void imprimirCabecalho() {
        System.out.println(
                "                                                                                                                      \r\n"
                        + //
                        "                                                                                                                      \r\n"
                        + //
                        "    ,---,.              ___                                                      ,-.----.                             \r\n"
                        + //
                        "  ,'  .' |            ,--.'|_                                                    \\    /  \\                            \r\n"
                        + //
                        ",---.'   |            |  | :,'   __  ,-.   ,---.                                 ;   :    \\          ,--,      ,---,  \r\n"
                        + //
                        "|   |   .'  .--.--.   :  : ' : ,' ,'/ /|  '   ,'\\      .---.                     |   | .\\ :        ,'_ /|  ,-+-. /  | \r\n"
                        + //
                        ":   :  |-, /  /    '.;__,'  /  '  | |' | /   /   |   /.  ./|   ,--.--.           .   : |: |   .--. |  | : ,--.'|'   | \r\n"
                        + //
                        ":   |  ;/||  :  /`./|  |   |   |  |   ,'.   ; ,. : .-' . ' |  /       \\          |   |  \\ : ,'_ /| :  . ||   |  ,\"' | \r\n"
                        + //
                        "|   :   .'|  :  ;_  :__,'| :   '  :  /  '   | |: :/___/ \\: | .--.  .-. |         |   : .  / |  ' | |  . .|   | /  | | \r\n"
                        + //
                        "|   |  |-, \\  \\    `. '  : |__ |  | '   '   | .; :.   \\  ' .  \\__\\/: . .         ;   | |  \\ |  | ' |  | ||   | |  | | \r\n"
                        + //
                        "'   :  ;/|  `----.   \\|  | '.'|;  : |   |   :    | \\   \\   '  ,\" .--.; |         |   | ;\\  \\:  | : ;  ; ||   | |  |/  \r\n"
                        + //
                        "|   |    \\ /  /`--'  /;  :    ;|  , ;    \\   \\  /   \\   \\    /  /  ,.  |         :   ' | \\.''  :  `--'   \\   | |--'   \r\n"
                        + //
                        "|   :   .''--'.     / |  ,   /  ---'      `----'     \\   \\ |;  :   .'   \\        :   : :-'  :  ,      .-./   |/       \r\n"
                        + //
                        "|   | ,'    `--'---'   ---`-'                         '---\" |  ,     .-./        |   |.'     `--`----'   '---'        \r\n"
                        + //
                        "`----'                                                       `--`---'            `---'                                \r\n"
                        + //
                        "                                                                                                                      ");
        System.out.println("Olá, seja bem vindo ao Estrova Run\n");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
    }

    public static void limpaTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.print("\033\143");
    }

    public static int lerEscolha() {
        int escolha = 0;
        do {
            escolha = SC.nextInt();
        } while (escolha > 2 || escolha < 1);
        return escolha;
    }

}
