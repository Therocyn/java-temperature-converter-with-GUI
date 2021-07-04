import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.Caret;

import java.awt.*;
import java.awt.event.*;


public class Jprat10 extends JFrame implements ActionListener {

	JButton botao1, botao2;
	JLabel label1, label2, label3;
	JTextField ctexto1, ctexto2;
	JPanel painelPrincipal, painelTopo, painelEsquerda, painelMeio, painelDireita, painelBaixo;
	JRadioButton rbotao1, rbotao2, rbotao3, rbotao4, rbotao5, rbotao6;
	double entrada;

	public Jprat10() {
		super("Conversor de Temperatura");

		Container container = getContentPane();
		label1 = new JLabel("Entrada: ");
		ctexto1 = new JTextField(30);
		painelPrincipal = new JPanel(new BorderLayout());

		painelTopo = new JPanel();
		painelTopo.add(label1);
		painelTopo.add(ctexto1);
		painelPrincipal.add(painelTopo, BorderLayout.NORTH);

		ButtonGroup grupo1 = new ButtonGroup();
		painelEsquerda = new JPanel();
		painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.Y_AXIS));
		TitledBorder bordap3 = BorderFactory.createTitledBorder("Entrada");
		painelEsquerda.setBorder(bordap3);
		rbotao1 = new JRadioButton("Celsius");
		rbotao2 = new JRadioButton("Fahrenheit");
		rbotao3 = new JRadioButton("Kelvin");
		grupo1.add(rbotao1);
		grupo1.add(rbotao2);
		grupo1.add(rbotao3);
		rbotao1.setSelected(true);
		painelEsquerda.add(rbotao1);
		painelEsquerda.add(rbotao2);
		painelEsquerda.add(rbotao3);
		painelPrincipal.add(painelEsquerda, BorderLayout.WEST);

		painelMeio = new JPanel();
		try {
			File imagem = new File("imagem.jpeg");
			BufferedImage minhaImagem = ImageIO.read(imagem);
			ImageIcon icon = new ImageIcon(minhaImagem);
			label2 = new JLabel(icon);
			painelMeio.add(label2);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Imagem inacess�vel!");
			// Se falhar, exibir o restante da aplica��o, sempre que poss�vel!
		}
		painelPrincipal.add(painelMeio, BorderLayout.CENTER);

		ButtonGroup grupo2 = new ButtonGroup();
		painelDireita = new JPanel();
		painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.Y_AXIS));
		TitledBorder bordap5 = BorderFactory.createTitledBorder("Saida");
		painelDireita.setBorder(bordap5);
		rbotao4 = new JRadioButton("Celsius");
		rbotao5 = new JRadioButton("Fahrenheit");
		rbotao6 = new JRadioButton("Kelvin");
		grupo2.add(rbotao4);
		grupo2.add(rbotao5);
		grupo2.add(rbotao6);
		rbotao4.setSelected(true);
		painelDireita.add(rbotao4);
		painelDireita.add(rbotao5);
		painelDireita.add(rbotao6);
		painelPrincipal.add(painelDireita, BorderLayout.EAST);

		painelBaixo = new JPanel();
		botao1 = new JButton("Converter!");
		botao2 = new JButton("Resetar");
		label3 = new JLabel("Saida: ");
		ctexto2 = new JTextField(30);
		ctexto2.setBackground(null);
		
		botao1.setActionCommand("Converter");
		botao2.setActionCommand("Resetar");
		botao1.addActionListener(this);
		botao2.addActionListener(this);
		
		
		painelBaixo.add(botao1);
		painelBaixo.add(botao2);
		painelBaixo.add(label3);
		painelBaixo.add(ctexto2);
		painelPrincipal.add(painelBaixo, BorderLayout.SOUTH);

		container.add(painelPrincipal);
		pack();
		setVisible(true);
	}
	
	void trataConversao() {
		double saida=0;
		try {
		 entrada=Double.parseDouble(ctexto1.getText());
		 if(rbotao1.isSelected() && rbotao4.isSelected()) {
			 saida= entrada;
		 }
		 if(rbotao1.isSelected() && rbotao5.isSelected()) {
			 saida= ((entrada*9)/5)+32;
		 }
		 if(rbotao1.isSelected() && rbotao6.isSelected()) {
			 saida= entrada+273.15;
		 }
		 if(rbotao2.isSelected() && rbotao4.isSelected()) {
			 saida= ((entrada-32)/9)*5;
		 }
		 if(rbotao2.isSelected() && rbotao5.isSelected()) {
			 saida= entrada;
		 }
		 if(rbotao2.isSelected() && rbotao6.isSelected()) {
			 saida= (((entrada-32)/9)*5)+273.15;
		 }
		 if(rbotao3.isSelected() && rbotao4.isSelected()) {
			 saida= entrada-273.15;
		 }
		 if(rbotao3.isSelected() && rbotao5.isSelected()) {
			 saida= (((entrada-273.15)/5)*9)+32;
		 }
		 if(rbotao3.isSelected() && rbotao6.isSelected()) {
			 saida= entrada;
		 }
		 ctexto2.setText(String.format("%.2f", saida));
		 ctexto2.setForeground(Color.black);
		}catch(NumberFormatException n){
			if(ctexto1.getText().equals("")) {
				ctexto2.setText(null);
			}else {
				ctexto2.setText("Valor invalido!");
				ctexto2.setForeground(Color.red);
			}
		}
		}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Resetar")) {
			ctexto1.setText(null);
			ctexto2.setText(null);
			ctexto2.setForeground(Color.black);
		}
		if(e.getActionCommand().equals("Converter")) {
			trataConversao();
		}
	}

}
