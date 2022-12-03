package com.sudoku.solver;

public class EmailRequest {

    private int[][] solvedPuzzle;
    private String toEmailAddress;

    public EmailRequest() {
    }

    public EmailRequest(int[][] solvedPuzzle, String toEmailAddress) {
        this.solvedPuzzle = solvedPuzzle;
        this.toEmailAddress = toEmailAddress;
    }

    public int[][] getSolvedPuzzle() {
        return solvedPuzzle;
    }

    public void setSolvedPuzzle(int[][] solvedPuzzle) {
        this.solvedPuzzle = solvedPuzzle;
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }
}
