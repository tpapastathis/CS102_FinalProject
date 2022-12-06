package finalproject;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OverrideMenu extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea matchesField = new JTextArea();
	JLabel instructions = new JLabel("Choose a shift based on the first number in each line");
	JButton override = new JButton("Override Shift");
	public static String nameSet = "";
	public OverrideMenu(String empName) {
		super("Override Shift");
		setLayout(new FlowLayout());
		setSize(500,150);
		
		nameSet = empName;
		
		ArrayList<Shift> matches = new ArrayList<Shift>();
		String matchesString = "";
		EmployeeWindow.loadShift();
		
		for(int i = 0; i < EmployeeWindow.shiftList.size(); i++) {
			if(EmployeeWindow.shiftList.get(i).name.equals(empName)) {
				matches.add(EmployeeWindow.shiftList.get(i));
				matchesString+=EmployeeWindow.shiftList.get(i).currentId+" "+EmployeeWindow.shiftList.get(i).name+" "+EmployeeWindow.shiftList.get(i).startTime+" "+EmployeeWindow.shiftList.get(i).endTime+"\n";
			}
		}
		
		
		add(instructions);
		instructions.setVisible(true);

		matchesField.setText(matchesString);
		matchesField.setEditable(false);
		
		add(matchesField);
		matchesField.setVisible(true);
		
		add(override);
		override.addActionListener(this);
		
		setLocationRelativeTo(null);
		toFront();
		repaint();
		requestFocus();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String shiftChosenString = JOptionPane.showInputDialog("Choose Shift");
		int shiftChosen = Integer.parseInt(shiftChosenString);
		OverrideShiftMenu shiftMenu = new OverrideShiftMenu(shiftChosen);
		dispose();
	}
	public static void main(String[] args) {
		OverrideMenu menu = new OverrideMenu("Test1");
	}
}
