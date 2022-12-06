package finalproject;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverrideShiftMenu extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton overrideStart = new JButton("Override Start Time");
	JButton overrideEnd = new JButton("Override End Time");
	JButton deleteShift = new JButton("Delete Shift Entirely");
	JTextField currentShift = new JTextField();
	Shift current = null;
	int shiftIndex = -1;
	public OverrideShiftMenu(int shiftEntered) {
		super("Override");
		EmployeeWindow.loadShift();
		
		
		
		for(int i = 0; i < EmployeeWindow.shiftList.size(); i++) {
			if(EmployeeWindow.shiftList.get(i).currentId==shiftEntered) {
				shiftIndex = i;
				current = EmployeeWindow.shiftList.get(i);
			}
		}
		if(shiftIndex==-1) {
			JOptionPane.showMessageDialog(null, "Invalid Shift Entered");
			dispose();
		}
		
		setLayout(new FlowLayout());
		setSize(700,175);
		
		if(shiftIndex!=-1) {
			currentShift.setText(current.toString());
			add(currentShift);
			currentShift.setEditable(false);
			currentShift.setVisible(true);
			
			add(overrideStart);
			overrideStart.setVisible(true);
			overrideStart.addActionListener(this);
			
			add(overrideEnd);
			overrideEnd.setVisible(true);
			overrideEnd.addActionListener(this);
			
			add(deleteShift);
			deleteShift.setVisible(true);
			deleteShift.addActionListener(this);
			
			
			setLocationRelativeTo(null);
			toFront();
			repaint();
			requestFocus();
			setVisible(true);
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==overrideStart) {
			String owST = JOptionPane.showInputDialog("Enter Date and Start Time to overwrite in 24-Hour Format (HH:mm:dd)", current.startTime);
			current.startTime = owST;
			EmployeeWindow.saveShift();
			JOptionPane.showMessageDialog(null, "Shift successfully edited!");
			super.dispose();
			OverrideMenu recursive1 = new OverrideMenu(OverrideMenu.nameSet);
		}
		
		if(e.getSource()==overrideEnd) {
			String owET = JOptionPane.showInputDialog("Enter Date and End Time to overwrite in 24-Hour Format (HH:mm:dd)", current.endTime);
			current.endTime = owET;
			EmployeeWindow.shiftList.get(shiftIndex);
			EmployeeWindow.saveShift();
			JOptionPane.showMessageDialog(null, "Shift successfully edited!");
			super.dispose();
			OverrideMenu recursive2 = new OverrideMenu(OverrideMenu.nameSet);
		}
		
		if(e.getSource()==deleteShift) {
			String yes = JOptionPane.showInputDialog("Are you sure you want to delete this shift? Type 'Yes' if you are sure");
			if(yes.toLowerCase().equals("yes")) {
				EmployeeWindow.shiftList.remove(shiftIndex);
				EmployeeWindow.saveShift();
				JOptionPane.showMessageDialog(null, "Shift successfully deleted");
				repaint();
				super.dispose();
				OverrideMenu recursive3 = new OverrideMenu(OverrideMenu.nameSet);
			}
		}
	}
}
