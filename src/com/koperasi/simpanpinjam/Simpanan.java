package com.koperasi.simpanpinjam;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Simpanan extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNoAnggota;
	private JTextField textFieldNamaAnggota;
	private JTextField textFieldNominal;
	private JLabel lblIdAnggota;
	private String id_simpanan;
	private JPanel panel;
	private JScrollPane scrollPane;
	private Map<String, String> mapJenisSimpanan = new HashMap<String, String>();
	

	private JTable tableSimpanan;
	JComboBox<String> comboBoxJenisSimpanan;

	KoneksiDatabase koneksi = new KoneksiDatabase();		
	Connection mysql= koneksi.getConnection();

	
	
	/*****************************************************
	 * Create the frame layout.
	 * 
	 *****************************************************/

	public Simpanan() {
		
		setTitle("Input Simpanan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 776, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//agar frame berada ditengah
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		panel = new JPanel();
		panel.setBounds(10, 212, 747, 269);
		contentPane.add(panel);
		panel.setLayout(null);		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 728, 247);
		tableSimpanan = new JTable();
		ListSimpanan();
		scrollPane.setViewportView(tableSimpanan);		
		panel.add(scrollPane);
	
		JLabel lblNoAnggota = new JLabel("No Anggota");
		lblNoAnggota.setBounds(27, 31, 88, 14);
		contentPane.add(lblNoAnggota);
		
		lblIdAnggota = new JLabel();
		contentPane.add(lblIdAnggota);
		
		textFieldNoAnggota = new JTextField();
		textFieldNoAnggota.setBounds(135, 29, 110, 20);
		contentPane.add(textFieldNoAnggota);
		textFieldNoAnggota.setColumns(10);
		
		JButton btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					Statement stmt = mysql.createStatement();
					String sql="SELECT * FROM anggota WHERE nomor_anggota='"+textFieldNoAnggota.getText()+"'";
					ResultSet res = stmt.executeQuery(sql);	
					if ( res.next()) {
						textFieldNamaAnggota.setText(res.getString("nama_anggota"));
						lblIdAnggota.setText(res.getString("id_anggota"));
					}else {
						JOptionPane.showMessageDialog(null, "Data Anggota Tidak Ditemukan !");
						textFieldNamaAnggota.setText("");
						textFieldNoAnggota.setText("");
					}					
				}
				catch ( Exception ex ){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		btnCari.setBounds(253, 28, 89, 23);
		contentPane.add(btnCari);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setBounds(24, 77, 48, 14);
		contentPane.add(lblNama);
		
		JLabel lblJenisSimpanan = new JLabel("Jenis Simpanan");
		lblJenisSimpanan.setBounds(24, 102, 110, 14);
		contentPane.add(lblJenisSimpanan);
		
		JLabel lblNominal = new JLabel("Jumlah (Rp)");
		lblNominal.setBounds(24, 130, 76, 14);
		contentPane.add(lblNominal);
		
		textFieldNamaAnggota = new JTextField();
		textFieldNamaAnggota.setBounds(135, 75, 207, 20);
		contentPane.add(textFieldNamaAnggota);
		textFieldNamaAnggota.setColumns(10);
		
		textFieldNominal = new JTextField();
		textFieldNominal.setBounds(135, 128, 207, 20);
		contentPane.add(textFieldNominal);
		textFieldNominal.setColumns(10);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setBounds(135, 167, 89, 23);
		contentPane.add(btnTambah);
		
		// clear form
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNominal.setText("");
				textFieldNoAnggota.setText("");
				textFieldNamaAnggota.setText("");
			}
		});
		btnClear.setBounds(336, 167, 89, 23);
		contentPane.add(btnClear);
		comboBoxJenisSimpanan = new JComboBox<String>();
		comboBoxJenisSimpanan.setBounds(135, 98, 207, 22);
		try {	
			Statement stmt = mysql.createStatement();
			String sql="SELECT * FROM jenis_simpanan";
			ResultSet res = stmt.executeQuery(sql);	
			 while(res.next()){
				 comboBoxJenisSimpanan.addItem(res.getString("nama_jenis_simpanan"));
				 mapJenisSimpanan.put(res.getString("nama_jenis_simpanan"),res.getString("id_jenis_simpanan"));
				 mapJenisSimpanan.put(res.getString("nama_jenis_simpanan"),res.getString("id_jenis_simpanan"));	
			
			 }
			 res.close();
		}
		catch ( Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());					
		}
		
		
		// add data handler
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDataSimpanan();
			}
		});
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteDataSimpanan();
			}
		});
		btnHapus.setBounds(234, 167, 89, 23);

		contentPane.add(comboBoxJenisSimpanan);
		contentPane.add(btnHapus);

		
	}
	
	/*****************************************************
	 * Delete Data handler
	 * 
	 *****************************************************/
	
	public void DeleteDataSimpanan() {
		try {
			
			Statement stmt = mysql.createStatement();
			String sqldelete="DELETE FROM simpanan WHERE id_simpanan=" + id_simpanan;
			int i = stmt.executeUpdate(sqldelete);
			
			if(i==1) {
				JOptionPane.showMessageDialog(null,"Data Anggota berhasil dihapus !");
				ListSimpanan();
			}else {
				JOptionPane.showMessageDialog(null,"Tidak ada data yang berhasil dihapus!");
			}
			
		}
		catch ( Exception ex ){				
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}

	}
	
	
	/*****************************************************
	 * Add Data handler
	 * 
	 *****************************************************/

	public void AddDataSimpanan() {
	
		String id_petugas;
		try {
			
			Statement stmt = mysql.createStatement();
			String sqlIdPetugas="SELECT * FROM petugas WHERE is_login=1";
			ResultSet resIdPetugas = stmt.executeQuery(sqlIdPetugas);
			
			if (resIdPetugas.next()) {
				id_petugas= resIdPetugas.getString("id_petugas");
				
			}else {
				id_petugas="1";
			}
			
			if ( lblIdAnggota.getText().length()>0 ) {
				
				String jenis_simpanan= mapJenisSimpanan.get(comboBoxJenisSimpanan.getItemAt(comboBoxJenisSimpanan.getSelectedIndex()));						
				String sqlInsert="INSERT INTO simpanan (tgl_simpanan, nilai_simpanan, jenis_simpanan, id_anggota, id_petugas) "
						+ "VALUE (NOW(),"+textFieldNominal.getText()+","+jenis_simpanan+" ,"+lblIdAnggota.getText()+", "+id_petugas+") ";
																
				if(stmt.executeUpdate(sqlInsert)>0) {
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
					textFieldNominal.setText("");
					textFieldNoAnggota.setText("");
					textFieldNamaAnggota.setText("");
					ListSimpanan();
				
				}else {
					JOptionPane.showMessageDialog(null, "Gagal menyimpan");
				}
			}
		}
		catch ( Exception ex ){
		
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}

	}
	

	/*****************************************************
	 * List Data handler
	 * 
	 *****************************************************/

	public void ListSimpanan() {
		
		tableSimpanan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableSimpanan.getModel();
				int selectedRowIndex = tableSimpanan.getSelectedRow();
				textFieldNoAnggota.setText(model.getValueAt(selectedRowIndex, 3).toString());
				textFieldNamaAnggota.setText(model.getValueAt(selectedRowIndex, 4).toString());
				lblIdAnggota.setText(model.getValueAt(selectedRowIndex,2).toString());
				int selIndex = Integer.parseInt(mapJenisSimpanan.get(model.getValueAt(selectedRowIndex, 5).toString()));
				comboBoxJenisSimpanan.setSelectedIndex(selIndex-1);
				textFieldNominal.setText(model.getValueAt(selectedRowIndex, 6).toString());
				id_simpanan = model.getValueAt(selectedRowIndex, 0).toString();

			}
		});

		Object[]dataTable = new Object[8];
		String[] header_table = new String[] {"No", "Tgl Transaksi","ID","No Anggota","Nama","Jenis Simpanan","Jumlah(Rp)","Petugas"};
		DefaultTableModel model = new DefaultTableModel(null,header_table);

		try {
			
			Statement stmtSimpanan = mysql.createStatement();
			String sqlSimpanan="SELECT id_simpanan, tgl_simpanan, s.id_anggota as id, nomor_anggota, nama_anggota, nama_jenis_simpanan, FORMAT(s.nilai_simpanan,0) as nilai_simpanan, nama_petugas "
					+ "FROM simpanan AS s "
					+ "JOIN anggota AS a ON a.id_anggota= s.id_anggota "
					+ "JOIN jenis_simpanan AS j ON j.id_jenis_simpanan=s.jenis_simpanan "
					+ "JOIN petugas AS p ON p.id_petugas=s.id_petugas order by s.id_simpanan desc";
						
			ResultSet resSimpanan = stmtSimpanan.executeQuery(sqlSimpanan);		
			resSimpanan.beforeFirst();			
			
			while(resSimpanan.next()) {				
				dataTable[0]= Integer.parseInt(resSimpanan.getString("id_simpanan"));
				dataTable[1]=resSimpanan.getString("tgl_simpanan");
				dataTable[2]=resSimpanan.getString("id");				
				dataTable[3]=resSimpanan.getString("nomor_anggota");
				dataTable[4]=resSimpanan.getString("nama_anggota");
				dataTable[5]=resSimpanan.getString("nama_jenis_simpanan");
				dataTable[6]=resSimpanan.getString("nilai_simpanan");
				dataTable[7]=resSimpanan.getString("nama_petugas");
				
				model.addRow(dataTable);
			}
		}catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

		tableSimpanan.setModel(model);
		
		tableSimpanan.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableSimpanan.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableSimpanan.getColumnModel().getColumn(2).setPreferredWidth(10);
		tableSimpanan.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableSimpanan.getColumnModel().getColumn(5).setPreferredWidth(120);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		tableSimpanan.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tableSimpanan.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tableSimpanan.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		tableSimpanan.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		
		tableSimpanan.setAutoCreateRowSorter(false);
		tableSimpanan.removeColumn(tableSimpanan.getColumnModel().getColumn(0));						
		tableSimpanan.removeColumn(tableSimpanan.getColumnModel().getColumn(1));						

		tableSimpanan.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		tableSimpanan.setVisible(true);
		

	}
}