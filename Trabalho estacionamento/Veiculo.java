public abstract class Veiculo {
    private String placa;
    public Veiculo(String placa){
        this.placa = placa;
    }
    public String getPlaca(){
        return this.placa;
    }
    public void setPlaca(String placa){
        this.placa=placa;
    }
}
