package airBnB_app;

import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.CardLayout;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import java.awt.Button;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
class Functions{
	static Boolean signUpInfoCheck(HashMap<String, User> users, JTextField username, JTextField name, JPasswordField password, JPasswordField recheck, JCheckBox check, JLabel usernamelabel, JLabel namelabel, JLabel passwordlabel, JLabel passwordchecklabel) {
		Boolean ok=true;
		JLabel arr[]=new JLabel[] {usernamelabel, namelabel, passwordlabel, passwordchecklabel};
		clear(arr);
		check.setBackground(Color.white);
		if(users.containsKey(username.getText())) {
		usernamelabel.setText("The Username is Taken!");
		ok=false;
	}
	if(username.getText().equals("")) {
		usernamelabel.setText("Username cannot be Empty!");
		ok=false;
	}
	if(name.getText().equals("")) {
		namelabel.setText("The name cannot be Empty!");
		ok=false;
	}
	if(password.getText().equals("")) {
		passwordlabel.setText("The Password cannot be Empty!");
		ok=false;
	}
	else if(!recheck.getText().equals(password.getText())) {
		passwordchecklabel.setText("Passwords Do not Match!");
		ok=false;
	}
	if(!check.isSelected()) {
		check.setBackground(Color.red);
		ok=false;
	}
	check.setSelected(false);
		return ok;
	}
	static Boolean signUpInfoCheck(HashMap<String, User> users, JTextField username, JTextField name, JPasswordField password, JPasswordField recheck, JTextField emailID, JCheckBox check, JLabel usernamelabel, JLabel namelabel, JLabel passwordlabel, JLabel passwordchecklabel, JLabel emailcheck) {
		emailcheck.setText("");
		String email=emailID.getText();
		Boolean ok=signUpInfoCheck(users, username, name, password, recheck, check, usernamelabel, namelabel, passwordlabel, passwordchecklabel);
		if(emailID.getText().equals("")) {
			emailcheck.setText("Email Cannot be empty");
			ok=false;
		}
		else if(email.indexOf("@")==-1||email.indexOf(".")==email.length()-1||email.lastIndexOf("@")>email.lastIndexOf(".")||email.indexOf(".")==-1||email.indexOf(" ")!=-1) {
			emailcheck.setText("Invalid Email Address!");
			ok=false;
		}
		return ok;
	}
	static void clear(JLabel[] arr) {
		for(int i=0;i<arr.length;i++) {
			arr[i].setText("");
		}
	}
	static void clear(JTextComponent[] arr) {
		for(int i=0;i<arr.length;i++) {
			arr[i].setText("");
		}
		
	}
}
public class Main extends JFrame {
	User currentUser;
	
	int signInTries=3;  //number of tries to sign in after which it will turn off
	
