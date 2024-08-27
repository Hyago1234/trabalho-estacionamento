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

public class GerarRelatorioFrame extends JFrame implements ActionListener {
    private JButton btnRelatorioOcupacao,btnRelatorioGeral,btnRelatorioFinanceiro;

    public GerarRelatorioFrame() {
        super("Gerar relatorio financeiro");

        setLayout(new GridLayout(3, 1));

        btnRelatorioOcupacao = new JButton("Relatorio de Ocupacao");
        btnRelatorioOcupacao.addActionListener(this);
        add(btnRelatorioOcupacao);

        btnRelatorioGeral = new JButton("Relatorio de entradas e saidas");
        btnRelatorioGeral.addActionListener(this);
        add(btnRelatorioGeral);

        btnRelatorioFinanceiro = new JButton("Relatorio financeiro");
        btnRelatorioFinanceiro.addActionListener(this);
        add(btnRelatorioFinanceiro);

        setSize(400, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnRelatorioOcupacao) {
            GerarRelatorioOcupacao();
        }else if (e.getSource() == btnRelatorioGeral) {
            GerarRelatorioGeral();
        }else if (e.getSource() == btnRelatorioFinanceiro) {
            GerarRelatorioPagamentosDiarios();
        }
    }
    public void GerarRelatorioGeral(){
        int quantidadeentradas=0,quantidadesaidas=0;
        String linha,relatorio;
        try (BufferedReader br = new BufferedReader(new FileReader("VeiculosEstacionados.txt"))) {
            while ((linha = br.readLine()) != null){
                quantidadeentradas+=1;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("Pagamentos.txt"))) {
            while ((linha = br.readLine()) != null){
                quantidadesaidas+=1;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        relatorio=String.format("%s veiculos passaram pelo estacionamento, %s estao presentes e %s já sairam",String.valueOf(quantidadeentradas+quantidadesaidas),String.valueOf(quantidadeentradas),String.valueOf(quantidadesaidas));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("RelatorioGeral.txt"))) {
            bw.write(relatorio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void GerarRelatorioOcupacao(){
        int quantidadeVeiculos=0,quantidadeCaminhao=0,quantidadeCarro=0,quantidadeMoto=0;
        String relatorio;
        try (BufferedReader br = new BufferedReader(new FileReader("VeiculosEstacionados.txt"))) {
            quantidadeVeiculos+=1;
            String linha;
            while ((linha = br.readLine()) != null){
                String[] palavras = linha.split(" ");
                for (String palavra : palavras){
                    if(palavra.equals("Carro:")){
                        quantidadeCarro +=1;
                    }else if(palavra.equals("Caminhao:")){
                        quantidadeCaminhao+=1;
                    }else if(palavra.equals("Moto:")){
                        quantidadeMoto+=1;
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        relatorio = String.format("Existem %s veiculos no estacionamento sendo: %s Carros , %s Caminhoes e %s Motos",String.valueOf(quantidadeVeiculos),String.valueOf(quantidadeCarro),String.valueOf(quantidadeCaminhao),String.valueOf(quantidadeMoto));


        try (BufferedWriter bw = new BufferedWriter(new FileWriter("RelatorioOcupacao.txt"))) {
            bw.write(relatorio);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void  GerarRelatorioPagamentosDiarios(){
        List<String> linhas = new ArrayList<>();
        String diaAtual,diaRegistro;
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1; // Os meses são indexados de 0 a 11, então adicione 1
        int ano = calendario.get(Calendar.YEAR);
        diaAtual = String.format("%s/%s/%s",String.valueOf(dia),String.valueOf(mes),String.valueOf(ano));
        try (BufferedReader br = new BufferedReader(new FileReader("Pagamentos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null){
                String[] palavras = linha.split(" ");
                diaRegistro=palavras[2];
                if(diaRegistro.equals(diaAtual)){
                    linhas.add(linha);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("RelatorioPagamentoDiario.txt"))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
