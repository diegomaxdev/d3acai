package PrincipalMain;

import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;


public class Calendario extends JDialog {

	private JPanel contentPane;
	
	public Calendario() {
		
		setTitle("Calendário");
		setResizable(false);
		setBounds(100, 100, 570, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(25, 25,  500, 380);
		desktopPane.add(calendar);
		
		repaint();
		setVisible(true);
	}
}

	
	
	

