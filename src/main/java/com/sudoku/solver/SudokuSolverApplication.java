package com.sudoku.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SudokuSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SudokuSolverApplication.class, args);
    }
}
