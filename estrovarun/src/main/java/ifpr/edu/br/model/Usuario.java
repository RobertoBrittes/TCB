package ifpr.edu.br.model;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String tipo_usuario;
    private Pessoa pessoa;

    public Usuario() {
    }

    public Usuario(String email, String senha, String tipo_usuario, Pessoa pessoa) {
        this.email = email;
        this.senha = senha;
        this.tipo_usuario = tipo_usuario;
        this.pessoa = pessoa;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


    
}