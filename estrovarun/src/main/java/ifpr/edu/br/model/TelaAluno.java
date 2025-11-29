package ifpr.edu.br.model;

import java.util.Scanner;

public class TelaAluno {

    final static Scanner SC = new Scanner(System.in);
    

    public void exibirTela(Usuario usuario) {
        while (true) {
            limparTerminal();
            System.out.println("Olá, " + usuario.getPessoa().getNome().split(" ")[0] + "! Bem-vindo ao menu do Aluno.");
            System.out.println("Escolha o que deseja fazer:");
            System.out.println("1 - Ver Perfil");
            switch (lerEscolha()) {
                case 1:
                    verPerfil(usuario);
                    break;
            
                default:
                    break;
            }
        }
    }

    private void verPerfil(Usuario usuario) {
        limparTerminal();
        System.out.println("=== Perfil do Aluno ===");
        System.out.println("Nome: " + usuario.getPessoa().getNome());
        System.out.println("Telefone: " + usuario.getPessoa().getTelefone());
        System.out.println("Data de Nascimento: " + usuario.getPessoa().getDataNasc());
        System.out.println("Email: " + usuario.getEmail());
        Aluno aluno = (Aluno) usuario.getPessoa();
        if (aluno.getTreinador() != null) {
            System.out.println("Treinador: " + aluno.getTreinador().getNome());
        } else {
            System.out.println("Treinador: Não atribuído");
        }
        System.out.println("Insira qualquer tecla para voltar...");
        SC.nextLine();
    }

    private static void limparTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.print("\033\143");
    }

    private int lerEscolha() {
        int escolha;
        do {
            escolha = Integer.parseInt(SC.nextLine());
        } while (escolha < 0 || escolha > 1);
        return escolha;
    }
}
