import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

//remember uncomment this
//import FinalGUI.JButtonStateController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

//import classes.ImportGUI.JButtonStateController;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class FinalGUI {
	//==========ImportPanel============//
	JFrame frame;// REMEMBER CHANGE BACK TO private JFrame frame;
	private JTextField userPathTextField;
	JTextArea shipTextArea;
	JTextArea errorTextField;
	JTextArea containerTextArea;
	JFileChooser chooser;
	String choosertitle;
	Document document;
	ImportXML folderPath = new ImportXML();

	//==========ExportPanel============//
	private JTextField userPathTextField1;
	private JTextField EmailtextField;
	ExportXML exportxml = new ExportXML();
	SendAttachmentInEmail email = new SendAttachmentInEmail();
	static ArrayList<Ship> exportArray = new ArrayList<Ship>();

	//===========Scheduling Panel===============//
	JTextArea MinNoOfShipsTextArea;
	JTextArea lowestPriceTextArea;
	JTextArea optimumTextArea;
	JTextArea shipsNotFullTextArea;
	//====== End of Scheduling Panel ==========//

	//============Update Panel=====================//

	//================ Container Tab ==============//
	JComboBox comboBoxCust;
	private JTextField custNameTextField;
	private JTextField custAgeTextField;
	private JTextField custCompanyTextField;
	private JTextField createCustIDTextField;
	private JTextField createShipNameTextField;

	//=================== Ships tab =============//
	JComboBox comboBoxShip;
	private JTextField shipOwnerTextField;
	private JTextField shipValueTextField;
	private JTextField shipCompanyTextField;
	//============== End of Update Panel===========//
	JTextField fieldName;
	ArrayList<JTextField> fieldNameArr = new ArrayList<JTextField>();
	ArrayList<JCheckBox> fieldCheckArr = new ArrayList<JCheckBox>();
	ArrayList<JTextField> fieldNumberArr = new ArrayList<JTextField>();
	ArrayList<JLabel> labelNumberArr = new ArrayList<JLabel>();
	ArrayList<JLabel> labelTypeArr = new ArrayList<JLabel>();

	ArrayList<JTextField> fieldMaxLoadArr = new ArrayList<JTextField>();
	ArrayList<JTextField> fieldPriceArr = new ArrayList<JTextField>();
	ArrayList<JLabel> labelMaxLoadArr = new ArrayList<JLabel>();
	ArrayList<JLabel> labelPriceArr = new ArrayList<JLabel>();
	ArrayList<JCheckBox> fieldCheckSArr = new ArrayList<JCheckBox>();
	String[]ComboBoxArrForContType = {"basic", "basic_special", "heavy", "heavy_special", "refrigerated", "refrigerated_special"};
	JLabel lblCreateCustId;//to set to  invisible
	JLabel lblShipType;
	JComboBox cComboBox;
	JLabel MaxLoad;
	JLabel Price;
	
	JLabel lblType;
	JTextField numberTextField;
	JLabel lblNumber;
	JCheckBox checkBoxes;
	private JTextField MaxLoadField;
	private JTextField PriceTextField;
	ArrayList<Ship> tempShipArrayList = new ArrayList<Ship>();
	ArrayList<Customer> tempCustArrayList = new ArrayList<Customer>();
	ArrayList<Container> tempContainerArrayList = new ArrayList<Container>();
	JTextField createNewTypeField;
	JPanel updateAndDisplayCust;
	private JTextField shipMaxLoad;
	private JTextField shipPrice;
	private JTextField TYpeCBBOX;
	private JTextField maxLoadTF;
	private JTextField PriceTF;
	ArrayList<JLabel> labelShipTypeArr = new ArrayList<JLabel>();
	ArrayList<JComboBox> comboBoxContArr = new ArrayList<JComboBox>();
	//============ Customer Buttons=================//
	JButton btnEditCust;
	JButton btnDeleteCust;
	JButton btnAddContainer;
	JButton btnDeleteCont;
	JButton btnUpdateFields;
	JButton btnCreateCust;
	JButton custCreateNew;
	
//===============Ship Buttons==========================//
	JButton btnCreateShip;
	JButton btnCreateContType;
	JButton btnDeleteContainer;
	JButton btnUpdateShips;
	JButton btnCreateNewShip;
	JButton btnEditShips;
	JButton btnDeleteShip;
	JLabel lblCreateShipName;
	private JTextField maxLoadTextField;
	private JTextField specialLoadTextField;
	private JTextField basicLoadTextField;
	private JTextField HeavyLoadTextField;
	private JTextField bPriceTextField;
	private JTextField hPriceTextField;
	private JTextField refrigLoadTextField;
	private JTextField rPricetextField;
	int y = 30;
	boolean isEdit = false;
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalGUI window = new FinalGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public FinalGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 845, 697);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 829, 659);
		frame.getContentPane().add(tabbedPane);

		final StringBuilder sm = new StringBuilder();

		JPanel ImportPanel = new JPanel();
		tabbedPane.addTab("Import", null, ImportPanel, null);
		ImportPanel.setLayout(null);

		JLabel label = new JLabel("Import Ship and Container Folder (.XML Files)");
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(227, 35, 437, 22);
		ImportPanel.add(label);

		JLabel label_1 = new JLabel("Enter Folder Path");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(122, 109, 122, 14);
		ImportPanel.add(label_1);

		userPathTextField = new JTextField();
		userPathTextField.setColumns(10);
		userPathTextField.setBounds(241, 106, 322, 20);
		ImportPanel.add(userPathTextField);

		JButton filebutton = new JButton("...");
		//button.addActionListener(this);
		filebutton.setBounds(573, 107, 45, 20);
		ImportPanel.add(filebutton);
		filebutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//
				// disable the "All files" option.
				//
				chooser.setAcceptAllFileFilterUsed(false);
				//    
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					//get the folder path
					//System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
					//System.out.println(filename);
					userPathTextField.setText(chooser.getSelectedFile().getPath());
				}
				else 
				{
					System.out.println("No Selection ");
				}
			}
		});

		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnImport.setEnabled(false);
		btnImport.setBounds(628, 105, 84, 25);
		document = userPathTextField.getDocument();
		document.addDocumentListener(new JButtonStateController(btnImport));
		ImportPanel.add(btnImport);
		btnImport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
				File folder = new File(userPathTextField.getText());
				File[] listOfFiles = folder.listFiles();
				Path path = Paths.get(userPathTextField.getText());
				if (Files.exists(path,LinkOption.NOFOLLOW_LINKS)) {
					//For file in listOfFiles
					for (File file : listOfFiles){					
						//If file is a file (.xml)
						if (file.isFile()){
							//Parse content into textarea
							String filename = file.toString();	
							System.out.println(filename);
							if(filename.endsWith(".xml")||filename.endsWith(".XML")){
								try {
									folderPath.fileParser(filename);
								} 
								catch( IllegalArgumentException e2){
									System.out.println(e2.toString());
									sm.append(e2.toString()+ "\nError Record at "+ filename);
									//JOptionPane.showMessageDialog(null, "An Error has occured with a record. Please check for errors in XML file.", "ERROR", 1);
								}catch (Exception e1) {
									// TODO Auto-generated catch block
									System.out.println(e1.toString());
									sm.append("\n" + e1.toString()+ "\nError Record at "+ filename);								//JOptionPane.showMessageDialog(null, "An Error has occured with a record. Please check for errors in XML file.", "ERROR", 1);
								}
							}						   
						}
						//Or if its a directory
						else if (file.isDirectory()){
							File[] dlistOfFiles = file.listFiles();

							//For dfiles in dlistOfFiles
							for(File dfile : dlistOfFiles){
								//Parse content into textarea
								String filename = dfile.toString();	
								try {
									folderPath.fileParser(filename);
								}catch( IllegalArgumentException e2){
									System.out.println(e2.toString());
									sm.append("\n" + e2.toString()+ "\nError Record at "+ filename);								//JOptionPane.showMessageDialog(null, "An Error has occured with a record. Please check for errors in XML file.", "ERROR", 1);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									System.out.println(e1.toString());
									sm.append("\n" + e1.toString()+ "\nError Record at "+ filename);								//JOptionPane.showMessageDialog(null, "An Error has occured with a record. Please check for errors in XML file.", "ERROR", 1);
									e1.printStackTrace();
								}
							}					     
						}					   
					}
					showContent(sm);
					JOptionPane.showMessageDialog(null, "Import Sucessful!", "Success", 1);
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid Path", "ERROR", 1);
				}
			}

		});


		JSeparator separator = new JSeparator();
		separator.setBounds(120, 168, 599, 2);
		ImportPanel.add(separator);

		JScrollPane containerScrollPane = new JScrollPane();
		containerScrollPane.setBounds(28, 200, 347, 268);
		ImportPanel.add(containerScrollPane);
		containerScrollPane.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createTitledBorder(
								null, "Container Information:",
								javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
								javax.swing.border.TitledBorder.DEFAULT_POSITION,
								new java.awt.Font("Verdana", 1, 11)
								),
						javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
						)
				);
		containerTextArea = new JTextArea();
		containerScrollPane.setViewportView(containerTextArea);
		containerTextArea.setEditable(false);

		JScrollPane shippScrollPane = new JScrollPane();
		shippScrollPane.setBounds(440, 204, 347, 264);
		ImportPanel.add(shippScrollPane);
		shippScrollPane.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createTitledBorder(
								null, "Ship Information:",
								javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
								javax.swing.border.TitledBorder.DEFAULT_POSITION,
								new java.awt.Font("Verdana", 1, 11)
								),
						javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
						)
				);

		shipTextArea = new JTextArea();
		shippScrollPane.setViewportView(shipTextArea);
		shipTextArea.setEditable(false);



		JScrollPane errorScrollPane = new JScrollPane();
		errorScrollPane.setBounds(28, 489, 749, 118);
		ImportPanel.add(errorScrollPane);
		errorScrollPane.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createTitledBorder(
								null, "Error Handling:",
								javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
								javax.swing.border.TitledBorder.DEFAULT_POSITION,
								new java.awt.Font("Verdana", 1, 11)
								),
						javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
						)
				);

		errorTextField = new JTextArea();
		errorScrollPane.setViewportView(errorTextField);
		errorTextField.setEditable(false);
