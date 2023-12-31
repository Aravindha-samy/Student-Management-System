package StudentManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		JFrame f=new JFrame();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 434, 55);
		contentPane.add(panel);
		
		JLabel lblHead = new JLabel("Login");
		lblHead.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblHead.setForeground(Color.WHITE);
		panel.add(lblHead);
		
		JLabel lblUser = new JLabel("UserName");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUser.setBounds(27, 93, 66, 21);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPass.setBounds(27, 139, 66, 21);
		contentPane.add(lblPass);
		
		txtUser = new JTextField();
		txtUser.setBounds(140, 85, 208, 28);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(140, 136, 208, 28);
		contentPane.add(txtPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(txtUser.getText().isEmpty()||txtPass.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(f,"Enter the Username");
					}
					
					else {
						String username=txtUser.getText();
						String password=txtPass.getText();
						
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","root");
						
						
						pst=con.prepareStatement("select * from user where username=? and password=?");
						pst.setString(1,username );
						pst.setString(2,password );
						rs=pst.executeQuery();
						System.out.print("l");
						
						if(rs.next()) 
						{
							Main m= new Main();
							f.hide();
							m.setVisible(true);		

						}
						else {
							JOptionPane.showMessageDialog(f,"Enter the Correct Username and Password");
							txtUser.setText("");
							txtPass.setText("");
							txtUser.requestFocus(); 
							
						}
					} 
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBounds(163, 201, 102, 34);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.CYAN);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(299, 201, 102, 34);
		contentPane.add(btnCancel);
		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewUser.setBackground(Color.RED);
		btnNewUser.setForeground(Color.WHITE);
		btnNewUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewUser.setBounds(27, 201, 102, 34);
		contentPane.add(btnNewUser);
	}
}
