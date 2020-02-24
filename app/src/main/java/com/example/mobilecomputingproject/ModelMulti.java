package com.example.mobilecomputingproject;

public class ModelMulti {
    private int qNumber;
    private int[] selectedAnswerPositions;
    private boolean selA, selB, selC, selD, selE;

    public ModelMulti(int qNumber) {
        this.qNumber = qNumber;
        selA = false;
        selB = false;
        selC = false;
        selD = false;
        selE = false;
        selectedAnswerPositions = new int[5];
    }

    public int getqNumber() {
        return qNumber;
    }

    public void setqNumber(int qNumber) {
        this.qNumber = qNumber;
    }

    public void setSelA(boolean selA) {
        this.selA = selA;
        if(selA) {
            selectedAnswerPositions[0] = 1;
        }
        else {
            selectedAnswerPositions[0] = 0;
        }
    }
    public boolean isSelA() {
        return selA;
    }
    public void setSelB(boolean selB) {
        this.selB = selB;
        if(selB) {
            selectedAnswerPositions[1] = 1;
        }
        else {
            selectedAnswerPositions[1] = 0;
        }
    }
    public boolean isSelB() {
        return selB;
    }

    public void setSelC(boolean selC) {
        this.selC = selC;
        if(selC) {
            selectedAnswerPositions[2] = 1;
        }
        else {
            selectedAnswerPositions[2] = 0;
        }
    }
    public boolean isSelC() {
        return selC;
    }

    public void setSelD(boolean selD) {
        this.selD = selD;
        if(selD) {
            selectedAnswerPositions[3] = 1;
        }
        else {
            selectedAnswerPositions[3] = 0;
        }
    }
    public boolean isSelD() {
        return selD;
    }

    public void setSelE(boolean selE) {
        this.selE = selE;
        if(selE) {
            selectedAnswerPositions[4] = 1;
        }
        else {
            selectedAnswerPositions[4] = 0;
        }
    }
    public boolean isSelE() {
        return selE;
    }

    public void setSelectedAnswerPositions(int i) {
        selectedAnswerPositions[i-1] = 1;
    }

    public int[] getSelectedAnswerPositions() {
        return selectedAnswerPositions;
    }

    public  String getSelected() {
        String temp = "";
        if(selA)
            temp = temp + "A";
        if(selB)
            temp = temp + "B";
        if(selC)
            temp = temp + "C";
        if(selD)
            temp = temp + "D";
        if(selE)
            temp = temp + "E";

        return temp;
    }
}