//=====================================END OF PUT IN====================================================================//
		//========================================================End of Create Panel===========================================================================
		
		//===========================Import/Update Panel ====================================//
		JTabbedPane updateAndDisplay = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Import/ Update", null, updateAndDisplay, null);

		//=========================== Import/ Update Panel (CUSTOMER)========================/
		int posY = 30;
		
		updateAndDisplayCust = new JPanel();
		updateAndDisplay.addTab("Containers", null, updateAndDisplayCust, null);
		updateAndDisplayCust.setLayout(null);

		JLabel lblCustomerID = new JLabel("Customer ID:");
		lblCustomerID.setBounds(70, 38, 88, 14);
		updateAndDisplayCust.add(lblCustomerID);

		comboBoxCust = new JComboBox();
		comboBoxCust.setBounds(150, 35, 131, 20);
		updateAndDisplayCust.add(comboBoxCust);
		comboBoxCust.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(comboBoxCust.getSelectedItem()!= null){
					btnEditCust.setVisible(false);
					btnDeleteCust.setVisible(false);
				}
			}
		});

		JLabel lblCustInfo = new JLabel("Customer Information");
		lblCustInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCustInfo.setBounds(70, 121, 144, 14);
		updateAndDisplayCust.add(lblCustInfo);

		JLabel lblCustName = new JLabel("Name:");
		lblCustName.setBounds(84, 146, 46, 14);
		updateAndDisplayCust.add(lblCustName);

		JLabel lblCustAge = new JLabel("Age:");
		lblCustAge.setBounds(84, 171, 46, 14);
		updateAndDisplayCust.add(lblCustAge);

		JLabel lblCustCompany = new JLabel("Company:");
		lblCustCompany.setBounds(84, 195, 65, 17);
		updateAndDisplayCust.add(lblCustCompany);

		JLabel lblContainerReq = new JLabel("Container Requirements");
		lblContainerReq.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContainerReq.setBounds(74, 223, 144, 14);
		updateAndDisplayCust.add(lblContainerReq);
		
		//==================================TASK 2 DISPLAY CUST========================//
		
		custNameTextField = new JTextField();
		custNameTextField.setColumns(10);
		custNameTextField.setBounds(153, 143, 258, 20);
		updateAndDisplayCust.add(custNameTextField);

		custAgeTextField = new JTextField();
		custAgeTextField.setColumns(10);
		custAgeTextField.setBounds(153, 168, 258, 20);
		updateAndDisplayCust.add(custAgeTextField);

		custCompanyTextField = new JTextField();
		custCompanyTextField.setColumns(10);
		custCompanyTextField.setBounds(153, 193, 258, 20);
		updateAndDisplayCust.add(custCompanyTextField);
		
		custNameTextField.setEditable(false);
		custAgeTextField.setEditable(false);
		custCompanyTextField.setEditable(false);
		
		createCustIDTextField = new JTextField();
		createCustIDTextField.setBounds(150, 87, 86, 20);
		updateAndDisplayCust.add(createCustIDTextField);
		createCustIDTextField.setColumns(10);
		createCustIDTextField.setVisible(false);

		lblCreateCustId = new JLabel("Cust ID:");
		lblCreateCustId.setBounds(74, 90, 46, 14);
		updateAndDisplayCust.add(lblCreateCustId);
		lblCreateCustId.setVisible(false);

		//=== Display Selected Customer Information ===
		int y = 30;
		JButton btnShowInfo = new JButton("Show Info");
		btnShowInfo.setBounds(322, 34, 102, 23);
		updateAndDisplayCust.add(btnShowInfo);
		btnShowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cleanContainerPanel(updateAndDisplayCust);
				btnEditCust.setVisible(true);
				btnDeleteCust.setVisible(true);
				btnCreateCust.setVisible(false);			
				custCreateNew.setVisible(true);
				btnAddContainer.setVisible(false);
				btnDeleteCont.setVisible(false);
				btnUpdateFields.setVisible(false);
				
				lblCreateCustId.setVisible(false);
				createCustIDTextField.setVisible(false);
				custNameTextField.setEditable(false);
				custAgeTextField.setEditable(false);
				custCompanyTextField.setEditable(false);
				ArrayList<Customer> tempCustArrayList = new ArrayList<Customer>();
				ArrayList<Container> tempContainerArrayList = new ArrayList<Container>();
				tempCustArrayList = folderPath.getCustArrayList();

				//Reset the JPanel and remove the existing dynamically field
				if(fieldNameArr.size() > 0){
					cleanContainerPanel(updateAndDisplayCust);
				}

				String tempCustID = (String) comboBoxCust.getSelectedItem();
				for(int j = 0; j < tempCustArrayList.size(); j++){

					Customer tempCust = tempCustArrayList.get(j);
					if(tempCustID == tempCust.getID()){
						custNameTextField.setText(tempCust.getName());
						custAgeTextField.setText(String.valueOf(tempCust.getAge()));
						custCompanyTextField.setText(tempCust.getCompany());
						
						tempContainerArrayList = tempCust.getContainerList();
						for(int i = 0; i < tempContainerArrayList.size(); i++){
							Container tempCon = tempContainerArrayList.get(i);

							fieldName = new JTextField();
							fieldName.setColumns(10);					
							fieldName.setBounds(106, 262 + (y * i), 150, 20);

							if(tempCon.getSpecialProperty() != null){
								fieldName.setText(tempCon.getType() + "_Special");
							}else{
								fieldName.setText(tempCon.getType());
							}
							fieldNameArr.add(fieldName);
							updateAndDisplayCust.add(fieldName);

							//Create label for type
							lblType = new JLabel("Type:");
							lblType.setBounds(70, 262 + (y * i), 46, 14);
							labelTypeArr.add(lblType);
							updateAndDisplayCust.add(lblType);
							
							//Create textField for Number
							numberTextField = new JTextField();
							numberTextField.setColumns(10);
							numberTextField.setBounds(337, 262 + (y * i), 50, 20);
							if(tempCon.getSpecialProperty() != null){
								numberTextField.setText(String.valueOf(tempCon.getSpecialNumber()));
							}
							else{
								numberTextField.setText(String.valueOf(tempCon.getNumber()));
							}
							fieldNumberArr.add(numberTextField);
							updateAndDisplayCust.add(numberTextField);

							//Create label for Number
							lblNumber = new JLabel("Number:");
							lblNumber.setBounds(277, 262 + (y * i), 65, 14);
							labelNumberArr.add(lblNumber);
							updateAndDisplayCust.add(lblNumber);

							checkBoxes = new JCheckBox("");
							checkBoxes.setBounds(410, 262 + (y * i), 30, 23);
							fieldCheckArr.add(checkBoxes);
							updateAndDisplayCust.add(checkBoxes);

							fieldName.setEditable(false);
							numberTextField.setEditable(false);

						}

					}				
				}
				updateAndDisplayCust.revalidate();
				updateAndDisplayCust.repaint();
			}
		});

		//=== Enable the field for creating new Customer ===
		custCreateNew = new JButton("Create New");
		custCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateXML createNewCust = new CreateXML();
				
				btnCreateCust.setVisible(true);
				btnAddContainer.setVisible(true);
				btnDeleteCont.setVisible(true);
				btnUpdateFields.setVisible(false);
				btnEditCust.setVisible(false);
				btnDeleteCust.setVisible(false);
				comboBoxCust.setSelectedItem(null);
				
				if(fieldNameArr.size() > 0){
					cleanContainerPanel(updateAndDisplayCust);
				}

				createCustIDTextField.setVisible(true);
				lblCreateCustId.setVisible(true);

				custNameTextField.setEditable(true);
				custAgeTextField.setEditable(true);
				custCompanyTextField.setEditable(true);
				custNameTextField.setText("");
				custAgeTextField.setText("");
				custCompanyTextField.setText("");				
				
