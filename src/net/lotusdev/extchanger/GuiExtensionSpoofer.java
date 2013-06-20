package net.lotusdev.extchanger;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiExtensionSpoofer extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	private static JFileChooser fileChooser;
	private static JTextField textField_1;
	private static JComboBox comboBox;
	private static JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changeLookAndFeel();
					GuiExtensionSpoofer frame = new GuiExtensionSpoofer();
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
	public GuiExtensionSpoofer() {
		/*
		 * Lots of crap from WindowBuilder.
		 */
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiExtensionSpoofer.class.getResource("/net/lotusdev/extchanger/res/FolderOpened_Yellow.png")));
		setTitle("Extension Changer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnSppof = new JButton("Change");
		btnSppof.setToolTipText("Change/overwrite the extension.");
		btnSppof.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	changeExt();
			}
		});
		btnSppof.setIcon(new ImageIcon(GuiExtensionSpoofer.class.getResource("/net/lotusdev/extchanger/res/arrow_right.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		lblNewLabel_1 = new JLabel("");
		
		JLabel lblJabaarbiz = new JLabel("lotusdev.net");
		lblJabaarbiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		    	java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		        try {
		        	/*
		        	 * Small little thing to direct to webste (I stopped doing this in recent projects).
		        	 */
		            java.net.URI uri = new java.net.URI("http://www.lotusdev.net");
		            desktop.browse(uri);
		        }catch(Exception e) {
		            System.err.println(e.getMessage());
		        }
			}
		});
		lblJabaarbiz.setIcon(new ImageIcon(GuiExtensionSpoofer.class.getResource("/net/lotusdev/extchanger/res/user_gray.png")));
		lblJabaarbiz.setForeground(Color.BLUE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblJabaarbiz)
							.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSppof))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 295, Short.MAX_VALUE))
					.addGap(20))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSppof)
						.addComponent(lblJabaarbiz)
						.addComponent(lblNewLabel_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblInput = new JLabel("Input:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("...");
		btnBrowse.setToolTipText("Select the input file.");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser = new JFileChooser(System.getProperty("user.dir"));
				fileChooser.setVisible(true);
				fileChooser.setDialogTitle("Choose input file");
	            int returnVal = fileChooser.showOpenDialog(GuiExtensionSpoofer.this); 
	            if(returnVal == JFileChooser.APPROVE_OPTION) {
	            	System.out.println("User selected file: " + fileChooser.getSelectedFile().getAbsolutePath());
	            	textField.setText(fileChooser.getSelectedFile().getPath());
	            	String noExtension = removeExtension(fileChooser.getSelectedFile().getName());
	            	textField_1.setText(noExtension);
	            }
			}
		});
		
		JLabel lblReanameoptional = new JLabel("Name:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			/*
			 * All of the most common types of extensions.
			 */
			comboBox.setModel(new DefaultComboBoxModel(new String[] {".mp3", ".mp4", ".jar", ".txt", ".exe", ".bat", ".html", ".java", ".dat", ".psd", ".jpg", ".png", ".gif", ".sh", ".dll", ".rar", ".bin", ".wav", ".wmv", ".avi", ".tif", ".bmp", ".zip"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblInput)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblReanameoptional)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInput)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReanameoptional)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(91))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Create the look and feel.
	 */
	public static void changeLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			System.out.println("Gui has been succesfully created.");
		}catch(Exception e) {
			
		}
	}
	
	/**
	 * Removes extension from the file name so it can be replaced.
	 * @param s
	 * @return
	 */
	public static String removeExtension(String s) {
	    String separator = System.getProperty("file.separator");
	    String filename;

	    int lastSeparatorIndex = s.lastIndexOf(separator);
	    if (lastSeparatorIndex == -1) {
	        filename = s;
	    }else {
	        filename = s.substring(lastSeparatorIndex + 1);
	    }

	    int extensionIndex = filename.lastIndexOf(".");
	    if (extensionIndex == -1)
	        return filename;

	    return filename.substring(0, extensionIndex);
	}
	
	/**
	 * Changes the extension.
	 */
	public static void changeExt() {	
		long time = System.currentTimeMillis();
		
		if(fileChooser.getSelectedFile().isFile()) {
			File file1 = fileChooser.getSelectedFile();
			String withoutExt = "";
			if(!textField_1.getText().equals("")) {
				withoutExt = textField_1.getText();
			}else {
				withoutExt = removeExtension(file1.getName());
			}
			
			File file2 = null;
			
			file2 = new File(fileChooser.getCurrentDirectory() + "/" + withoutExt + comboBox.getSelectedItem());
			
			System.out.println("Working: " + fileChooser.getSelectedFile().getAbsolutePath());
			boolean success = fileChooser.getSelectedFile().renameTo(file2);
			System.out.println(fileChooser.getCurrentDirectory().getAbsolutePath() + (success?" : check":" : fail"));
			
			time = System.currentTimeMillis() - time;
			
			lblNewLabel_1.setText(success? "Success - " + time + " ms" : "Error.");
			lblNewLabel_1.setForeground(success? Color.BLUE : Color.RED);
			
			textField.setText("");
			textField_1.setText("");
		}
	}
	
	/**
	 * Copy from file to file (duplicate before doing something).
	 * @param from
	 * @param to
	 */
	public static void copyFile(File from, File to) {
		try {
	    	Files.copy(from.toPath(), to.toPath());
	    	System.out.println("owned");
		}catch(Exception e) {System.out.println("haha");}
	}
}
