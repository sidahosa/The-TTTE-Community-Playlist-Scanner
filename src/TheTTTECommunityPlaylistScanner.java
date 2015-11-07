package CommunityRemakesUtility;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JToolBar;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import javax.swing.JScrollPane;

/*
 * The Thomas and Friends Community channel's playlist scanner's
 * GUI built for The Thomas and Friends Community WIKIA page.
 */

public class TheTTTECommunityPlaylistScanner {

	private JFrame frame;
	YouTubeUserNameSorter newObj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    
	    EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					TheTTTECommunityPlaylistScanner window = new TheTTTECommunityPlaylistScanner();
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
	public TheTTTECommunityPlaylistScanner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Symbol", Font.PLAIN, 18));
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		frame.setBounds(100, 100, 450, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel yt = new JLabel("Enter the YouTube Playlist URL");
		yt.setBackground(Color.WHITE);
		yt.setFont(new Font("Tahoma", Font.BOLD, 22));
		yt.setForeground(new Color(255, 0, 0));
		yt.setBounds(52, 11, 343, 23);
		frame.getContentPane().add(yt);
		
		JTextArea urltext = new JTextArea();
		urltext.setBounds(10, 52, 414, 23);
		frame.getContentPane().add(urltext);
		
		JTextPane tttepanel = new JTextPane();
		tttepanel.setBounds(10, 118, 414, 391);
		frame.getContentPane().add(tttepanel);
		
		JScrollPane scrollPane = new JScrollPane(tttepanel);
		scrollPane.setBounds(10, 118, 414, 391);
		frame.getContentPane().add(scrollPane);
		JButton btnNewButton = new JButton("Run Scan");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url;
				try{
					url = urltext.getText();
					if ( !(url.contains("youtube.com")) ){
						JOptionPane.showMessageDialog(null, "This is not a "
								+ "YouTube link playlist Silly Engine!");
					}
					newObj = new YouTubeUserNameSorter(url);
					String playlisttext = newObj.runScan().toString();
					tttepanel.setText(playlisttext);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Help Me Thomas!");
				}
				
			}
		});
		
		btnNewButton.setBounds(177, 84, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblUseTheCut = new JLabel("Use the Cut, Copy, & Paste Keyboard commands");
		lblUseTheCut.setBounds(105, 37, 249, 14);
		frame.getContentPane().add(lblUseTheCut);
		
	
	}
}