//				cComboBox = new JComboBox(ComboBoxArrForContType);
//				comboBoxContArr.add(cComboBox);
//				cComboBox.setBounds(106, 262, 150, 20);
//				updateAndDisplayCust.add(cComboBox);

				fieldName = new JTextField();
				fieldName.setColumns(10);	
				fieldName.setBounds(106, 262, 150, 20);
				fieldNameArr.add(fieldName);
				updateAndDisplayCust.add(fieldName);
				
				lblType = new JLabel("Type:");				
				lblType.setBounds(70, 262, 46, 14);
				labelTypeArr.add(lblType);
				updateAndDisplayCust.add(lblType);

				numberTextField = new JTextField();
				numberTextField.setColumns(10);
				numberTextField.setBounds(337, 262, 70, 20);
				fieldNumberArr.add(numberTextField);
				updateAndDisplayCust.add(numberTextField);

				lblNumber = new JLabel("Number:");
				lblNumber.setBounds(277, 262, 65, 14);
				labelNumberArr.add(lblNumber);
				updateAndDisplayCust.add(lblNumber);

				checkBoxes = new JCheckBox("");
				checkBoxes.setBounds(410, 262, 30, 23);
				fieldCheckArr.add(checkBoxes);
				updateAndDisplayCust.add(checkBoxes);

				updateAndDisplayCust.revalidate();
				updateAndDisplayCust.repaint();
			}
		});
		custCreateNew.setBounds(454, 34, 101, 23);
		updateAndDisplayCust.add(custCreateNew);
		
		//=== Creating new Customer ===
		btnCreateCust = new JButton("Create");
		btnCreateCust.setVisible(false);
		btnCreateCust.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String custID, name, company, containertype; 
				int age, containerNumber;
				ArrayList<Container> tempContainerArr = new ArrayList<Container>();
				
				try{
					custID = createCustIDTextField.getText().toString();
					name = custNameTextField.getText().trim().toString();
					company = custCompanyTextField.getText().trim().toString();
					age = Integer.parseInt(custAgeTextField.getText().trim().toString());
					
					for(int i = 0; i < fieldNameArr.size(); i++){
						containertype = fieldNameArr.get(i).getText().trim().toString();
						containerNumber = Integer.parseInt(fieldNumberArr.get(i).getText().trim().toString());		
						tempContainerArr = createContainerObject(tempContainerArr, containertype, containerNumber);
					}
					//SaveXML saveUpdates = new SaveXML();
					//saveUpdates.UpdateCust(custID, name, age, company, tempContainerArr, userPathTextField.getText());
					
					boolean result = insertNewCustomer(custID, name, age, company, tempContainerArr);
					
					if(result == true){
						btnCreateCust.setVisible(false);
						createCustIDTextField.setVisible(false);
						lblCreateCustId.setVisible(true);
						createCustIDTextField.setText("");
						
						comboBoxCust.removeAllItems();
						bindCustomerID();
						JOptionPane.showMessageDialog(null, "Successfully Create New Customer: " + custID, "Success", 1);
					}else{
						JOptionPane.showMessageDialog(null, "Error occurs when creating Customer: " + custID, "Warning", 1);
					}
		
				}catch(NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "Enter valid Age!", "Warning", 1);
				}catch(Exception msg){
					JOptionPane.showMessageDialog(null, msg.getMessage() , "Warning", 1);
				}
				
			}		
		});
		btnCreateCust.setBounds(456, 325, 102, 23);
		updateAndDisplayCust.add(btnCreateCust);

		//=== Enable the field to be editable for user to update ===
		btnEditCust = new JButton("Edit");
		btnEditCust.setVisible(false);
		btnEditCust.setBounds(454, 142, 102, 23);
		updateAndDisplayCust.add(btnEditCust);
		btnEditCust.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				btnAddContainer.setVisible(true);
				btnDeleteCont.setVisible(true);
				btnUpdateFields.setVisible(true);
				btnCreateCust.setVisible(false);
				custCreateNew.setVisible(false);
				
				custNameTextField.setEditable(true);
				custAgeTextField.setEditable(true);
				custCompanyTextField.setEditable(true);

				for(int i = 0; i < fieldNameArr.size(); i++){
					fieldNameArr.get(i).setEditable(true);
					fieldNumberArr.get(i).setEditable(true);					
				}
				
				
			}
		});
		
		//=== Update Customer Information ===
		btnUpdateFields = new JButton("Update");
		btnUpdateFields.setVisible(false);
		btnUpdateFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String custID, name, company, containertype; 
				int age, containerNumber;
				ArrayList<Container> tempContainerArr = new ArrayList<Container>();
				
				try{
					custID = comboBoxCust.getSelectedItem().toString();
					name = custNameTextField.getText().trim().toString();
					company = custCompanyTextField.getText().trim().toString();
					age = Integer.parseInt(custAgeTextField.getText().trim().toString());
					
					for(int i = 0; i < fieldNameArr.size(); i++){
						containertype = fieldNameArr.get(i).getText().trim().toString();
						containerNumber = Integer.parseInt(fieldNumberArr.get(i).getText().trim().toString());		
						tempContainerArr = updateContainerInfo(custID, containertype, containerNumber);
					}
					//SaveXML saveUpdates = new SaveXML();
					//saveUpdates.UpdateCust(custID, name, age, company, tempContainerArr, userPathTextField.getText());
					
					
					boolean result = updateCustomerInfo(custID, name, age, company, tempContainerArr);
					
					if(result == true){
						JOptionPane.showMessageDialog(null, "Successfully Updated Customer: " + custID, "Success", 1);
					}else{
						JOptionPane.showMessageDialog(null, "Error occurs when updating Customer: " + custID, "Warning", 1);
					}
		
				}catch(NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "Enter valid Age!", "Warning", 1);
				}catch(Exception msg){
					JOptionPane.showMessageDialog(null, msg.getMessage() , "Warning", 1);
				}
			}
		});
		btnUpdateFields.setBounds(454, 325, 102, 23);
		updateAndDisplayCust.add(btnUpdateFields);

		//=== Delete the selected customer for the array list ===
		btnDeleteCust = new JButton("Delete Customer");
		btnDeleteCust.setVisible(false);
		btnDeleteCust.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				
				String tempCustID = (String) comboBoxCust.getSelectedItem();
				boolean result = deleteCustomer(tempCustID);
				
				if(result == true){
					custNameTextField.setText("");
					custAgeTextField.setText("");
					custCompanyTextField.setText("");
					cleanContainerPanel(updateAndDisplayCust);
					comboBoxCust.removeAllItems();
					bindCustomerID();
					JOptionPane.showMessageDialog(null, "Successfully Deleted Customer ID: " + tempCustID, "Success", 1);
				} else {
					JOptionPane.showMessageDialog(null, "Error occurs when deleting Customer ID: " + tempCustID, "Warning", 1);
				}
			}
		});
		btnDeleteCust.setBounds(454, 171, 130, 23);
		updateAndDisplayCust.add(btnDeleteCust);

		//=== Add an new container field to the panel ===

		btnAddContainer = new JButton("Add Container");	
		btnAddContainer.setVisible(false);
		btnAddContainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldNameArr.size() >= 6){
					JOptionPane.showMessageDialog(null, "Cannot have more than 6 Containers Type", "Error", 1);
				}
				else{
					fieldName = new JTextField();
					fieldName.setColumns(10);	
					fieldName.setBounds(106, (262 + (y * fieldNameArr.size())), 150, 20);
					fieldNameArr.add(fieldName);
					updateAndDisplayCust.add(fieldName);
					
//					cComboBox = new JComboBox(ComboBoxArrForContType);
//					comboBoxContArr.add(cComboBox);
//					cComboBox.setBounds(106, (240 + (y * comboBoxContArr.size())), 150, 20);
//					updateAndDisplayCust.add(cComboBox);
					
					lblType = new JLabel("Type:");
					lblType.setBounds(70, (262 + (y * labelTypeArr.size())), 46, 14);
					labelTypeArr.add(lblType);
					updateAndDisplayCust.add(lblType);
					
					numberTextField = new JTextField();
					numberTextField.setColumns(10);
					numberTextField.setBounds(337, (262 + (y * fieldNumberArr.size())), 70, 20);
					fieldNumberArr.add(numberTextField);
					updateAndDisplayCust.add(numberTextField);

					lblNumber = new JLabel("Number:");
					lblNumber.setBounds(277, (262 + (y * labelNumberArr.size())), 65, 14);
					labelNumberArr.add(lblNumber);
					updateAndDisplayCust.add(lblNumber);
					
					checkBoxes = new JCheckBox("");
					checkBoxes.setBounds(410, (262 + (y * fieldCheckArr.size())), 30, 23);
					fieldCheckArr.add(checkBoxes);
					updateAndDisplayCust.add(checkBoxes);	
					
					updateAndDisplayCust.revalidate();
					updateAndDisplayCust.repaint();
				}		
			}
		});
		btnAddContainer.setBounds(454, 257, 133, 23);
		updateAndDisplayCust.add(btnAddContainer);
		
		//=== Remove the container field or delete the selected container form the customer ===
		btnDeleteCont = new JButton(" Delete Container");
		btnDeleteCont.setVisible(false);
		btnDeleteCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int size = fieldCheckArr.size() - 1;
				do{
					if(fieldCheckArr.get(size).isSelected()){
						System.out.println("Hit:" +size);
						JTextField tempFieldName = fieldNameArr.get(size);
						fieldNameArr.remove(tempFieldName);	
						updateAndDisplayCust.remove(tempFieldName);
						
						JLabel tempLabelType = labelTypeArr.get(size);
						labelTypeArr.remove(tempLabelType);
						updateAndDisplayCust.remove(tempLabelType);
						
						JTextField tempFieldNumber = fieldNumberArr.get(size);
						fieldNumberArr.remove(tempFieldNumber);
						updateAndDisplayCust.remove(tempFieldNumber);
						
						JLabel tempNumber = labelNumberArr.get(size);
						labelNumberArr.remove(tempNumber);
						updateAndDisplayCust.remove(tempNumber);
						
						JCheckBox tempFieldCheck = fieldCheckArr.get(size);
						fieldCheckArr.remove(tempFieldCheck);
						updateAndDisplayCust.remove(tempFieldCheck);
						
						size = fieldCheckArr.size() - 1;
					}
					else{
						size = size - 1;
					}
					
				}while(size != -1);
				
				updateAndDisplayCust.revalidate();
				updateAndDisplayCust.repaint();	
				System.out.println("End" + fieldCheckArr.size());
			}
		});
		btnDeleteCont.setBounds(454, 291, 131, 23);
		updateAndDisplayCust.add(btnDeleteCont);
		
		
		//========================= End of Import/Update Panel (CUSTOMER) ===============================//

		//========================== Import/Update Panel (SHIPS) ========================================//
		JPanel updateAndDisplayShips = new JPanel();
		updateAndDisplay.addTab("Ships", null, updateAndDisplayShips, null);
		updateAndDisplayShips.setLayout(null);

		JLabel lblShipName = new JLabel("Ship Name:");
		lblShipName.setBounds(67, 29, 88, 14);
		updateAndDisplayShips.add(lblShipName);

		comboBoxShip = new JComboBox();
		comboBoxShip.setBounds(146, 26, 164, 20);
		updateAndDisplayShips.add(comboBoxShip);
		comboBoxShip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				btnEditShips.setVisible(false);
				btnDeleteShip.setVisible(false);
			}
		});

		//===============================TASk 2 DISPLAY SHIPS==========================/
		JLabel lblShipInfo = new JLabel("Ship Information");
		lblShipInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShipInfo.setBounds(67, 110, 118, 14);
		updateAndDisplayShips.add(lblShipInfo);

		JLabel lblShipOwner = new JLabel("Owner:");
		lblShipOwner.setBounds(77, 138, 46, 14);
		updateAndDisplayShips.add(lblShipOwner);

		JLabel lblShipValue = new JLabel("Value:");
		lblShipValue.setBounds(77, 163, 46, 14);
		updateAndDisplayShips.add(lblShipValue);

		JLabel lblCompany = new JLabel("Company:");
		lblCompany.setBounds(77, 187, 65, 17);
		updateAndDisplayShips.add(lblCompany);

		JLabel label_34 = new JLabel("Container Type");
		label_34.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_34.setBounds(67, 215, 103, 14);
		updateAndDisplayShips.add(label_34);
		
		shipOwnerTextField = new JTextField();
		shipOwnerTextField.setColumns(10);
		shipOwnerTextField.setBounds(146, 135, 258, 20);
		updateAndDisplayShips.add(shipOwnerTextField);

		shipValueTextField = new JTextField();
		shipValueTextField.setColumns(10);
		shipValueTextField.setBounds(146, 160, 258, 20);
		updateAndDisplayShips.add(shipValueTextField);

		shipCompanyTextField = new JTextField();
		shipCompanyTextField.setColumns(10);
		shipCompanyTextField.setBounds(146, 185, 258, 20);
		updateAndDisplayShips.add(shipCompanyTextField);

		shipOwnerTextField.setEditable(false);
		shipValueTextField.setEditable(false);
		shipCompanyTextField.setEditable(false);
		
		lblCreateShipName = new JLabel("Ship Name:");
		lblCreateShipName.setBounds(67, 75, 65, 14);
		updateAndDisplayShips.add(lblCreateShipName);
		lblCreateShipName.setVisible(false);
		
		createShipNameTextField = new JTextField();
		createShipNameTextField.setBounds(143, 72, 130, 20);
		updateAndDisplayShips.add(createShipNameTextField);
		createShipNameTextField.setColumns(10);
		createShipNameTextField.setVisible(false);
		
		JLabel lblMaxLoad = new JLabel("Max Load:");
		lblMaxLoad.setBounds(77, 243, 65, 14);
		updateAndDisplayShips.add(lblMaxLoad);
		
		maxLoadTextField = new JTextField();
		maxLoadTextField.setBounds(184, 240, 65, 20);
		updateAndDisplayShips.add(maxLoadTextField);
		maxLoadTextField.setEditable(false);
		maxLoadTextField.setColumns(10);
		
		JLabel lblSpecialLoad = new JLabel("Special Load:");
		lblSpecialLoad.setBounds(261, 243, 88, 14);
		updateAndDisplayShips.add(lblSpecialLoad);
		
		specialLoadTextField = new JTextField();
		specialLoadTextField.setColumns(10);
		specialLoadTextField.setBounds(339, 240, 65, 20);
		specialLoadTextField.setEditable(false);
		updateAndDisplayShips.add(specialLoadTextField);
		
		basicLoadTextField = new JTextField();
		basicLoadTextField.setBounds(184, 271, 65, 20);
		updateAndDisplayShips.add(basicLoadTextField);
		basicLoadTextField.setEditable(false);
		basicLoadTextField.setColumns(10);
		
		HeavyLoadTextField = new JTextField();
		HeavyLoadTextField.setColumns(10);
		HeavyLoadTextField.setBounds(185, 302, 64, 20);
		HeavyLoadTextField.setEditable(false);
		updateAndDisplayShips.add(HeavyLoadTextField);
		
		bPriceTextField = new JTextField();
		bPriceTextField.setColumns(10);
		bPriceTextField.setBounds(339, 268, 65, 20);
		bPriceTextField.setEditable(false);
		updateAndDisplayShips.add(bPriceTextField);
		
		hPriceTextField = new JTextField();
		hPriceTextField.setColumns(10);
		hPriceTextField.setBounds(340, 302, 64, 20);
		hPriceTextField.setEditable(false);
		updateAndDisplayShips.add(hPriceTextField);
		
		refrigLoadTextField = new JTextField();
		refrigLoadTextField.setColumns(10);
		refrigLoadTextField.setBounds(185, 334, 64, 20);
		refrigLoadTextField.setEditable(false);
		updateAndDisplayShips.add(refrigLoadTextField);
		
		rPricetextField = new JTextField();
		rPricetextField.setColumns(10);
		rPricetextField.setBounds(339, 334, 64, 20);
		rPricetextField.setEditable(false);
		updateAndDisplayShips.add(rPricetextField);
		
		JLabel lblNewLabel = new JLabel("Basic Load:");
		lblNewLabel.setBounds(77, 274, 108, 14);
		updateAndDisplayShips.add(lblNewLabel);
		
		JLabel lblHeavyLoad = new JLabel("Heavy Load:");
		lblHeavyLoad.setBounds(77, 305, 108, 14);
		updateAndDisplayShips.add(lblHeavyLoad);
		
		JLabel lblRefrigeratedLoad = new JLabel("Refrigerated Load:");
		lblRefrigeratedLoad.setBounds(77, 337, 93, 14);
		updateAndDisplayShips.add(lblRefrigeratedLoad);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(261, 274, 65, 14);
		updateAndDisplayShips.add(lblPrice);
		
		JLabel lblPrice_1 = new JLabel("Price:");
		lblPrice_1.setBounds(261, 305, 65, 14);
		updateAndDisplayShips.add(lblPrice_1);
		
		JLabel lblPrice_2 = new JLabel("Price:");
		lblPrice_2.setBounds(261, 337, 65, 14);
		updateAndDisplayShips.add(lblPrice_2);

		//=== Display Selected Customer Information ===
		JButton btnShowInfoShip = new JButton("Show Info");
		btnShowInfoShip.setBounds(392, 25, 93, 23);
		updateAndDisplayShips.add(btnShowInfoShip);
		btnShowInfoShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				//set Editable
				shipOwnerTextField.setEditable(false);
				shipValueTextField.setEditable(false);
				shipCompanyTextField.setEditable(false);	
				basicLoadTextField.setEditable(false);
				bPriceTextField.setEditable(false);
				HeavyLoadTextField.setEditable(false);
				hPriceTextField.setEditable(false);
				refrigLoadTextField.setEditable(false);
				rPricetextField.setEditable(false);
				
				btnUpdateShips.setVisible(false);
				btnCreateShip.setVisible(false);
				createShipNameTextField.setVisible(false);
				lblCreateShipName.setVisible(false);
				btnEditShips.setVisible(true);
				btnCreateNewShip.setVisible(true);
				btnDeleteShip.setVisible(true);
				
				maxLoadTextField.setEditable(false);
				specialLoadTextField.setEditable(false);
				
				tempShipArrayList = folderPath.getShipArrayList();

				int y = 286;
				for (int i = 0; i < tempShipArrayList.size(); i++){
					String tempShipName = (String) comboBoxShip.getSelectedItem();
					Ship tempShip = tempShipArrayList.get(i);
					if(tempShipName == tempShip.getName())
					{
						shipOwnerTextField.setText(tempShip.getOwner());
						shipValueTextField.setText(String.valueOf(tempShip.getValue()));
						shipCompanyTextField.setText(tempShip.getCompany());
						maxLoadTextField.setText(String.valueOf(tempShip.getMaxLoad()));
						specialLoadTextField.setText(String.valueOf(tempShip.getSpecialLoad()));
						basicLoadTextField.setText(String.valueOf(tempShip.getBasicLoad()));
						bPriceTextField.setText(String.valueOf(tempShip.getBasicPrice()));
						HeavyLoadTextField.setText(String.valueOf(tempShip.getHeavyLoad()));
						hPriceTextField.setText(String.valueOf(tempShip.getHeavyPrice()));
						refrigLoadTextField.setText(String.valueOf(tempShip.getRefrigLoad()));
						rPricetextField.setText(String.valueOf(tempShip.getRefrigPrice()));
					}

				}
				//Create MaxLoad
				updateAndDisplayShips.revalidate();
				updateAndDisplayShips.repaint();

			}
		});

		//=== Enable the field for creating of new ship ===
		btnCreateNewShip = new JButton("Create New");
		btnCreateNewShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateShip.setVisible(true);
				btnEditShips.setVisible(false);
				btnDeleteShip.setVisible(false);
				btnUpdateShips.setVisible(false);
				maxLoadTextField.setText("");
				specialLoadTextField.setText("");
				maxLoadTextField.setEditable(true);
				specialLoadTextField.setEditable(true);
				createShipNameTextField.setEditable(true);
				
				createShipNameTextField.setText("");
				shipOwnerTextField.setText("");
				shipValueTextField.setText("");
				shipCompanyTextField.setText("");
				basicLoadTextField.setText("");
				bPriceTextField.setText("");
				HeavyLoadTextField.setText("");
				hPriceTextField.setText("");
				refrigLoadTextField.setText("");
				rPricetextField.setText("");
				
				createShipNameTextField.setVisible(true);
				lblCreateShipName.setVisible(true);
				shipOwnerTextField.setEditable(true);
				shipValueTextField.setEditable(true);
				shipCompanyTextField.setEditable(true);	
				basicLoadTextField.setEditable(true);
				bPriceTextField.setEditable(true);
				HeavyLoadTextField.setEditable(true);
				hPriceTextField.setEditable(true);
				refrigLoadTextField.setEditable(true);
				rPricetextField.setEditable(true);
				
				updateAndDisplayShips.revalidate();
				updateAndDisplayShips.repaint();
			}
		});
		btnCreateNewShip.setBounds(505, 25, 103, 23);
		updateAndDisplayShips.add(btnCreateNewShip);
		
		//=== Create new Ship ===
		btnCreateShip = new JButton("Create");
		btnCreateShip.setBounds(452, 333, 89, 23);
		btnCreateShip.setVisible(false);
		btnCreateShip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tempShipName, owner, company;
				int basicMaxLoad, heavyMaxLoad, refrigeratedMaxLoad, basicPrice, heavyPrice, refrigeratedPrice, special, allMaxLoad, shipValue;
				
				try{
					tempShipName = createShipNameTextField.getText().trim().toString();
					owner = shipOwnerTextField.getText().trim().toString();
					shipValue = (int) Double.parseDouble(shipValueTextField.getText().trim().toString());
					company = shipCompanyTextField.getText().trim().toString();
					allMaxLoad = Integer.parseInt(maxLoadTextField.getText().trim().toString());
					special = Integer.parseInt(specialLoadTextField.getText().trim().toString());
					basicMaxLoad = Integer.parseInt(basicLoadTextField.getText().trim().toString());
					basicPrice = Integer.parseInt(bPriceTextField.getText().trim().toString());
					heavyMaxLoad = Integer.parseInt(HeavyLoadTextField.getText().trim().toString());
					heavyPrice = Integer.parseInt(hPriceTextField.getText().trim().toString());
					refrigeratedMaxLoad =Integer.parseInt(refrigLoadTextField.getText().trim().toString());
					refrigeratedPrice = Integer.parseInt(rPricetextField.getText().trim().toString());
					
					boolean result = insertNewShip(tempShipName, owner, shipValue, company, basicMaxLoad, basicPrice, heavyMaxLoad, heavyPrice,
							refrigeratedMaxLoad, refrigeratedPrice, special, allMaxLoad);
					
					if(result == true){
						//Successfully Created
						btnCreateShip.setVisible(false);
						
						maxLoadTextField.setText("");
						specialLoadTextField.setText("");
						basicLoadTextField.setText("");
						bPriceTextField.setText("");
						HeavyLoadTextField.setText("");
						hPriceTextField.setText("");
						refrigLoadTextField.setText("");
						rPricetextField.setText("");
						shipOwnerTextField.setText("");
						shipValueTextField.setText("");
						shipCompanyTextField.setText("");
						createShipNameTextField.setText("");
						createShipNameTextField.setVisible(false);
						lblShipName.setVisible(false);
						
						comboBoxShip.removeAllItems();
						bindShipName();
						JOptionPane.showMessageDialog(null, "Successfully Create New Ship: " + tempShipName, "Success", 1);
					}else{
						JOptionPane.showMessageDialog(null, "Error occurs when Creating New Ship: " + tempShipName, "Warning", 1);
					}
					
				}catch(NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "Enter valid Ship Value or Max Load/Price number!", "Warning", 1);
				}catch(Exception msg){
					JOptionPane.showMessageDialog(null, msg.getMessage() , "Warning", 1);
				}
			}
		});
		updateAndDisplayShips.add(btnCreateShip);
		
		//=== Enable the field for editing of Ship information ===
		btnEditShips = new JButton("Edit");
		btnEditShips.setVisible(false);
		btnEditShips.setBounds(452, 138, 93, 23);
		updateAndDisplayShips.add(btnEditShips);
		btnEditShips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateShips.setVisible(true);
				btnCreateShip.setVisible(false);
				
				maxLoadTextField.setEditable(true);
				specialLoadTextField.setEditable(true);
				basicLoadTextField.setEditable(true);
				bPriceTextField.setEditable(true);
				HeavyLoadTextField.setEditable(true);
				hPriceTextField.setEditable(true);
				refrigLoadTextField.setEditable(true);
				rPricetextField.setEditable(true);				
				
				shipOwnerTextField.setEditable(true);
				shipValueTextField.setEditable(true);
				shipCompanyTextField.setEditable(true);
			}
		});

		//=== Update the selected ship information ===
		btnUpdateShips = new JButton("Update");
		btnUpdateShips.setVisible(false);
		btnUpdateShips.setBounds(452, 333, 89, 23);
		updateAndDisplayShips.add(btnUpdateShips);
		btnUpdateShips.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				String tempShipName, owner, company;
				int basicMaxLoad, heavyMaxLoad, refrigeratedMaxLoad, basicPrice, heavyPrice, refrigeratedPrice, special, allMaxLoad, shipValue;
				
				try{
					tempShipName = comboBoxShip.getSelectedItem().toString();
					owner = shipOwnerTextField.getText().trim().toString();
					shipValue = (int) Double.parseDouble(shipValueTextField.getText().trim().toString());
					company = shipCompanyTextField.getText().trim().toString();	
					basicMaxLoad = Integer.parseInt(basicLoadTextField.getText().trim().toString());
					basicPrice = Integer.parseInt(bPriceTextField.getText().trim().toString());
					heavyMaxLoad = Integer.parseInt(HeavyLoadTextField.getText().trim().toString());
					heavyPrice = Integer.parseInt(hPriceTextField.getText().trim().toString());
					refrigeratedMaxLoad = Integer.parseInt(refrigLoadTextField.getText().trim().toString());
					refrigeratedPrice = Integer.parseInt(rPricetextField.getText().trim().toString());
					special = Integer.parseInt(specialLoadTextField.getText().trim().toString());
					allMaxLoad = Integer.parseInt(maxLoadTextField.getText().trim().toString());

					boolean result = updateShipInformation(tempShipName, owner, shipValue, company, basicMaxLoad, basicPrice, heavyMaxLoad, heavyPrice,
							refrigeratedMaxLoad, refrigeratedPrice, special, allMaxLoad);
					
					if(result == true){
						//Successfully Updated
						btnUpdateShips.setVisible(false);
						createShipNameTextField.setEditable(false);
						shipOwnerTextField.setEditable(false);
						shipValueTextField.setEditable(false);
						shipCompanyTextField.setEditable(false);
						basicLoadTextField.setEditable(false);
						bPriceTextField.setEditable(false);
						HeavyLoadTextField.setEditable(false);
						hPriceTextField.setEditable(false);
						refrigLoadTextField.setEditable(false);
						rPricetextField.setEditable(false);
						specialLoadTextField.setEditable(false);
						maxLoadTextField.setEditable(false);
						JOptionPane.showMessageDialog(null, "Successfully Updated Ship: " + tempShipName, "Success", 1);
					}
					else{
						JOptionPane.showMessageDialog(null, "Error occurs when updating Ship: " + tempShipName, "Warning", 1);
					}
				}catch(NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "Enter valid Ship Value or Max Load/Price number!", "Warning", 1);
				}catch(Exception msg){
					JOptionPane.showMessageDialog(null, msg.getMessage() , "Warning", 1);
				}
			}
		});

		//=== Delete the selected ship ===
		btnDeleteShip = new JButton("Delete Ship");
		btnDeleteShip.setVisible(false);
		btnDeleteShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tempShipName = comboBoxShip.getSelectedItem().toString();
				boolean result = deleteShip(tempShipName);
				
				if(result == true){
					createShipNameTextField.setText("");
					shipOwnerTextField.setText("");
					shipValueTextField.setText("");
					shipCompanyTextField.setText("");
					basicLoadTextField.setText("");
					bPriceTextField.setText("");
					HeavyLoadTextField.setText("");
					hPriceTextField.setText("");
					refrigLoadTextField.setText("");
					rPricetextField.setText("");
					specialLoadTextField.setText("");
					maxLoadTextField.setText("");
					
					createShipNameTextField.setEditable(false);
					shipOwnerTextField.setEditable(false);
					shipValueTextField.setEditable(false);
					shipCompanyTextField.setEditable(false);
					basicLoadTextField.setEditable(false);
					bPriceTextField.setEditable(false);
					HeavyLoadTextField.setEditable(false);
					hPriceTextField.setEditable(false);
					refrigLoadTextField.setEditable(false);
					specialLoadTextField.setEditable(false);
					maxLoadTextField.setEditable(false);
					rPricetextField.setEditable(false);
					
					comboBoxShip.removeAllItems();		
					bindShipName();
					JOptionPane.showMessageDialog(null, "Successfully Deleted Ship: " + tempShipName, "Success", 1);
				} else {
					JOptionPane.showMessageDialog(null, "Error occurs when deleting Ship: " + tempShipName, "Warning", 1);
				}	
			}
		});
		btnDeleteShip.setBounds(452, 167, 118, 23);
		updateAndDisplayShips.add(btnDeleteShip);

		//========================== Import/Update Panel (SHIPS) ========================================//
		//========================== End of Import/ Update Panel ===========================================//

		//====================================SCHEDULING TAB========================================================//
		JPanel SchedulingPanel = new JPanel();
		tabbedPane.addTab("Scheduling", null, SchedulingPanel, null);
		SchedulingPanel.setLayout(null);

		JCheckBox minNoOfShipsCheckBox = new JCheckBox("Minimum No. Of Ships");
		minNoOfShipsCheckBox.setBackground(SystemColor.control);
		minNoOfShipsCheckBox.setBounds(25, 46, 170, 23);
		SchedulingPanel.add(minNoOfShipsCheckBox);

		JCheckBox lowestPriceCheckBox = new JCheckBox("Lowest Price");
		lowestPriceCheckBox.setBackground(SystemColor.control);
		lowestPriceCheckBox.setBounds(25, 66, 128, 23);
		SchedulingPanel.add(lowestPriceCheckBox);

		JCheckBox optimumCheckBox = new JCheckBox("Optimum Solution Overall");
		optimumCheckBox.setBackground(SystemColor.control);
		optimumCheckBox.setBounds(25, 87, 184, 23);
		SchedulingPanel.add(optimumCheckBox);

		JCheckBox shipsNotFullCheckBox = new JCheckBox("Ships that are not full");
		shipsNotFullCheckBox.setBackground(SystemColor.control);
		shipsNotFullCheckBox.setBounds(25, 106, 184, 23);
		SchedulingPanel.add(shipsNotFullCheckBox);

		JButton displayButton = new JButton("Display");
		displayButton.setBounds(221, 106, 89, 23);
		SchedulingPanel.add(displayButton);
		
		//==========================TASK 3/4/5====================================//
		displayButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				MinNoOfShipsTextArea.setText("");
				lowestPriceTextArea.setText("");
				shipsNotFullTextArea.setText("");
				optimumTextArea.setText("");
				
				if(minNoOfShipsCheckBox.isSelected()){
					try {
						ArrayList<Ship> returnedList = Minimum.search(folderPath.getShipArrayList(),folderPath.getCustArrayList());
						StringBuilder cam = new StringBuilder();
						cam.append("Number of ships used: "+returnedList.size()+ "\n");
						cam.append("Ships to be used are: \n");
						for (int i=0;i<returnedList.size();i++){
		    			cam.append("=============================================================================\n");
		    			cam.append("Name: "+returnedList.get(i).getName()+"\n");
		    			cam.append("Basic: "+returnedList.get(i).getCurrentBasicLoad()+" / "+returnedList.get(i).getBasicLoad()+"\n");
		    			cam.append("Heavy: "+returnedList.get(i).getCurrentHeavyLoad()+" / "+returnedList.get(i).getHeavyLoad()+"\n");
		    			cam.append("Refri: "+returnedList.get(i).getCurrentRefrigLoad()+" / "+returnedList.get(i).getRefrigLoad()+"\n");
		    			cam.append("Special Basic: "+returnedList.get(i).getCurrentSpecialBasic()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("Special Heavy: "+returnedList.get(i).getCurrentSpecialHeavy()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("Special Refrig: "+returnedList.get(i).getCurrentSpecialRefrig()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("Special Total: "+returnedList.get(i).getCurrentSpecialTotal()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("MAX LOAD: "+returnedList.get(i).getCurrentMaxLoad()+" / "+returnedList.get(i).getMaxLoad()+"\n");
		    			cam.append("=============================================================================\n");
		    			cam.append("Basic fee: $"+returnedList.get(i).getBasicPrice()+" * "+returnedList.get(i).getCurrentBasicLoad()+" = "+(returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+"\n");
		                cam.append("Heavy fee: $"+returnedList.get(i).getHeavyPrice()+" * "+returnedList.get(i).getCurrentHeavyLoad()+" = "+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+"\n");
		                cam.append("Refrigerated fee: $"+returnedList.get(i).getRefrigPrice()+" * "+returnedList.get(i).getCurrentRefrigLoad()+" = "+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad())+"\n");
		                cam.append("Total fee: $"+((returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad()))+"\n");
		                cam.append("=============================================================================\n");
		                
		                MinNoOfShipsTextArea.setText(cam.toString());
		    		}
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(lowestPriceCheckBox.isSelected()){
					ArrayList<Ship> returnedList = (ArrayList<Ship>) MinimumCostScheduler.cheapest(folderPath.getCustArrayList(),folderPath.getShipArrayList());
					StringBuilder cam = new StringBuilder();
					cam.append("Number of ships used: "+returnedList.size()+ "\n");
					cam.append("Ships to be used are: \n");
					for (int i=0;i<returnedList.size();i++){
	    			cam.append("=============================================================================\n");
	    			cam.append("Name: "+returnedList.get(i).getName()+"\n");
	    			cam.append("Basic: "+returnedList.get(i).getCurrentBasicLoad()+" / "+returnedList.get(i).getBasicLoad()+"\n");
	    			cam.append("Heavy: "+returnedList.get(i).getCurrentHeavyLoad()+" / "+returnedList.get(i).getHeavyLoad()+"\n");
	    			cam.append("Refri: "+returnedList.get(i).getCurrentRefrigLoad()+" / "+returnedList.get(i).getRefrigLoad()+"\n");
	    			cam.append("Special Basic: "+returnedList.get(i).getCurrentSpecialBasic()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("Special Heavy: "+returnedList.get(i).getCurrentSpecialHeavy()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("Special Refrig: "+returnedList.get(i).getCurrentSpecialRefrig()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("Special Total: "+returnedList.get(i).getCurrentSpecialTotal()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("MAX LOAD: "+returnedList.get(i).getCurrentMaxLoad()+" / "+returnedList.get(i).getMaxLoad()+"\n");
	    			cam.append("=============================================================================\n");
	    			cam.append("Basic fee: $"+returnedList.get(i).getBasicPrice()+" * "+returnedList.get(i).getCurrentBasicLoad()+" = "+(returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+"\n");
	                cam.append("Heavy fee: $"+returnedList.get(i).getHeavyPrice()+" * "+returnedList.get(i).getCurrentHeavyLoad()+" = "+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+"\n");
	                cam.append("Refrigerated fee: $"+returnedList.get(i).getRefrigPrice()+" * "+returnedList.get(i).getCurrentRefrigLoad()+" = "+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad())+"\n");
	                cam.append("Total fee: $"+((returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad()))+"\n");
	                cam.append("=============================================================================\n");
	                
					lowestPriceTextArea.setText(cam.toString());
					}

				}
				if(optimumCheckBox.isSelected()){
					try {
						ArrayList<Ship> returnedList = MinimumBest.bestMin(folderPath.getShipArrayList(),folderPath.getCustArrayList());
						StringBuilder cam = new StringBuilder();
						cam.append("Number of ships used: "+returnedList.size()+ "\n");
						cam.append("Ships to be used are: \n");
						for (int i=0;i<returnedList.size();i++){
		    			cam.append("=============================================================================\n");
		    			cam.append("Name: "+returnedList.get(i).getName()+"\n");
		    			cam.append("Basic: "+returnedList.get(i).getCurrentBasicLoad()+" / "+returnedList.get(i).getBasicLoad()+"\n");
		    			cam.append("Heavy: "+returnedList.get(i).getCurrentHeavyLoad()+" / "+returnedList.get(i).getHeavyLoad()+"\n");
		    			cam.append("Refri: "+returnedList.get(i).getCurrentRefrigLoad()+" / "+returnedList.get(i).getRefrigLoad()+"\n");
		    			cam.append("Special Basic: "+returnedList.get(i).getCurrentSpecialBasic()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("Special Heavy: "+returnedList.get(i).getCurrentSpecialHeavy()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("Special Refrig: "+returnedList.get(i).getCurrentSpecialRefrig()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("Special Total: "+returnedList.get(i).getCurrentSpecialTotal()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
		    			cam.append("MAX LOAD: "+returnedList.get(i).getCurrentMaxLoad()+" / "+returnedList.get(i).getMaxLoad()+"\n");
		    			cam.append("=============================================================================\n");
		    			cam.append("Basic fee: $"+returnedList.get(i).getBasicPrice()+" * "+returnedList.get(i).getCurrentBasicLoad()+" = "+(returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+"\n");
		                cam.append("Heavy fee: $"+returnedList.get(i).getHeavyPrice()+" * "+returnedList.get(i).getCurrentHeavyLoad()+" = "+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+"\n");
		                cam.append("Refrigerated fee: $"+returnedList.get(i).getRefrigPrice()+" * "+returnedList.get(i).getCurrentRefrigLoad()+" = "+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad())+"\n");
		                cam.append("Total fee: $"+((returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad()))+"\n");
		                cam.append("=============================================================================\n");
		                
		                optimumTextArea.setText(cam.toString());
		    		}
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(shipsNotFullCheckBox.isSelected()){
					ArrayList<Ship> returnedList = ShipsNotFull.listShipsNotFull(folderPath.getShipArrayList());
					StringBuilder cam = new StringBuilder();
					cam.append("Number of ships used: "+returnedList.size()+ "\n");
					cam.append("Ships to be used are: \n");
					for (int i=0;i<returnedList.size();i++){
	    			cam.append("=============================================================================\n");
	    			cam.append("Name: "+returnedList.get(i).getName()+"\n");
	    			cam.append("Basic: "+returnedList.get(i).getCurrentBasicLoad()+" / "+returnedList.get(i).getBasicLoad()+"\n");
	    			cam.append("Heavy: "+returnedList.get(i).getCurrentHeavyLoad()+" / "+returnedList.get(i).getHeavyLoad()+"\n");
	    			cam.append("Refri: "+returnedList.get(i).getCurrentRefrigLoad()+" / "+returnedList.get(i).getRefrigLoad()+"\n");
	    			cam.append("Special Basic: "+returnedList.get(i).getCurrentSpecialBasic()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("Special Heavy: "+returnedList.get(i).getCurrentSpecialHeavy()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("Special Refrig: "+returnedList.get(i).getCurrentSpecialRefrig()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("Special Total: "+returnedList.get(i).getCurrentSpecialTotal()+" / "+returnedList.get(i).getSpecialLoad()+"\n");
	    			cam.append("MAX LOAD: "+returnedList.get(i).getCurrentMaxLoad()+" / "+returnedList.get(i).getMaxLoad()+"\n");
	    			cam.append("=============================================================================\n");
	    			cam.append("Basic fee: $"+returnedList.get(i).getBasicPrice()+" * "+returnedList.get(i).getCurrentBasicLoad()+" = "+(returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+"\n");
	                cam.append("Heavy fee: $"+returnedList.get(i).getHeavyPrice()+" * "+returnedList.get(i).getCurrentHeavyLoad()+" = "+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+"\n");
	                cam.append("Refrigerated fee: $"+returnedList.get(i).getRefrigPrice()+" * "+returnedList.get(i).getCurrentRefrigLoad()+" = "+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad())+"\n");
	                cam.append("Total fee: $"+((returnedList.get(i).getBasicPrice()*returnedList.get(i).getCurrentBasicLoad())+(returnedList.get(i).getHeavyPrice()*returnedList.get(i).getCurrentHeavyLoad())+(returnedList.get(i).getRefrigPrice()*returnedList.get(i).getCurrentRefrigLoad()))+"\n");
	                cam.append("=============================================================================\n");
	                
	                shipsNotFullTextArea.setText(cam.toString());
					}
					
				}
			}
		});

		JLabel label_7 = new JLabel("Scheduling Functions");
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 23));
		label_7.setBackground(Color.WHITE);
		label_7.setBounds(338, 11, 233, 28);
		SchedulingPanel.add(label_7);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(25, 153, 361, 196);
		MinNoOfShipsTextArea = new JTextArea();
		MinNoOfShipsTextArea.setBounds(38, 255, 205, 319);
		SchedulingPanel.add(scrollPane1);
		scrollPane1.setViewportView(MinNoOfShipsTextArea);
		scrollPane1.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createTitledBorder(
								null, "Min No. Of Ships:",
								javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
								javax.swing.border.TitledBorder.DEFAULT_POSITION,
								new java.awt.Font("Verdana", 1, 11)
								),
						javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
						)
				);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(432, 153, 382, 196);
		lowestPriceTextArea = new JTextArea();
		lowestPriceTextArea.setBounds(120, 377, 160, 243);
		SchedulingPanel.add(scrollPane2);
		scrollPane2.setViewportView(lowestPriceTextArea);
		scrollPane2.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createTitledBorder(
								null, "Lowest Price:",
								javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
								javax.swing.border.TitledBorder.DEFAULT_POSITION,
								new java.awt.Font("Verdana", 1, 11)
								),
						javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
						)
				);

		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(25, 380, 361, 196);
		optimumTextArea = new JTextArea();
		optimumTextArea.setBounds(190, 301, 205, 319);
		SchedulingPanel.add(scrollPane3);
		scrollPane3.setViewportView(optimumTextArea);
		scrollPane3.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createTitledBorder(
								null, "Optimum Solution",
								javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
								javax.swing.border.TitledBorder.DEFAULT_POSITION,
								new java.awt.Font("Verdana", 1, 11)
								),
						javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
						)
				);

		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(432, 380, 382, 196);
		shipsNotFullTextArea = new JTextArea();
		shipsNotFullTextArea.setBounds(475, 398, 184, 144);
		SchedulingPanel.add(scrollPane4);
		scrollPane4.setViewportView(shipsNotFullTextArea);
		scrollPane4.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createTitledBorder(
								null, "Ships (Not Full):",
								javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
								javax.swing.border.TitledBorder.DEFAULT_POSITION,
								new java.awt.Font("Verdana", 1, 11)
								),
						javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)
						)
				);
		//========================== End of Scheduling Tab ========================================//
		//========================== Start of Export Tab ========================================//	
				JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBounds(0, 0, 829, 659);
				frame.getContentPane().add(tabbedPane);

				JPanel ExportPanel = new JPanel();


				tabbedPane.addTab("Export", null, ExportPanel, null);
				ExportPanel.setLayout(null);
				JLabel label1 = new JLabel("Export Scheduling Plan (.CSV Files)");
				label1.setFont(new Font("Times New Roman", Font.BOLD, 18));
				label1.setBounds(250, 26, 437, 22);
				ExportPanel.add(label1);

				JLabel lblSchedulingPlan = new JLabel("Scheduling Plan");
				lblSchedulingPlan.setFont(new Font("Times New Roman", Font.BOLD, 15));
				lblSchedulingPlan.setBounds(140, 131, 116, 14);
				ExportPanel.add(lblSchedulingPlan);

				String[] options = { "Please Choose a Scheduling Plan", "Scheduled to the minimum number of ships", "The total price of the loading these containers is minimized", "Dual Optimization (Min. Ships and Cheapest)", "Ships that are not full"};
				final JComboBox comboBox = new JComboBox(options);
				//JComboBox comboBox = new JComboBox();
				comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				comboBox.setBounds(261, 121, 392, 34);
				ExportPanel.add(comboBox);

				comboBox.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// Do something when you select a value
						if(comboBox.getSelectedItem() == "Please Choose a Scheduling Plan"){
							JOptionPane.showMessageDialog(null, "Please Choose a Scheduling Plan " + userPathTextField1.getText(), "Choose", 1);
						}
						else if(comboBox.getSelectedItem() == "Scheduled to the minimum number of ships"){
							try {
								exportArray = Minimum.search(folderPath.getShipArrayList(),folderPath.getCustArrayList());
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						else if(comboBox.getSelectedItem() == "The total price of the loading these containers is minimized"){
							exportArray = MinimumCostScheduler.cheapest(folderPath.getCustArrayList(),folderPath.getShipArrayList());
						}
						else if(comboBox.getSelectedItem() == "Dual Optimization (Min. Ships and Cheapest)"){
							try {
								exportArray = MinimumBest.bestMin(folderPath.getShipArrayList(),folderPath.getCustArrayList());
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						else if(comboBox.getSelectedItem() == "Ships that are not full"){
							exportArray =ShipsNotFull.listShipsNotFull(folderPath.getShipArrayList());

						}
					}
				});

				JLabel label_2 = new JLabel("Select Folder Path");
				label_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
				label_2.setBounds(129, 204, 134, 14);
				ExportPanel.add(label_2);

				userPathTextField1 = new JTextField();
				userPathTextField1.setColumns(10);
				userPathTextField1.setBounds(261, 201, 250, 20);
				userPathTextField1.setEditable(false);
				ExportPanel.add(userPathTextField1);

				JButton filebutton1 = new JButton("...");
				//button.addActionListener(this);
				filebutton1.setBounds(514, 201, 45, 20);
				ExportPanel.add(filebutton1);

				filebutton1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						chooser = new JFileChooser(); 
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle(choosertitle);
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						//
						// disable the "All files" option.
						//
						chooser.setAcceptAllFileFilterUsed(false);
						//    
						if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
							//get the folder path
							userPathTextField1.setText(chooser.getSelectedFile().getPath()+"/ShipSchedule.xlsx");
						}
						else 
						{
							System.out.println("No Selection ");
						}
					}
				});

				JButton btnImport1 = new JButton("Export");
				btnImport1.setFont(new Font("Times New Roman", Font.BOLD, 12));
				btnImport1.setEnabled(false);
				btnImport1.setBounds(569, 199, 84, 25);
				document = userPathTextField1.getDocument();
				document.addDocumentListener(new JButtonStateController(btnImport1));
				ExportPanel.add(btnImport1);

				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(86, 265, 628, 2);
				ExportPanel.add(separator_1);

				JLabel lblAlternative = new JLabel("Forward Scheduling Plan (.txt Files)( Via Email )");
				lblAlternative.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblAlternative.setBounds(230, 293, 352, 34);
				ExportPanel.add(lblAlternative);

				JLabel lblEmail = new JLabel("Email");
				lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
				lblEmail.setBounds(191, 364, 60, 14);
				ExportPanel.add(lblEmail);


				EmailtextField = new JTextField();
				EmailtextField.setColumns(10);
				EmailtextField.setBounds(261, 362, 298, 20);
				ExportPanel.add(EmailtextField);

				JButton btnEmail = new JButton("Email");
				btnEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
				btnEmail.setEnabled(false);
				btnEmail.setBounds(569, 360, 84, 25);
				ExportPanel.add(btnEmail);
				document = EmailtextField.getDocument();
				document.addDocumentListener(new JButtonStateController(btnEmail));

				btnImport1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try {
							exportxml.export(userPathTextField1.getText(),exportArray);
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "File not Found", "Error", 1);
						}
						CSVUtils csv = new CSVUtils();
						try {
							//System.out.println(System.getProperty("user.dir"));
							csv.convertExcelToCSV(userPathTextField1.getText(),System.getProperty("user.dir")+"\\src/ShipSchedule.txt");
						} catch (InvalidFormatException | IOException e3) {
							// TODO Auto-generated catch block
							//e3.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Export Sucessful to " + userPathTextField1.getText(), "Success", 1);
					}

				});

				btnEmail.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try {
							if (SendAttachmentInEmail.checkEmail(EmailtextField.getText()) == true){
								System.out.println(new File(".").getAbsoluteFile());
								try {
									SendAttachmentInEmail.email(null, EmailtextField.getText(), System.getProperty("user.dir")+"\\src/ShipSchedule.txt");
									JOptionPane.showMessageDialog(null, "Email Sucessful to " + EmailtextField.getText(), "Success", 1);
								} catch (MessagingException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, "Error Has Occured while sending email", "Error", 1);
									//e1.printStackTrace();
								}
							}
						} catch (AddressException | HeadlessException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Invalid Email Address. Please check again.", "Error", 1);
							//e1.printStackTrace();
						}
					}	
				});
			}
	
	//======================================== Functions =================================================
	public void bindCustomerID(){
		ArrayList<Customer> tempCustList = folderPath.getCustArrayList();
		System.out.println("ComboBoxSize: " + comboBoxCust.getModel().getSize());
		System.out.println("TempArraySize: " + tempCustList.size());
		for(int i = 0; i < tempCustList.size(); i++){
			Customer temp = tempCustList.get(i);
			comboBoxCust.addItem(temp.getID());
		}

	}

	public void bindShipName(){
		for(int i = 0; i < folderPath.shipArrayList.size(); i++){
			Ship temp = folderPath.shipArrayList.get(i);
			comboBoxShip.addItem(temp.getName());
		}
	}


	//===========================PUT IN AND COMMENT OUT THE OTHER ONE==============================//
		public void showContent(StringBuilder sm){
			containerTextArea.setText(folderPath.printAllCustomer());
			//custTextArea.setText(folderPath.printAllCustomer());
			//userPathTextField.setText("");
			shipTextArea.setText(folderPath.printShipContent());
			//shipsTextArea.setText(folderPath.printShipContent());
			errorTextField.setText(sm.toString());
			bindCustomerID();
			bindShipName();
		}
		//=========================================================================================//
	
	public void showContInShips(String lblType, int y, JPanel update,  int load, int price){		
		JLabel MaxLoad = new JLabel(lblType);
		MaxLoad.setBounds(75, y, 150, 14);
		labelMaxLoadArr.add(MaxLoad);
		update.add(MaxLoad);

		MaxLoadField = new JTextField();
		MaxLoadField.setEditable(false);
		MaxLoadField.setColumns(10);
		MaxLoadField.setBounds(215, y, 50, 20);
		//MaxLoadField.setText("dot");
		MaxLoadField.setText(String.valueOf(load));
		fieldMaxLoadArr.add(MaxLoadField);
		update.add(MaxLoadField);
		
		if(price > 0){
			JLabel Price = new JLabel("Price:");
			Price.setBounds(290, y, 65, 14);
			labelPriceArr.add(Price);
			update.add(Price);
		
			PriceTextField = new JTextField();
			PriceTextField.setEditable(false);
			PriceTextField.setColumns(10);
			PriceTextField.setBounds(335, y, 50, 20);
			PriceTextField.setText(String.valueOf(price));
			fieldPriceArr.add(PriceTextField);
			update.add(PriceTextField);



			checkBoxes = new JCheckBox("");
			checkBoxes.setBounds(400, y, 30, 23);
			fieldCheckSArr.add(checkBoxes);
			update.add(checkBoxes);
		}
		//Create Price text field		
	}

		
	public void cleanContainerPanel(JPanel updateAndDisplayCust){
		for(int x = 0; x < labelTypeArr.size(); x++){
			updateAndDisplayCust.remove(fieldNameArr.get(x));
			updateAndDisplayCust.remove(labelTypeArr.get(x));
			updateAndDisplayCust.remove(fieldNumberArr.get(x));
			updateAndDisplayCust.remove(labelNumberArr.get(x));
			updateAndDisplayCust.remove(fieldCheckArr.get(x));

		}
		updateAndDisplayCust.revalidate();
		updateAndDisplayCust.repaint();
	}

	//===== Functions contributed by Law Kiang Hong, 15SIC019U =====
	public boolean insertNewCustomer(String custID, String name, int age, String Company, ArrayList<Container> tempContArr) throws Exception{
		ArrayList<Customer> tempCustArr = folderPath.getCustArrayList();
		
		Pattern pattern = Pattern.compile("^[a-z A-Z ]+$");  
		Matcher matcher = pattern.matcher(name); // Your String should come here
		if(!matcher.find()){
			throw new Exception("Name only can contains Alphabets");
		}
		
		if(age < 0 || age > 100){
			throw new Exception("Age must be in acceptable range of 1 - 100");
		}
		
		Customer newCust = new Customer(custID, name, age, Company, tempContArr);
		tempCustArr.add(newCust);
		folderPath.setCustArrayList(tempCustArr);
		return true;
	}
	
	public ArrayList<Container> createContainerObject(ArrayList<Container> temContainerArr, String containerType, int containerNum) throws Exception{
		ArrayList<Container> tempContainerArr = temContainerArr;
		Container tempCont;
		boolean exists = false;
		
		if(containerNum <= 0){
			throw new Exception("Container Number must not be less than or equal 0");
		}
		
		for(int i =0; i < tempContainerArr.size(); i++){
			if(tempContainerArr.get(i).getType() == containerType){
				tempContainerArr.get(i).setNumber(tempContainerArr.get(i).getNumber() + containerNum);
				exists = true;
			}
		}
		
		if(exists == false){
			if(containerType.compareTo("Basic") == 0){
				tempCont = new Basic(containerNum, 0 ,null);
				tempContainerArr.add(tempCont);
			}else if(containerType.compareTo("Basic_Special") == 0){
				tempCont = new Basic(0, containerNum ,"explosive,toxic");
				tempContainerArr.add(tempCont);
			}else if(containerType.compareTo("Heavy") == 0){
				tempCont = new Heavy(containerNum, 0 , null);
				tempContainerArr.add(tempCont);
			}else if(containerType.compareTo("Heavy_Special") == 0){
				tempCont = new Heavy(0, containerNum ,"explosive,toxic");
				tempContainerArr.add(tempCont);
			}else if(containerType.compareTo("Refrigerated") == 0){
				tempCont = new Refrigerated(containerNum, 0 ,null);
				tempContainerArr.add(tempCont);
			}else if(containerType.compareTo("Refrigerated_Special") == 0){
				tempCont = new Refrigerated(0, containerNum ,"explosive,toxic");
				tempContainerArr.add(tempCont);
			}
		}
			
		return tempContainerArr;
	}
	
	public boolean updateCustomerInfo(String custID, String name, int age, String Company, ArrayList<Container> tempContArr) throws Exception{
		ArrayList<Customer> tempCustArr = folderPath.getCustArrayList();
		
		Pattern pattern = Pattern.compile("^[a-z A-Z ]+$");  
		Matcher matcher = pattern.matcher(name); // Your String should come here
		if(!matcher.find()){
			throw new Exception("Name only can contains Alphabets");
		}
		
		if(age < 0 || age > 100){
			throw new Exception("Age must be in acceptable range of 1 - 100");
		}
		for(int i = 0; i < tempCustArr.size(); i++){
			Customer tempCust = tempCustArr.get(i);
			if(tempCust.getID() == custID){
				
				tempCust.updateAllInfo(name, age, Company, tempContArr);
				folderPath.setCustArrayList(tempCustArr);
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Container> updateContainerInfo(String custID, String containerType, int containerNum) throws Exception{
		
		ArrayList<Customer> tempCustomerArr = folderPath.getCustArrayList();
		ArrayList<Container> tempContArr = new ArrayList<Container>();
		Container tempCont;
		String tempType = "Basic";
		boolean isSpecial = false;
		boolean exists = true;
		
		if(containerNum <= 0){
			throw new Exception("Container Number must not be less than or equal 0");
		}
		
		for(int i = 0; i < tempCustomerArr.size(); i++){
			Customer tempCust = tempCustomerArr.get(i);
			if(tempCust.getID() == custID){
				tempContArr = tempCust.getContainerList();
			}
		}

		if(containerType.compareTo("Basic") == 0){
			tempType = "Basic";
			isSpecial = false;
		}else if(containerType.compareTo("Basic_Special") == 0){
			tempType = "Basic";
			isSpecial = true;
		}else if(containerType.compareTo("Heavy") == 0){
			tempType = "Heavy";
			isSpecial = false;
		}else if(containerType.compareTo("Heavy_Special") == 0){
			tempType = "Heavy";
			isSpecial = true;
		}else if(containerType.compareTo("Refrigerated") == 0){
			tempType = "Refrigerated";
			isSpecial = false;
		}else if(containerType.compareTo("Refrigerated_Special") == 0){
			tempType = "Refrigerated";
			isSpecial = true;
		}
		
		System.out.println(tempType + ":" + isSpecial + "-" + tempContArr.size() + "---" + containerType);
		for(int i =0; i < tempContArr.size(); i++){
			if(isSpecial == true){		
				if(tempType.compareTo(tempContArr.get(i).getType()) == 0 && tempContArr.get(i).getSpecialProperty() != null){
					tempContArr.get(i).setSpecialNumber(containerNum);
					exists = true;
					break;
				}
				else{
					exists = false;
				}
			}
			else{	
				if(tempType.compareTo(tempContArr.get(i).getType()) == 0){
					tempContArr.get(i).setNumber(containerNum);
					exists = true;
					break;
				}
				else{
					exists = false;
				}
			}
			
			
		}
		
		if(exists == false){
			if(containerType.compareTo("Basic") == 0){
				tempCont = new Basic(containerNum, 0 ,null);
				tempContArr.add(tempCont);
			}else if(containerType.compareTo("Basic_Special") == 0){
				tempCont = new Basic(0, containerNum ,"explosive,toxic");
				tempContArr.add(tempCont);
			}else if(containerType.compareTo("Heavy") == 0){
				tempCont = new Heavy(containerNum, 0 , null);
				tempContArr.add(tempCont);
			}else if(containerType.compareTo("Heavy_Special") == 0){
				tempCont = new Heavy(0, containerNum ,"explosive,toxic");
				tempContArr.add(tempCont);
			}else if(containerType.compareTo("Refrigerated") == 0){
				tempCont = new Refrigerated(containerNum, 0 ,null);
				tempContArr.add(tempCont);
			}else if(containerType.compareTo("Refrigerated_Special") == 0){
				tempCont = new Refrigerated(0, containerNum ,"explosive,toxic");
				tempContArr.add(tempCont);
			}
		}
		

		return tempContArr;
	}
	
	public boolean deleteCustomer(String custID){
		
		ArrayList<Customer> tempCustArr = folderPath.getCustArrayList();
		for(int i = 0; i < tempCustArr.size(); i++){
			Customer tempCust = tempCustArr.get(i);
			if(tempCust.getID() == custID){
				tempCustArr.remove(tempCust);
				folderPath.setCustArrayList(tempCustArr);
				return true;
			}
		}
		
		return false;	
	}
	
	public boolean deleteContainer(){
		return false;
	}
	
	public boolean insertNewShip(String shipName, String owner, int shipValue, String company,int basicMaxLoad,int basicPrice,int heavyMaxLoad,int heavyPrice,int refrigeratedMaxLoad,int refrigeratedPrice, int special, int allMaxLoad) throws Exception{
		ArrayList<Ship> tempShipArr = folderPath.getShipArrayList();
		
		Pattern pattern = Pattern.compile("^[a-z A-Z ]+$");  
		Matcher matcher = pattern.matcher(owner); // Your String should come here
		if(!matcher.find()){
			throw new Exception("Owner Name only can contains Alphabets");
		}
		
		if(basicMaxLoad != allMaxLoad){
			throw new Exception("Basic Max Laod cannot be more or less than All Max Load");
		}
		
		if(heavyMaxLoad > allMaxLoad || refrigeratedMaxLoad > allMaxLoad || special > allMaxLoad){
			throw new Exception("Containers Type Max Load cannot be more than All Max Load");
		}
		
		if(heavyMaxLoad < 0 || refrigeratedMaxLoad < 0 || basicMaxLoad < 0 || special < 0) {
			throw new Exception("Containers Type Max Load must be a positive number");
		}
		
		if(basicPrice < 0 || heavyPrice < 0 || refrigeratedPrice < 0){
			throw new Exception("Pricing must be a positive number");
		}
		
		Ship newShip = new Ship(shipName, owner, shipValue, company, allMaxLoad, basicMaxLoad, basicPrice, heavyMaxLoad, heavyPrice, 
				refrigeratedMaxLoad, refrigeratedPrice, special);
		tempShipArr.add(newShip);
		folderPath.setShipArrayList(tempShipArr);
		return true;

	}
	
	public boolean updateShipInformation(String shipName, String owner, int shipValue, String company,int basicMaxLoad,int basicPrice,int heavyMaxLoad,int heavyPrice,int refrigeratedMaxLoad,int refrigeratedPrice, int special, int allMaxLoad) throws Exception{
		ArrayList<Ship> tempShipArr = folderPath.getShipArrayList();
		
		Pattern pattern = Pattern.compile("^[a-z A-Z ]+$");  
		Matcher matcher = pattern.matcher(owner); // Your String should come here
		if(!matcher.find()){
			throw new Exception("Owner Name only can contains Alphabets");
		}
		
		if(basicMaxLoad != allMaxLoad){
			throw new Exception("Basic Max Laod cannot be more or less than All Max Load");
		}
		
		if(heavyMaxLoad > allMaxLoad || refrigeratedMaxLoad > allMaxLoad || special > allMaxLoad){
			throw new Exception("Containers Type Max Load cannot be more than All Max Load");
		}
		
		if(heavyMaxLoad < 0 || refrigeratedMaxLoad < 0 || basicMaxLoad < 0 || special < 0) {
			throw new Exception("Containers Type Max Load must be a positive number");
		}
		
		if(basicPrice < 0 || heavyPrice < 0 || refrigeratedPrice < 0){
			throw new Exception("Pricing must be a positive number");
		}
		
		for(int i = 0; i < tempShipArr.size(); i++){
			Ship tempShip = tempShipArr.get(i);
			if(tempShip.getName() == shipName){
				tempShip.updateAll(owner, shipValue, company, basicMaxLoad, basicPrice, heavyMaxLoad, heavyPrice, refrigeratedMaxLoad, refrigeratedPrice, special, allMaxLoad);
				//SaveXML saveShips = new SaveXML();
				//saveShips.UpdateShip(tempShip, userPathTextField.getText());
				return true;
			}
		}
		
		return false;
	}
	
	public boolean deleteShip(String shipName){
		ArrayList<Ship> tempShipArr = folderPath.getShipArrayList();

		for(int i = 0; i < tempShipArr.size(); i++){
			Ship tempShip = tempShipArr.get(i);
			if(tempShip.getName() == shipName){
				tempShipArr.remove(tempShip);
				folderPath.setShipArrayList(tempShipArr);
				return true;
			}
		}
		return false;
	}
	
	//===== End of function for Insert, Update and Delete =====
	
	
	class JButtonStateController implements DocumentListener {
		private JButton button;

		JButtonStateController(JButton btnImport) {
			button = btnImport;
		}

		public void changedUpdate(DocumentEvent e) {
			disableIfEmpty(e);
		}

		public void insertUpdate(DocumentEvent e){
			disableIfEmpty(e);
		}

		public void removeUpdate(DocumentEvent e){
			disableIfEmpty(e);
		}

		public void disableIfEmpty(DocumentEvent e) {
			button.setEnabled(e.getDocument().getLength() > 0);
		}
	}
}
