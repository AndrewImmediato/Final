package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import org.apache.poi.ss.formula.functions.FinanceLib;
import Helper.Loan;
import Helper.Payment;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;

	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AdditionalPayments;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterest;
	
	@FXML
	private Label numPaymentlbl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {		
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		double iTerm = Double.parseDouble(NbrOfYears.getText());
		LocalDate localDate = PaymentStartDate.getValue();
		double dExtraPayment = Double.parseDouble(AdditionalPayments.getText());
		int intTerm = (int) iTerm;
		java.util.Date updatedDate = java.sql.Date.valueOf(localDate);
		System.out.println("Amount: " + dLoanAmount);
		System.out.println("Rate: " + dInterestRate);
		System.out.println("Term: " + iTerm);
		System.out.println("Start Date: " + localDate);
		System.out.println("Extra: " + dExtraPayment);
		Loan l = new Loan(dLoanAmount, dInterestRate, intTerm, 0.0, false, dExtraPayment, updatedDate);
		String labelPayments = String.valueOf(l.getLoanPaymentSize());
		String labelPMT = String.valueOf(l.getPMT());
		String labelIPMT = String.valueOf(l.getIPMT());
		numPaymentlbl.setText(labelPayments);
		lblTotalInterest.setText(labelIPMT);
		lblTotalPayments.setText(labelPMT);
	}
}
