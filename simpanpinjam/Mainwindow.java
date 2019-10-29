package com.koperasi.simpanpinjam;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;;

public class Mainwindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
/*	
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
//					Login frmLogin = new Login();
//					frmLogin.setVisible(true);
					Mainwindow frame = new Mainwindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

*/	
	
	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	/**
	 * Create the panel.
	 */
	
	
		

	public Mainwindow() {
		setAutoRequestFocus(false);
		setMinimumSize(new Dimension(1000, 600));
		setTitle("Koperasi Simpan Pinjam");

		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial", Font.PLAIN, 15));
		menuBar.setBounds(0, 0, 984, 34);
		getContentPane().add(menuBar);
		
		JMenu mnDataMaster = new JMenu("File") {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			    public Dimension getPreferredSize() {
			        Dimension d = super.getPreferredSize();
			        d.width = Math.max(d.width, 85); // set minimums
			        d.height = Math.max(d.height, 100);
			        return d;
			    }
			};
		
		mnDataMaster.setFont(new Font("Arial", Font.PLAIN, 15));
		mnDataMaster.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/folder.png")));
		menuBar.add(mnDataMaster);
		
		JMenuItem mnItemProfile = new JMenuItem("Profile Petugas");
		mnItemProfile.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemProfile.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Profile-icon.png")));		
		
		mnItemProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//setEnabled(false);
				Profile frmProfile = new Profile();
				frmProfile.display();
				frmProfile.setVisible(true);
				//dispose();
				
			}
		});
		
		JMenu mnMasterData = new JMenu("Master Data");
		mnMasterData.setFont(new Font("Arial", Font.PLAIN, 13));
		mnMasterData.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Checklist-icon.png")));
		mnDataMaster.add(mnMasterData);
		
		JMenuItem mntmPetugas = new JMenuItem("Petugas");
		mntmPetugas.setFont(new Font("Arial", Font.PLAIN, 13));
		mnMasterData.add(mntmPetugas);
		
		JMenuItem mntmJenisSimpanan = new JMenuItem("Jenis Simpanan");
		mntmJenisSimpanan.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmJenisSimpanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JenisSimpanan frmJenisSimpanan = new JenisSimpanan();
				
				frmJenisSimpanan.setVisible(true);
			}
		});
		mnMasterData.add(mntmJenisSimpanan);
		mnDataMaster.add(mnItemProfile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setFont(new Font("Arial", Font.PLAIN, 13));

		mntmLogout.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Logout-icon.png")));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KoneksiDatabase koneksi = new KoneksiDatabase();		
				Connection mysql= koneksi.getConnection();
				
				try {
					
					Statement stmt = mysql.createStatement();
					String sql="UPDATE petugas SET is_login = '0' WHERE is_login=1";
					stmt.executeUpdate(sql);
					mysql.close();
					
				}
				catch  ( Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());	
				}
				
				

				Login frmLogin = new Login();
				frmLogin.setVisible(true);
				
				close();
				
				
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		mnDataMaster.add(separator_1);
		
		mnDataMaster.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmExit.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Button-Close-icon.png")));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JSeparator separator = new JSeparator();
		mnDataMaster.add(separator);
		mnDataMaster.add(mntmExit);
		
		JMenu mnAnggota = new JMenu("Anggota") {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			    public Dimension getPreferredSize() {
			        Dimension d = super.getPreferredSize();
			        d.width = Math.max(d.width, 110); // set minimums
			        d.height = Math.max(d.height, 100);
			        return d;
			    }
			};
			
		mnAnggota.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnAnggota.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/icon-user.png")));
		menuBar.add(mnAnggota);
		
		JMenuItem mntmTambahAnggota = new JMenuItem("Tambah Anggota");
		mntmTambahAnggota.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmTambahAnggota.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Folder-Add-icon.png")));
		mntmTambahAnggota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Anggota frmAnggota = new Anggota();
				//frmAnggota.Layout();
				//frmAnggota.Aksi();
				frmAnggota.setVisible(true);
			
			}
		});
		mnAnggota.add(mntmTambahAnggota);
		
		JMenuItem mntmDaftarAnggota = new JMenuItem("Daftar Anggota");
		mntmDaftarAnggota.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmDaftarAnggota.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Checklist-icon.png")));
		mntmDaftarAnggota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CRUDAnggota frmAnggota = new CRUDAnggota();
				//frmAnggota.Layout();
				
				ListAnggota frmListAnggota = new ListAnggota();
				frmListAnggota.setVisible(true);
				//frmAnggota.setVisible(true);

			}
		});
		
		JSeparator separator_2 = new JSeparator();
		mnAnggota.add(separator_2);
		mnAnggota.add(mntmDaftarAnggota);
		
		JMenu mnTransaksi = new JMenu("Transaksi"){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			    public Dimension getPreferredSize() {
			        Dimension d = super.getPreferredSize();
			        d.width = Math.max(d.width, 110); // set minimums
			        d.height = Math.max(d.height, 100);
			        return d;
			    }
			};
		mnTransaksi.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/money-bag.png")));
		mnTransaksi.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnTransaksi);
		
		JMenuItem mntmSimpanan = new JMenuItem("Input Simpanan");
		mntmSimpanan.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmSimpanan.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Money-icon.png")));
		mntmSimpanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Simpanan frmSimpanan = new Simpanan();
			frmSimpanan.setVisible(true);
			}
		});
		mnTransaksi.add(mntmSimpanan);
		
		JMenuItem mntmPinjaman = new JMenuItem("Input Pinjaman");
		mntmPinjaman.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmPinjaman.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/upload-icon.png")));
		mntmPinjaman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Pinjaman frmPinjaman = new Pinjaman();
				frmPinjaman.setVisible(true);
			}
		});
		
		JSeparator separator_3 = new JSeparator();
		mnTransaksi.add(separator_3);
		mnTransaksi.add(mntmPinjaman);
		
		JMenuItem mntmAngsuran = new JMenuItem("Input Angsuran");
		mntmAngsuran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Angsuran frmAngsuran = new Angsuran();
				frmAngsuran.setVisible(true);
			}
		});
		
		JSeparator separator_4 = new JSeparator();
		mnTransaksi.add(separator_4);
		mntmAngsuran.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmAngsuran.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Sales-by-payment-method-icon.png")));
		mnTransaksi.add(mntmAngsuran);
		
		JMenu mnLaporan = new JMenu("Laporan"){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			    public Dimension getPreferredSize() {
			        Dimension d = super.getPreferredSize();
			        d.width = Math.max(d.width, 110); // set minimums
			        d.height = Math.max(d.height, 100);
			        return d;
			    }
			};
		mnLaporan.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/newspaper.png")));
		mnLaporan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnLaporan);
		
		JMenuItem mntmLaporanSimpanan = new JMenuItem("Laporan Simpanan");
		mntmLaporanSimpanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LapSimpanan frmSimpanan = new LapSimpanan();
				frmSimpanan.setVisible(true);

			}
		});
		mntmLaporanSimpanan.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmLaporanSimpanan.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Product-sale-report-icon.png")));
		mnLaporan.add(mntmLaporanSimpanan);
		
		JSeparator separator_5 = new JSeparator();
		mnLaporan.add(separator_5);
		
		JMenuItem mntmLaporanPinjaman = new JMenuItem("Laporan Pinjaman");
		mntmLaporanPinjaman.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmLaporanPinjaman.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Increase-icon.png")));
		mnLaporan.add(mntmLaporanPinjaman);
		
		JMenu mnAbout = new JMenu("Tentang");
		mnAbout.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/information.png")));
		mnAbout.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutMe = new JMenuItem("About Me");
		mntmAboutMe.setFont(new Font("Arial", Font.PLAIN, 13));
		mntmAboutMe.setIcon(new ImageIcon(Mainwindow.class.getResource("/Images/Profile-icon.png")));
		mnAbout.add(mntmAboutMe);
		mntmAboutMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About halAbout = new About();
				halAbout.setVisible(true);
			}
		});

	}
}
