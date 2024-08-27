public class Cliente {
    private String nome;
    private long telefone;
    private String email;
    private String veiculo;

    public Cliente(String nome,long telefone,String email,String veiculo){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.veiculo = veiculo;
    }

    //getters
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getVeiculo(){
        return this.veiculo;
    }
    public long getTelefone(){
        return this.telefone;
    }
    //setters
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setVeiculo(String veiculo){
        this.veiculo = veiculo;
    }
    public void setTelefone(long telefone){
        this.telefone = telefone;
    }

}
