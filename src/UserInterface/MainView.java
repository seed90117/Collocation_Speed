package UserInterface;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import IO.LoadFile;
import IO.WriteExcel;
import Values.Data;
import Values.Item;

public class MainView extends JFrame {
	
	/*
	 * M10456012
	 * Kevin Yen
	 * kelly10056040@gmail.com
	 */
	private static final long serialVersionUID = 1L;
	private boolean isLoad = false;
	private String dataName = "";
	
	//*****宣告介面*****//
	Container cp = this.getContentPane();
	
	//*****宣告元件*****//
	JLabel clustertionLabel = new JLabel("Clustertion:");
	JLabel dataSetLabel = new JLabel("File Name: ");
	
	JTextField clustertionTextField = new JTextField("3");
	
	JButton loadFileButton = new JButton("Load File");
	JButton startButton = new JButton("Starts Algorithm");
	JButton exportButton = new JButton("Export Excel");
	
	JFileChooser open = new JFileChooser();
	
	MainView()
	{
		//*****設定介面*****//
		this.setSize(350, 300);
		this.setLayout(null);
		this.setTitle("Collocation Speed");
		
		//*****設定元件位置*****//
		clustertionLabel.setBounds(30, 30, 100, 30);
		clustertionTextField.setBounds(150, 30, 100, 30);
		
		loadFileButton.setBounds(30, 80, 250, 40);
		startButton.setBounds(30, 130, 250, 40);
		exportButton.setBounds(30, 180, 250, 40);
		dataSetLabel.setBounds(30, 230, 300, 30);
		
		cp.add(clustertionLabel);
		cp.add(clustertionTextField);
		cp.add(startButton);
		cp.add(loadFileButton);
		cp.add(dataSetLabel);
		cp.add(exportButton);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		loadFileButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LoadFile loadFile = new LoadFile();
				String fileName = loadFile.loadfile(open);
				if (!fileName.equals("")) {
					dataName = fileName;
				}
				dataSetLabel.setText(dataName);
				if (!dataSetLabel.getText().equals("File Name: ")) {
					isLoad = true;
				}
			}});
		
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isLoad) {
					
				}
			}});
		exportButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					testMethod();
					dataSetLabel.setText("Export Excel file success.");
				} catch (Exception exception) {
					dataSetLabel.setText("Error pleaes cell manager.");
				}
			}});
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainView();
	}

	private void testMethod() {
		WriteExcel writeExcel = new WriteExcel();
		Data data = Data.getInstance();
		writeExcel.createNewExcel("test");
		writeExcel.createNewSheet("test", 0);
		for (int r=0;r<data.getRow();r++) {
			for (int c=0;c<data.getColumn();c++) {
				Item item = data.getPersonData(r, c);
				writeExcel.setCellContent(c, r, item.value);
			}
		}
		writeExcel.writeData();
		writeExcel.closeWorkbook();
	}
}
