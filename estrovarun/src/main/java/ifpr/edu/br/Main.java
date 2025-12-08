package ifpr.edu.br;

import java.time.LocalDate;
import java.util.Scanner;

import ifpr.edu.br.controllers.AlunoController;
import ifpr.edu.br.controllers.LoginController;
import ifpr.edu.br.controllers.TreinadorController;
import ifpr.edu.br.model.TelaAluno;
import ifpr.edu.br.model.TelaTreinador;
import ifpr.edu.br.model.Usuario;

public class Main {

    static TreinadorController treinadorController = new TreinadorController();
    static AlunoController alunoController = new AlunoController();
    static LoginController loginController = new LoginController();
    static TelaAluno telaAluno = new TelaAluno();
    static TelaTreinador telaTreinador = new TelaTreinador();

    public static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            limparTerminal();
            imprimirCabecalho();
            switch (lerEscolha()) {
                case 1:
                    efetuarCadastro();
                    break;
                case 2:
                    efetuarLogin();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void efetuarCadastro() {
        limparTerminal();
        System.out.println("=== Cadastro de Usuário ===");
        System.out.print("Digite seu nome: ");
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
                alunoController.cadastrarAluno(nome, telefone, dataNasc, email, senha, AlunoController.USER_ALUNO);
                break;
            default:
                System.out.println("Digite seu CREF:");
                String cref = SC.nextLine();
                treinadorController.cadastrarTreinador(nome, telefone, dataNasc, cref, email, senha,
                        TreinadorController.USER_TREINADOR);
                break;
        }

    }

    public static void efetuarLogin() throws InterruptedException {
        while (true) {
            limparTerminal();
            Usuario usuarioLogado = null;
            try {
                System.out.println("=== Login do Usuario ===");
                System.out.print("Digite seu email: ");
                String email = SC.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = SC.nextLine();
                usuarioLogado = loginController.efetuarLogin(email, senha);
                Thread.sleep(2000);
                if(usuarioLogado.getTipo_usuario().equalsIgnoreCase(AlunoController.USER_ALUNO)) {
                    telaAluno.exibirTela(usuarioLogado);
                    break;
                } else if (usuarioLogado.getTipo_usuario().equalsIgnoreCase(TreinadorController.USER_TREINADOR)) {
                    telaTreinador.exibirTela(usuarioLogado);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Erro ao efetuar login: " + e.getMessage());
                Thread.sleep(2000);
            }
        }
    }

    public static void imprimirCabecalho() {
        System.out.println("   _________________  ____ _   _____       ___  __  ___  __\r\n" + //
                        "  / __/ __/_  __/ _ \\/ __ \\ | / / _ |     / _ \\/ / / / |/ /\r\n" + //
                        " / _/_\\ \\  / / / , _/ /_/ / |/ / __ |    / , _/ /_/ /    / \r\n" + //
                        "/___/___/ /_/ /_/|_|\\____/|___/_/ |_|   /_/|_|\\____/_/|_/  \r\n" );

        System.out.println("Olá, seja bem vindo ao Estrova Run\n");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
        System.out.println("0 - Sair");
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
            escolha = Integer.parseInt(SC.nextLine());
        } while (escolha > 2 || escolha < 0);
        return escolha;
    }

}
