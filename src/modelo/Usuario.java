package modelo;

public class Usuario extends Pessoa {
    private String matricula;

    public Usuario(int id, String nome, String email, String matricula) {
        super(id, nome, email);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String exibirDados() {
        return "ID: " + getId()
                + " | Nome: " + getNome()
                + " | Email: " + getEmail()
                + " | Matricula: " + matricula;
    }
}

