import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroClientesFrame extends JFrame implements ActionListener {
    private JTextField textNome, textTelefone, textEmail,textVeiculo;
    private JButton btnSalvar;

    public CadastroClientesFrame() {
        super("Cadastro de Clientes");

        setLayout(new GridLayout(5, 2));

        // Componentes
        add(new JLabel("Nome:"));
        textNome = new JTextField();
        add(textNome);

        add(new JLabel("Telefone:"));
        textTelefone = new JTextField();
        add(textTelefone);

        add(new JLabel("Email:"));
        textEmail = new JTextField();
        add(textEmail);

        add(new JLabel("Veiculo"));
        textVeiculo = new JTextField();
        add(textVeiculo);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        add(btnSalvar);

        setSize(400, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String caminhoArquivo = "Cliente.txt"; // Especifique o caminho do arquivo aqui
        Cliente cliente = new Cliente(this.textNome.getText(),Long.parseLong(this.textTelefone.getText()),this.textEmail.getText(),this.textVeiculo.getText());
        String cliente_info = String.format("%s %s %s %s",cliente.getNome(),String.valueOf(cliente.getTelefone()),cliente.getEmail(),cliente.getVeiculo());
        // true no FileWriter indica que o arquivo será aberto no modo de adição
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(cliente_info);
            writer.newLine(); // Adiciona uma nova linha
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}