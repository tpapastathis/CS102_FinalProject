package finalproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ManagerVacay extends JFrame implements ActionListener{
	JLabel label = new JLabel("Enter date to offer");
	JTextField enterDate = new JTextField("mm/dd/yyyy");
	JButton submit = new JButton("Submit");
	public static String loadedVacay = "";
	public ManagerVacay() {
		super("Offer Date");
		setSize(150,150);
		setLayout(new FlowLayout());
		
		add(label);
		label.setVisible(true);
		
		add(enterDate);
		enterDate.setVisible(true);
		
		add(submit);
		submit.addActionListener(this);
		submit.setVisible(true);
		
		setLocationRelativeTo(null);
		toFront();
		repaint();
		requestFocus();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String dateEntered = enterDate.getText();
		try {
			File fileOutput = new File("vacay.txt");
			Scanner sc = new Scanner(fileOutput);
			StringBuffer buffer = new StringBuffer();

			while(sc.hasNextLine()) {
				buffer.append(sc.nextLine()+System.lineSeparator());
			}
			buffer.append(dateEntered);
			sc.close();
			FileWriter writer = new FileWriter(fileOutput);
			writer.append(buffer.toString());
			writer.flush();
			writer.close();
			JOptionPane.showMessageDialog(null, "Success!");
		}
		catch(FileNotFoundException ex) {
			try {
				File file = new File("vacay.txt");
				FileWriter writer = new FileWriter(file);
				writer.append("");
				writer.flush();
				writer.close();
			}
			catch(IOException ioe) {
				
			}
			ex.printStackTrace();
		}
		catch(IOException ioe1) {
			ioe1.printStackTrace();
		}
		
	}
	public static void loadVacay() {
		try {
			String file = "vacay.txt";
			Scanner sc = new Scanner(new FileReader(file));
			while(sc.hasNextLine())
				loadedVacay += sc.nextLine()+System.lineSeparator();
		}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
	public static void saveVacay(String newVacay) throws IOException{
		try {
			File fileOutput = new File("vacay.txt");
			Scanner sc = new Scanner(fileOutput);
			StringBuffer buffer = new StringBuffer();
			String subStringVacay = newVacay.substring(0, 10);
			
			
			
			try {
				while(sc.hasNextLine()) {
						buffer.append(sc.nextLine()+System.lineSeparator());
				}
			}
			catch(NoSuchElementException nse) {
				nse.printStackTrace();
			}
			
			FileWriter writer = new FileWriter(fileOutput);
			String fullContents = buffer.toString();

			fullContents = fullContents.replaceAll(subStringVacay, newVacay);
			
			writer.append(fullContents);
			writer.flush();
			
			writer.close();
			sc.close();
			
			JOptionPane.showMessageDialog(null, "Success!");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
}



