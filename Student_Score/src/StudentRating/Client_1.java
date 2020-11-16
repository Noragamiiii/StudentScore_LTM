package StudentRating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.Naming;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Client_1 extends JFrame implements ActionListener {

	private JPanel contentPane, panel;
	private JTextField txtClassName, txtFilename;
	private JButton btnChooseFile, btnSend, btnResult;
	private JLabel className, fileName;
	private JTextArea txtSortStudent, txtTop5, txtClsStudent;

	int xMouse;
	int yMouse;

	static boolean maximized = true;
	boolean isSent = false;

	String Studentlist[][] = new String[10][2];// 10 students, with name - class - score
	String serverURL1 = "rmi://localhost:1112/Server1";
	String serverURL2 = "rmi://localhost:1114/Server2";
	String serverURL3 = "rmi://localhost:1113/Server3";
	
	ServerInterface server1, server2, server3;

	ArrayList<Student> list = new ArrayList<Student>();
	Student student;

	public static void main(String[] args) {
		new Client_1();
	}

	public Client_1() {

		super("Client_1"); 
		try {
			InitComponent();
			server1 = (ServerInterface) Naming.lookup(serverURL1);
			server2 = (ServerInterface) Naming.lookup(serverURL2);
			server3 = (ServerInterface) Naming.lookup(serverURL3);

			while (true) {
				Thread.sleep(1000);

				if (server1.isResultReady() && server2.isResultReady() && server3.isResultReady()) {
					btnResult.setVisible(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
			private void InitComponent() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 600, 400);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.inactiveCaption);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setOpaque(false);
		    setLocationRelativeTo(null);
		    setVisible(true);

			panel = new JPanel();
			panel.setBounds(0, 0, 584, 361);
			panel.setBackground(SystemColor.inactiveCaption);

			className = new JLabel("Class name");
			className.setBounds(10, 22, 98, 28);
			className.setFont(new Font("Segoe UI", Font.BOLD, 14));

			fileName = new JLabel("Browse file");
			fileName.setBounds(10, 69, 98, 28);
			fileName.setFont(new Font("Segoe UI", Font.BOLD, 14));

			txtClassName = new JTextField();
			txtClassName.setBounds(118, 22, 197, 28);
			txtClassName.setBackground(SystemColor.inactiveCaptionBorder);
			txtClassName.setBorder(null);
			txtClassName.setColumns(10);

			btnChooseFile = new JButton("Choose file");
			btnChooseFile.setBounds(118, 74, 117, 23);
			btnChooseFile.setBackground(SystemColor.inactiveCaptionBorder);
			btnChooseFile.setBorder(null);
			btnChooseFile.setFont(new Font("Segoe UI", Font.PLAIN, 11));

			txtFilename = new JTextField();
			txtFilename.setBounds(256, 75, 74, 20);
			txtFilename.setBackground(SystemColor.inactiveCaption);
			txtFilename.setEditable(false);
			txtFilename.setColumns(10);

			btnSend = new JButton("Send");
			btnSend.setBounds(10, 115, 74, 23);
			btnSend.setBackground(SystemColor.inactiveCaptionBorder);
			btnSend.setFont(new Font("Segoe UI", Font.BOLD, 11));
			btnSend.setBorder(null);

			btnResult = new JButton("Result");
			btnResult.setBounds(118, 115, 74, 23);
			btnResult.setFont(new Font("Segoe UI", Font.BOLD, 11));
			btnResult.setBorder(null);
			btnResult.setBackground(SystemColor.inactiveCaptionBorder);

			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(10, 149, 564, 200);

			JPanel sv1 = new JPanel();
			sv1.setBackground(SystemColor.controlLtHighlight);
			tabbedPane.addTab("Sort Students", null, sv1, null);
			sv1.setLayout(null);

			txtSortStudent = new JTextArea();
			txtSortStudent.setBounds(0, 0, 569, 172);
			sv1.add(txtSortStudent);

			JPanel panel_2 = new JPanel();
			panel_2.setBackground(SystemColor.controlLtHighlight);
			tabbedPane.addTab("Top 5 Students", null, panel_2, null);
			panel_2.setLayout(null);

			txtTop5 = new JTextArea();
			txtTop5.setBounds(0, 0, 575, 172);
			panel_2.add(txtTop5);

			JPanel panel_3 = new JPanel();
			panel_3.setBackground(SystemColor.controlLtHighlight);
			tabbedPane.addTab("Classify Students", null, panel_3, null);
			panel_3.setLayout(null);

			txtClsStudent = new JTextArea();
			txtClsStudent.setBounds(0, 0, 575, 172);
			panel_3.add(txtClsStudent);

			txtFilename.setEditable(false);
			contentPane.setLayout(null);
			contentPane.add(panel);
			panel.setLayout(null);
			panel.add(className);
			panel.add(txtClassName);
			panel.add(fileName);
			panel.add(btnResult);
			panel.add(btnChooseFile);
			panel.add(txtFilename);
			panel.add(btnSend);
			panel.add(tabbedPane);

			btnResult.setVisible(false);
			btnSend.setVisible(false);

			btnResult.addActionListener(this);
			btnSend.addActionListener(this);
			btnChooseFile.addActionListener(this);
			}

			

	public void actionPerformed(ActionEvent evt) {
		Object sourceObj = evt.getSource();
		// neu chon file
		if (btnChooseFile == sourceObj) {
			try {

				JFileChooser chooser = new JFileChooser();
				// chi duoc chon file co duoi txt hoac csv
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				// choose file
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					BufferedReader br = new BufferedReader(new FileReader(file.getName()));// given the name of the file
					String line = "";
					String separator = "-";
					// never send before
					if (!isSent) {
						System.out.println(file.getName());// get file name and print it first
						if (chooser.getTypeDescription(file).equals("Text Document")) {// file .txt, get separator "-"
							txtFilename.setText(file.getName());
							separator = "-";
							int i = 0;
							while ((line = br.readLine()) != null) {
								Studentlist[i] = line.split(separator);
								i++;
							}
							btnSend.setVisible(true);
						} else if (chooser.getTypeDescription(file)
								.equals("Microsoft Office Excel Comma Separated Values File")) {// file exel -->
																								// separator ","
							txtFilename.setText(file.getName());
							separator = ",";
							int i = 0;
							// read file
							while ((line = br.readLine()) != null) {
								Studentlist[i] = line.split(separator);
								i++;
							}
							// after reading file
							btnSend.setVisible(true);
							// send only one/ 1 run
						} else
							JOptionPane.showMessageDialog(null, "Invalid File");
						// sent --> do not allow send again
					} else {
						JOptionPane.showMessageDialog(null, "Submit only one");
						btnSend.setVisible(false);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// start sending data
		} else if (btnSend == sourceObj) {
			try {
				if ((txtClassName == null || txtClassName.getText().length() == 0)) {
					JOptionPane.showMessageDialog(null, "Enter you class");
					return;
				} else if (!isSent) {

					for (int i = 0; i < Studentlist.length; i++) {
						System.out.println(Studentlist[i][0] + " " + Studentlist[i][1] + "  " + Studentlist[i][2]);
						student = new Student(Studentlist[i][0], Studentlist[i][1], Studentlist[i][2]);
						list.add(student);
						
					}
					server1.sendData(Studentlist, className.getText());
					server2.sendData(Studentlist, className.getText());
					server3.sendData(Studentlist, className.getText());
					
					server1.ListStudent(Studentlist);
					server2.getTop5(Studentlist);
					server3.classifyStudent(Studentlist);
					
					txtSortStudent.setText((String)server1.ListStudent(Studentlist));
					txtTop5.setText((String)server2.getTop5(Studentlist));
					txtClsStudent.setText((String)server3.classifyStudent(Studentlist));
					
					isSent = true;// control send thread
				} else
					JOptionPane.showMessageDialog(null, "Submit only one");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception: " + ex);
			}
		} 
		// result button
		else {
			try {
				Student s;
				for(int i = 0; i < list.size(); i++) {
					
				}				
				
				txtSortStudent.setText((String)server1.ListStudent(Studentlist));
				txtTop5.setText((String)server2.getTop5(Studentlist));
				txtClsStudent.setText((String)server3.classifyStudent(Studentlist));
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
