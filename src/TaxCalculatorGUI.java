import java.awt.EventQueue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.Font;

public class TaxCalculatorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField grossSalaryField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton filerRadioButton;
	private JLabel taxResultLabel;
	private JLabel netSalaryResultLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaxCalculatorGUI frame = new TaxCalculatorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TaxCalculatorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(208, 201, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TAX & SALARY CALCULATOR");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setBounds(120, 26, 250, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Gross Salary:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblNewLabel_1.setBounds(43, 70, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		grossSalaryField = new JTextField();
		grossSalaryField.setBounds(175, 67, 120, 20);
		contentPane.add(grossSalaryField);
		grossSalaryField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Select Tax Status:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(43, 116, 111, 14);
		contentPane.add(lblNewLabel_2);
		
		filerRadioButton = new JRadioButton("Filer (5%)");
		buttonGroup.add(filerRadioButton);
		filerRadioButton.setSelected(true); // Filer by default select rahega
		filerRadioButton.setBounds(156, 112, 150, 22);
		contentPane.add(filerRadioButton);
		
		JRadioButton nonFilerRadioButton = new JRadioButton("Non-Filer (10%)");
		buttonGroup.add(nonFilerRadioButton);
		nonFilerRadioButton.setBounds(155, 142, 151, 22);
		contentPane.add(nonFilerRadioButton);
		
		taxResultLabel = new JLabel("Tax Deduction: Rs. 0.0");
		taxResultLabel.setForeground(new Color(255, 0, 0));
		taxResultLabel.setBounds(155, 226, 250, 14);
		contentPane.add(taxResultLabel);
		
		netSalaryResultLabel = new JLabel("Net In-Hand Salary: Rs. 0.0");
		netSalaryResultLabel.setBackground(new Color(192, 192, 192));
		netSalaryResultLabel.setForeground(new Color(0, 128, 0));
		netSalaryResultLabel.setBounds(140, 251, 250, 14);
		contentPane.add(netSalaryResultLabel);
		
		JButton calculateButton = new JButton("Calculate Salary");
		calculateButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		calculateButton.setBounds(140, 181, 144, 22);
		contentPane.add(calculateButton);
		
		// Event Handling Logic
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String salaryText = grossSalaryField.getText();
					if(salaryText.isEmpty()) {
					    throw new NumberFormatException(); 
					}
					double grossSalary = Double.parseDouble(salaryText);
					
					boolean isFiler = filerRadioButton.isSelected();
					
					TaxLogic logic = new TaxLogic();
					
					double taxAmount = logic.calculateTaxAmount(grossSalary, isFiler);
					double netSalary = logic.calculateNetSalary(grossSalary, taxAmount);
					
					taxResultLabel.setText("Tax Deduction: Rs. " + String.format("%.2f", taxAmount));
					netSalaryResultLabel.setText("Net In-Hand Salary: Rs. " + String.format("%.2f", netSalary));
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error: Invalid Input! Please enter numbers only for salary.", "Input Error", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Logic Error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "System Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}