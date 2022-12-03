package com.sudoku.solver;

import com.sudoku.solver.service.AmazonSESSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class SudukoController {

    @Autowired
    private BacktrackingAlgorithm solver;

    @Autowired
    private AmazonSESSample amazonSESSample;

    @PostMapping("/api/solve")
    public int[][] solve(@RequestBody int[][] board) {
        boolean isSolved = solver.solve(board);
        solver.printBoard(board);
        if (isSolved) {
            return board;
        } else {
            throw new RuntimeException("Error in solving the suduko");
        }
    }
    @PostMapping("/api/email")
    public boolean sendEmail(@RequestBody EmailRequest emailRequest) {
        boolean isSolved = solver.solve(emailRequest.getSolvedPuzzle());
        return amazonSESSample.sendEmail(emailRequest);
    }
}
