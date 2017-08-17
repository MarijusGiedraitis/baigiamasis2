package com.narys.klase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.Narys;
import database.NarysDao;

public class UserForma extends JFrame implements ActionListener{

	String month[] = {"January", "February", "March", "April", "May", "June", "July",
	      	"August", "September", "October", "November", "December"};
	
	 
	
	public void actionPerformed(ActionEvent event){
		String name1 = this.name.getText().toString();
		String surname1 = this.surname.getText().toString();
		String email1 = this.email.getText().toString();
		String password1 = this.password.getText().toString();

		
		/*String confirmPassword1 = this.confirmPassword.getText().toString();*/

		if(!validateName(name1))
		JOptionPane.showMessageDialog(this, "First name field cannot contain numbers, symbols or be empty", "Error", 
				JOptionPane.ERROR_MESSAGE);
		
		else if(!validateSurname(surname1))
			JOptionPane.showMessageDialog(this, "Last name field cannot contain numbers, symbols or be empty", "Error",
					JOptionPane.ERROR_MESSAGE);
		
		else if(!validatePassword(password1))
			JOptionPane.showMessageDialog(this, "Password does not meet requirements! "
					+ "Your password must be at least 5 characters and contain "
					+ "one upper case, one lower case and one number",
					"Error", JOptionPane.ERROR_MESSAGE);
	
		/*else if(!validateConfirmPassword(confirmPassword1) && (password1!=confirmPassword1))
		JOptionPane.showMessageDialog(this, "Passwords does not much!",
				"Error", JOptionPane.ERROR_MESSAGE);*/
		
		else if(!validateEmail(email1))
			JOptionPane.showMessageDialog(this, "Please enter a valid email address, e.g. name@example.com", "Error",
					JOptionPane.ERROR_MESSAGE);
		
		else if(agree.isSelected()){
	   		JOptionPane.showMessageDialog(agree, "Congratulations! Your registration completed successfully! \n" +
	   		name1 + "\n" +
	   		surname1 + "\n" +
	        email1 + "\n" +
	        password1 + "\n", "New user created", JOptionPane.INFORMATION_MESSAGE);
	   		} else {
	   		JOptionPane.showMessageDialog(agree, "You must read and accept terms and conditions of cupid.com to finish your registration","Warning",
					JOptionPane.WARNING_MESSAGE);
	   		}
	   	}
	   		/*else if(confirmPassword1!=password1){
			JOptionPane.showMessageDialog(this, "CBB!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}*/
		
