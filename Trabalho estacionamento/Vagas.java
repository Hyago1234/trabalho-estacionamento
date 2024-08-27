public class Vagas {
    private int numero;
    private String localizacao;
    private String status;
    private String tipo_veiculo;
    public Vagas(int numero,String localizacao,String tipo_veiculo){
        this.numero = numero;
        this.localizacao = localizacao;
        this.status = "vazia";
        this.tipo_veiculo = tipo_veiculo;
    }

    //getters
    public String getLocalizacao(){
        return this.localizacao;
    }
    public String getStatus(){
        return this.status;
    }
    public String getTipoVeiculo(){
        return this.tipo_veiculo;
    }
    public int getNumero(){
        return this.numero;
    }
    //setters
    public void setLocalizacao(String localizacao){
        this.localizacao = localizacao;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public void setTipoVeiculo(String tipo_veiculo){
        this.tipo_veiculo=tipo_veiculo;
    }
    public void setNumero(int numero){
        this.numero=numero;
    }
}
