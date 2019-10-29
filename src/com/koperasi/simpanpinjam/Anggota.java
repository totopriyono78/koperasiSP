package com.koperasi.simpanpinjam;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Anggota extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_NoAnggota;
	private JTextField text_NamaAnggota;
	private JTextField text_TempatLahirAnggota;
	private JTextField text_TglLahirAnggota;
	private JTextField text_HpAnggota;
	private JTextArea text_AlamatAnggota;

	JLabel lbl_Image = new JLabel("Foto");
	
	String imgPath = null;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anggota frame = new Anggota();
					//frame.Search();
					//frame.Save();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/

	void Clear() {
		text_NoAnggota.setText("");
		text_NamaAnggota.setText("");
		text_AlamatAnggota.setText("");
		text_TempatLahirAnggota.setText("");
		text_TglLahirAnggota.setText("");
		text_HpAnggota.setText("");
		lbl_Image.setIcon(null);
	}

	/**
	 * Create the frame.
	 */
	public Anggota() {
		setTitle("Data Anggota");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 743, 399);
		
		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomorAnggota = new JLabel("Nomor Anggota");
		lblNomorAnggota.setBounds(30, 29, 123, 14);
		contentPane.add(lblNomorAnggota);
		
		JLabel lblNamaAnggota = new JLabel("Nama Anggota");
		lblNamaAnggota.setBounds(30, 57, 90, 14);
		contentPane.add(lblNamaAnggota);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(30, 152, 48, 14);
		contentPane.add(lblAlamat);
		
		JLabel lblTglLahir = new JLabel("Tgl Lahir");
		lblTglLahir.setBounds(30, 113, 90, 14);
		contentPane.add(lblTglLahir);
		
		JLabel lblNoTlphp = new JLabel("No Tlp/Hp");
		lblNoTlphp.setBounds(30, 207, 90, 14);
		contentPane.add(lblNoTlphp);
		
		JLabel lblFotoKtp = new JLabel("Foto KTP");
		lblFotoKtp.setBounds(30, 237, 90, 14);
		contentPane.add(lblFotoKtp);
		
		JLabel lblTempatLahir = new JLabel("Tempat Lahir");
		lblTempatLahir.setBounds(30, 88, 76, 14);
		contentPane.add(lblTempatLahir);
		
		text_NoAnggota = new JTextField();
		text_NoAnggota.setBounds(163, 30, 219, 20);
		contentPane.add(text_NoAnggota);
		text_NoAnggota.setColumns(10);
		
		text_NamaAnggota = new JTextField();
		text_NamaAnggota.setBounds(163, 58, 219, 20);
		contentPane.add(text_NamaAnggota);
		text_NamaAnggota.setColumns(10);
		
		text_TempatLahirAnggota = new JTextField();
		text_TempatLahirAnggota.setBounds(163, 89, 219, 20);
		contentPane.add(text_TempatLahirAnggota);
		text_TempatLahirAnggota.setColumns(10);
		
		text_TglLahirAnggota = new JTextField();
		text_TglLahirAnggota.setBounds(163, 120, 133, 20);
		contentPane.add(text_TglLahirAnggota);
		text_TglLahirAnggota.setColumns(10);
		
		text_AlamatAnggota = new JTextArea();
		text_AlamatAnggota.setLineWrap(true);
		text_AlamatAnggota.setWrapStyleWord(true);
		text_AlamatAnggota.setBounds(163, 151, 219, 49);
		contentPane.add(text_AlamatAnggota);
		
		text_HpAnggota = new JTextField();
		text_HpAnggota.setBounds(163, 208, 219, 20);
		contentPane.add(text_HpAnggota);
		text_HpAnggota.setColumns(10);
		
		JButton buttonBrowse = new JButton("Browse ...");
		buttonBrowse.setBounds(163, 237, 89, 23);
		contentPane.add(buttonBrowse);
		buttonBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BROWSEIMAGEActionPerformed(e);		
			}
		});
		
		JButton buttonSave = new JButton("Tambah");
		buttonSave.setBounds(163, 303, 89, 23);
		contentPane.add(buttonSave);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CREATEActionPerformed(e);		
			}
		});		
		
		JButton buttonUpdate = new JButton("Update");
		buttonUpdate.setBounds(262, 303, 89, 23);
		contentPane.add(buttonUpdate);
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPDATEActionPerformed(e);		
			}
		});
		
		JButton buttonSearch = new JButton("Cari");
		buttonSearch.setBounds(392, 29, 89, 23);
		contentPane.add(buttonSearch);
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SEARCHActionPerformed(e);		
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(502, 33, 196, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lbl_Image.setBounds(10, 11, 176, 191);
		panel.add(lbl_Image);
		
		JButton buttonDelete = new JButton("Hapus");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DELETEActionPerformed(e);
			}
		});
		buttonDelete.setBounds(361, 303, 89, 23);
		contentPane.add(buttonDelete);
		
		JButton buttonClear = new JButton("Clear");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		buttonClear.setBounds(460, 303, 89, 23);
		contentPane.add(buttonClear);
		
		JLabel lblYyyymmdd = new JLabel("( yyyy-mm-dd )");
		lblYyyymmdd.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblYyyymmdd.setBounds(306, 123, 98, 14);
		contentPane.add(lblYyyymmdd);
	}
	
	
	
	 public ImageIcon ResizeImage(String ImagePath, byte[] pic)
	    {
	        ImageIcon MyImage = null;
	        if(ImagePath != null)
	        {
	           MyImage = new ImageIcon(ImagePath);
	        }else
	        {
	            MyImage = new ImageIcon(pic);
	        }
	        Image img = MyImage.getImage();
	        Image newImg = img.getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(newImg);
	        return image;
	    }
	    
	
	private void BROWSEIMAGEActionPerformed(java.awt.event.ActionEvent evt) {                                           

	        // browse image
	        
	        JFileChooser file = new JFileChooser();
	        file.setCurrentDirectory(new File(System.getProperty("user.home")));
	        //filter the files
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	        file.addChoosableFileFilter(filter);
	        int result = file.showSaveDialog(null);
	        //if the user click on save in Jfilechooser
	        if(result == JFileChooser.APPROVE_OPTION){
	            
	            File selectedFile = file.getSelectedFile();
	            String path = selectedFile.getAbsolutePath();
	            lbl_Image.setIcon(ResizeImage(path,null));
	            
	            imgPath = path;
	        }
	        //if the user click on save in Jfilechooser

	        else if(result == JFileChooser.CANCEL_OPTION){
	            System.out.println("No File Select");
	        }

	    }       
	
	private void SEARCHActionPerformed(java.awt.event.ActionEvent evt) {
	
		KoneksiDatabase koneksi = new KoneksiDatabase();		
		Connection mysql= koneksi.getConnection();
		try {
			Statement stmt = mysql.createStatement();
			String Cari_noAnggota = text_NoAnggota.getText();
			
			String sql = "SELECT * FROM anggota WHERE nomor_anggota='"+Cari_noAnggota+"'";
			ResultSet res = stmt.executeQuery(sql);
			
			if(res.next()) {
				text_NamaAnggota.setText(res.getString("nama_anggota"));
				text_AlamatAnggota.setText(res.getString("alamat_anggota"));
				text_TempatLahirAnggota.setText(res.getString("tempat_lahir_anggota"));
				text_TglLahirAnggota.setText(res.getString("tgl_lahir_anggota"));
				text_HpAnggota.setText(res.getString("nohp_anggota"));
				lbl_Image.setIcon(ResizeImage(null, res.getBytes("foto_ktp")));
			}else {
				JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
				Clear();
			}
			mysql.close();
			
		}
		catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}			
	}

	private void CREATEActionPerformed(java.awt.event.ActionEvent evt) {

		KoneksiDatabase koneksi = new KoneksiDatabase();		
		Connection mysql= koneksi.getConnection();
		try {

			String sql = "INSERT INTO anggota (`nomor_anggota`, `nama_anggota`, `alamat_anggota`, `tempat_lahir_anggota`, `tgl_lahir_anggota`, `nohp_anggota`, `foto_ktp`) VALUES ('"
					+text_NoAnggota.getText()+"', '"
					+text_NamaAnggota.getText()+"', '"
					+text_AlamatAnggota.getText() +"', '"
					+text_TempatLahirAnggota.getText()+"', '"
					+text_TglLahirAnggota.getText() +"', '"
					+text_HpAnggota.getText()+"',?);";
			
			PreparedStatement  stmt = (PreparedStatement) mysql.prepareStatement(sql);
								
			InputStream img = new FileInputStream(new File(imgPath));
			
			stmt.setBlob(1,img);

			int i = stmt.executeUpdate();
			
			if(i==1) {
				JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Disimpan");
			}
			Clear();
			mysql.close();
		}
		catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}			
	}

	private void UPDATEActionPerformed(java.awt.event.ActionEvent evt) {
		
		KoneksiDatabase koneksi = new KoneksiDatabase();		
		Connection mysql= koneksi.getConnection();
		try {
			String Cari_noAnggota = text_NoAnggota.getText();
			
			String sql = "UPDATE anggota SET nomor_anggota='"
					+text_NoAnggota.getText()+ "', nama_anggota='"
					+text_NamaAnggota.getText()+ "', alamat_anggota='"
					+text_AlamatAnggota.getText()+ "', tempat_lahir_anggota='"
					+text_TempatLahirAnggota.getText()+ "', tgl_lahir_anggota='"
					+text_TglLahirAnggota.getText()+ "', nohp_anggota='"
					+text_HpAnggota.getText()+ "', foto_ktp=? WHERE nomor_anggota='"+Cari_noAnggota+"'";
			//System.out.println(sql);
			
			PreparedStatement  stmt ;
			
			if(imgPath != null) {
				stmt = (PreparedStatement) mysql.prepareStatement(sql);
				InputStream img = new FileInputStream(new File(imgPath));			
				stmt.setBlob(1,img);
			}else {
				
				 sql = "UPDATE anggota SET nomor_anggota='"
						+text_NoAnggota.getText()+ "', nama_anggota='"
						+text_NamaAnggota.getText()+ "', alamat_anggota='"
						+text_AlamatAnggota.getText()+ "', tempat_lahir_anggota='"
						+text_TempatLahirAnggota.getText()+ "', tgl_lahir_anggota='"
						+text_TglLahirAnggota.getText()+ "', nohp_anggota='"
						+text_HpAnggota.getText()+ "' WHERE nomor_anggota='"+Cari_noAnggota+"'";
				  stmt = (PreparedStatement) mysql.prepareStatement(sql);
			}

			int i = stmt.executeUpdate();
			
			if(i==1) {
				JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Diubah");
			}else {
				
			}
			
			Clear();
			mysql.close();
			
		}
		catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}	
	}
		
	private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {
		KoneksiDatabase koneksi = new KoneksiDatabase();		
		Connection mysql= koneksi.getConnection();
		try {
			Statement stmt = mysql.createStatement();
			String Cari_noAnggota = text_NoAnggota.getText();
			
			String sql = "DELETE FROM anggota WHERE nomor_anggota='"+Cari_noAnggota+"'";
			int i = stmt.executeUpdate(sql);
			
			if(i==1) {
				JOptionPane.showMessageDialog(null,"Data Anggota berhasil dihapus !");
			}else {
				JOptionPane.showMessageDialog(null,"Tidak ada data yang berhasil dihapus!");
			}
			
			Clear();
			mysql.close();
			
		}
		catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}	
	

	}

}