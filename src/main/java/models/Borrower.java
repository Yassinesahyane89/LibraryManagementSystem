package models;

import java.util.List;

public class Borrower {
    private String memberNumber;
    private String name;
    private List<LoanRecord> loanRecords;

    public Borrower(String memberNumber, String name) {
        this.memberNumber = memberNumber;
        this.name = name;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "memberNumber='" + memberNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
