package ifpr.edu.br.model;

public class TelaTreinador {
    public void exibirTela(Usuario usuario) {
        System.out.println("Olá, " + usuario.getPessoa().getNome().split("")[0] + "! Bem-vindo ao menu do Treinador.");
    }
}
