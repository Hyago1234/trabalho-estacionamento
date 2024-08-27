public class Caminhao extends Veiculo {
    private int cargaMax;
    private int comprimento;
    public Caminhao(int cargaMax,int comprimento,String placa){
        super(placa);
        this.cargaMax = cargaMax;
        this.comprimento = comprimento;
    }
    //getters
    public int getCargaMax(){
        return this.cargaMax;
    }
    public int getComprimento(){
        return this.comprimento;
    }
    //setters
    public void setCargaMax(int cargaMax){
        this.cargaMax = cargaMax;
    }
    public void setComprimento(int comprimento){
        this.comprimento = comprimento;
    }
}
