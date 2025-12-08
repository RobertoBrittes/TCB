package ifpr.edu.br.model;

import java.time.LocalDate;
import java.util.Scanner;

import ifpr.edu.br.controllers.UsuarioController;
import ifpr.edu.br.controllers.AlunoController;
import ifpr.edu.br.controllers.PessoaController;
import ifpr.edu.br.controllers.TreinadorController;
import ifpr.edu.br.controllers.TreinoController;
import ifpr.edu.br.controllers.TreinoTemPlanoController;
import ifpr.edu.br.controllers.PlanoTreinoController;
import ifpr.edu.br.controllers.TreinoConcluidoController;
import java.util.ArrayList;

public class TelaTreinador {

    private static final Scanner SC = new Scanner(System.in);
    static UsuarioController usuarioController = new UsuarioController();
    static PessoaController pessoaController = new PessoaController();
    static TreinadorController treinadorController = new TreinadorController();
    static AlunoController alunoController = new AlunoController();
    static PlanoTreinoController planoTreinoController = new PlanoTreinoController();
    static TreinoConcluidoController treinoConcluidoController = new TreinoConcluidoController();

    public void exibirTela(Usuario usuario) throws InterruptedException {
        while (true) {
            limparTerminal();
            System.out.println("=== Menu do Treinador ===");
            System.out.println(
                    "Olá, " + usuario.getPessoa().getNome().split(" ")[0] + "! Bem-vindo ao menu do Treinador.");
            System.out.println("O que deseja fazer?");
            System.out.println("1 - Ver Perfil");
            System.out.println("2 - Alterar Perfil");
            System.out.println("3 - Gerenciar Alunos");
            System.out.println("4 - Gerenciar Planos De Treino");
            System.out.println("0 - Deslogar");
            switch (lerEscolha()) {
                case 1:
                    verPerfil(usuario);
                    break;
                case 2:
                    alterarPerfil(usuario);
                    break;
                case 3:
                    try {
                        gerenciarAlunos(usuario);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    gerenciarPlanosDeTreino(usuario);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha inválida.");
                    Thread.sleep(2000);
                    break;
            }
        }
    }

    private static void verPerfil(Usuario usuario) {
        limparTerminal();
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

    private static void alterarPerfil(Usuario usuario) throws InterruptedException {
        limparTerminal();
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
            limparTerminal();
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
            limparTerminal();
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
            limparTerminal();
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
            limparTerminal();
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

    private static void gerenciarAlunos(Usuario usuario) throws InterruptedException {
        while (true) {
            limparTerminal();
            System.out.println("=== Gerenciar Alunos ===");
            System.out.println("1 - Ver lista de alunos");
            System.out.println("2 - Remover aluno");
            System.out.println("3 - Ver Perfil de um Aluno");
            System.out.println("4 - Ver Progresso do Aluno");
            System.out.println("0 - Voltar");
            switch (lerEscolha()) {
                case 1:
                    listarAlunos(usuario);
                    break;
                case 2:
                    removerAluno(usuario);
                    break;
                case 3:
                    verPerfilDoAluno(usuario);
                    break;
                case 4:
                    verProgressoDoAluno(usuario);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha inválida.");
                    return;
            }
        }
    }

    private static void criarPlanoTreinoParaAluno(Usuario usuario) throws InterruptedException {
        try {
            limparTerminal();
            Plano_treino plano = new Plano_treino();

            System.out.println("=== Criar Plano de Treino para Aluno ===");
            treinadorController.listarAlunos(usuario.getPessoa().getId());
            System.out.println("Digite o ID do aluno para quem deseja criar o plano de treino: ");
            int alunoId = Integer.parseInt(SC.nextLine());
            Aluno aluno = alunoController.buscarPorId(alunoId);

            limparTerminal();

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

            Plano_treino planoAtivo = planoTreinoController.buscarPlanoTreinoAtivo(alunoId);
            if (planoAtivo != null) {
                planoTreinoController.desativarPlanoTreino(planoAtivo.getIdplano_treino());
            }

            plano = planoTreinoController.criarPlanoTreino(aluno, (Treinador) usuario.getPessoa(), nomePlano,
                    objetivoPlano, descricaoPlano, dataInicio, duracaoPlano, qtdTreinosSemanais, true);

            if (plano == null || plano.getIdplano_treino() == 0) {
                System.out.println("Erro ao criar o plano. Operação cancelada.");
                Thread.sleep(2000);
                return;
            }
            System.out.println("Agora vamos adicionar os treinos: ");
            String diaSemana;

            TreinoTemPlanoController treinoTemPlanoController = new TreinoTemPlanoController();
            for (int i = 0; i < qtdTreinosSemanais; i++) {
                limparTerminal();
                System.out.printf("Que dia da semana deseja adicionar o %d treino?\n", i + 1);
                System.out.println("1 - Domingo");
                System.out.println("2 - Segunda-feira");
                System.out.println("3 - Terça-feira");
                System.out.println("4 - Quarta-feira");
                System.out.println("5 - Quinta-feira");
                System.out.println("6 - Sexta-feira");
                System.out.println("7 - Sábado");
                System.out.print("Escolha uma opção: ");
                diaSemana = escolherDiaSemana();

                if (treinoTemPlanoController.verificarDiaDaSemana(diaSemana, plano.getIdplano_treino()) != null) {
                    System.out.println(
                            "Já existe um treino cadastrado para esse dia da semana. Por favor, escolha outro dia.");
                    Thread.sleep(2000);
                    i--;
                    continue;
                }

                System.out.println();
                System.out.println("Deseja adicionar:");
                System.out.println("1 - Treino existente");
                System.out.println("2 - Criar novo treino");
                switch (lerEscolha()) {
                    case 1:
                        limparTerminal();
                        TreinoController treinoController = new TreinoController();
                        treinoController.listarTreinosProntos();
                        treinoTemPlanoController.adicionarTreinoAoPlano(escolherTreinoExistente(plano, diaSemana));
                        System.out.println("Treino adicionado ao plano com sucesso!");
                        System.out.println("Pressione qualquer tecla para voltar...");
                        SC.nextLine();
                        break;
                    case 2:
                        limparTerminal();
                        Treino novoTreino = new Treino();
                        novoTreino = criarNovoTreino();
                        if (novoTreino == null) {
                            System.out.println("Erro ao criar o treino. Operação cancelada.");
                            System.out.println("Pressione qualquer tecla para voltar...");
                            SC.nextLine();
                            return;
                        }
                        TreinoTemPlano novoTreinoPlano = new TreinoTemPlano();
                        novoTreinoPlano.setDiaSemana(diaSemana);
                        novoTreinoPlano.setPlano_treino(plano);
                        novoTreinoPlano.setTreino(novoTreino);
                        new TreinoTemPlanoController().adicionarTreinoAoPlano(novoTreinoPlano);
                        System.out.println("Novo treino criado e adicionado ao plano com sucesso!");
                        System.out.println("Pressione qualquer tecla para voltar...");
                        SC.nextLine();
                        break;
                    default:
                        System.out.println("Escolha inválida. Treino não adicionado.");
                        System.out.println("Pressione qualquer tecla para voltar...");
                        SC.nextLine();
                        i--;
                        continue;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar plano de treino: " + e.getMessage());
            System.out.println("Operação cancelada.");
            Thread.sleep(2000);
        }
    }

    private static TreinoTemPlano escolherTreinoExistente(Plano_treino plano, String diaSemana) throws InterruptedException {
        TreinoController treinoController = new TreinoController();
        TreinoTemPlano treinoPlano = new TreinoTemPlano();
        treinoController.listarTreinosProntos();

        System.out.print("Digite o ID do treino: ");
        int id = Integer.parseInt(SC.nextLine());
        treinoPlano.setDiaSemana(diaSemana);
        treinoPlano.setPlano_treino(plano);
        treinoPlano.setTreino(treinoController.buscarTreinoPorId(id));
        return treinoPlano;
    }

    private static Treino criarNovoTreino() throws InterruptedException {
        Treino novo = new Treino();

        System.out.print("Nome do Treino: ");
        novo.setNome(SC.nextLine());

        System.out.print("Duração (minutos): ");
        novo.setDuracao(SC.nextLine());

        System.out.println("Tipo: ");
        System.out.println("1 - Longão");
        System.out.println("2 - Tiro");
        System.out.println("3 - Regenerativo");
        System.out.println("4 - Fartlek");
        System.out.println("5 - Progressivo");
        System.out.println("6 - Rodagem");
        switch (lerEscolha()) {
            case 1:
                novo.setTipo_treino("longão");
                break;
            case 2:
                novo.setTipo_treino("tiro");
                break;
            case 3:
                novo.setTipo_treino("regenerativo");
                break;
            case 4:
                novo.setTipo_treino("fartlek");
                break;
            case 5:
                novo.setTipo_treino("progressivo");
                break;
            case 6:
                novo.setTipo_treino("rodagem");
                break;
            default:
                System.out.println("Escolha inválida. ");
                return null;
        }

        System.out.print("Descrição: ");
        novo.setDescricao(SC.nextLine());

        novo.setTreinoPronto(false);

        int novoId = new TreinoController().salvarTreino(novo);
        novo.setIdTreino(novoId);

        return novo;
    }

    private static String escolherDiaSemana() throws InterruptedException  {
        switch (lerEscolha()) {
            case 1:
                return "domingo";
            case 2:
                return "segunda";
            case 3:
                return "terca";
            case 4:
                return "quarta";
            case 5:
                return "quinta";
            case 6:
                return "sexta";
            case 7:
                return "sabado";
            default:
                throw new RuntimeException("Escolha inválida.");
        }
    }

    private static void listarAlunos(Usuario usuario) {
        limparTerminal();
        System.out.println("=== Sua Lista de Alunos ===");
        new TreinadorController().listarAlunos(usuario.getPessoa().getId());
        System.out.println("Pressione qualquer tecla para voltar...");
        SC.nextLine();
    }

    private static void removerAluno(Usuario usuario) throws InterruptedException {
        try {
            limparTerminal();
            System.out.println("=== Remover Aluno ===");
            System.out.println("Que aluno você deseja remover?");
            new TreinadorController().listarAlunos(usuario.getPessoa().getId());

            System.out.println("Digite o ID do aluno que deseja remover:");
            int alunoId = Integer.parseInt(SC.nextLine());
            new TreinadorController().removerAluno(alunoId, usuario.getPessoa().getId());

            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao remover aluno: " + e.getMessage());
            Thread.sleep(2000);
        }
    }

    private static void verPerfilDoAluno(Usuario usuario) {
        try {
            limparTerminal();
            System.out.println("=== Ver Perfil do Aluno ===");
            System.out.println("Que aluno você deseja ver?");
            new TreinadorController().listarAlunos(usuario.getPessoa().getId());

            System.out.println("Digite o ID do aluno que deseja ver: ");
            int alunoId = Integer.parseInt(SC.nextLine());
            Aluno aluno = alunoController.buscarPorId(alunoId);
            Usuario usuarioAluno = usuarioController.buscarPorPessoaId(alunoId);

            if (aluno.getTreinador().getId() != usuario.getPessoa().getId()) {
                System.out.println("Voce não pode ver o perfil de um aluno que não é seu.");
                System.out.println("Pressione qualquer tecla para voltar...");
                SC.nextLine();
                return;
            }

            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Telefone: " + aluno.getTelefone());
            System.out.println("Data de Nascimento: " + aluno.getDataNasc());
            System.out.println("Email: " + usuarioAluno.getEmail());
            System.out.println("Treinador: Você");
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao ver perfil do aluno: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private static void gerenciarPlanosDeTreino(Usuario usuario) throws InterruptedException {
        while (true) {
            limparTerminal();
            System.out.println("=== Gerenciar Planos de Treino ===");
            System.out.println("O que deseja fazer?");
            System.out.println("1 - Criar Novo Plano de Treino para Aluno");
            System.out.println("2 - Visualizar Plano de um Aluno");
            System.out.println("3 - Editar Plano de um Aluno");
            System.out.println("4 - Desativar o Plano de um Aluno");
            System.out.println("0 - Voltar");
            switch (lerEscolha()) {
                case 1:
                    try {
                        criarPlanoTreinoParaAluno(usuario);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    visualizarPlanoDeAluno(usuario);
                    break;
                case 3:
                    editarPlanoAluno(usuario);
                    break;
                case 4:
                    desativarPlanoTreino(usuario);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha inválida.");
                    break;
            }
        }
    }

    private static void visualizarPlanoDeAluno(Usuario usuario) {
        limparTerminal();
        Plano_treino plano = new Plano_treino();
        System.out.println("=== Visualizar Plano de Treino do Aluno ===");
        System.out.println("Sua lista de alunos: ");
        new TreinadorController().listarAlunos(usuario.getPessoa().getId());

        System.out.println("Digite o ID do aluno que deseja ver o plano");
        int alunoId = Integer.parseInt(SC.nextLine());

        plano = new PlanoTreinoController().buscarPlanoTreinoAtivo(alunoId);
        if (plano == null) {
            System.out.println("Aluno não possui plano de treino.");
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
            return;
        }

        limparTerminal();
        System.out.println("=== Visualizar Plano de Treino do Aluno ===");
        System.out.println("Nome do Plano:" + plano.getNome());
        System.out.println("Objetivo: " + plano.getObjetivo());
        System.out.println("Descrição: " + plano.getDescricao());
        System.out.println("Data de Início: " + plano.getDataInicio());
        System.out.println("Data de Fim: " + (plano.getDataFim() == null ? "Em andamento" : plano.getDataFim()));
        System.out.println("Duração do Plano: " + plano.getDuracao_plano() + " semanas");
        System.out.println("Quantidade de Treinos Semanais: " + plano.getQtd_treino_semanal() + " treinos");
        System.out.println("Ativo:" + (plano.isAtivo() ? "Sim" : "Não"));
        System.out.println();
        System.out.println("=== Treinos do Plano ===");
        TreinoTemPlanoController treinoTemPlanoController = new TreinoTemPlanoController();
        ArrayList<TreinoTemPlano> treinosDoPlano = treinoTemPlanoController
                .listarTreinosDoPlano(plano.getIdplano_treino());
        for (TreinoTemPlano ttp : treinosDoPlano) {
            System.out.println("Dia da Semana: " + ttp.getDiaSemana());
            System.out.println("Nome do Treino: " + ttp.getTreino().getNome());
            System.out.println("Tipo do Treino: " + ttp.getTreino().getTipo_treino());
            System.out.println("Duração: " + ttp.getTreino().getDuracao() + " minutos");
            System.out.println("Descrição: " + ttp.getTreino().getDescricao());
            System.out.println("---------------------------");
        }
        System.out.println("Pressione qualquer tecla para voltar...");
        SC.nextLine();
    }

    private static void editarPlanoAluno(Usuario usuario) throws InterruptedException {
        limparTerminal();
        System.out.println("=== Editar Plano de um Aluno ===");
        System.out.println("Sua lista de alunos: ");
        new TreinadorController().listarAlunos(usuario.getPessoa().getId());

        System.out.println("Digite o ID do aluno que deseja editar o plano");
        int alunoId = Integer.parseInt(SC.nextLine());

        Plano_treino plano = new PlanoTreinoController().buscarPlanoTreinoAtivo(alunoId);
        if (plano == null) {
            System.out.println("Aluno não possui plano de treino.");
            return;
        }

        while (true) {
            limparTerminal();

            System.out.println("=== Editar Plano de um Aluno ===");
            System.out.println("O que deseja fazer?: ");
            System.out.println("1 - Editar Nome do Plano");
            System.out.println("2 - Editar Objetivo");
            System.out.println("3 - Editar Descrição");
            System.out.println("4 - Editar Treinos");

            switch (lerEscolha()) {
                case 1:
                    System.out.println("Insira o novo nome: ");
                    new PlanoTreinoController().atualizarNome(plano.getIdplano_treino(), SC.nextLine());
                    break;
                case 2:
                    System.out.println("Insira o novo objetivo: ");
                    new PlanoTreinoController().atualizarObjetivo(plano.getIdplano_treino(), SC.nextLine());
                    break;
                case 3:
                    System.out.println("Insira a nova descrição: ");
                    new PlanoTreinoController().atualizarDescricao(plano.getIdplano_treino(), SC.nextLine());
                    break;
                case 4:
                    editarTreinos(plano);
                    break;
                default:
                    System.out.println("Escolha inválida.");
                    Thread.sleep(2000);
                    break;
            }
        }

    }

    private static void editarTreinos(Plano_treino plano) {
            
            try {
                TreinoTemPlanoController treinoTemPlanoController = new TreinoTemPlanoController();
                ArrayList<TreinoTemPlano> treinosDoPlano = treinoTemPlanoController
                        .listarTreinosDoPlano(plano.getIdplano_treino());
                for (TreinoTemPlano ttp : treinosDoPlano) {
                    System.out.println("Id: " + ttp.getId());
                    System.out.println("Dia da Semana: " + ttp.getDiaSemana());
                    System.out.println("Nome do Treino: " + ttp.getTreino().getNome());
                    System.out.println("Tipo do Treino: " + ttp.getTreino().getTipo_treino());
                    System.out.println("Duração: " + ttp.getTreino().getDuracao() + " minutos");
                    System.out.println("Quantidade treino semanal: ");
                    System.out.println("Descrição: " + ttp.getTreino().getDescricao());
                    System.out.println("---------------------------");
                }
                System.out.println("Insira o id do treino que quer editar: ");
                int ttpId = Integer.parseInt(SC.nextLine());
    
                TreinoTemPlano ttp = new TreinoTemPlanoController().buscarPlanoTreinoPorId(ttpId);
                Treino treino = new TreinoController().buscarTreinoPorId(ttp.getTreino().getIdTreino());
                while (true) {
                    limparTerminal();
        
                    System.out.println("=== Editar Treino do Plano ===");
                    System.out.println("1 - Editar Dia da Semana");
                    System.out.println("2 - Editar Nome do Treino");
                    System.out.println("3 - Editar Tipo do treino");
                    System.out.println("4 - Editar Descrição");
                    System.out.println("5 - Editar Duração");
        
                    switch (lerEscolha()) {
                        case 1:
                            editarDiaSemana(ttpId);
                            break;
                        case 2:
                            editarNomeDoTreino(ttpId);
                            break;
                        case 3:
                            editarTipoDoTreino(ttpId);
                            break;
                        case 4:
                            editarDescricaoDoTreino(ttpId);
                            break;
                        case 5:
                            editarDuracaoDoTreino(ttpId);
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Escolha inválida.");
                            System.out.println("Pressione qualquer tecla para voltar...");
                            SC.nextLine();
                            break;
                    }
        
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Pressione qualquer tecla para voltar...");
                SC.nextLine();
            }
        
    }

    private static void desativarPlanoTreino(Usuario usuario) throws InterruptedException {
        try {
            System.out.println("=== Desativar Plano de Treino ===");
            new TreinadorController().listarAlunos(usuario.getPessoa().getId());

            System.out.println("Insira o id do aluno que deseja desativar o plano?");
            int alunoId = Integer.parseInt(SC.nextLine());

            Aluno aluno = new AlunoController().buscarPorId(alunoId);

            if (aluno.getTreinador().getId() != usuario.getPessoa().getId()) {
                throw new RuntimeException("Este aluno não é seu.");
            }

            Plano_treino plano = new PlanoTreinoController().buscarPlanoTreinoAtivo(alunoId);

            if (plano == null) {
                throw new RuntimeException("Plano ativo inexistente.");
            }

            new PlanoTreinoController().desativarPlanoTreino(plano.getIdplano_treino());

            System.out.println("Plano desativado com sucesso!");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Erro ao desativar plano treino: " + e.getMessage());
            Thread.sleep(2000);
        }

    }

    private static void editarDiaSemana(int ttpId) {
        try {
            limparTerminal();
            System.out.println("Escolha para que dia da semana deseja alterar:");
            String diaSemana = escolherDiaSemana();

            if (new TreinoTemPlanoController().verificarDiaDaSemana(diaSemana, ttpId) != null) {
                throw new RuntimeException(
                        "Já existe um treino cadastrado para esse dia da semana. Por favor, escolha outro dia.");
            }

            new TreinoTemPlanoController().atualizarDiaSemana(ttpId, diaSemana);
            System.out.println("Dia da semana atualizado com sucesso!");

            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private static void editarNomeDoTreino(int treinoId) {

        try {
            limparTerminal();
            System.out.println("Digite o novo nome do treino:");
            new TreinoController().atualizarNomeDoTreino(treinoId, SC.nextLine());
            System.out.println("Nome do treino atualizado com sucesso!");
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }
    }

    private static void editarDuracaoDoTreino(int treinoId) {
        try {
            limparTerminal();
            System.out.println("Digite a nova duracao:");
            new TreinoController().atualizarDuracao(treinoId, SC.nextLine());
            System.out.println("Duracao do treino atualizado com sucesso!");
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }

    }

    private static void editarTipoDoTreino(int treinoId) {
        try {

            String tipo;
            limparTerminal();
            System.out.println("Tipo: ");
            System.out.println("1 - Longão");
            System.out.println("2 - Tiro");
            System.out.println("3 - Regenerativo");
            System.out.println("4 - Fartlek");
            System.out.println("5 - Progressivo");
            System.out.println("6 - Rodagem");
            switch (lerEscolha()) {
                case 1:
                    tipo = "longao";
                    break;
                case 2:
                    tipo = "tiro";
                    break;
                case 3:
                    tipo = "regenerativo";
                    break;
                case 4:
                    tipo = "fartlek";
                    break;
                case 5:
                    tipo = "progressivo";
                    break;
                case 6:
                    tipo = "rodagem";
                    break;
                default:
                    System.out.println("Escolha inválida. ");
                    System.out.println("Pressione qualquer tecla para voltar...");
                    SC.nextLine();
                    return;
            }

            new TreinoController().atualizarTipo(treinoId, tipo);
            System.out.println("Tipo do treino atualizado com sucesso!");
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }

    }

    private static void editarDescricaoDoTreino(int treinoId) {
        try {
            limparTerminal();
            System.out.println("Digite a nova descricao:");
            new TreinoController().atualizarDescricao(treinoId, SC.nextLine());
            System.out.println("Descricao do treino atualizado com sucesso!");
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        }

    }

    private static void verProgressoDoAluno(Usuario usuario) {
        try {

            limparTerminal();

            treinadorController.listarAlunos(usuario.getPessoa().getId());

            System.out.print("\nDigite o ID do aluno que deseja ver o progresso: ");
            int alunoId = Integer.parseInt(SC.nextLine());

            Aluno aluno = alunoController.buscarPorId(alunoId);
            if (aluno == null) {
                System.out.println("Aluno não encontrado.");
                System.out.println("Pressione qualquer tecla para voltar...");
                SC.nextLine();
                return;
            }

            Plano_treino plano = planoTreinoController.buscarPlanoTreinoAtivo(alunoId);
            if (plano == null) {
                System.out.println("\nEste aluno não possui um plano de treino ativo.");
                System.out.println("Pressione qualquer tecla para voltar...");
                SC.nextLine();
                return;
            }

            int diasTotais = treinoConcluidoController.getTreinosTotais(plano);
            int concluidos = treinoConcluidoController.getTreinosConcluidos(alunoId, plano.getIdplano_treino());
            int restantes = treinoConcluidoController.getTreinosRestantes(alunoId, plano);
            int semanasRest = treinoConcluidoController.getSemanasRestantes(alunoId, plano);

            limparTerminal();
            System.out.println("===== PROGRESSO DO ALUNO =====\n");
            System.out.println("Aluno: " + aluno.getNome());
            System.out.println("Plano: " + plano.getNome());
            System.out.println("----------------------------------");
            System.out.println("Total de treinos:    " + diasTotais);
            System.out.println("Treinos concluídos:  " + concluidos);
            System.out.println("Treinos restantes:   " + restantes);
            System.out.println("Semanas restantes:   " + semanasRest);
            System.out.println("----------------------------------");

            System.out.println("Pressione qualquer tecla para voltar...");
            SC.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao ver progresso do aluno: " + e.getMessage());
        }

    }

    private static void limparTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.print("\033\143");
    }

    public static int lerEscolha() throws InterruptedException {
        while (true) {
            try {
                int escolha = 0;
                do {
                    escolha = Integer.parseInt(SC.nextLine());
                } while (escolha > 7 || escolha < 0);
                return escolha;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                Thread.sleep(2000);
            }

        }
    }
}
