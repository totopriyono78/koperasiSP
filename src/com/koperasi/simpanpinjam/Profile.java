package com.koperasi.simpanpinjam;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;

public class Profile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3634484587709294041L;
	private JPanel contentPane;
	JLabel varNamaPetugas = new JLabel();
	JLabel varAlamatPetugas = new JLabel();
	JLabel varTglLahirPetugas = new JLabel();
	JLabel varNoTlp = new JLabel();	
	JLabel varJabatan = new JLabel();
	JLabel lblPhoto = new JLabel("");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
					frame.display();
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
	 public Profile() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 57, 115, 120);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblPhoto.setBounds(-12, 0, 150, 180);
		panel.add(lblPhoto);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(166, 57, 48, 14);
		contentPane.add(lblNama);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(166, 82, 48, 14);
		contentPane.add(lblAlamat);
		
		JLabel lblNewLabel = new JLabel("Tgl Lahir");
		lblNewLabel.setBounds(166, 106, 60, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNoTlp = new JLabel("No Tlp");
		lblNoTlp.setBounds(166, 131, 48, 14);
		contentPane.add(lblNoTlp);
		
		JLabel lblJabatan = new JLabel("Jabatan");
		lblJabatan.setBounds(166, 156, 48, 14);
		contentPane.add(lblJabatan);
				
		JLabel lblProfilePetugas = new JLabel("Profile Petugas");
		lblProfilePetugas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProfilePetugas.setBounds(27, 11, 171, 22);
		contentPane.add(lblProfilePetugas);
	}
	 
	public void display() {
		
		KoneksiDatabase koneksi = new KoneksiDatabase();		
		Connection mysql= koneksi.getConnection();
		
		try {
			
			Statement stmt = mysql.createStatement();
			String sql="SELECT * FROM petugas JOIN jabatan ON petugas.jabatan_petugas=jabatan.id_jabatan WHERE is_login=1";
			ResultSet res = stmt.executeQuery(sql);	
			if(res.next()){
					
				lblPhoto.setIcon(new ImageIcon(Profile.class.getResource(res.getString("photo_petugas"))));

				
				varNamaPetugas.setText(": "+ res.getString("nama_petugas"));
				varNamaPetugas.setBounds(224, 57, 263, 14);
				contentPane.add(varNamaPetugas);

				varAlamatPetugas.setText(": "+ res.getString("alamat_petugas"));				
				varAlamatPetugas.setBounds(224, 82, 263, 14);
				contentPane.add(varAlamatPetugas);

				varTglLahirPetugas.setText(": "+ res.getString("tgl_lahir_petugas"));				
				varTglLahirPetugas.setBounds(224, 106, 263, 14);
				contentPane.add(varTglLahirPetugas);
				
				varNoTlp.setText(": "+ res.getString("nohp_petugas"));
				varNoTlp.setBounds(224, 131, 263, 14);
				contentPane.add(varNoTlp);
				
				varJabatan.setText(": "+ res.getString("jenis_jabatan"));
				varJabatan.setBounds(224, 156, 263, 14);
				contentPane.add(varJabatan);
				
				
				
	            }
			res.close();
		}
		catch ( Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());					
		}
	

	
	}

}
