package com.koperasi.simpanpinjam;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.SystemColor;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private String sessUsername;

	public Login() {
							
		//set ukuran frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 629, 400);
		
		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(SystemColor.desktop);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(321, 151, 78, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.desktop);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(321, 194, 78, 20);
		contentPane.add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(399, 147, 188, 32);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		AbstractAction action = new AbstractAction()
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 9206686655104992903L;

			@Override
		    public void actionPerformed(ActionEvent e)
		    {
		        handleLogin();
		    }
		};

		passwordField.addActionListener(action);
		
		passwordField.setBounds(399, 190, 188, 32);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleLogin();
			
			}

		});
		
		btnLogin.setBounds(399, 236, 96, 39);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Cancel");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnReset.setBounds(505, 236, 82, 39);
		contentPane.add(btnReset);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 290, 377);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Images/koperasi.jpg")));
		lblNewLabel.setBounds(10, 79, 290, 216);
		panel.add(lblNewLabel);
		
		JLabel lblKoperasiSimpanPinjam = new JLabel("Koperasi");
		lblKoperasiSimpanPinjam.setForeground(SystemColor.desktop);
		lblKoperasiSimpanPinjam.setHorizontalAlignment(SwingConstants.LEFT);
		lblKoperasiSimpanPinjam.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblKoperasiSimpanPinjam.setBounds(321, 11, 248, 56);
		contentPane.add(lblKoperasiSimpanPinjam);
		
		JLabel lblVersion = new JLabel("Version 1.0");
		lblVersion.setForeground(SystemColor.desktop);
		lblVersion.setBounds(520, 336, 67, 14);
		contentPane.add(lblVersion);
		
		JLabel label = new JLabel("Simpan Pinjam");
		label.setForeground(SystemColor.desktop);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Verdana", Font.PLAIN, 19));
		label.setBounds(321, 39, 248, 56);
		contentPane.add(label);	
	}

		
	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	public void handleLogin() {
		KoneksiDatabase koneksi = new KoneksiDatabase();		
		Connection mysql= koneksi.getConnection();
		
		try {
			
			sessUsername = textUsername.getText();
			char[] password = passwordField.getPassword();
			String strPassword = new String(password);
			
			Statement stmt = mysql.createStatement();
			String sql="SELECT * FROM petugas WHERE username_petugas='"+sessUsername+"' AND password_petugas='"+strPassword+"'";
			ResultSet res = stmt.executeQuery(sql);	
			
			 if(res.next()){
	                if(sessUsername.equals(res.getString("username_petugas")) && strPassword.equals(res.getString("password_petugas"))){		                	
	                				                	
	                	Statement stmtUpdate = mysql.createStatement();
	                	String sqlUpdateZero ="UPDATE petugas SET is_login = '0'";
	                	stmtUpdate.executeUpdate(sqlUpdateZero);
	                	
	                	String sqlUpdate ="UPDATE petugas SET is_login = '1' WHERE id_petugas ="+res.getString("id_petugas");
	                	stmtUpdate.executeUpdate(sqlUpdate);
	                	
	                	Mainwindow halUtama = new Mainwindow();
	                	halUtama.setVisible(true);
	                	close();
	                }
	            }else{
	            	JOptionPane.showMessageDialog(null, "username atau password salah");
	                }
			mysql.close();
		}
		catch ( Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());					
		}
	}

}

