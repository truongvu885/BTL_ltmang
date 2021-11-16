package Bai4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client_giaodien extends JFrame {

	private JPanel contentPane;
	private JTextField txbGui;
	private static Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					socket = new Socket("127.0.0.1",3000);
					client_giaodien frame = new client_giaodien();
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
	public client_giaodien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		txbGui = new JTextField();
		GridBagConstraints gbc_txbGui = new GridBagConstraints();
		gbc_txbGui.insets = new Insets(0, 0, 5, 0);
		gbc_txbGui.fill = GridBagConstraints.HORIZONTAL;
		gbc_txbGui.gridx = 4;
		gbc_txbGui.gridy = 1;
		contentPane.add(txbGui, gbc_txbGui);
		txbGui.setColumns(10);
		
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 4;
		gbc_textArea.gridy = 5;
		contentPane.add(textArea, gbc_textArea);
		
		JButton btnGui = new JButton("Gửi");
		btnGui.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
					DataInputStream data_in = new DataInputStream(socket.getInputStream());
					
					
						String chuoi_so = txbGui.getText();
						int so = Integer.parseInt(chuoi_so);
						
						
						
							data_out.write(so);
							data_out.flush();
							
							String str = data_in.readUTF();
							textArea.setText(str);
							txbGui.setText("");
						
				
					
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
		});
		GridBagConstraints gbc_btnGui = new GridBagConstraints();
		gbc_btnGui.insets = new Insets(0, 0, 5, 0);
		gbc_btnGui.anchor = GridBagConstraints.SOUTH;
		gbc_btnGui.gridx = 4;
		gbc_btnGui.gridy = 3;
		contentPane.add(btnGui, gbc_btnGui);
		
	}

}
