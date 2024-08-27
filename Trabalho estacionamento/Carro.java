public class Carro extends Veiculo {
    private String marca;
    private String modelo;
    private String cor;
    public Carro(String marca,String modelo,String cor,String placa){
        super(placa);
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
    }
    //getters
    public String getMarca(){
        return this.marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public String getCor(){
        return this.cor;
    }
    //setters
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
}
