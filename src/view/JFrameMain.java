package view;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class JFrameMain extends JFrame{
	private JPanelMain jPanelMain;
	
	public JFrameMain() throws HeadlessException {
		super();
		this.jPanelMain = new JPanelMain();
		init();
	}

	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200,720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(jPanelMain);
		this.setVisible(true);
	}

	public void showTableReport(String[] columnNames, ArrayList<Object[]> rowQuery) {
		jPanelMain.showTableReport(columnNames, rowQuery);
	}

	public void showMenu() {
		jPanelMain.showMenu();
	}
}
