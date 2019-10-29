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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pinjaman extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNoAnggota;
	private JTextField textFieldNamaAnggota;
	private JTextField textFieldTenor;
	private JLabel lblIdAnggota;
	private String id_pinjaman;
	private JPanel panel;
	private JScrollPane scrollPane;
	private String id_Anggota;

	
	
	private JTable tablePinjaman;

	KoneksiDatabase koneksi = new KoneksiDatabase();		
	Connection mysql= koneksi.getConnection();
	private JTextField textFieldJumlah;
	private JTextField textFieldBunga;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pinjaman frame = new Pinjaman();
		frame.setVisible(true);
		
	}

	
	/*****************************************************
	 * Create the frame layout.
	 * 
	 *****************************************************/

	public Pinjaman() {
		
		setTitle("Input Pinjaman");
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
		panel.setBounds(10, 238, 747, 243);
		contentPane.add(panel);
		panel.setLayout(null);		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 728, 247);
		tablePinjaman = new JTable();
		ListPinjaman();
		scrollPane.setViewportView(tablePinjaman);		
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
						id_Anggota = res.getString("id_anggota");
						//lblIdAnggota.setText(id_Anggota);
						
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
		
		JLabel lblNama = new JLabel("Nama Anggota");
		lblNama.setBounds(24, 77, 95, 14);
		contentPane.add(lblNama);
		
		JLabel lblJumlah = new JLabel("Jumlah Pinjaman");
		lblJumlah.setBounds(24, 102, 110, 14);
		contentPane.add(lblJumlah);
		
		JLabel lblTenor = new JLabel("Tenor");
		lblTenor.setBounds(24, 130, 48, 14);
		contentPane.add(lblTenor);
		
		textFieldNamaAnggota = new JTextField();
		textFieldNamaAnggota.setBounds(135, 75, 207, 20);
		contentPane.add(textFieldNamaAnggota);
		textFieldNamaAnggota.setColumns(10);
		
		textFieldTenor = new JTextField();
		textFieldTenor.setBounds(135, 128, 89, 20);
		contentPane.add(textFieldTenor);
		textFieldTenor.setColumns(10);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setBounds(135, 195, 89, 23);
		contentPane.add(btnTambah);
		
		// clear form
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldTenor.setText("");
				textFieldNoAnggota.setText("");
				textFieldNamaAnggota.setText("");
				textFieldJumlah.setText("");
				textFieldBunga.setText("");
			}
		});
		btnClear.setBounds(336, 195, 89, 23);
		contentPane.add(btnClear);

		
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
		btnHapus.setBounds(234, 195, 89, 23);
		contentPane.add(btnHapus);
		
		textFieldJumlah = new JTextField();
		textFieldJumlah.setBounds(135, 99, 207, 20);
		contentPane.add(textFieldJumlah);
		textFieldJumlah.setColumns(10);
		
		JLabel lblBunga = new JLabel("Bunga");
		lblBunga.setBounds(24, 155, 48, 14);
		contentPane.add(lblBunga);
		
		textFieldBunga = new JTextField();
		textFieldBunga.setBounds(135, 152, 88, 20);
		contentPane.add(textFieldBunga);
		textFieldBunga.setColumns(10);
		
		JLabel lblBulan = new JLabel("Bulan");
		lblBulan.setBounds(236, 130, 48, 14);
		contentPane.add(lblBulan);
		
		JLabel label = new JLabel("%");
		label.setBounds(234, 155, 48, 14);
		contentPane.add(label);

		
	}
	
	/*****************************************************
	 * Delete Data handler
	 * 
	 *****************************************************/
	
	public void DeleteDataSimpanan() {
		try {
			
			Statement stmt = mysql.createStatement();
			String sqldelete="DELETE FROM pinjaman WHERE id_pinjaman=" + id_pinjaman;
			int i = stmt.executeUpdate(sqldelete);
			
			if(i==1) {
				JOptionPane.showMessageDialog(null,"Data Anggota berhasil dihapus !");
				ListPinjaman();
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
	
		String id_Petugas;
		try {
			
			Statement stmt = mysql.createStatement();
			String sqlIdPetugas="SELECT * FROM petugas WHERE is_login=1";
			ResultSet resIdPetugas = stmt.executeQuery(sqlIdPetugas);
			
			if (resIdPetugas.next()) {
				id_Petugas= resIdPetugas.getString("id_petugas");
				
			}else {
				id_Petugas="1";
			}
			
			if ( id_Anggota.length()>0 ) {
								
				float jumlah_pinjaman = Float.valueOf(textFieldJumlah.getText()).floatValue();
				float bunga_pinjaman = Float.valueOf(textFieldBunga.getText()).floatValue();
				Integer tenor_pinjaman = Integer.parseInt(textFieldTenor.getText());
				
				float total_pinjaman_bunga = (tenor_pinjaman/12)*(bunga_pinjaman/100)*jumlah_pinjaman + jumlah_pinjaman;
				float angsuran_per_bulan = total_pinjaman_bunga/tenor_pinjaman;
				
				String sqlInsert="INSERT INTO pinjaman (tgl_pinjaman, jumlah_pinjaman, tenor_pinjaman, bunga_pinjaman, status_pinjaman,total_pinjaman_bunga, jumlah_angsuran,id_anggota, id_petugas, last_update) "
						+ "VALUE (NOW(),"+textFieldJumlah.getText()+","+textFieldTenor.getText()+","+ textFieldBunga.getText()+",0,"+total_pinjaman_bunga +","+angsuran_per_bulan+","+id_Anggota+","+id_Petugas+", NOW())";
								
				if(stmt.executeUpdate(sqlInsert)>0) {
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
					textFieldTenor.setText("");
					textFieldJumlah.setText("");
					textFieldBunga.setText("");
					textFieldNoAnggota.setText("");
					textFieldNamaAnggota.setText("");					
					ListPinjaman();
				
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

	public void ListPinjaman() {
		
		tablePinjaman.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tablePinjaman.getModel();
				int selectedRowIndex = tablePinjaman.getSelectedRow();
				textFieldNoAnggota.setText(model.getValueAt(selectedRowIndex, 2).toString());
				textFieldNamaAnggota.setText(model.getValueAt(selectedRowIndex, 3).toString());
				//lblIdAnggota.setText(model.getValueAt(selectedRowIndex,2).toString());
				textFieldJumlah.setText(model.getValueAt(selectedRowIndex, 4).toString());				
				textFieldTenor.setText(model.getValueAt(selectedRowIndex, 5).toString());
				textFieldBunga.setText(model.getValueAt(selectedRowIndex, 6).toString());				
				id_pinjaman = model.getValueAt(selectedRowIndex, 0).toString();
			}
		});

		Object[]dataTable = new Object[8];
		String[] header_table = new String[] {"No", "Tgl Transaksi","No Anggota","Nama","Pinjaman(Rp)","Tenor","Bunga(%)","Petugas"};
		DefaultTableModel model = new DefaultTableModel(null,header_table);

		try {
			
			Statement stmtPinjaman = mysql.createStatement();
			String sqlPinjaman="SELECT id_pinjaman, tgl_pinjaman, s.id_anggota as id, nomor_anggota, nama_anggota, "
					+ "FORMAT(jumlah_pinjaman,0) as jumlah_pinjaman, tenor_pinjaman, bunga_pinjaman, nama_petugas "
					+ "FROM pinjaman AS s "
					+ "JOIN anggota AS a ON a.id_anggota= s.id_anggota "
					+ "JOIN petugas AS p ON p.id_petugas=s.id_petugas order by s.id_pinjaman desc";
			ResultSet resSimpanan = stmtPinjaman.executeQuery(sqlPinjaman);		
			resSimpanan.beforeFirst();			
			
			while(resSimpanan.next()) {				
				dataTable[0]= Integer.parseInt(resSimpanan.getString("id_pinjaman"));
				dataTable[1]=resSimpanan.getString("tgl_pinjaman");
				//dataTable[2]=resSimpanan.getString("id");				
				dataTable[2]=resSimpanan.getString("nomor_anggota");
				dataTable[3]=resSimpanan.getString("nama_anggota");
				dataTable[4]=resSimpanan.getString("jumlah_pinjaman");
				dataTable[5]=resSimpanan.getString("tenor_pinjaman");
				dataTable[6]=resSimpanan.getString("bunga_pinjaman");				
				dataTable[7]=resSimpanan.getString("nama_petugas");
				model.addRow(dataTable);
			}
		}catch (Exception ex ) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

		tablePinjaman.setModel(model);		
		tablePinjaman.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablePinjaman.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablePinjaman.getColumnModel().getColumn(2).setPreferredWidth(70);
		tablePinjaman.getColumnModel().getColumn(3).setPreferredWidth(140);
		tablePinjaman.getColumnModel().getColumn(4).setPreferredWidth(80);
		tablePinjaman.getColumnModel().getColumn(5).setPreferredWidth(30);
		tablePinjaman.getColumnModel().getColumn(6).setPreferredWidth(40);
		tablePinjaman.getColumnModel().getColumn(7).setPreferredWidth(50);
				
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tablePinjaman.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tablePinjaman.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		tablePinjaman.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		tablePinjaman.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		
		tablePinjaman.setAutoCreateRowSorter(false);
		tablePinjaman.removeColumn(tablePinjaman.getColumnModel().getColumn(0));		
		tablePinjaman.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		tablePinjaman.setVisible(true);
		

	}
}