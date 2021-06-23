package com.employeepayroll.entity;

public class EmployeePayrollDetails {

    private double basicPay;
    private double deduction;
    private double payableTax;
    private double tax;
    private double netPay;

    public EmployeePayrollDetails(double basicPay, double deduction, double payableTax, double tax, double netPay) {
        this.basicPay = basicPay;
        this.deduction = deduction;
        this.payableTax = payableTax;
        this.tax = tax;
        this.netPay = netPay;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getPayableTax() {
        return payableTax;
    }

    public void setPayableTax(double payableTax) {
        this.payableTax = payableTax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}
