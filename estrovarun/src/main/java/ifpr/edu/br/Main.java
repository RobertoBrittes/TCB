package ifpr.edu.br;

import java.time.LocalDate;
import java.util.Scanner;

import ifpr.edu.br.controllers.AlunoController;
import ifpr.edu.br.controllers.TreinadorController;

public class Main {

    static TreinadorController treinadorController = new TreinadorController();
    static AlunoController alunoController = new AlunoController();

    public static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        limparTerminal();
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
        limparTerminal();
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
        System.out.print("Digite seu nome: ");
        SC.nextLine();
        String nome = SC.nextLine();
        System.out.println();
        System.out.print("Digite sua data de nascimento (AAAA-MM-DD): ");
        LocalDate dataNasc = java.time.LocalDate.parse(SC.nextLine());
        System.out.println();
        System.out.print("Digite seu telefone: ");
        String telefone = SC.nextLine();
        System.out.println();
        System.out.print("Digite seu email: ");
        String email = SC.nextLine();
        System.out.println();
        System.out.print("Crie sua senha:");
        String senha = SC.nextLine();
        System.out.println();
        System.out.println("Você é um(a):");
        System.out.println("1 - Aluno");
        System.out.println("2 - Treinador");
        int tipoUsuario = lerEscolha();
        switch (tipoUsuario) {
            case 1:     
                SC.nextLine();
                alunoController.cadastrarAluno(nome, telefone, dataNasc, email, senha, AlunoController.USER_ALUNO);
                break;
            default:
                SC.nextLine();
                System.out.println("Digite seu CREF:");
                String cref = SC.nextLine();
                treinadorController.cadastrarTreinador(nome, telefone, dataNasc, cref, email, senha, TreinadorController.USER_TREINADOR);
                break;
        }

    }

    public static void imprimirCabecalho() {
        System.out.println("""
            ,----,                                                                                                
          ,/   .`|              ,----..                                                                       ,--.
,---,.  .--.--.      ,`   .'  :,-.----.     /   /   \\                 ,---,              ,-.----.                        ,--.'|
,'  .' | /  /    '.  ;    ;     /\\    /  \\   /   .     :        ,---.  '  .' \\             \\    /  \\           ,--,    ,--,:  : |
,---.'   ||  :  /`. /.'___,/    ,' ;   :    \\ .   /   ;.  \\      /__./| /  ;    '.           ;   :    \\        ,'_ /| ,`--.'`|  ' :
|   |   .';  |  |--` |    :     |  |   | .\\ :.   ;   /  ` ; ,---.;  ; |:  :       \\          |   | .\\ :   .--. |  | : |   :  :  | |
:   :  |-,|  :  ;_   ;    |.';  ;  .   : |: |;   |  ; \\ ; |/___/ \\  | |:  |   /\\   \\         .   : |: | ,'_ /| :  . | :   |   \\ | :
:   |  ;/| \\  \\    `.`----'  |  |  |   |  \\ :|   :  | ; | '\\   ;  \\ ' ||  :  ' ;.   :        |   |  \\ : |  ' | |  . . |   : '  '; |
|   :   .'  `----.   \\   '   :  ;  |   : .  /.   |  ' ' ' : \\   \\  \\: ||  |  ;/  \\   \\       |   : .  / |  | ' |  | | '   ' ;.    ;
|   |  |-,  __ \\  \\  |   |   |  '  ;   | |  \\'   ;  \\; /  |  ;   \\  ' .'  :  | \\  \\ ,'       ;   | |  \\ :  | | :  ' ; |   | | \\   |
'   :  ;/| /  /`--'  /   '   :  |  |   | ;\\  \\\\   \\  ',  /    \\   \\   '|  |  '  '--'         |   | ;\\  \\|  ; ' |  | ' '   : |  ; .'
|   |    \\'--'.     /    ;   |.'   :   ' | \\.'' ;   :    /      \\   `  ;|  :  :               :   ' | \\.':  | : ;  ; | |   | '`--'  
|   :   .'  `--'---'     '---'     :   : :-'    \\   \\ .'        :   \\ ||  | ,'               :   : :-'  '  :  `--'   \\'   : |      
|   | ,'                           |   |.'       `---`           '---" `--''                 |   |.'    :  ,      .-./;   |.'      
`----'                             `---'                                                     `---'       `--`----'    '---'         
""");

        System.out.println("Olá, seja bem vindo ao Estrova Run\n");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
    }

    public static void limparTerminal() {
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
