package com.internship.project.controllers;

import com.internship.project.calculating.ExpressionCalculator;
import com.internship.project.entities.Memory;
import com.internship.project.repositories.MemoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/memory")
public class MemoryController {

    private final MemoryRepository memoryRepo;

    MemoryController(MemoryRepository memoryRepo) {
        this.memoryRepo = memoryRepo;
    }

    @PostMapping(value = "/calculator/{id}", consumes = "text/plain")
    public ResponseEntity<?> calculateExpression(@PathVariable("id") long id , @RequestBody String expression) {
        Optional<Memory> memoryValue = memoryRepo.findById(id);
        Memory memory = memoryValue.orElseGet(() -> {
            Memory example = new Memory();
            example.setId(id);
            memoryRepo.save(example);
            return memoryRepo.findById(id).get();
        });
        ExpressionCalculator calculator = new ExpressionCalculator();

        String leftSideOfExpression = expression.substring(0, expression.indexOf("="));
        String rightSideOfExpression = expression.substring(expression.indexOf("="));
        leftSideOfExpression = leftSideOfExpression.contains("M")
                ? leftSideOfExpression.replace("M", String.valueOf(memory.getValue()))
                : leftSideOfExpression;
        String resultOfLeftSide = calculator.apply(leftSideOfExpression);
        if (!rightSideOfExpression.contains("?")) {
            updateMemory(resultOfLeftSide, rightSideOfExpression, memory);
            memoryRepo.save(memory);
        }
        return ResponseEntity.ok(leftSideOfExpression + " = " + resultOfLeftSide);
    }

    private void updateMemory(String resultOfLeftSide, String rightSideOfExpression, Memory memory) {
        if (rightSideOfExpression.contains("M+")) {
            memory.setValue(memory.getValue() + Double.parseDouble(resultOfLeftSide));
        } else if (rightSideOfExpression.contains("M-")) {
            memory.setValue(memory.getValue() - Double.parseDouble(resultOfLeftSide));
        } else {
            memory.setValue(Double.parseDouble(resultOfLeftSide));
        }
    }
}
