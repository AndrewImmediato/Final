package Helper;

import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.Finance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Loan {
		double dLoanAmount;
		double dInterestRate;
		int iTerm;
		double dFutureValue;
		boolean bInterestCalc;
		double dExtraPayment;
		Date firstDate;
		private LinkedList<Payment> loanPayments = new LinkedList<Payment>();
		public Loan(double LoanAmount, double InterestRate, int Term, double FutureValue, boolean bInterestCalc, double ExtraPayment, Date firstDate) {
		super();
		dLoanAmount = LoanAmount; //total loaned
		dInterestRate = InterestRate; //monthly interest rate
		iTerm = Term; //number of payments (monthly)
		dFutureValue = FutureValue; //what we are trying to reach
		dExtraPayment = ExtraPayment; //Extra payments per month
		this.bInterestCalc = bInterestCalc; 
		int paymentNum = 0;
		double changingBalance = this.dLoanAmount;
			do {
				Payment p = new Payment(++paymentNum, firstDate, changingBalance, this);
				loanPayments.add(p);
				changingBalance = p.getBalance();
				System.out.println("Payment " + paymentNum + ":" + loanPayments.getLast().getBalance());
				if (loanPayments.getLast().getBalance() <= 0) {
					loanPayments.getLast().Zeroer();
					break;
				}
			} while (true);
		}
		public double getLoanAmount() {
			return dLoanAmount;
		}
		public double getInterestRate() {
			return dInterestRate;
		}
		public int getTerm() {
			return iTerm;
		}
		public double getExtraPayment() {
			return dExtraPayment;
		}
		public double getFutureValue() {
			return dFutureValue;
		}
		public int getLoanPaymentSize() {
			return loanPayments.size();
		}
		public double getIPMT() {
			double y = 0;
				for (int k = 0; k < getLoanPaymentSize(); k++) {
					y += Math.abs(Finance.ipmt(dInterestRate/12, k, iTerm*12, dLoanAmount));
					y = Math.round(y*100.00)/100.00;
				}
			return y;
					
		}
		public double getPMT() {
			double x = Math.abs(Finance.pmt(dInterestRate/12, iTerm*12, dLoanAmount));
			return Math.round(x*100.0)/100.0;
		}
		public static Date parseDate(String date) {
			try {
				return new SimpleDateFormat("yyyy.MM.dd").parse(date);
				} catch (ParseException e) {
					return null;
				}
		}
		
}

