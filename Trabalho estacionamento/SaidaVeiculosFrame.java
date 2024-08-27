import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaidaVeiculosFrame extends JFrame implements ActionListener {
    private JTextField textPlaca;
    private JComboBox<String> comboBoxMetodoPagamento;
    private JButton btnSalvar;

    public SaidaVeiculosFrame() {
        super("Saída de Veículos");

        setLayout(new GridLayout(3, 2));

        // Componentes
        add(new JLabel("Placa:"));
        textPlaca = new JTextField();
        add(textPlaca);

        add(new JLabel("Metodo de pagamento"));
        comboBoxMetodoPagamento = new JComboBox<>(new String[]{"Pix", "Dinheiro", "Cartao"});
        add(comboBoxMetodoPagamento);

        btnSalvar = new JButton("Registrar Saída");
        btnSalvar.addActionListener(this);
        add(btnSalvar);

        setSize(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String recibo;
        boolean continuar=true,encontrou=false;
        String numerovaga;
        LocalDateTime tempoHoraAtual = LocalDateTime.now();
        LocalTime tempoAtual = tempoHoraAtual.toLocalTime();
        int tempodesaida = tempoAtual.toSecondOfDay();
        float valorpago=0;
        String tempo;
        try (BufferedReader br = new BufferedReader(new FileReader("VeiculosEstacionados.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null&&continuar==true) {
                // Dividir a linha em palavras
                String[] palavras = linha.split(" ");
                //ultimaPalavra="";
                tempo=palavras[palavras.length-2];
                numerovaga=palavras[palavras.length-1];
                // Iterar pelas palavras e verificar se alguma corresponde à palavra alvo
                for (String palavra : palavras){
                    if(palavra.equals(textPlaca.getText())){
                        encontrou=true;
                    }
                    //ultimaPalavra = palavra;
                }
                if(encontrou==true){
                    Calendar calendario = Calendar.getInstance();

                    // Obter o dia, mês e ano da data atual
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);
                    int mes = calendario.get(Calendar.MONTH) + 1; // Os meses são indexados de 0 a 11, então adicione 1
                    int ano = calendario.get(Calendar.YEAR);
                    valorpago = 2*(tempodesaida-(Integer.parseInt(tempo)));
                    RemoveLinha("VeiculosEstacionados.txt",linha);
                    //JOptionPane.showMessageDialog(this,tipoveiculo.substring(0, tipoveiculo.length() - 1));
                    //AtualizaStatusVaga("Vagas.txt",tipoveiculo.substring(0, tipoveiculo.length() - 1));
                    AtualizaStatusVaga("Vagas.txt",numerovaga);
                    recibo = String.format("%s %s %s/%s/%s",comboBoxMetodoPagamento.getSelectedItem(), String.valueOf(valorpago),String.valueOf(dia),String.valueOf(mes),String.valueOf(ano));
                    RegistraPreço("Pagamentos.txt",recibo);
                    continuar=false;
                }
                

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void RegistraPreço(String caminhoArquivo,String mensagem){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(mensagem);
            bw.newLine(); // Adiciona uma nova linha após o texto
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void RemoveLinha(String caminhoArquivo,String linhaAlvo){
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Remover a linha alvo da lista
        linhas.remove(linhaAlvo);

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

    public void AtualizaStatusVaga(String caminhoArquivo,String tipo){
        List<String> linhas = new ArrayList<>();
        String substituta;
        boolean continuar=true,encontrou=false;
        int flag=0;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null&&continuar==true) {
                encontrou=false;
                substituta = "";
                // Dividir a linha em palavras
                String[] palavras = linha.split(" ");
                //auxiliares
                // Iterar pelas palavras e verificar se alguma corresponde à palavra alvo
                for (String palavra : palavras){
                    //JOptionPane.showMessageDialog(this,palavra);
                    if(palavra.equals(tipo)&&flag==0){
                        //JOptionPane.showMessageDialog(this,"Encontrou");
                        encontrou=true;
                        flag=1;
                    }
                    if(palavra.equals("Ocupada")&&encontrou==true){
                        //JOptionPane.showMessageDialog(this,"Chegou aqui");
                        substituta += "vazia ";
                    }
                    else{
                        substituta += palavra +" ";
                    }
                }
                JOptionPane.showMessageDialog(this,substituta);
                if(encontrou=true){
                    linhas.add(substituta);
                }
                else{
                    linhas.add(linha);
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
