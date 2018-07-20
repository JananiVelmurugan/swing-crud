import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JTextField;


public class Crudapp {

	private JFrame frame;
	private JTable table;
	String[] columnNames = {"S.NO","STUDENTID", "STUDENT NAME", "CGPA", "COLLEGE ID", "COLLEGE NAME"};
	DefaultTableModel model;
	private JTextField sid;
	private JTextField sname;
	private JTextField cgpa3;
	private JTextField fourth;
	private JTextField fifth;
	static int a=100;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crudapp window = new Crudapp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Crudapp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void showdata()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecedb","root","root");
			Statement stmnt=con.createStatement();
			String rquery="select * from student";
			ResultSet rs=stmnt.executeQuery(rquery);
			model.setRowCount(0);
			int i=1;
			while(rs.next())
			{
				model.addRow(new Object[]{i++,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
			}
			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Exception Invalid Data");
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 830, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 255, 47));
		panel.setBounds(0, 23, 804, 37);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRUD APPLICATIONS");
		lblNewLabel.setBounds(0, 0, 804, 37);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(288, 118, 516, 205);
		frame.getContentPane().add(scrollPane);
		
		model=new DefaultTableModel();
		table = new JTable(model);
		model.setColumnIdentifiers(columnNames);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("LOAD DATA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecedb","root","root");
					Statement stmnt=con.createStatement();
					String rquery="select * from student";
					ResultSet rs=stmnt.executeQuery(rquery);
					model.setRowCount(0);
					int i=1;
					while(rs.next())
					{
						model.addRow(new Object[]{i++,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
					}
					
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Exception Invalid Data");
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 0, 255));
		btnNewButton.setBackground(new Color(250, 250, 210));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(290, 356, 141, 45);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				System.out.println(a);
				a++;
				
			}
		});
		btnNewButton_1.setBackground(new Color(250, 250, 210));
		btnNewButton_1.setForeground(new Color(255, 0, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(479, 356, 148, 45);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int column = 1;
					int row = table.getSelectedRow();
					String value = table.getModel().getValueAt(row, column).toString();
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecedb","root","root");
					Statement stmnt=con.createStatement();
					String delqury="delete from student where student_id="+value+"";
					int result=stmnt.executeUpdate(delqury);
					if(result>0)
					{
						showdata();
						//JOptionPane.showMessageDialog(null, "DATA SAVED");
						
					}
					con.close();
					stmnt.close();
					
				}catch(Exception e)
				{
					System.out.println(e);
				}
				
			}
		});
		btnNewButton_2.setForeground(new Color(255, 0, 255));
		btnNewButton_2.setBackground(new Color(250, 250, 210));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(669, 356, 135, 45);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("STUDENT ID");
		lblNewLabel_1.setBounds(10, 133, 94, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		sid = new JTextField();
		sid.setBounds(126, 133, 152, 30);
		frame.getContentPane().add(sid);
		sid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("STUDENT NAME");
		lblNewLabel_2.setBounds(10, 189, 94, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		sname = new JTextField();
		sname.setBounds(126, 189, 152, 30);
		frame.getContentPane().add(sname);
		sname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CGPA");
		lblNewLabel_3.setBounds(10, 244, 94, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		cgpa3 = new JTextField();
		cgpa3.setBounds(126, 244, 148, 30);
		frame.getContentPane().add(cgpa3);
		cgpa3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("COLLEGE ID");
		lblNewLabel_4.setBounds(10, 293, 94, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		fourth = new JTextField();
		fourth.setBounds(126, 293, 148, 30);
		frame.getContentPane().add(fourth);
		fourth.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("CLG NAME");
		lblNewLabel_5.setBounds(10, 346, 94, 30);
		frame.getContentPane().add(lblNewLabel_5);
		
		fifth = new JTextField();
		fifth.setBounds(127, 346, 147, 30);
		frame.getContentPane().add(fifth);
		fifth.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("SAVE DATA");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecedb","root","root");
					Statement stmnt=con.createStatement();
					int stu_id=Integer.parseInt(sid.getText());
					String name=sname.getText();
					double cgpa=Double.parseDouble(cgpa3.getText());
					int clgid=Integer.parseInt(fourth.getText());
					String clgname=fifth.getText();
					String sqlinsert="insert into student values("+stu_id+",'"+name+"',"+cgpa+","+clgid+",'"+clgname+"')";
					int result=stmnt.executeUpdate(sqlinsert);
					if(result>0)
					{
						showdata();
						//JOptionPane.showMessageDialog(null, "DATA SAVED");
						
					}
					con.close();
					stmnt.close();
					
					
					
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Exception Invalid Data");
				}
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setForeground(new Color(255, 0, 255));
		btnNewButton_3.setBounds(10, 418, 141, 45);
		frame.getContentPane().add(btnNewButton_3);
	}
}
