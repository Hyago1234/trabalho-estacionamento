public class Moto extends Veiculo{
    private String marca;
    private int cilindrada;
    public Moto(String marca,int cilindrada,String placa){
        super(placa);
        this.marca = marca;
        this.cilindrada = cilindrada;
    }
    //getters
    public int getCilindrada(){
        return this.cilindrada;
    }
    public String getMarca(){
        return this.marca;
    }
    //setters
    public void setCilindrada(int cilindrada){
        this.cilindrada = cilindrada;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
}
