package ifpr.edu.br.model;

import java.time.LocalDate;
import java.util.Scanner;

import ifpr.edu.br.controllers.UsuarioController;
import ifpr.edu.br.controllers.AlunoController;
import ifpr.edu.br.controllers.PessoaController;
import ifpr.edu.br.controllers.TreinadorController;
import ifpr.edu.br.controllers.PlanoTreinoController;

public class TelaTreinador {

    private static final Scanner SC = new Scanner(System.in);
    static UsuarioController usuarioController = new UsuarioController();
    static PessoaController pessoaController = new PessoaController();
    static TreinadorController treinadorController = new TreinadorController();
    static AlunoController alunoController = new AlunoController();
    static PlanoTreinoController planoTreinoController = new PlanoTreinoController();

    public void exibirTela(Usuario usuario) {
        System.out.println("=== Menu do Treinador ===");
        System.out.println("Olá, " + usuario.getPessoa().getNome().split(" ")[0] + "! Bem-vindo ao menu do Treinador.");
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Ver Perfil");
        System.out.println("2 - Alterar Perfil");
        System.out.println("3 - Gerenciar Alunos");
        System.out.println("4 - Gerenciar Planos De Treino");
        System.out.println("0 - Sair");
        switch (lerEscolha()) {
            case 1:
                verPerfil(usuario);
                break;
            case 2:
                alterarPerfil(usuario);
                break;
            case 3:
                gerenciarAlunos(usuario);
                break;
            default:
                System.out.println("Escolha inválida.");
                return;
        }
    }

    private static void verPerfil(Usuario usuario) {
        System.out.println("=== Perfil do Treinador ===");
        Treinador treinador = (Treinador) usuario.getPessoa();
        System.out.println("Nome: " + usuario.getPessoa().getNome());
        System.out.println("Telefone: " + usuario.getPessoa().getTelefone());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Data de Nascimento: " + usuario.getPessoa().getDataNasc());
        System.out.println("CREF: " + treinador.getCref());
        System.out.println("Pressione qualquer tecla para voltar...");
        SC.nextLine();
    }

    private static void alterarPerfil(Usuario usuario) {
        System.out.println("=== Alterar Perfil ===");
        System.out.println("1 - Alterar Senha");
        System.out.println("2 - Alterar Email");
        System.out.println("3 - Alterar Telefone");
        System.out.println("4 - Alterar Nome");
        switch (lerEscolha()) {
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
            default:
                return;
        }
    }

    private static void alterarSenha(Usuario usuario) {
        try {
            System.out.println("Digite sua senha atual: ");
            String senhaAtual = SC.nextLine();
            if (!usuario.getSenha().equals(senhaAtual)) {
                System.out.println("Senha incorreta. Pressione qualquer tecla para voltar...");
                SC.nextLine();
                return;
            }
            System.out.println("Digite sua nova senha: ");
            String novaSenha = SC.nextLine();
            usuario.setSenha(novaSenha);
            usuarioController.atualizarSenha(usuario.getId(), novaSenha);
            System.out.println("Senha alterada com sucesso! Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao alterar senha: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private static void alterarEmail(Usuario usuario) {
        try {
            System.out.println("Digite seu novo email: ");
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

    private static void alterarTelefone(Usuario usuario) {
        try {
            System.out.println("Digite seu novo telefone: ");
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

    private static void alterarNome(Usuario usuario) {
        try {
            System.out.println("Digite seu novo nome: ");
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

    private static void gerenciarAlunos(Usuario usuario) {
        System.out.println("=== Gerenciar Alunos ===");
        System.out.println("1 - Ver lista de alunos");
        System.out.println("2 - Remover aluno");
        System.out.println("3 - Criar plano de treino para aluno");
        System.out.println();
        SC.nextLine();
    }

    private static void criarPlanoTreinoParaAluno(Usuario usuario) {
        System.out.println("=== Criar Plano de Treino para Aluno ===");
        treinadorController.listarAlunos(usuario.getPessoa().getId());
        System.out.println("Digite o ID do aluno para quem deseja criar o plano de treino: ");
        int alunoId = Integer.parseInt(SC.nextLine());
        Aluno aluno = alunoController.buscarPorId(alunoId);
        System.out.println("Nome do Plano: ");
        String nomePlano = SC.nextLine();
        System.out.println("Objetivo do Plano: ");
        String objetivoPlano = SC.nextLine();
        System.out.println("Descrição do Plano: ");
        String descricaoPlano = SC.nextLine();
        System.out.println("Duração do Plano (em semanas): ");
        int duracaoPlano = Integer.parseInt(SC.nextLine());
        System.out.println("Quantidade de Treinos Semanais: ");
        int qtdTreinosSemanais = Integer.parseInt(SC.nextLine());
        LocalDate dataInicio = LocalDate.now();
        planoTreinoController.criarPlanoTreino(aluno, (Treinador) usuario.getPessoa(), nomePlano, objetivoPlano, descricaoPlano, dataInicio, duracaoPlano, qtdTreinosSemanais, true);
        System.out.println("Agora vamos adicionar os treinos: ");
        

        
        
    }

    private static void limparTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.print("\033\143");
    }

    private static int lerEscolha() {
        int escolha;
        do {
            escolha = Integer.parseInt(SC.nextLine());
        } while (escolha < 0 || escolha > 6);
        return escolha;
    }
}
