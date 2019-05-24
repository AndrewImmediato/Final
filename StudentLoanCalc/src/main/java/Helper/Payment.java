package Helper;
import org.apache.poi.ss.formula.functions.FinanceLib;
import org.apache.poi.ss.formula.functions.*;
import java.util.Date;

public class Payment {
	int paymentID;
	Date due;
	double IPMT;
	double PPMT;
	double principleTotal;
	double balance;
	Loan l;
	
	public double getBalance() {
		return balance;
	}
	public double getPPMT() {
		return Finance.ppmt(l.getInterestRate()/12, this.paymentID, l.getTerm()*12, l.getLoanAmount());
	}
	public void Zeroer() {
		balance = 0;
	}
	public Payment(int pmntID, Date dueDate, double blnce, Loan l) {
		paymentID = pmntID;
		due = dueDate;
		balance = blnce;
		this.l = l;
		this.IPMT = blnce + l.getInterestRate()/12;
		this.PPMT = Math.abs(getPPMT());
		this.principleTotal = this.PPMT + l.getExtraPayment();
		this.balance = blnce - this.principleTotal;
		
	}
	
}