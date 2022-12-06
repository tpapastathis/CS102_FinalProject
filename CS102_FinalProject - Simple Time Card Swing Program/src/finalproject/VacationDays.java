package finalproject;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class VacationDays extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea availableDays = new JTextArea();
	JButton button = new JButton("Request Day");
	String nameInput = "";
	public VacationDays(String nameInput){
		super(nameInput+" - Vacation Request");
		ManagerVacay.loadVacay();
		availableDays.setText(ManagerVacay.loadedVacay);
		ManagerVacay.loadedVacay = "";
		this.nameInput = nameInput;
		
		setSize(300,300);
		setLayout(new FlowLayout());
		add(availableDays);
		availableDays.setVisible(true);
		availableDays.setEditable(false);
		
		add(button);
		button.setVisible(true);
		button.addActionListener(this);
		
		setLocationRelativeTo(null);
		toFront();
		repaint();
		requestFocus();
		setVisible(true);

	}

	public static void main(String[] args) {
		VacationDays vacation = new VacationDays("test");

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String day = JOptionPane.showInputDialog("Type the date you wish to request");
		day+=" - "+nameInput;
		try {
			ManagerVacay.saveVacay(day);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		dispose();
		VacationDays vacation = new VacationDays(nameInput);
	}
}			



