package StudentRating;

import java.rmi.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.security.*;
import javax.swing.filechooser.*;

public class Client extends JFrame implements ActionListener {
	JTextField fileName, className;
	JTextArea display;
	JButton browse, send, result;
	String Studentlist[][] = new String[10][3];// 10 students, with name - class - score
	String serverURL1 = "rmi://localhost:1112/Server1";
	String serverURL2 = "rmi://localhost:1114/Server2";
	String serverURL3 = "rmi://localhost:1113/Server3";
	ServerInterface server1, server2, server3;
	boolean isSent = false;
	String oldFileName = "";

	ArrayList<Student> list = new ArrayList<Student>();

	Client() {
		super("Client");
		try {

			JLabel l1 = new JLabel("College Name :"), l2 = new JLabel("Select File :");

			browse = new JButton("browse");
			fileName = new JTextField(20);
			className = new JTextField(20);
			send = new JButton("send");
			result = new JButton("Get Result");
			display = new JTextArea();

			fileName.setEditable(false);
			display.setEditable(false);

			result.setVisible(false);
			send.setVisible(false);

			setLayout(null);
			l1.setBounds(10, 10, 100, 30);
			add(l1);
			className.setBounds(130, 10, 320, 30);
			add(className);
			l2.setBounds(10, 50, 100, 30);
			add(l2);
			browse.setBounds(130, 50, 100, 30);
			add(browse);
			fileName.setBounds(250, 50, 200, 30);
			add(fileName);
			send.setBounds(10, 90, 100, 30);
			add(send);
			result.setBounds(130, 90, 100, 30);
			add(result);
			display.setBounds(10, 150, 450, 300);
			add(display);

			setSize(500, 500);
			setVisible(true);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			browse.addActionListener(this);
			send.addActionListener(this);
			result.addActionListener(this);

			server1 = (ServerInterface) Naming.lookup(serverURL1);
			server2 = (ServerInterface) Naming.lookup(serverURL2);
			server3 = (ServerInterface) Naming.lookup(serverURL3);
			while (true) {
				Thread.sleep(1000);
				if(server1.isResultReady()) {
				result.setVisible(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object sourceObj = e.getSource();
		// neu chon file
		if (browse == sourceObj) {
			try {

				JFileChooser chooser = new JFileChooser();
				// chi duoc chon file co duoi txt hoac csv
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				// chon file
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					BufferedReader br = new BufferedReader(new FileReader(file.getName()));// given the name of the file
					String line = "";
					String separator = "-";
					// chua gui di lan nao
					if (!isSent) {
						System.out.println(file.getName());// get file name and print it first
						if (chooser.getTypeDescription(file).equals("Text Document")) {// file .txt, get separator "-"
							fileName.setText(file.getName());
							separator = "-";
							int i = 0;
							while ((line = br.readLine()) != null) {
								Studentlist[i] = line.split(separator);
								i++;
							}
							send.setVisible(true);
						} else if (chooser.getTypeDescription(file)
								.equals("Microsoft Office Excel Comma Separated Values File")) {// file exel -->
																								// separator ","
							fileName.setText(file.getName());
							separator = ",";
							int i = 0;
							// doc file
							while ((line = br.readLine()) != null) {
								Studentlist[i] = line.split(separator);
								i++;
							}
							// doc duoc file roi moi cho gui hay khong
							send.setVisible(true);
							// chi duoc gui 1 lan/ 1 lan run
						} else
							JOptionPane.showMessageDialog(null, "Invalid File");
						// sent --> khong cho send lai
					} else {
						JOptionPane.showMessageDialog(null, "Submit only one");
						send.setVisible(false);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// bat dau gui du lieu di
		} else if (send == sourceObj) {
			try {
				if ((className == null || className.getText().length() == 0)) {
					JOptionPane.showMessageDialog(null, "Enter you class");
					return;
				} else if (!isSent) {

					for (int i = 0; i < Studentlist.length; i++) {
						System.out.println(Studentlist[i][0] + " " + Studentlist[i][1] + "  " + Studentlist[i][2]);
					}
					server1.sendData(Studentlist, className.getText());

					String data = server1.getTop5(Studentlist);
					server1.TransformData(Studentlist);
					System.out.println(data);
					isSent = true;// control send thread
				} else
					JOptionPane.showMessageDialog(null, "Submit only one");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception: " + ex);
			}
		} else {
			try {
				display.setText(server1.getTop5(Studentlist));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		new Client();
	}
}
