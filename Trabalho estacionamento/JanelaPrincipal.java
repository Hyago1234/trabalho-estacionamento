import javax.swing.*;

import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JanelaPrincipal extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menuCadastro, menuRegistro,menuRelatorio,menuReservarVaga;
    private JMenuItem menuItemCadastroVagas, menuItemCadastroClientes, menuItemEntradaVeiculos, menuItemSaidaVeiculos,menuItemcriarrelatorio,menuItemReservarVaga;

    public JanelaPrincipal() {
        super("Sistema de Gerenciamento de Estacionamento");
        this.setLayout(new BorderLayout());


        // Menu
        menuBar = new JMenuBar();
        menuCadastro = new JMenu("Cadastro");
        menuRegistro = new JMenu("Registro");
        menuRelatorio= new JMenu("Relatorio");
        menuReservarVaga = new JMenu("Reservar");

        menuItemCadastroVagas = new JMenuItem("Cadastro de Vagas");
        menuItemCadastroClientes = new JMenuItem("Cadastro de Clientes");
        menuItemEntradaVeiculos = new JMenuItem("Entrada de Veículos");
        menuItemSaidaVeiculos = new JMenuItem("Saída de Veículos");
        menuItemcriarrelatorio = new JMenuItem("Gerar relatorios");
        menuItemReservarVaga = new JMenuItem("Reservar Vaga");

        menuCadastro.add(menuItemCadastroVagas);
        menuCadastro.add(menuItemCadastroClientes);
        menuRegistro.add(menuItemEntradaVeiculos);
        menuRegistro.add(menuItemSaidaVeiculos);
        menuRelatorio.add(menuItemcriarrelatorio);
        menuReservarVaga.add(menuItemReservarVaga);

        menuBar.add(menuCadastro);
        menuBar.add(menuRegistro);
        menuBar.add(menuRelatorio);
        menuBar.add(menuReservarVaga);

        setJMenuBar(menuBar);

        // Action Listeners
        menuItemCadastroVagas.addActionListener(this);
        menuItemCadastroClientes.addActionListener(this);
        menuItemEntradaVeiculos.addActionListener(this);
        menuItemSaidaVeiculos.addActionListener(this);
        menuItemcriarrelatorio.addActionListener(this);
        menuItemReservarVaga.addActionListener(this);

        // Configurações da janela
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemCadastroVagas) {
            new CadastroVagasFrame();
        } else if (e.getSource() == menuItemCadastroClientes) {
            new CadastroClientesFrame();
        } else if (e.getSource() == menuItemEntradaVeiculos) {
            new EntradaVeiculosFrame();
        } else if (e.getSource() == menuItemSaidaVeiculos) {
            new SaidaVeiculosFrame();
        } else if (e.getSource() == menuItemcriarrelatorio){
            new GerarRelatorioFrame();
        } else if(e.getSource() == menuItemReservarVaga){
            new ReservarVagaFrame();
        }
    }

}