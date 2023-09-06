package models;

import java.util.Date;

public class LoanRecord {
    private int recordId;
    private String bookIsbn;
    private String borrowerMemberNumber;
    private Date loanDate;
    private Date returnDate;

    public LoanRecord(int recordId, String bookIsbn, String borrowerMemberNumber, Date loanDate, Date returnDate) {
        this.recordId = recordId;
        this.bookIsbn = bookIsbn;
        this.borrowerMemberNumber = borrowerMemberNumber;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBorrowerMemberNumber() {
        return borrowerMemberNumber;
    }

    public void setBorrowerMemberNumber(String borrowerMemberNumber) {
        this.borrowerMemberNumber = borrowerMemberNumber;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "LoanRecord{" +
                "recordId=" + recordId +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", borrowerMemberNumber='" + borrowerMemberNumber + '\'' +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
