package model.contato;

public class ContatoVO {
    private Integer id;
    private String nome;
    private String email;

    public ContatoVO() {
    }

    public ContatoVO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContatoVO [id=" + id + ", nome=" + nome + ", email=" + email + "]";
    }
    
}
