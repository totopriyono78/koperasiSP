package com.koperasi.simpanpinjam;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		
		JLabel lblSistemKoperasiSimpan = new JLabel("Sistem Koperasi Simpan Pinjam");
		lblSistemKoperasiSimpan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSistemKoperasiSimpan.setBounds(178, 11, 227, 25);
		contentPane.add(lblSistemKoperasiSimpan);
		
		JLabel lblVersi = new JLabel("Version 1.0");
		lblVersi.setBounds(178, 36, 72, 14);
		contentPane.add(lblVersi);
		
		JLabel lblCopyrightBy = new JLabel("Copyright @2019 by Toto Priyono");
		lblCopyrightBy.setBounds(178, 98, 171, 14);
		contentPane.add(lblCopyrightBy);
		
		JLabel lblMahasiswaUniversitasMercu = new JLabel("Mahasiswa Universitas Mercu Buana Yogyakarta");
		lblMahasiswaUniversitasMercu.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMahasiswaUniversitasMercu.setBounds(178, 171, 256, 14);
		contentPane.add(lblMahasiswaUniversitasMercu);
		
		JLabel lblSebagaiTugasMata = new JLabel("Sebagai tugas mata kuliah OOP");
		lblSebagaiTugasMata.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSebagaiTugasMata.setBounds(178, 188, 227, 14);
		contentPane.add(lblSebagaiTugasMata);
		
		JLabel lblTlp = new JLabel("Tlp. 08112502883");
		lblTlp.setBounds(178, 140, 107, 14);
		contentPane.add(lblTlp);
		
		JLabel lblTotopriyonogmailcom = new JLabel("Toto.priyono@gmail.com");
		lblTotopriyonogmailcom.setBounds(178, 123, 131, 14);
		contentPane.add(lblTotopriyonogmailcom);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 160, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-30, 11, 180, 180);
		lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/totosmall.jpg")));
		panel.add(lblNewLabel);
	}

}
