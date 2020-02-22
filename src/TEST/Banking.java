package TEST;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Banking extends JPanel{
	 private JFrame frame;
	   private JPanel panel;
	   private JButton withdraw;
	   private JButton deposit;
	   private JButton transfer;
	   private JButton balance;
	   private JRadioButton checking;
	   private JRadioButton savings;
	   private JLabel label;
	   private JTextField input;
	   private Account checkingAccount;
	   private Account savingsAccount;
	   private Account currentAccount;
	   private double amount;
	   public Banking(Account checkingAccount,Account savingsAccount)
	   {

	       frame=new JFrame("ATM");
	       panel=new JPanel();
	       label = new JLabel ("Select Type of account"); //instantiate the label
	       checking=new JRadioButton("Business / Current");
	       savings=new JRadioButton("Personal / Savings");
	       withdraw=new JButton("Withdraw");
	       deposit=new JButton("Deposit");
	       transfer=new JButton("Transfer");
	       balance=new JButton("Balance");
	       
	       input=new JTextField("Enter Amount");
	       this.checkingAccount=checkingAccount;
	       this.savingsAccount=savingsAccount;

	       panel.setLayout(new GridLayout(8,2));
	       panel.add(label);
	       panel.add(checking);panel.add(savings);
	       panel.add(balance);
	       panel.add(deposit);
	       panel.add(transfer);
	       panel.add(withdraw);
	      
	       panel.add(input);

	       frame.add(panel);
	       frame.pack();
	       frame.setLocationRelativeTo(null);
	       frame.setSize(500,300);
	       frame.setVisible(true);


	       checking.addActionListener(new ActionListener(){

	           public void actionPerformed(ActionEvent e)
	           {
	               if(checking.isSelected())
	               {
	                   currentAccount=checkingAccount;
	                   savings.setSelected(false);
	               }
	           }
	       });

	       savings.addActionListener(new ActionListener(){

	           public void actionPerformed(ActionEvent e)
	           {
	               if(savings.isSelected())
	               {
	                   currentAccount=savingsAccount;
	                   checking.setSelected(false);
	               }
	           }
	       });


	       withdraw.addActionListener(new ActionListener(){

	           public void actionPerformed(ActionEvent e)
	           {


	             try
	             {
	                 amount=Double.parseDouble(input.getText());
	                 if(amount%100==0)
	                 {
	                     currentAccount.withdraw(amount);
	                     JOptionPane.showMessageDialog(frame, "You've withdrawn Ksh. "+amount);


	                 }
	                 else
	                 {
	                     JOptionPane.showMessageDialog(frame, "You can only withdraw money in multiples of Ksh.100");

	                 }
	             }
	             catch(NumberFormatException a)
	             {
	                 JOptionPane.showMessageDialog(frame, "Please enter the amount to withdraw");
	             } 
	             catch (InsufficientFunds e1)
	             {
	                 JOptionPane.showMessageDialog(frame, "You do not have enough funds to withdraw Ksh." +amount);

	             }

	           }
	       });


	       transfer.addActionListener(new ActionListener(){

	           public void actionPerformed(ActionEvent e)
	           {
	        	   
	                   try
	                   {
	                	   amount=Double.parseDouble(input.getText());
	                	   
	                	   if(currentAccount.equals(checkingAccount)) {
	                		   
	                       currentAccount.transferTo(savingsAccount, amount);
	                	   }
	                	   else
	                		   currentAccount.transferTo(savingsAccount, amount); 
	                	   
	                       JOptionPane.showMessageDialog(frame, "You've succesffuly transfered Ksh." +amount  );
	                   }
	                   catch(NumberFormatException a)
	                   {
	                       JOptionPane.showMessageDialog(frame, "Please enter amount to transfer.");
	                   } 
	                   catch (InsufficientFunds e1)
	                   {
	                       JOptionPane.showMessageDialog(frame, "You do not have enough funds to transfer Ksh. " +amount);

	                   
	               }
	              
	           }
	     });

	   deposit.addActionListener(new ActionListener(){

	       public void actionPerformed(ActionEvent e)
	       {
	           try
	           {
	               amount=Double.parseDouble(input.getText());
	               currentAccount.deposit(amount);
	                 JOptionPane.showMessageDialog(frame, "You've succesffuly deposited Ksh. "+amount  );


	           }
	          catch(NumberFormatException a)
	          {
	                 JOptionPane.showMessageDialog(frame, "Please enter the amount to deposit");
	          } 
	       }
	   });


	   balance.addActionListener(new ActionListener(){

	       public void actionPerformed(ActionEvent e)
	       {

	           JOptionPane.showMessageDialog(frame, "Your balance is Ksh. "+currentAccount.getBalance());

	       }
	   });


	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Account checking=new Account(10000.00);
	       Account savings=new Account(2000.00);
	       Banking myBank=new Banking(checking,savings);

	}

}
