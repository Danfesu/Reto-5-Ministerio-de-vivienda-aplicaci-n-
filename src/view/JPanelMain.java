package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Controller;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class JPanelMain extends JPanel{
	private GridBagConstraints gbc;
	private JLabel jlabelTitle;
	private JButton jButtonImageLider;
	private JButton jButtonImageCasa;
	private JButton jButtonImageHomecenter;
	private JLabel jLabelReportLider;
	private JLabel jLabelReportCasa;
	private JLabel jLabelReportHomecenter;
	private JLabel jLabelBottom;
	private JLabel jLabelLogo;
	private JPanel jPanelTop;
	private JPanel jPanelMid;
	private JPanel jPanelTable;
	private JButton jButtonBack;
	
	public JPanelMain() {
		super(new BorderLayout());
		this.jlabelTitle = new JLabel("<html><center>Bienvenido al sistema de consultas<center><html>");
		this.gbc = new GridBagConstraints();
		this.jButtonImageLider = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/lider.png")).getImage().getScaledInstance(400,300,Image.SCALE_SMOOTH)));
		this.jButtonImageCasa = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/pc.png")).getImage().getScaledInstance(400,300,Image.SCALE_SMOOTH)));
		this.jButtonImageHomecenter = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/homecenter.png")).getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH)));
		this.jLabelReportLider = new JLabel("<html><center>Reporte de lideres<center><html>", JLabel.CENTER);
		this.jLabelReportCasa = new JLabel("<html><center>Reporte casa campestre (Cartagena, Santa Marta, Barranquilla)<center><html>", JLabel.CENTER);
		this.jLabelReportHomecenter = new JLabel("<html><center>Reporte compras Homecenter para Salento<center><html>", JLabel.CENTER);
		this.jLabelBottom = new JLabel("Cada día luchando por mejorar la calidad de vida de los colombianos", JLabel.CENTER);
		this.jLabelLogo = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/res/logo.png")).getImage().getScaledInstance(400,100,Image.SCALE_SMOOTH)));
		this.jPanelTop = new JPanel(new GridLayout(1,2));
		this.jPanelMid = new JPanel(new GridBagLayout());
		this.jButtonBack = new JButton("Atras");
		jButtonBack.addActionListener(Controller.getInstance());
		jButtonBack.setActionCommand("ComandoAtras");
		init();
	}
	
	@Override
	public Insets getInsets() {
		return new Insets(30,0,20,0);
	}

	private void init() {
		this.setOpaque(false);
		this.jPanelTop.setOpaque(false);
		this.jPanelMid.setOpaque(false);
		
		initProperties();
	}

	private void initProperties() {
		configJLabel(jlabelTitle, true, 50);
		configJLabel(jLabelReportCasa, false, 24);
		configJLabel(jLabelReportLider, false, 24);
		configJLabel(jLabelReportHomecenter, false, 24);
		configJLabel(jLabelBottom, true, 30);
		
		configButton(jButtonImageCasa, "ComandoCasa");
		configButton(jButtonImageLider, "ComandoLider");
		configButton(jButtonImageHomecenter, "ComandoHomecenter");
		
		jPanelTop.add(jLabelLogo);
		jPanelTop.add(jlabelTitle);
		
		this.add(jPanelTop, BorderLayout.NORTH);
		
		jPanelMid.add(jButtonImageLider, gbc);
		
		gbc.gridx = 1;	
		jPanelMid.add(jButtonImageCasa, gbc);
		
		gbc.gridx = 2;
		gbc.insets.right = 70;
		jPanelMid.add(jButtonImageHomecenter, gbc);
		
		gbc.weightx = 1;
		gbc.fill = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.right = 0;
		jPanelMid.add(jLabelReportLider, gbc);
		
		gbc.gridx = 1;
		jPanelMid.add(jLabelReportCasa, gbc);
		
		gbc.gridx = 2;
		gbc.insets.right = 70;
		jPanelMid.add(jLabelReportHomecenter, gbc);
		
		gbc.weightx = 0;
		gbc.fill = 0;
		
		this.add(jPanelMid, BorderLayout.CENTER);
		
		this.add(jLabelBottom, BorderLayout.SOUTH);
	}
	
	private void configJLabel(JLabel jlabel, boolean negrita, int size) {
		jlabel.setFont(new Font("Arial",negrita?Font.BOLD:Font.PLAIN, size));
		jlabel.setVerticalAlignment(JLabel.TOP);
		jlabel.setForeground(Color.BLACK);
	}
	
	private void configButton(JButton button, String actionComand) {
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(400,100));
		button.setFocusable(false);
		button.addActionListener(Controller.getInstance());
		button.setActionCommand(actionComand);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(new ImageIcon(getClass().getResource("/res/fondo.png")).getImage(),0,0,getWidth(),getHeight(), this);	
		super.paint(g);
	}

	public void showTableReport(String[] columnNames, ArrayList<Object[]> rowQuery) {
		deleteComponents();
		this.jPanelTable = new JPanel(new GridBagLayout());
		jPanelTop.setVisible(true);
		this.add(jPanelTop, BorderLayout.NORTH);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = 1;
		gbc.insets = new Insets(10,30,20,30);
		jPanelTable.setOpaque(false);
		jPanelTable.add(new JTableReport(rowQuery, columnNames),gbc);
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,20,0);
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = 0;
		jPanelTable.add(jButtonBack, gbc);
		gbc.insets.bottom = 0;
		this.add(jPanelTable, BorderLayout.CENTER);
		jLabelBottom.setVisible(true);
		this.add(jLabelBottom, BorderLayout.SOUTH);
	}

	private void deleteComponents() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setVisible(false);
		}
		this.removeAll();
	}

	public void showMenu() {
		deleteComponents();
		jPanelTop.setVisible(true);
		this.add(jPanelTop, BorderLayout.NORTH);
		jPanelMid.setVisible(true);
		this.add(jPanelMid, BorderLayout.CENTER);
		jLabelBottom.setVisible(true);
		this.add(jLabelBottom, BorderLayout.SOUTH);
	}
}