    public static boolean validateName(String nameStr) { 
		final Pattern VALID_NAME = 
			    Pattern.compile("^[a-zA-Z]{1,20}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_NAME.matcher(nameStr);
		return matcher.find();
	}
	
    public static boolean validateSurname(String surnameStr) { 
		final Pattern VALID_SURNAME = 
			    Pattern.compile("^[a-zA-Z-]{1,20}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_SURNAME.matcher(surnameStr);
		return matcher.find();
	}
    
    public static boolean validateEmail(String emailStr) { 
    	final Pattern VALID_EMAIL_ADDRESS_REGEX = 
				   Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
    
    public static boolean validatePassword(String passwordStr) { 
		final Pattern VALID_PASSWORD = 
			    Pattern.compile("^.*(?=.{5,20})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_PASSWORD.matcher(passwordStr);
		return matcher.find();  
    }
    
    /*public static boolean validateConfirmPassword(String confirmPasswordStr) { 
		final Pattern VALID_CONFIRMPASSWORD = 
			    Pattern.compile("^.*(?=.{5,20})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_CONFIRMPASSWORD.matcher(confirmPasswordStr);
		return matcher.find();  
    }*/
   
    
    TextField id, name, surname, email, birthDate, sex; //birthday ir sex neturi textfield, todel ju neisvalo
    JCheckBox agree;
    JComboBox monthList, dayList, yearList;
    JButton register, cancel, add, update, delete, search;
    JLabel optional;
    
	JPasswordField password;
	JPasswordField confirmPassword;
    
	String year = yearList.getSelectedItem().toString();
	
    public UserForma() {
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Find your soulmate at cupid.com!");
        setVisible(true);
        create();
        pack();
    }
    
    public void cleanFields() {
        id.setText("");
        name.setText("");
        surname.setText("");
        yearList.removeAllItems();
        //birthDate.setText("");//neturi terxtField
        sex.setText("");
        email.setText("");
        password.setText("");
    }
    		
    public void create() {
    	
        Container container = getContentPane();
        container.setLayout(new GridLayout(12,0,0,0));
        container.setBackground(Color.gray);
        container.setSize(100,400);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.lightGray);
        textArea.setForeground(Color.black);
    
        //Id
        JPanel panelId = new JPanel();
        panelId.setBorder(new TitledBorder("User id"));
        id = new TextField("", 20);
        panelId.add(id);
        container.add(panelId);
        
        //Id
        /*JLabel labelId = new JLabel("User id * ");
		JPanel panelId = new JPanel();
		panelId.setBackground(Color.LIGHT_GRAY);
		panelId.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		JTextField id = new JTextField("", 20); 
		panelId.add(labelId,  BorderLayout.NORTH);
		panelId.add(id, BorderLayout.SOUTH);
		container.add(panelId);*/
        
        //Name
        JPanel panelName = new JPanel();
        panelName.setBorder(new TitledBorder("Name * "));
        name = new TextField("", 20);
        panelName.add(name);
        container.add(panelName);

        //Surname
        JPanel panelSurname = new JPanel();
        panelSurname.setBorder(new TitledBorder("Surname * "));
        surname = new TextField("", 20);
        panelSurname.add(surname);
        container.add(panelSurname);
		
        //BirthDate
        JPanel panelBirthDate = new JPanel();
      	panelBirthDate.setBorder(new TitledBorder("Birth date * "));
      	String [] years = new String[100];
      	for(int i = 0; i < 100; i++){
      	int begin = 1940 + i;
      	years[i] = Integer.toString(begin);
      	}
      	
      	String day [] = new String[32];
      	for(int i = 1; i < 32; i++){
      	day[i] = Integer.toString(i);
      	}
      	JComboBox dayList = new JComboBox(day);
      	JComboBox monthList = new JComboBox(month);
      	JComboBox yearList = new JComboBox(years);
      	dayList.setSelectedIndex(1);
      	monthList.setSelectedIndex(0);
      	yearList.setSelectedIndex(35);
      	panelBirthDate.add(yearList);
      	panelBirthDate.add(monthList);
      	panelBirthDate.add(dayList);
      	container.add(panelBirthDate);
      	
      	//Sex
      	JPanel panelSex = new JPanel();
     	panelSex.setBorder(new TitledBorder("Sex * "));
     	JRadioButton man = new JRadioButton("man");
     	JRadioButton woman = new JRadioButton("woman");
     	man.setActionCommand("man");
     	woman.setActionCommand("woman");
     	ButtonGroup bG = new ButtonGroup();
     	bG.add(man);
     	bG.add(woman);
     	man.setSelected(true);
     	panelSex.add(man); 
     	panelSex.add(woman);
     	container.add(panelSex);
		
     	//Password
        JPanel panelPassword = new JPanel();
        panelPassword.setBorder(new TitledBorder("Password * "));
        this.password = new JPasswordField(15);
        password.setEchoChar('*');
        panelPassword.add(password);
		container.add(panelPassword);
		
		//Confirm Password
		/*JPanel panelConfirmPassword = new JPanel();
		panelConfirmPassword.setBorder(new TitledBorder("Confirm password: "));
		this.confirmPassword = new JPasswordField(15);
		confirmPassword.setEchoChar('*');
		panelConfirmPassword.add(confirmPassword);
		container.add(panelConfirmPassword);*/
     	
     	//Email
     	JPanel panelEmail = new JPanel();
        panelEmail.setBorder(new TitledBorder("Email * "));
        email = new TextField("", 20);
        panelEmail.add(email);
        container.add(panelEmail);
		
        //CheckBox
		JPanel panelCheckBox = new JPanel();
		CheckboxGroup cg = new CheckboxGroup();
        agree = new JCheckBox("I have read and agree to the terms and conditions");
        panelCheckBox.setBorder(new TitledBorder(""));
		panelCheckBox.add(agree);
		agree.setHorizontalTextPosition(SwingConstants.RIGHT);
		container.add(panelCheckBox);

		//Register
		JPanel panelRegister = new JPanel();	
		this.register = new JButton("Create account");
		cancel = new JButton("Cancel");
		panelRegister.add(this.register);
		panelRegister.add(cancel);
		container.add(panelRegister);
		register.addActionListener(this);
		cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }});
		
		//* - required fields
		JLabel required = new JLabel("* - these fields are required");
		JPanel panelRequired = new JPanel();
		panelRequired.setBorder(new TitledBorder(""));
		panelRequired.add(required, BorderLayout.WEST);
		container.add(panelRequired);
		
        //Buttons
		JPanel panelSubmit = new JPanel();
        panelSubmit.setBorder(new TitledBorder("Actions:"));
        add 	= new JButton ("Add");
        update 	= new JButton ("Update");
        delete 	= new JButton ("Delete");
        search 	= new JButton ("Search");
        panelSubmit.add(add);
        panelSubmit.add(update);
        panelSubmit.add(delete);
        panelSubmit.add(search);
        container.add(panelSubmit);

        
        JTable table = new JTable(new DefaultTableModel(new Object[]{"Number", "Id", 
        		"Name", "Surname", "Email", "Birthday", "Sex", "Password"}, 0));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
	    scrollPane.setPreferredSize(new Dimension(400, 200));
	    container.add(scrollPane);
	    
        NarysDao dao = new NarysDao();

        add.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent e){
        		  Narys narys = new Narys();
        		  String.valueOf(id);
        		  String firstName = name.getText().toString();
        		  narys.setName(firstName);
        		  narys.setSurname(surname.getText());
        		  narys.setEmail(email.getText());
        		  
        		  String year = yearList.getSelectedItem().toString();
        		  String month2 = monthList.getSelectedItem().toString();
        		  String day = (String) dayList.getSelectedItem().toString();
        		  int menesisSkaiciuFormatu=0;
        		  for (String months: month){
        			  menesisSkaiciuFormatu++;
        			  if (months.equals(month2)) {
        				  break;
        			  }
        		  }
        		  String birthDate;
        		  if((menesisSkaiciuFormatu>9) || (Integer.parseInt(day)>9))
        			   birthDate = year + "-" + menesisSkaiciuFormatu + "-" + day;
    			  else
    				   birthDate = year + "-0" + menesisSkaiciuFormatu + "-0" + day;  
        			  
        		  	narys.setBirthDate(birthDate);
        		  
        		  String gender = bG.getSelection().getActionCommand();
        		  JOptionPane.showMessageDialog(container, gender, "Info" ,
                		  JOptionPane.INFORMATION_MESSAGE);
        		  narys.setSex(gender);
        		  narys.setPassword(password.getText());

        		  dao.addNarys(narys);
        		  JOptionPane.showMessageDialog(container, "new user added successfully", "Info" ,
        		  JOptionPane.INFORMATION_MESSAGE);
        		  	cleanFields();
        	  }
        });
        
        update.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        String year = yearList.getSelectedItem().toString();
  		  String month = monthList.getSelectedItem().toString();
  		  String day = (String) dayList.getSelectedItem();
  		  String birthDate = year + month + day;
  		  String gender = bG.getSelection().getActionCommand();
            Narys narys = new Narys(Integer.valueOf(id.getText()), name.getText(), surname.getText(), 
            	email.getText(),
            	birthDate,
            	gender,
            	password.getText());
            	dao.updateNarys(narys);
            	JOptionPane.showMessageDialog(container, "user updated successfully", "Info" , 
            	JOptionPane.INFORMATION_MESSAGE);
            		//cleanFields();
            	
        }});
        
        delete.addActionListener(new ActionListener(){
      	  public void actionPerformed(ActionEvent e){
      		 NarysDao dao = new NarysDao();
      		 dao.deleteNarys(Integer.valueOf(id.getText()));
      		 JOptionPane.showMessageDialog(container, "user deleted successfully", "Info" , 
      	     JOptionPane.INFORMATION_MESSAGE);
      	     	//cleanFields();
      	        }});
        
        search.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent e){
        		  model.setRowCount(0);
        		  java.util.List<Narys> nariai;
        		  if (name.getText().toString().equals("")) {
        			  nariai = dao.getAllNariai();
        		  } else {
        			  nariai = dao.getNarysByName(name.getText().toString());
        		  }
        		  
        		  Object[] data;
        		  int rowNumber = 0;
        		  for (Narys narys : nariai) {
        			  if(!nariai.isEmpty()) {
        				  data = new Object[8];
        				  data[0] = ++rowNumber;
        				  data[1] = narys.getId();
        				  data[2] = narys.getName();
        				  data[3] = narys.getSurname();
        				  data[4] = narys.getEmail();
        				  data[5] = narys.getBirthDate();
        				  data[6] = narys.getSex();
        				  data[7] = narys.getPassword();
        				  model.addRow(data);
        			  } else {
        				  model.setRowCount(0);
        			  }
        		  }
        	  }
        });   
    }
}
