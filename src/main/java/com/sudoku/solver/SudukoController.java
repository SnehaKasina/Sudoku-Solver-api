package com.sudoku.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudukoController {

    @Autowired
    private BacktrackingAlgorithm solver;

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
}
