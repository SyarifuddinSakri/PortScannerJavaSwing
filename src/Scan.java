import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Scan extends JFrame{
	JPanel mainPanel = new JPanel();
	JButton scanButton = new JButton();
	JProgressBar bar = new JProgressBar();
	DefaultListModel<String> model = new DefaultListModel<>();
	JList<String>l = new JList<String>(model);
	JTextField ipTf = new JTextField();
	JTextField startTf = new JTextField();
	JTextField endTf = new JTextField();
	public Scan() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Color bg = new Color(4,122,177);
		
		JLabel title = new JLabel("Port Scanner");
		title.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 20));
		title.setForeground(new Color(255,255,255));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel west = new JPanel();
		JLabel ipLabel  = new JLabel("IP Address");
		ipLabel.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
		ipLabel.setForeground(new Color(255,255,255));
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel startLabel  = new JLabel("Start Port");
		startLabel.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
		startLabel.setForeground(new Color(255,255,255));
		startLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel endLabel  = new JLabel("End Port");
		endLabel.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
		endLabel.setForeground(new Color(255,255,255));
		endLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridLayout gl1 = new GridLayout(3,0,10,10);
		west.setLayout(gl1);
		west.add(ipLabel);
		west.add(startLabel);
		west.add(endLabel);
		west.setBorder(new EmptyBorder(10, 0, 0, 0));
		west.setBackground(bg);
		
		
		JPanel center = new JPanel();
		ipTf.setHorizontalAlignment(SwingConstants.CENTER);
		startTf.setHorizontalAlignment(SwingConstants.CENTER);
		endTf.setHorizontalAlignment(SwingConstants.CENTER);
		GridLayout gl2 = new GridLayout(3,0,0,35);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Please enter inputs");
		titledBorder.setTitleFont(new Font("Franklin Gothic Medium", Font.ITALIC, 14));
		titledBorder.setTitleColor(Color.WHITE); // Set the color to white
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		center.setLayout(gl2);
		center.add(ipTf);
		JPanel buffer1 = new JPanel();
		buffer1.setBackground(bg);
		buffer1.setLayout(new GridLayout(0,2,0,0));
		buffer1.add(startTf);
		center.add(buffer1);
		JPanel buffer2 = new JPanel();
		buffer2.setBackground(bg);
		buffer2.setLayout(new GridLayout(0,2,0,0));
		buffer2.add(endTf);
		center.add(buffer2);
		center.setBorder(titledBorder);
		center.setBackground(bg);
		
		JPanel east = new JPanel();
		GridLayout gl3 = new GridLayout(3,0,0,35);
		scanButton.setText("Scan");
		east.setLayout(gl3);
		east.setBackground(bg);
		east.add(Box.createVerticalStrut(20));
		east.add(scanButton);
		east.add(Box.createVerticalStrut(20));
		
		JPanel south = new JPanel();
		south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
		south.setPreferredSize(new Dimension(400, 200)); // Set preferred size for the panel


		bar.setMaximumSize(new Dimension(400, 30)); // Set maximum size for the progress bar
		bar.setStringPainted(true);
		bar.setBackground(Color.GREEN);
		JScrollPane scroll = new JScrollPane(l);
		scroll.setMaximumSize(new Dimension(400, 200)); // Set maximum size for the scroll pane
		south.setBackground(bg);
		south.add(bar);
		south.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing between components
		south.add(scroll);
		
		
		mainPanel.setLayout(new BorderLayout(10,10));
		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(west, BorderLayout.WEST);
		mainPanel.add(center, BorderLayout.CENTER);
		mainPanel.add(east, BorderLayout.EAST);
		mainPanel.add(south, BorderLayout.SOUTH);
		mainPanel.setBackground(bg);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20,5,5,5));
	}
	public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		Scan sc = new Scan();
		sc.add(sc.mainPanel);
		sc.setSize(450, 500);
		sc.setTitle("Port Scanner by SyarifuddinSakri");
		sc.setVisible(true);
		sc.setResizable(false);
		sc.setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		sc.scanButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sc.model.clear();
				sc.bar.setValue(0);

				try {
					String ip =sc.ipTf.getText();
					InetAddress lmao = InetAddress.getByName(ip);
					if (lmao.isReachable(5000)) {
						try {
							int startPort = Integer.parseInt(sc.startTf.getText());
							int endPort = Integer.parseInt(sc.endTf.getText());	
							if (startPort<=endPort) {
								sc.bar.setMaximum(endPort-startPort);
								ArrayList<Integer>range = new ArrayList<Integer>();
								for (int i = startPort; i<=endPort; i++) {
									range.add(i);
								}
								for (Integer arg:range) {
									Thread t1 = new Thread(()->{
										try {
											Socket s = new Socket(ip,arg);
											s.close();
											String pro="unknown";
											switch(arg) {
											case 20:pro="FTP";break;
											case 21:pro="FTP";break;
											case 22:pro="SSH";break;
											case 23:pro="Telnet";break;
											case 25:pro="SMTP";break;
											case 53:pro="DNS";break;
											case 80:pro="HTTP";break;
											case 110:pro="POP3";break;
											case 123:pro="NTP";break;
											case 135:pro="RPC-Microsoft Remote Procedural Call";break;
											case 161:pro="SNMP";break;
											case 162:pro="SNMP TRAP";break;
											case 443:pro="HTTPS";break;
											case 445:pro="Microsoft-DS SMB";break;
											case 502:pro="Modbus";break;
											case 554:pro="RTSP-Real Time Transport(Media)";break;
											case 587:pro="SMTP";break;
											case 1755:pro="MMS-Microsoft Media Service";break;
											case 1935:pro="RTMP-Real Time Messaging";break;
											case 1947:pro="Licensing for Autodesk/SolidWorks";break;
											case 2343:pro="NTP";break;
											case 3389:pro="RDP-Remote Desktop";break;
											case 4001:pro="Moxa Nport serial device server";break;
											case 4800:pro="Moxa MGate Industrial Protocol Gateway";break;
											case 4900:pro="Moxa OnCell cellular IP Gateway";break;
											case 5004:pro="RTP/RTCP-Real Time Transport(Media)";break;
											case 5005:pro="RTP/RTCP-Real Time Transport(Media)";break;
											case 5060:pro="SIP-Session Initiation(Media)";break;
											case 9502:pro="Moxa UPort USB to serial Converter";break;
											}
											sc.model.addElement("There is available port at : "+arg+" ("+pro+")");
											System.out.println("Port here : "+arg);
											SwingUtilities.invokeLater(()->{
												sc.bar.setValue(sc.bar.getValue()+1);
												});
										} catch (SecurityException e1) {
											SwingUtilities.invokeLater(()->{
												sc.bar.setValue(sc.bar.getValue()+1);
												});		
										} catch (IOException e1) {
											System.out.println("No Port available here : "+arg);
											SwingUtilities.invokeLater(()->{
												sc.bar.setValue(sc.bar.getValue()+1);
												});
										}
									});
									t1.start();
								}
							}else {
								JOptionPane.showMessageDialog(null, "End Port must be larger than Start Port","Port value Error", JOptionPane.ERROR_MESSAGE);
							}
							
						}catch(NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Start and End port must be an Integer","Port input Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Host Unreachable","Ping Failed", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(UnknownHostException e1) {
					JOptionPane.showMessageDialog(null, "Please check the IP Address","Invalid IP Address", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException e2) {
					JOptionPane.showMessageDialog(null, "An Error has Occured","Error", JOptionPane.ERROR_MESSAGE);
				}
			}	
		});	
	}
}
