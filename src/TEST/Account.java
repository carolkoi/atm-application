package TEST;

public class Account {
	private double balance;
	

	public Account(double balance)
	{
	    this.balance=balance;
	}

	public void withdraw(double amount) throws InsufficientFunds
	{
	 
	     if(amount<=balance)
	     {

	         if(balance<(amount+1.50))
	         {
	             throw new InsufficientFunds((amount+1.50)-balance);
	         }
	         else
	         {
	             balance=balance-amount-1.50;
	         }

	    
	 }


	}

	public double getBalance()
	{
	    return balance;
	}

	public void deposit(double amount)
	{
	    balance=balance+amount;
	}

	public void transferTo(Account bank, double amount) throws InsufficientFunds
	{
		if(amount<=balance)
	    {
	        withdraw(amount);
	        bank.deposit(amount);
	    }
	    else
	    {
	        throw new InsufficientFunds(amount);
	    }
	}

}
