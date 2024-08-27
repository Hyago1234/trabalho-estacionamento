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
import java.util.Calendar;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservarVagaFrame extends JFrame implements ActionListener{
    private JButton btnReservar;
    private JTextField textNome;
    public ReservarVagaFrame() {
        super("Gerar relatorio financeiro");

        setLayout(new GridLayout(2, 2));

        add(new JLabel("Nome Cliente:"));
        textNome=new JTextField();
        add(textNome);


        btnReservar = new JButton("Reservar Vaga");
        btnReservar.addActionListener(this);
        add(btnReservar);

        setSize(400, 300);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String nome;
        nome = textNome.getText();
        ReservarVaga(nome);
    }

    public void ReservarVaga(String nome){
        boolean achou=false;
        String veiculo="";
        try (BufferedReader br = new BufferedReader(new FileReader("Clientes.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null){
                String[] palavras = linha.split(" ");
                for (String palavra : palavras){
                    if(palavra.equals(nome)){
                        achou=true;
                    }
                    veiculo = palavra;
                }
                if(achou==true){
                    AtualizaVaga(veiculo);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void AtualizaVaga(String tipo){
        List<String> linhas = new ArrayList<>();
        String substituta;
        boolean continuar=true,encontrou=false;
        int flag=0;
        try (BufferedReader br = new BufferedReader(new FileReader("Vagas.txt"))) {
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
                    if(palavra.equals("vazia")&&encontrou==true){
                        //JOptionPane.showMessageDialog(this,"Chegou aqui");
                        substituta += "Reservada ";
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Vagas.txt"))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
