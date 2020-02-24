package com.example.mobilecomputingproject;

public class RubricModel {
    private String testName;
    private String mode;
    private int qNumbers;
    private String answers;
    private boolean multiAnswers;
    private boolean partialCredit;
    private  int rev;

    public RubricModel() {
        testName = new String();
        mode = new String();
        qNumbers = -1;
        answers = new String();
        multiAnswers = false;
        partialCredit = false;
        rev = 0;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getAnswers() {
        return answers;
    }

    public void setqNumbers(int qNumbers) {
        this.qNumbers = qNumbers;
    }

    public int getqNumbers() {
        return qNumbers;
    }

    public void setMultiAnswers(boolean multiAnswers) {
        this.multiAnswers = multiAnswers;
    }

    public boolean isMultiAnswers() {
        return multiAnswers;
    }

    public void setPartialCredit(boolean partialCredit) {
        this.partialCredit = partialCredit;
    }

    public boolean isPartialCredit() {
        return partialCredit;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public int getRev() {
        return rev;
    }
}