	private JPanel contentPane;
	private JTextField signInUsername;
	private JPasswordField signInPassword;
	private JTextField customerSignupUsername;	
	private JTextField customerSignupName;
	private JPasswordField customerSignupPassword;
	private JPasswordField customerPasswordConfirm;
	private JTextField managerSignupUsername;
	private JTextField managerSignupName;
	private JPasswordField managerPasswordEnter;
	private JPasswordField managerPasswordReEnter;
	private JTextField managerEmailID;
	private JTextField date;
	private JTextField noOfDays;
	private JTextField propertyName;
	private JTextField propertyPricePerNight;
	private JTextField propertyAddress;
	Datasaver b = new Datasaver();
	private JTextField bookingIndex;
	int start=0, days=0;
	private JTextField cancelBookingIndex;
	public static void main(String[] args)throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class Datasaver implements Serializable{
		private static final long serialVersionUID = 1L;
		HashMap<String, User> users=new HashMap<String, User>();
		HashMap<String, Property> properties=new HashMap<String, Property>();
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		public Datasaver(String username, User user1) {
			users.put(username, user1);
			}
		public Datasaver(String str1, Property pro) {
			properties.put(str1, pro);
		}
		public Datasaver() {}
	}
	public Main() throws IOException {
		try {
			FileInputStream fis = new FileInputStream("data.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			b = (Datasaver)ois.readObject();
			ois.close();
			fis.close();
			
		}
		catch (Exception e){
			System.out.println("NO usernames!");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel main = new JPanel();
		contentPane.add(main, "name_352439749772100");
		main.setLayout(null);
		
		JPanel signupoptions = new JPanel();
		contentPane.add(signupoptions, "name_353765212025300");
		signupoptions.setLayout(null);
		
		JPanel signupasManager = new JPanel();
		contentPane.add(signupasManager, "name_369356274267900");
		signupasManager.setLayout(null);
		
		JButton backtosignupoptions_1 = new JButton("Back");
		
		backtosignupoptions_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backtosignupoptions_1.setBounds(40, 53, 124, 40);
		signupasManager.add(backtosignupoptions_1);
		
		JLabel newUsername_1 = new JLabel("Enter Username");
		newUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newUsername_1.setBounds(180, 180, 182, 32);
		signupasManager.add(newUsername_1);
		
		managerSignupUsername = new JTextField();
		managerSignupUsername.setColumns(10);
		managerSignupUsername.setBounds(393, 180, 513, 32);
		signupasManager.add(managerSignupUsername);
		
		JLabel lblEnterName_1 = new JLabel("Enter Name");
		lblEnterName_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterName_1.setBounds(180, 237, 182, 32);
		signupasManager.add(lblEnterName_1);
		
		managerSignupName = new JTextField();
		managerSignupName.setColumns(10);
		managerSignupName.setBounds(393, 241, 513, 32);
		signupasManager.add(managerSignupName);
		
		JLabel newPassword_1 = new JLabel("Enter Password");
		newPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPassword_1.setBounds(180, 302, 182, 32);
		signupasManager.add(newPassword_1);
		
		managerPasswordEnter = new JPasswordField();
		managerPasswordEnter.setColumns(10);
		managerPasswordEnter.setBounds(393, 302, 513, 32);
		signupasManager.add(managerPasswordEnter);
		
		JLabel confirmpassword_1 = new JLabel("Re-Enter Password");
		confirmpassword_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmpassword_1.setBounds(180, 358, 182, 32);
		signupasManager.add(confirmpassword_1);
		
		managerPasswordReEnter = new JPasswordField();
		managerPasswordReEnter.setColumns(10);
		managerPasswordReEnter.setBounds(393, 358, 513, 32);
		signupasManager.add(managerPasswordReEnter);
		
		JButton managerSignupFinal = new JButton("Sign Up!");
		
		managerSignupFinal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		managerSignupFinal.setBounds(393, 574, 513, 40);
		signupasManager.add(managerSignupFinal);
		
		JLabel signUpAsManagerLogo = new JLabel("");
		signUpAsManagerLogo.setBounds(1021, 11, 223, 205);
		signupasManager.add(signUpAsManagerLogo);
		signUpAsManagerLogo.setIcon(new ImageIcon(new ImageIcon("airbnb_sec_logo.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		
		JLabel confirmpassword_1_1 = new JLabel("Enter Email Address");
		confirmpassword_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmpassword_1_1.setBounds(180, 422, 182, 32);
		signupasManager.add(confirmpassword_1_1);
		
		managerEmailID = new JTextField();
		managerEmailID.setColumns(10);
		managerEmailID.setBounds(393, 422, 513, 32);
		signupasManager.add(managerEmailID);
		
		JLabel lblNewLabel_2_1 = new JLabel("I have read the terms and conditions and the privacy policy");
		lblNewLabel_2_1.setBounds(201, 516, 367, 14);
		signupasManager.add(lblNewLabel_2_1);
		
		JCheckBox managerTermsAndConditions = new JCheckBox("");
		managerTermsAndConditions.setHorizontalAlignment(SwingConstants.CENTER);
		managerTermsAndConditions.setFont(new Font("Tahoma", Font.PLAIN, 9));
		managerTermsAndConditions.setBounds(180, 516, 15, 14);
		signupasManager.add(managerTermsAndConditions);
		
		JLabel managerpasswordconfirmLabel = new JLabel("");
		managerpasswordconfirmLabel.setForeground(Color.RED);
		managerpasswordconfirmLabel.setBounds(393, 387, 513, 14);
		signupasManager.add(managerpasswordconfirmLabel);
		
		JLabel managerpasswordlabel = new JLabel("");
		managerpasswordlabel.setForeground(Color.RED);
		managerpasswordlabel.setBounds(393, 330, 513, 14);
		signupasManager.add(managerpasswordlabel);
		
		JLabel managernamelabel = new JLabel("");
		managernamelabel.setForeground(Color.RED);
		managernamelabel.setBounds(393, 271, 513, 14);
		signupasManager.add(managernamelabel);
		
		JLabel managerusernamelabel = new JLabel("");
		managerusernamelabel.setForeground(Color.RED);
		managerusernamelabel.setBounds(393, 209, 513, 14);
		signupasManager.add(managerusernamelabel);
		
		JLabel managerEmailIDLabel = new JLabel("");
		managerEmailIDLabel.setForeground(Color.RED);
		managerEmailIDLabel.setBounds(393, 452, 513, 14);
		signupasManager.add(managerEmailIDLabel);
		
		JPanel addPropertyPage = new JPanel();
		contentPane.add(addPropertyPage, "name_433831356572100");
		addPropertyPage.setLayout(null);
		
		JButton backToUserPage = new JButton("Back");
		
		backToUserPage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backToUserPage.setBounds(40, 53, 124, 40);
		addPropertyPage.add(backToUserPage);
		
		JLabel addPropertyLogo = new JLabel("");
		addPropertyLogo.setHorizontalAlignment(SwingConstants.CENTER);
		addPropertyLogo.setBounds(543, 0, 109, 107);
		addPropertyPage.add(addPropertyLogo);
		addPropertyLogo.setIcon(new ImageIcon(new ImageIcon("userpage logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		
		JLabel lblNewLabel_3 = new JLabel("Name of Property");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(313, 224, 157, 40);
		addPropertyPage.add(lblNewLabel_3);
		
		propertyName = new JTextField();
		propertyName.setBounds(480, 230, 338, 32);
		addPropertyPage.add(propertyName);
		propertyName.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Address");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(313, 302, 157, 40);
		addPropertyPage.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Price Per Night");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_2.setBounds(317, 393, 157, 40);
		addPropertyPage.add(lblNewLabel_3_2);
		
		propertyPricePerNight = new JTextField();
		propertyPricePerNight.setColumns(10);
		propertyPricePerNight.setBounds(480, 399, 338, 32);
		addPropertyPage.add(propertyPricePerNight);
		
		JButton addPropertyFinal = new JButton("Add Property");
		
		addPropertyFinal.setBounds(484, 485, 331, 76);
		addPropertyPage.add(addPropertyFinal);
		
		propertyAddress = new JTextField();
		propertyAddress.setColumns(10);
		propertyAddress.setBounds(480, 314, 708, 32);
		addPropertyPage.add(propertyAddress);
		
		JLabel propertyNameLabel = new JLabel("");
		propertyNameLabel.setForeground(Color.RED);
		propertyNameLabel.setBounds(480, 263, 513, 14);
		addPropertyPage.add(propertyNameLabel);
		
		JLabel propertyAddressLabel = new JLabel("");
		propertyAddressLabel.setForeground(Color.RED);
		propertyAddressLabel.setBounds(480, 345, 513, 14);
		addPropertyPage.add(propertyAddressLabel);
		
		JLabel propertyPriceLabel = new JLabel("");
		propertyPriceLabel.setForeground(Color.RED);
		propertyPriceLabel.setBounds(480, 429, 513, 14);
		addPropertyPage.add(propertyPriceLabel);
		
		JPanel userPage = new JPanel();
		userPage.setBackground(new Color(245, 245, 245));
		contentPane.add(userPage, "name_376792899867800");
		userPage.setLayout(null);
		
		JLabel userPageLogo = new JLabel("");
		userPageLogo.setHorizontalAlignment(SwingConstants.CENTER);
		userPageLogo.setBounds(543, 0, 109, 107);
		userPage.add(userPageLogo);
		userPageLogo.setIcon(new ImageIcon(new ImageIcon("userpage logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		
		JPanel managerProperties = new JPanel();
		managerProperties.setBounds(966, 201, 278, 459);
		userPage.add(managerProperties);
		managerProperties.setLayout(null);
		
		JButton addProperty = new JButton("Add Property");
		addProperty.setBounds(10, 416, 258, 32);
		managerProperties.add(addProperty);
		
		JList managerPropertiesList = new JList();
		managerPropertiesList.setBounds(0, 25, 278, 391);
		managerProperties.add(managerPropertiesList);
		
		JLabel lblNewLabel_6 = new JLabel("Properties Added by you");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 0, 258, 25);
		managerProperties.add(lblNewLabel_6);
		
		JLabel welcome = new JLabel("Welcome!");
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 22));
		welcome.setBounds(26, 26, 104, 53);
		userPage.add(welcome);
		
		JLabel userNameOfUser = new JLabel("");
		userNameOfUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		userNameOfUser.setBounds(125, 26, 284, 53);
		userPage.add(userNameOfUser);
		
		Button logOutButton = new Button("LogOut");
		logOutButton.setBounds(1089, 26, 126, 53);
		userPage.add(logOutButton);
		
		JLabel nameOfUser = new JLabel("");
		nameOfUser.setBounds(26, 69, 255, 36);
		userPage.add(nameOfUser);
		
		JLabel balanceOfUser = new JLabel("Current Account Balance: ");
		balanceOfUser.setBounds(26, 110, 255, 36);
		userPage.add(balanceOfUser);
		
		JList bookingsList = new JList();
		bookingsList.setBounds(10, 240, 355, 376);
		userPage.add(bookingsList);
		
		JList searchResultList = new JList();
		searchResultList.setBounds(396, 201, 523, 459);
		userPage.add(searchResultList);
		
		date = new JTextField();
		date.setBounds(527, 118, 47, 20);
		userPage.add(date);
		date.setColumns(10);
		
		noOfDays = new JTextField();
		noOfDays.setColumns(10);
		noOfDays.setBounds(711, 118, 47, 20);
		userPage.add(noOfDays);
		
		JLabel lblNewLabel = new JLabel("Check In Date");
		lblNewLabel.setBounds(401, 121, 116, 14);
		userPage.add(lblNewLabel);
		
		JLabel lblNumberOfDays = new JLabel("Number Of Days");
		lblNumberOfDays.setBounds(584, 121, 117, 14);
		userPage.add(lblNumberOfDays);
		
		JButton searchProperty = new JButton("Search");
		
		searchProperty.setBounds(803, 104, 116, 36);
		userPage.add(searchProperty);
		
		bookingIndex = new JTextField();
		bookingIndex.setBounds(683, 169, 86, 20);
		userPage.add(bookingIndex);
		bookingIndex.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Enter the Index of the Property You want to Book!");
		lblNewLabel_4.setBounds(396, 172, 300, 14);
		userPage.add(lblNewLabel_4);
		
		JButton bookFinal = new JButton("Book Now!");
		
		bookFinal.setBounds(803, 151, 116, 40);
		userPage.add(bookFinal);

		JPanel signupasCustomer = new JPanel();
		contentPane.add(signupasCustomer, "name_352424517883600");
		signupasCustomer.setLayout(null);
		
		JButton signUpAsAM = new JButton("As a Manager");
		
		signUpAsAM.setBounds(466, 186, 274, 92);
		signupoptions.add(signUpAsAM);
		
		JButton signupAsAC = new JButton("As a Customer");
		
		signupAsAC.setBounds(466, 313, 274, 92);
		signupoptions.add(signupAsAC);
		
		JButton backtomain = new JButton("Back");
		
		backtomain.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backtomain.setBounds(40, 53, 124, 40);
		signupoptions.add(backtomain);
		
		JLabel signupoptions_logo = new JLabel("");
		signupoptions.add(signupoptions_logo);
		signupoptions_logo.setIcon(new ImageIcon(new ImageIcon("airbnb_sec_logo.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		signupoptions_logo.setBounds(1021, 11, 223, 205);
		
		JLabel mainPageLogo = new JLabel("");
		mainPageLogo.setIcon(new ImageIcon(new ImageIcon("airbnb-logo.png").getImage().getScaledInstance(300, 225, Image.SCALE_DEFAULT)));
		mainPageLogo.setBounds(494, 62, 356, 212);
		main.add(mainPageLogo);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(158, 312, 103, 36);
		main.add(lblNewLabel_1);
		
		signInUsername = new JTextField();
		signInUsername.setColumns(10);
		signInUsername.setBounds(273, 318, 784, 32);
		main.add(signInUsername);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(158, 383, 103, 36);
		main.add(lblNewLabel_1_1);
		
		signInPassword = new JPasswordField();
		signInPassword.setColumns(10);
		signInPassword.setBounds(273, 389, 784, 32);
		main.add(signInPassword);
		
		JButton btnLogIn = new JButton("LogIn");
		btnLogIn.setBounds(273, 463, 378, 55);
		main.add(btnLogIn);
		
		JButton btnSignup = new JButton("SignUp");
		
		btnSignup.setBounds(679, 463, 378, 55);
		main.add(btnSignup);
		
		JLabel incorrectUsername = new JLabel("");
		incorrectUsername.setForeground(Color.RED);
		incorrectUsername.setBounds(273, 361, 260, 14);
		main.add(incorrectUsername);
		
		JLabel incorrectPassword = new JLabel("");
		incorrectPassword.setForeground(Color.RED);
		incorrectPassword.setBounds(273, 432, 289, 14);
		main.add(incorrectPassword);
		
		
		JButton backtosignupoptions = new JButton("Back");
		
		backtosignupoptions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backtosignupoptions.setBounds(40, 53, 124, 40);
		signupasCustomer.add(backtosignupoptions);
		
		customerSignupUsername = new JTextField();
		customerSignupUsername.setColumns(10);
		customerSignupUsername.setBounds(393, 251, 513, 32);
		signupasCustomer.add(customerSignupUsername);
		
		JLabel newUsername = new JLabel("Enter Username");
		newUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newUsername.setBounds(180, 251, 182, 32);
		signupasCustomer.add(newUsername);
		
		JLabel lblEnterName = new JLabel("Enter Name");
		lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterName.setBounds(180, 308, 182, 32);
		signupasCustomer.add(lblEnterName);
		
		customerSignupName = new JTextField();
		customerSignupName.setColumns(10);
		customerSignupName.setBounds(393, 312, 513, 32);
		signupasCustomer.add(customerSignupName);
		
		customerSignupPassword = new JPasswordField();
		customerSignupPassword.setColumns(10);
		customerSignupPassword.setBounds(393, 373, 513, 32);
		signupasCustomer.add(customerSignupPassword);
		
		JLabel newPassword = new JLabel("Enter Password");
		newPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPassword.setBounds(180, 373, 182, 32);
		signupasCustomer.add(newPassword);
		
		JLabel confirmpassword = new JLabel("Re-Enter Password");
		confirmpassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		confirmpassword.setBounds(180, 429, 182, 32);
		signupasCustomer.add(confirmpassword);
		
		customerPasswordConfirm = new JPasswordField();
		customerPasswordConfirm.setColumns(10);
		customerPasswordConfirm.setBounds(393, 429, 513, 32);
		signupasCustomer.add(customerPasswordConfirm);
		
		JLabel signupAsCustomerLogo = new JLabel("");
		signupAsCustomerLogo.setIcon(new ImageIcon(new ImageIcon("airbnb_sec_logo.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		signupAsCustomerLogo.setBounds(1021, 11, 223, 205);
		signupasCustomer.add(signupAsCustomerLogo);
		
		JButton cutomerSignUpFinal = new JButton("Sign Up!");
		cutomerSignUpFinal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cutomerSignUpFinal.setBounds(393, 574, 513, 40);
		signupasCustomer.add(cutomerSignUpFinal);
		
		JCheckBox customerTermsAndConditions = new JCheckBox("");
		customerTermsAndConditions.setHorizontalAlignment(SwingConstants.CENTER);
		customerTermsAndConditions.setFont(new Font("Tahoma", Font.PLAIN, 9));
		customerTermsAndConditions.setBounds(186, 517, 15, 14);
		signupasCustomer.add(customerTermsAndConditions);
		
		JLabel customerusernamelabel = new JLabel("");
		customerusernamelabel.setForeground(Color.RED);
		customerusernamelabel.setBounds(393, 283, 513, 14);
		signupasCustomer.add(customerusernamelabel);
		
		JLabel customernamelabel = new JLabel("");
		customernamelabel.setForeground(Color.RED);
		customernamelabel.setBounds(393, 341, 513, 14);
		signupasCustomer.add(customernamelabel);
		
		JLabel customerpasswordlabel = new JLabel("");
		customerpasswordlabel.setForeground(Color.RED);
		customerpasswordlabel.setBounds(393, 404, 513, 14);
		signupasCustomer.add(customerpasswordlabel);
		
		JLabel customerpasswordconfirmLabel = new JLabel("");
		customerpasswordconfirmLabel.setForeground(Color.RED);
		customerpasswordconfirmLabel.setBounds(393, 461, 513, 14);
		signupasCustomer.add(customerpasswordconfirmLabel);
		
		DefaultListModel dlm = new DefaultListModel();
		DefaultListModel dlmBookings = new DefaultListModel();
		DefaultListModel dlmSearch = new DefaultListModel();
		searchResultList.setModel(dlmSearch);
		bookingsList.setModel(dlmBookings);
		
		cancelBookingIndex = new JTextField();
		cancelBookingIndex.setBounds(174, 640, 55, 20);
		userPage.add(cancelBookingIndex);
		cancelBookingIndex.setColumns(10);
		
		JButton cancelBooking = new JButton("Cancel Booking");
		
		cancelBooking.setBounds(239, 627, 126, 33);
		userPage.add(cancelBooking);
		
		JLabel lblNewLabel_5 = new JLabel((String) null);
		lblNewLabel_5.setBounds(1089, 214, 46, 14);
		userPage.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("My Current Bookings");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(10, 214, 215, 28);
		userPage.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Enter Index of Booking you ");
		lblNewLabel_8.setBounds(20, 620, 154, 24);
		userPage.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("want to cancel");
		lblNewLabel_8_1.setBounds(20, 636, 154, 24);
		userPage.add(lblNewLabel_8_1);

		
		ArrayList<Property> searchedProperties=new ArrayList<Property>();
		
		JLabel lblNewLabel_2 = new JLabel("I have read the terms and conditions and the privacy policy");
		lblNewLabel_2.setBounds(207, 517, 367, 14);
		signupasCustomer.add(lblNewLabel_2);
		incorrectUsername.setVisible(true);
		incorrectPassword.setVisible(true);
		backtosignupoptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField arr[]=new JTextField[] {customerSignupUsername, customerSignupName, customerSignupPassword, customerPasswordConfirm};
				Functions.clear(arr);
				JLabel arr2[]=new JLabel[] {customernamelabel, customerpasswordconfirmLabel, customerusernamelabel, customerpasswordlabel};
				Functions.clear(arr2);
				customerTermsAndConditions.setSelected(false);
				customerTermsAndConditions.setBackground(Color.white);
				signupasCustomer.setVisible(false);
				signupoptions.setVisible(true);
			}
		});
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupoptions.setVisible(true);
				main.setVisible(false);
			}
		});
		backtomain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				signupoptions.setVisible(false);
			}
		});
		signupAsAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupasCustomer.setVisible(true);
				signupoptions.setVisible(false);
			}
		});
		signUpAsAM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupoptions.setVisible(false);
				signupasManager.setVisible(true);	
			}
		});
		backtosignupoptions_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField[] arr=new JTextField[] {managerSignupUsername, managerEmailID, managerSignupName, managerPasswordEnter, managerPasswordReEnter};
				Functions.clear(arr);
				JLabel[] arr2=new JLabel[] {managerusernamelabel, managerEmailIDLabel, managernamelabel, managerpasswordlabel, managerpasswordconfirmLabel};
				Functions.clear(arr2);
				managerTermsAndConditions.setSelected(false);
				managerTermsAndConditions.setBackground(Color.white);
				signupasManager.setVisible(false);
				signupoptions.setVisible(true);
			}
		});
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNameOfUser.setText("");
				JLabel[] arr=new JLabel[]{userNameOfUser, balanceOfUser, nameOfUser};
				Functions.clear(arr);
				JTextField[] arr2= new JTextField[] {date, noOfDays, bookingIndex, cancelBookingIndex};
				Functions.clear(arr2);
				dlm.clear();
				dlmBookings.clear();
				dlmSearch.clear();
				searchedProperties.clear();
				main.setVisible(true);
				userPage.setVisible(false);
			}
		});
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incorrectUsername.setText("");
				incorrectPassword.setText("");
				if(!b.users.containsKey(signInUsername.getText())) {
					if(signInUsername.getText().equals(""))
						incorrectUsername.setText("Enter a Username!");
					else
						incorrectUsername.setText("Username not Found!");
					--signInTries;
				}
				else if(!b.users.get(signInUsername.getText()).getPassword().equals(signInPassword.getText())) {
					incorrectPassword.setText("Wrong Password!");
					--signInTries;
				}
				else {
					if(b.users.get(signInUsername.getText()).getIsManager()) {
						currentUser=(Manager) b.users.get(signInUsername.getText());
						dlm.addAll(currentUser.getAddedProperties());
						managerPropertiesList.setModel(dlm);
					}
					else
					currentUser=b.users.get(signInUsername.getText());
					main.setVisible(false);
					signInUsername.setText("");
					signInPassword.setText("");
					userNameOfUser.setText(currentUser.getUsername());
					balanceOfUser.setText("Current Account Balance: "+Float.toString(currentUser.getBalance())+" Rs");
					nameOfUser.setText(currentUser.getName());
					managerProperties.setVisible(currentUser.getIsManager());
					userPage.setVisible(true);
					dlmBookings.addAll(currentUser.getUserBookingsList());
					signInTries=3;
				}
				if(signInTries==0) {
					JOptionPane.showMessageDialog(contentPane, "Due to too many Sign in failures, the application will be closing!", "Too many Sign in Failures!", JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
			}
		});
		cutomerSignUpFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Functions.signUpInfoCheck(b.users, customerSignupUsername, customerSignupName, customerSignupPassword, customerPasswordConfirm, customerTermsAndConditions, customerusernamelabel, customernamelabel, customerpasswordlabel, customerpasswordconfirmLabel)) {
					User temp=new Customer(customerSignupUsername.getText(), customerSignupName.getText(), customerSignupPassword.getText());
					b.users.put(customerSignupUsername.getText(), temp);
					customerSignupUsername.setText("");
					customerSignupName.setText("");
					customerSignupPassword.setText("");
					customerPasswordConfirm.setText("");
					
					try {
						FileOutputStream fos = new FileOutputStream("data.txt");
					    ObjectOutputStream oos = new ObjectOutputStream (fos);
					    oos.writeObject(b);
					    oos.close();
					    fos.close();
					    }
					catch(Exception e1) {
					}
					JOptionPane.showMessageDialog(contentPane, "You have successfully signed up!", "Signup Succesful", JOptionPane.DEFAULT_OPTION);
					currentUser=temp;
					userNameOfUser.setText(currentUser.getUsername());
					balanceOfUser.setText(balanceOfUser.getText()+Float.toString(currentUser.getBalance())+" Rs");
					nameOfUser.setText(currentUser.getName());
					managerProperties.setVisible(currentUser.getIsManager());
					signupasCustomer.setVisible(false);
					userPage.setVisible(true);
					
					
				}
			}
		});
		managerSignupFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Functions.signUpInfoCheck(b.users, managerSignupUsername, managerSignupName, managerPasswordEnter, managerPasswordReEnter, managerEmailID ,managerTermsAndConditions, managerusernamelabel, managernamelabel, managerpasswordlabel, managerpasswordconfirmLabel, managerEmailIDLabel)) {
					User temp=new Manager(managerSignupUsername.getText(), managerSignupName.getText(), managerPasswordEnter.getText(), managerEmailID.getText());
					b.users.put(managerSignupUsername.getText(), temp);
					JTextField arr[] = new JTextField[] {managerSignupName, managerEmailID, managerSignupUsername, managerPasswordEnter, managerPasswordReEnter};
					Functions.clear(arr);
					
					try {
						FileOutputStream fos = new FileOutputStream("data.txt");
					    ObjectOutputStream oos = new ObjectOutputStream (fos);
					    oos.writeObject(b);
					    oos.close();
					    fos.close();
					    }
					catch(Exception e1) {
						
					}
					JOptionPane.showMessageDialog(contentPane, "You have successfully signed up!", "Signup Succesful", JOptionPane.DEFAULT_OPTION);
					currentUser=temp;
					userNameOfUser.setText(currentUser.getUsername());
					balanceOfUser.setText(balanceOfUser.getText()+Float.toString(currentUser.getBalance())+" Rs");
					nameOfUser.setText(currentUser.getName());
					managerProperties.setVisible(currentUser.getIsManager());
					signupasManager.setVisible(false);
					userPage.setVisible(true);
				}
			}
		});
		addProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPage.setVisible(false);
				addPropertyPage.setVisible(true);
			}
		});
		backToUserPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField[] arr=new JTextField[] {propertyAddress, propertyName, propertyPricePerNight};
				Functions.clear(arr);
				addPropertyPage.setVisible(false);
				userPage.setVisible(true);
			}
		});
		addPropertyFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean errorfound=false;
				if(b.properties.containsKey(propertyName.getText())) {
					propertyNameLabel.setText("This Property Already Exists!");
					errorfound=true;
				}
				if(propertyAddress.getText().equals("")) {
					propertyAddressLabel.setText("Address cannot be empty!");
					errorfound=true;
				}
				if(Float.parseFloat(propertyPricePerNight.getText())<200) {
					propertyPriceLabel.setText("Price per Night cannot be less than 200!");
					errorfound=true;
				}
				if(!errorfound) {
					Property temp=new Property(currentUser.getUsername(), propertyName.getText(), propertyAddress.getText(), Float.parseFloat(propertyPricePerNight.getText()));
					b.users.get(currentUser.getUsername()).addProperty(temp);
					b.properties.put(temp.getName(), temp);
					try {
						FileOutputStream fos = new FileOutputStream("data.txt");
					    ObjectOutputStream oos = new ObjectOutputStream (fos);
					    oos.writeObject(b);
					    oos.close();
					    fos.close();
					    }
					catch(Exception e2) {
						
					}
					JTextField[] arr=new JTextField[] {propertyAddress, propertyName, propertyPricePerNight};
					Functions.clear(arr);
					JLabel[] arr2 = new JLabel[] {propertyAddressLabel, propertyNameLabel, propertyPriceLabel};
					Functions.clear(arr2);
					dlm.addElement(temp);
					JOptionPane.showMessageDialog(contentPane, "Property Successfully Added!", "Property Added!", JOptionPane.DEFAULT_OPTION);
				}
				
			}
		});
		searchProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean errorfound=false;
				try {
					start=Integer.parseInt(date.getText());
					days=Integer.parseInt(noOfDays.getText());
				}
				catch (Exception e3) {
					JOptionPane.showMessageDialog(contentPane, "Values you have entered are not Valid!", "Invalid Entries!", JOptionPane.WARNING_MESSAGE);
					errorfound=true;
				}
				if(!errorfound) {
					if(start<1||start>30||start+days>31||days<1) {
						JOptionPane.showMessageDialog(contentPane, "Values you have entered are not Valid!", "Invalid Entries!", JOptionPane.WARNING_MESSAGE);
						errorfound=true;
					}
				}
				if(!errorfound) {
					searchedProperties.clear();
					dlmSearch.clear();
					Iterator it = b.properties.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pair = (Map.Entry)it.next();
				        if(((Property)pair.getValue()).enquiry(start, days)) {
				        	searchedProperties.add((Property)pair.getValue());
				        	dlmSearch.addElement((Property)pair.getValue());
				        } 
				    }
				}
			}
		});
		bookFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean errorfound=false;
				int index=-1;
				try {
					index=Integer.parseInt(bookingIndex.getText());
					if(index>searchedProperties.size())
						throw new Exception();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "The Index you have entered is not valid", "Invalid Index!", JOptionPane.WARNING_MESSAGE);
					errorfound=true;
				}
				if(!errorfound) {
					float payment=searchedProperties.get(index-1).getPricePerNight()*days;
					if(currentUser.getBalance()<payment) {
						JOptionPane.showMessageDialog(contentPane, "You Don't have enough balance!", "Insufficient Balance!", JOptionPane.WARNING_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "You have successfully Booked this Property!", "Booking Successful!", JOptionPane.DEFAULT_OPTION);
						Booking temp = new Booking(currentUser, searchedProperties.get(index-1), start, days);
						b.bookings.add(temp);
						currentUser.book(temp);
						searchedProperties.get(index-1).setBooked(start, days);
						dlmBookings.addElement(temp);
						b.users.get(searchedProperties.get(index-1).getManager()).payment(payment);
						balanceOfUser.setText("Current Account Balance: "+currentUser.getBalance()+" Rs");
						try {
							FileOutputStream fos = new FileOutputStream("data.txt");
						    ObjectOutputStream oos = new ObjectOutputStream (fos);
						    oos.writeObject(b);
						    oos.close();
						    fos.close();
						    }
						catch(Exception e1) {
						}
						date.setText("");
						noOfDays.setText("");
						bookingIndex.setText("");
						dlmSearch.clear();
					}
				}
			}
		});
		cancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean errorfound=false;
				int index=0;
				try {
					index=Integer.parseInt(cancelBookingIndex.getText());
					if(index>currentUser.getUserBookingsList().size())
						throw new Exception();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "The Index you have entered is not valid", "Invalid Index!", JOptionPane.WARNING_MESSAGE);
					errorfound=true;
				}
				if(!errorfound) {
					JOptionPane.showMessageDialog(contentPane, "Your Selected Booking has been successfully cancelled!", "Booking Cancelled!", JOptionPane.DEFAULT_OPTION);
					Booking temp = currentUser.getUserBookingsList().get(index-1);
					b.bookings.remove(temp);
					b.users.get(temp.getProperty().getManager()).setBalance(b.users.get(temp.getProperty().getManager()).getBalance()-(temp.getProperty().getPricePerNight()-100)*temp.getNoOfDays());
					currentUser.cancelBooking(temp);
					temp.getProperty().cancelBooking(temp);
					balanceOfUser.setText("Current Account Balance: "+currentUser.getBalance()+" Rs");
					cancelBookingIndex.setText("");
					dlmBookings.clear();
					dlmBookings.addAll(currentUser.getUserBookingsList());
					try {
						FileOutputStream fos = new FileOutputStream("data.txt");
					    ObjectOutputStream oos = new ObjectOutputStream (fos);
					    oos.writeObject(b);
					    oos.close();
					    fos.close();
					    }
					catch(Exception e1) {
					}
				}
			}
		});
	}
}