package com.example.mobilecomputingproject;

public class ModelSingle {
    private int qNumber;
    private int selectedAnswerPosition;
    private boolean selA, selB, selC, selD, selE;

    public ModelSingle(int qNumber) {

        this.qNumber = qNumber;
        selectedAnswerPosition = -1;
        selA = false;
        selB = false;
        selC = false;
        selD = false;
        selE = false;
    }

    public int getqNumber(){

        return qNumber;
    }
    public void setqNumber(int qNumber) {

        this.qNumber = qNumber;
    }

    public void setSelA(boolean selA) {
        this.selA = selA;
        if(selA) {
            setSelB(false);
            setSelC(false);
            setSelD(false);
            setSelE(false);
        }
    }

    public boolean isSelA() {
        return selA;
    }

    public void setSelB(boolean selB) {
        this.selB = selB;
        if(selB) {
            setSelA(false);
            setSelC(false);
            setSelD(false);
            setSelE(false);
        }
    }

    public boolean isSelB() {
        return selB;
    }

    public void setSelC(boolean selC) {
        this.selC = selC;
        if(selC) {
            setSelA(false);
            setSelB(false);
            setSelD(false);
            setSelE(false);
        }
    }

    public boolean isSelC() {
        return selC;
    }

    public void setSelD(boolean selD) {
        this.selD = selD;
        if(selD) {
            setSelA(false);
            setSelB(false);
            setSelC(false);
            setSelE(false);
        }
    }

    public boolean isSelD() {
        return selD;
    }

    public void setSelE(boolean selE) {
        this.selE = selE;
        if(selE) {
            setSelA(false);
            setSelB(false);
            setSelC(false);
            setSelD(false);
        }
    }

    public boolean isSelE() {
        return selE;
    }

    public int getSelectedAnswerPosition() {
        return selectedAnswerPosition;
    }

    public String getSelected() {
        if(selA)
            return "A";
        else if(selB)
            return "B";
        else if(selC)
            return "C";
        else if(selD)
            return "D";
        else if(selE)
            return "E";
        else
            return "NONE";
    }

    public void setSelectedAnswerPosition(int selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
    }
}
