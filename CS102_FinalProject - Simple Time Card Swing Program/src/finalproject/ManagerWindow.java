package finalproject;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;  
import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDateTime;

public class ManagerWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int SIZE = 300;
	JLabel manMode = new JLabel("Manager Mode");
	JButton enterAudit = new JButton("Enter Shift Audit Mode");
	JButton overrideShift = new JButton("Override Employee Shift");
	JButton vacayDays = new JButton("Offer Vacation Days");
	
	public ManagerWindow() {
		super("Manager Mode");
		setSize(SIZE,SIZE);
		setLayout(new GridLayout(0,1));
		
		add(manMode);
		
		add(enterAudit);
		enterAudit.addActionListener(this);
		
		add(overrideShift);
		overrideShift.addActionListener(this);
		
		add(vacayDays);
		vacayDays.addActionListener(this);
		
		setLocationRelativeTo(null);
		toFront();
		repaint();
		requestFocus();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enterAudit) {
			EnterAudit enterAudit = new EnterAudit();
		}
		else if(e.getSource()==overrideShift) {
			String empNameOverride = JOptionPane.showInputDialog("Enter Employee Name");
			OverrideMenu menu = new OverrideMenu(empNameOverride);
		}
		else if(e.getSource()==vacayDays) {
			//enter vacation days offer mode
			ManagerVacay vacayMenu = new ManagerVacay();
		}
		
	}
	public static void main(String[] args) {
		ManagerWindow test = new ManagerWindow();
	}

}

class EnterAudit extends JFrame implements ActionListener{
	JLabel label = new JLabel("Enter Employee Name");
	JTextField empName = new JTextField("Employee Name");
	JTextField dateSpan = new JTextField("Enter Month (MM)");
	JButton submitAudit = new JButton("Submit");
	String empNameString, monthEntered;
	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	ArrayList<LocalDateTime> ldt = new ArrayList<LocalDateTime>();
	protected EnterAudit() {
		super("Audit Mode");
		setSize(200,300);
		setLayout(new GridLayout(0,1));
		
		label.setBorder(border);
		add(label);
		
		add(empName);
		empName.setSize(5,5);
		
		add(dateSpan);
		dateSpan.setSize(5,5);
		
		add(submitAudit);
		submitAudit.addActionListener(this);
		
		setLocationRelativeTo(null);
		toFront();
		repaint();
		requestFocus();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submitAudit) {
			ArrayList<Shift> matches = new ArrayList<Shift>();
			EmployeeWindow.loadShift();
			empNameString = empName.getText(); 
			monthEntered = dateSpan.getText();
			for(int i = 0; i < EmployeeWindow.shiftList.size(); i++) {
				try {
					if(EmployeeWindow.shiftList.get(i).name.equals(empNameString) 
							&& EmployeeWindow.shiftList.get(i).endTime.substring(0,2).equals(monthEntered)) {
						matches.add(EmployeeWindow.shiftList.get(i));
						
					}
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(!matches.isEmpty())
				auditTime(matches);
			else 
				JOptionPane.showMessageDialog(null, "Invalid Information Entered or No Shifts Found");
		}
	}
	public static void auditTime(ArrayList<Shift> match) {
		ArrayList<String> tempStartTimes = new ArrayList<String>();
		ArrayList<String> tempEndTimes = new ArrayList<String>();
		ArrayList<LocalDateTime> tempStartLDT = new ArrayList<LocalDateTime>();
		ArrayList<LocalDateTime> tempEndLDT = new ArrayList<LocalDateTime>();
		ArrayList<Long> durations = new ArrayList<Long>();
		
		long fullWorkDurationSeconds = 0;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		for(int i = 0; i < match.size(); i++) {
			tempStartTimes.add(match.get(i).startTime.toString());
			tempEndTimes.add(match.get(i).endTime.toString());
			
			tempStartLDT.add(LocalDateTime.parse(tempStartTimes.get(i), formatter));
			tempEndLDT.add(LocalDateTime.parse(tempEndTimes.get(i), formatter));
			
			durations.add(Duration.between(tempStartLDT.get(i), tempEndLDT.get(i)).toSeconds());
		}
		for(int i = 0; i < durations.size(); i++) {
			fullWorkDurationSeconds+=durations.get(i);
		}
		String timeWorked = formatSeconds(fullWorkDurationSeconds);
		JOptionPane.showMessageDialog(null, "Total Time Working : "+timeWorked);
	}
	private static String formatSeconds(long seconds) {
		int S =(int) seconds % 60;
        int H =(int) seconds / 60;
        int M = H % 60;
        H = (int) H / 60;
       
		return ((H < 10 ? "0" : "")+H) + ":" + (M < 10 ? "0" : "")+M+ ":" + (S < 10 ? "0" : "")+S;
	}
}
	
	
	
	

