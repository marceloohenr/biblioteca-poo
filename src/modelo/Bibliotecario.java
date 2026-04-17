package modelo;

public class Bibliotecario extends Pessoa {
    private String cargo;

    public Bibliotecario(int id, String nome, String email, String cargo) {
        super(id, nome, email);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String exibirDados() {
        return "ID: " + getId()
                + " | Nome: " + getNome()
                + " | Email: " + getEmail()
                + " | Cargo: " + cargo;
    }
}

