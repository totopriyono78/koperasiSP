package com.koperasi.simpanpinjam;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import javax.swing.JTable;

public class ListAnggota extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableAnggota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListAnggota frame = new ListAnggota();
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
	public ListAnggota() {
		setTitle("Daftar Anggota");	
		String[] header_table = new String[] {"Foto", "No Anggota","Nama","Alamat","Tempat Lahir","Tgl Lahir","No Tlp"};
		
		KoneksiDatabase koneksi = new KoneksiDatabase();		
		Connection mysql= koneksi.getConnection();
	
		try {
			Statement stmt = mysql.createStatement();
			String sql="SELECT foto_ktp,nomor_anggota, nama_anggota, alamat_anggota, tempat_lahir_anggota, tgl_lahir_anggota, nohp_anggota FROM anggota";
			ResultSet res = stmt.executeQuery(sql);		
			
			
			DefaultTableModel model = new DefaultTableModel(null,header_table){
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public Class<?> getColumnClass(int column) {
	                if (column==0) return ImageIcon.class;
	                return Object.class;
	            }
			};
	        
					
			Object[]dataTable = new Object[7];
			
			res.beforeFirst();
			while(res.next()) {
				ImageIcon imgIcon = new ImageIcon(res.getBytes("foto_ktp"));
				Image img = imgIcon.getImage();
				Image newimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon newimgIcon = new ImageIcon(newimg);
				
				dataTable[0]= newimgIcon;
				dataTable[1]=res.getString("nomor_anggota");
				dataTable[2]=res.getString("nama_anggota");
				dataTable[3]=res.getString("alamat_anggota");
				dataTable[4]=res.getString("tempat_lahir_anggota");
				dataTable[5]=res.getString("tgl_lahir_anggota");
				dataTable[6]=res.getString("nohp_anggota");
				//x++;
				model.addRow(dataTable);
			}
			

		    tableAnggota = new JTable();
		    tableAnggota.setModel(model);
		   // tableAnggota.tableChanged(
		    tableAnggota.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		    tableAnggota.setRowHeight(110);
		    tableAnggota.getColumnModel().getColumn(0).setPreferredWidth(110);

		    tableAnggota.setVisible(true);
			

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(843,464);
			getContentPane().setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane(tableAnggota);
			scrollPane.setBounds(29, 59, 788, 331);
			
			getContentPane().add(scrollPane);
			setLocationRelativeTo(null);
			setVisible(true);
			
			stmt.close();
			res.close();
		}
		catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

		
	}
}
