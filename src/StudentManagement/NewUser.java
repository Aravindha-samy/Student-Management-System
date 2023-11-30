package StudentManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class NewUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtConfirm;
	
	Connection con;
	PreparedStatement pst;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
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
	public NewUser() {
		setType(Type.UTILITY);
		setTitle("NewUserForm\r\n");
		JFrame f=new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(43, 30, 112, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(43, 75, 112, 14);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfirmPassword.setBounds(43, 120, 112, 14);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserType.setBounds(43, 165, 112, 14);
		contentPane.add(lblUserType);
		
		txtUser = new JTextField();
		txtUser.setBounds(175, 27, 196, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(175, 72, 196, 20);
		contentPane.add(txtPass);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setBounds(175, 117, 196, 20);
		contentPane.add(txtConfirm);
		
		JComboBox txtUtype = new JComboBox();
		txtUtype.setModel(new DefaultComboBoxModel(new String[] {"User", "Admin"}));
		txtUtype.setBounds(175, 161, 196, 22);
		contentPane.add(txtUtype);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUser.getText().length()==0) {
					 JOptionPane.showMessageDialog(f,"Please enter the User Name");
				}
				else if(txtPass.getText().length()==0) { 
					JOptionPane.showMessageDialog(f, "Please, Enter the Password");
				}
				else if(txtPass.getText().equals(txtConfirm.getText())==false) {
					JOptionPane.showMessageDialog(f, " Password does not Matched");
				}
				else {
					
					//database Connection
					
					try {
						
						String username=txtUser.getText();
						String confirmpass=txtConfirm.getText();
						String usertype=txtUtype.getSelectedItem().toString();
						
						
							
						
					    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","root");
						pst = con.prepareStatement("insert into user(username,password,utype) values (?,?,?)");
						pst.setString(1, username);
						pst.setString(2, confirmpass);
						pst.setString(3, usertype);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"User Created");
						txtUser.setText("");
						txtPass.setText("");
						txtConfirm.setText("");
						txtUtype.setSelectedIndex(-1);
						txtUser.requestFocus();
												
						
						
					} catch (SQLException e1) {
						System.out.println("Error While Connecting to the database");
						e1.printStackTrace();
					}
					
						
						
						
						
						
												
						
				
					
					
					
					
					
				}
			}
		});
		btnAdd.setBounds(206, 216, 89, 34);
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(318, 216, 89, 34);
		contentPane.add(btnCancel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, lblNewLabel, lblPassword, lblConfirmPassword, lblUserType, txtUser, txtPass, txtConfirm, txtUtype, btnAdd, btnCancel}));
	}
}
