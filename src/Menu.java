import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Menu extends JFrame implements ActionListener{
	
	Connect db = new Connect();
	
	JLabel kodeLabel, namaLabel, hargaLabel, stokLabel;
	JTextField kodeField, namaField, hargaField, stokField;
	
	JButton addButton, updateButton, deleteButton;
	
	JTable menuTable;
	DefaultTableModel dtm;
	JScrollPane jsp;
	
	Vector<String>columnName = new Vector<>();
	Vector<Vector<Object>>data = new Vector<Vector<Object>>();
	
	
	
	
	public Menu() {
		setFrame();
		getData();
		
		//kode
		kodeLabel = new JLabel("Kode :");
		kodeLabel.setBounds(20,50,150,30);
		this.add(kodeLabel);
		
		kodeField = new JTextField();
		kodeField.setBounds(100,50,200,30);
		this.add(kodeField);
		
		//nama
		namaLabel = new JLabel("Nama :");
		namaLabel.setBounds(20,100,150,30);
		this.add(namaLabel);
		
		namaField = new JTextField();
		namaField.setBounds(100,100,200,30);
		this.add(namaField);
		
		//harga
		hargaLabel = new JLabel("Harga :");
		hargaLabel.setBounds(20,150,150,30);
		this.add(hargaLabel);
		
		hargaField = new JTextField();
		hargaField.setBounds(100,150,200,30);
		this.add(hargaField);
		
		//stok
		stokLabel = new JLabel("Stok :");
		stokLabel.setBounds(20,200,150,30);
		this.add(stokLabel);
		
		stokField = new JTextField();
		stokField.setBounds(100,200,200,30);
		this.add(stokField);
		
		//button
		addButton = new JButton("Add");
		addButton.setBounds(20,240,100,30);
		addButton.addActionListener(this);
		this.add(addButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(190,240,100,30);
		deleteButton.addActionListener(this);
		this.add(deleteButton);
		
		updateButton = new JButton("Update");
		updateButton.setBounds(360,240,100,30);
		updateButton.addActionListener(this);
		this.add(updateButton);
		
		//table
		columnName.add("ID");
		columnName.add("Kode");
		columnName.add("Nama");
		columnName.add("Harga");
		columnName.add("Stok");
		
		dtm = new DefaultTableModel(data, columnName);
		menuTable = new JTable(dtm);
		jsp = new JScrollPane(menuTable);
		jsp.setBounds(40,280,400,200);
		this.add(jsp);
	}
	
	public void setFrame() {
		this.setVisible(true);
		this.setSize(500,500);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Menu");
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == addButton) {
			Random rand = new Random();
			int id = rand.nextInt(1000);
			
			String kode = kodeField.getText();
			String nama = namaField.getText();
			String harga = hargaField.getText();
			String stok = stokField.getText();
			
			db.insertMenu(id, kode, nama, harga, stok);
			
			Object row[] = {id,kode,nama,harga,stok};
			dtm.addRow(row);
			
			JOptionPane.showMessageDialog(this, "New Menu Inserted!");
			
			
		}else if(e.getSource() == deleteButton) {
			int index = menuTable.getSelectedRow();
			
			String id = String.valueOf(dtm.getValueAt(index, 0));
			
			db.deleteMenu(id);
			
			JOptionPane.showMessageDialog(this, "Menu Deleted!");
			dtm.removeRow(index);
		}else if(e.getSource() == updateButton) {
			int index = menuTable.getSelectedRow();
			String id =  String.valueOf(dtm.getValueAt(index, 0));
			String kode = kodeField.getText();
			String nama = namaField.getText();
			String harga = hargaField.getText();
			String stok = stokField.getText();
			
			db.updateMahasiswa(id, kode, nama, harga, stok);
			
			JOptionPane.showMessageDialog(this, "Menu In ID " + id + " is updated!!");
			dtm.setValueAt(kode, index, 1);
			dtm.setValueAt(nama, index, 2);
			dtm.setValueAt(harga, index, 3);
			dtm.setValueAt(stok, index, 3);
		}
	}
	
	public void getData() {
		db.rs = db.getMenu();
		
		try {
			while(db.rs.next()) {
				Vector<Object>newRow = new Vector<>();
				newRow.add(db.rs.getString("id"));
				newRow.add(db.rs.getString("kode"));
				newRow.add(db.rs.getString("nama"));
				newRow.add(db.rs.getString("harga"));
				newRow.add(db.rs.getString("stok"));
				
				data.add(newRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
