package com.internship.project.controllers;

import com.internship.project.entities.Memory;
import com.internship.project.repositories.MemoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/memory")
public class MemoryController {

    private final MemoryRepository memoryRepo;

    MemoryController(MemoryRepository memoryRepo) {
        this.memoryRepo = memoryRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMemory(Double value) {
        Memory memory = new Memory(value);
        return ResponseEntity.ok("Memory with id = " + memory.getId() + " was successfully saved!");
    }

    @PostMapping("/subtract")
    public Double subtractMemory() {
        List<Memory> memory = memoryRepo.findAll();
        return memory.get(memory.size() - 1).getValue();
    }
}
