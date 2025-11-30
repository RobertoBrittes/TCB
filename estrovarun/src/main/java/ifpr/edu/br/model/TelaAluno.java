package ifpr.edu.br.model;

import java.util.Scanner;
import ifpr.edu.br.controllers.UsuarioController;
import ifpr.edu.br.controllers.PessoaController;
import ifpr.edu.br.controllers.TreinadorController;
import ifpr.edu.br.controllers.AlunoController;

public class TelaAluno {

    final static Scanner SC = new Scanner(System.in);
    static UsuarioController usuarioController = new UsuarioController();
    static PessoaController pessoaController = new PessoaController();
    static AlunoController alunoController = new AlunoController();
    static TreinadorController treinadorController = new TreinadorController();

    public void exibirTela(Usuario usuario) {
        while (true) {
            limparTerminal();
            System.out.println("=== Menu do Aluno ===");
            System.out.println("Olá, " + usuario.getPessoa().getNome().split(" ")[0] + "! Bem-vindo ao menu do Aluno.");
            System.out.println("Escolha o que deseja fazer:");
            System.out.println("1 - Ver Perfil");
            System.out.println("2 - Alterar Perfil");
            System.out.println("3 - Ver Perfil do Treinador");
            System.out.println("0 - Sair");
            switch (lerEscolha()) {
                case 0:
                    return;
                case 1:
                    verPerfil(usuario);
                    break;
                case 2:
                    alterarPerfil(usuario);
                    break;
                case 3:
                    verPerfilTreinador(usuario);
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

    private void alterarPerfil(Usuario usuario) {
        while (true) {
            limparTerminal();
            System.out.println("=== Alterar Perfil ===");
            System.out.println("1 - Alterar Senha");
            System.out.println("2 - Alterar Email");
            System.out.println("3 - Alterar Telefone");
            System.out.println("4 - Alterar Nome");
            System.out.println("5 - Atribuir Treinador");
            System.out.println("6 - Trocar Treinador");
            switch (lerEscolha()) {
                case 0:
                    return;
                case 1:
                    alterarSenha(usuario);
                    break;
                case 2:
                    alterarEmail(usuario);
                    break;
                case 3:
                    alterarTelefone(usuario);
                    break;
                case 4:
                    alterarNome(usuario);
                    break;
                case 5:
                    atribuirTreinador(usuario);
                    break;
                case 6:
                    trocarTreinador(usuario);
                    break;
                default:
                    return;
            }
        }

    }

    private void alterarSenha(Usuario usuario) {
        try {
            limparTerminal();
            System.out.println("=== Alterar Senha ===");
            System.out.print("Digite sua senha atual: ");
            String senhaAtual = SC.nextLine();
            if (!usuario.getSenha().equals(senhaAtual)) {
                System.out.println("Senha atual incorreta. Pressione qualquer tecla para voltar...");
                SC.nextLine();
                return;
            }
            System.out.print("Digite a nova senha: ");
            String novaSenha = SC.nextLine();
            usuario.setSenha(novaSenha);
            usuarioController.atualizarSenha(usuario.getId(), novaSenha);
            System.out.println("Senha alterada com sucesso! Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (RuntimeException e) {
            System.out.println("Erro ao alterar senha: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private void alterarEmail(Usuario usuario) {
        try {
            limparTerminal();
            System.out.println("=== Alterar Email ===");
            System.out.print("Digite o novo email: ");
            String novoEmail = SC.nextLine();
            usuario.setEmail(novoEmail);
            usuarioController.atualizarEmail(usuario.getId(), novoEmail);
            System.out.println("Email alterado com sucesso! Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao alterar email: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private void alterarTelefone(Usuario usuario) {

        try {
            limparTerminal();
            System.out.println("=== Alterar Telefone ===");
            System.out.print("Digite o novo telefone: ");
            String novoTelefone = SC.nextLine();
            usuario.getPessoa().setTelefone(novoTelefone);
            pessoaController.atualizarTelefone(usuario.getPessoa().getId(), novoTelefone);
            System.out.println("Telefone alterado com sucesso! Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao alterar telefone: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private void alterarNome(Usuario usuario) {
        try {
            limparTerminal();
            System.out.println("=== Alterar Nome ===");
            System.out.print("Digite o novo nome: ");
            String novoNome = SC.nextLine();
            usuario.getPessoa().setNome(novoNome);
            pessoaController.atualizarNome(usuario.getPessoa().getId(), novoNome);
            System.out.println("Nome alterado com sucesso! Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao alterar nome: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private void atribuirTreinador(Usuario usuario) {
        try {
            limparTerminal();
            Aluno aluno = (Aluno) usuario.getPessoa();
            if (aluno.getTreinador() != null) {
                System.out
                        .println("Você já possui um treinador atribuído. Use a opção 'Trocar Treinador' para alterar.");
            } else {
                System.out.println("=== Atribuir Treinador ===");
                System.out.print("Digite o email do treinador a ser atribuído: ");
                String emailTreinador = SC.nextLine();
                aluno = alunoController.atribuirTreinador(usuario.getPessoa().getId(), emailTreinador);
                usuario.setPessoa(aluno);
                System.out.println("Treinador atribuído com sucesso! ");
            }
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao atribuir treinador: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private void trocarTreinador(Usuario usuario) {
        try {
            limparTerminal();
            Aluno aluno = (Aluno) usuario.getPessoa();
            System.out.println("=== Trocar Treinador ===");
            System.out.print("Digite o email do novo treinador: ");
            String emailTreinador = SC.nextLine();
            aluno = alunoController.atribuirTreinador(usuario.getPessoa().getId(), emailTreinador);
            usuario.setPessoa(aluno);
            System.out.println("Treinador trocado com sucesso! ");
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao trocar treinador: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private void verPerfilTreinador(Usuario usuario) {
        limparTerminal();
        System.out.println("=== Perfil do Treinador ===");
        Aluno aluno = (Aluno) usuario.getPessoa();
        Usuario treinadorUsuario = usuarioController.buscarPorPessoaId(aluno.getTreinador().getId());
        if (aluno.getTreinador() != null) {
            Treinador treinador = aluno.getTreinador();
            System.out.println("Nome: " + treinador.getNome());
            System.out.println("Telefone: " + treinador.getTelefone());
            System.out.println("Email: " + treinadorUsuario.getEmail());
            System.out.println("Data de Nascimento: " + treinador.getDataNasc());
            System.out.println("CREF: " + treinador.getCref());
        } else {
            System.out.println("Você ainda não possui um treinador atribuído.");
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
        } while (escolha < 0 || escolha > 6);
        return escolha;
    }
}
