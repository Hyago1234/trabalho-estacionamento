import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class EntradaVeiculosFrame extends JFrame implements ActionListener {
    private JTextField textPlaca, textTipo, textMarcaCarro,textMarcaMoto, textModelo, textCor, textCargaMaxima, textComprimento, textCilindradas;
    private JComboBox<String> comboBoxTipoVeiculo;
    private JButton btnSalvar;

    public EntradaVeiculosFrame() {
        super("Entrada de Veículos");

        setLayout(new GridLayout(11, 2));

        // Componentes
        add(new JLabel("Placa:"));
        textPlaca = new JTextField();
        add(textPlaca);

        add(new JLabel("Tipo (carro, caminhao, moto):"));
        textTipo = new JTextField();
        add(textTipo);

        // Campos específicos para Carro
        add(new JLabel("Marca (Carro):"));
        textMarcaCarro = new JTextField();
        add(textMarcaCarro);

        add(new JLabel("Modelo (Carro):"));
        textModelo = new JTextField();
        add(textModelo);

        add(new JLabel("Cor (Carro):"));
        textCor = new JTextField();
        add(textCor);

        // Campos específicos para Caminhão
        add(new JLabel("Carga Máxima (Caminhão):"));
        textCargaMaxima = new JTextField();
        add(textCargaMaxima);

        add(new JLabel("Comprimento (Caminhão):"));
        textComprimento = new JTextField();
        add(textComprimento);

        // Campos específicos para Moto
        add(new JLabel("Marca (Moto):"));
        textMarcaMoto = new JTextField();
        add(textMarcaMoto);

        add(new JLabel("Cilindradas (Moto):"));
        textCilindradas = new JTextField();
        add(textCilindradas);

        add(new JLabel("Tipo de Veiculo"));
        comboBoxTipoVeiculo = new JComboBox<>(new String[]{"Carro", "Caminhao", "Moto"});
        add(comboBoxTipoVeiculo);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        add(btnSalvar);

        setSize(400, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(comboBoxTipoVeiculo.getSelectedItem()=="Carro"){
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            LocalTime horaAtual = dataHoraAtual.toLocalTime();
            int segundosDesdeMeiaNoite = horaAtual.toSecondOfDay();
            String arquivoPesquisa = "Vagas.txt";
            String caminhoArquivo = "VeiculosEstacionados.txt"; // Especifique o caminho do arquivo aqui
            Carro carro = new Carro(this.textMarcaCarro.getText(),this.textModelo.getText(),this.textCor.getText(),this.textPlaca.getText());
            String carro_info = String.format("Carro: %s %s %s %s %s",carro.getMarca(),carro.getModelo(),carro.getCor(),carro.getPlaca(),String.valueOf(segundosDesdeMeiaNoite));
            JOptionPane.showMessageDialog(this,carro_info);
            VagaDisponivel("Carro",arquivoPesquisa,caminhoArquivo,carro_info);
            
        }else if(comboBoxTipoVeiculo.getSelectedItem()=="Caminhao"){
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            LocalTime horaAtual = dataHoraAtual.toLocalTime();
            int segundosDesdeMeiaNoite = horaAtual.toSecondOfDay();
            String arquivoPesquisa = "Vagas.txt";
            String caminhoArquivo = "VeiculosEstacionados.txt"; // Especifique o caminho do arquivo aqui
            Caminhao caminhao = new Caminhao(Integer.parseInt(this.textCargaMaxima.getText()),Integer.parseInt(this.textComprimento.getText()),textPlaca.getText());
            String caminhao_info = String.format("Caminhao: %s %s %s %s",String.valueOf(caminhao.getCargaMax()),String.valueOf(caminhao.getComprimento()),caminhao.getPlaca(),String.valueOf(segundosDesdeMeiaNoite));
            VagaDisponivel("Caminhao",arquivoPesquisa,caminhoArquivo,caminhao_info);
            
        }else if(comboBoxTipoVeiculo.getSelectedItem()=="Moto"){
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            LocalTime horaAtual = dataHoraAtual.toLocalTime();
            int segundosDesdeMeiaNoite = horaAtual.toSecondOfDay();
            String arquivoPesquisa = "Vagas.txt";
            String caminhoArquivo = "VeiculosEstacionados.txt"; // Especifique o caminho do arquivo aqui
            Moto moto = new Moto(this.textMarcaMoto.getText(),Integer.parseInt(this.textCilindradas.getText()),textPlaca.getText());
            String moto_info = String.format("Moto: %s %s %s %s",moto.getMarca(),String.valueOf(moto.getCilindrada()),moto.getPlaca(),String.valueOf(segundosDesdeMeiaNoite));
            VagaDisponivel("Moto",arquivoPesquisa,caminhoArquivo,moto_info);
            
        }
    }
    public void VagaDisponivel(String alvo,String arquivoPesquisa,String caminhoArquivo,String saida){
        String substituta;
        boolean continuar=true;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoPesquisa))) {
            //JOptionPane.showMessageDialog(this,saida);
            String linha;
            boolean flag01=false,flag02=false;
            while ((linha = br.readLine()) != null&&continuar==true) {
                substituta = "";
                // Dividir a linha em palavras
                String[] palavras = linha.split(" ");
                //auxiliares
                flag01=false;
                flag02=false;

                // Iterar pelas palavras e verificar se alguma corresponde à palavra alvo
                for (String palavra : palavras){
                    //palavra = palavra.trim();
                    if(palavra.equals("vazia")){
                        substituta += "Ocupada " ;
                    }else{
                        substituta += palavra + " " ;
                    }
                    if (palavra.equals(alvo)){
                        flag01 = true;
                    }
                    if(palavra.equals("vazia")){
                        flag02 = true;
                    }
                }
                if(flag01==true&&flag02==true){
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
                        saida += String.format(" %s",palavras[0]);
                        writer.write(saida);
                        writer.newLine(); // Adiciona uma nova linha
                        AtualizaStatusVaga(arquivoPesquisa,linha,substituta);
                        continuar = false;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void AtualizaStatusVaga(String caminhoArquivo,String alvo,String novaLinha){
        List<String> linhas = new ArrayList<>();

        // Ler o arquivo e armazenar todas as linhas na lista
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Modificar a linha alvo na lista
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).equals(alvo)) {
                linhas.set(i, novaLinha);
                break;
            }
        }

        // Reescrever o arquivo com as linhas modificadas
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
