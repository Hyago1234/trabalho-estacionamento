import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroVagasFrame extends JFrame implements ActionListener {
    private JTextField textNumero, textLocalizacao, textTipoVeiculo;
    private JComboBox<String> comboBoxStatus;
    private JButton btnSalvar;

    public CadastroVagasFrame() {
        super("Cadastro de Vagas");

        setLayout(new GridLayout(5, 2));

        // Componentes
        add(new JLabel("Número da Vaga:"));
        textNumero = new JTextField();
        add(textNumero);

        add(new JLabel("Localização:"));
        textLocalizacao = new JTextField();
        add(textLocalizacao);

        add(new JLabel("Tipo de Veículo:"));
        textTipoVeiculo = new JTextField();
        add(textTipoVeiculo);

        add(new JLabel("Status:"));
        comboBoxStatus = new JComboBox<>(new String[]{"livre", "ocupada", "reservada"});
        add(comboBoxStatus);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        add(btnSalvar);

        setSize(400, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String caminhoArquivo = "Vagas.txt"; // Especifique o caminho do arquivo aqui
        Vagas vaga = new Vagas(Integer.parseInt(this.textNumero.getText()),this.textLocalizacao.getText(),this.textTipoVeiculo.getText());
        String vaga_info = String.format("%s %s %s %s",String.valueOf(vaga.getNumero()),vaga.getLocalizacao(),vaga.getTipoVeiculo(),vaga.getStatus());
        // true no FileWriter indica que o arquivo será aberto no modo de adição
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(vaga_info);
            writer.newLine(); // Adiciona uma nova linha
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}