package com.week2assignment.homework.controllers;

import com.week2assignment.homework.dto.DepartmentDTO;
import com.week2assignment.homework.exception.ResourceNotFoundException;
import com.week2assignment.homework.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/departments")
public class DepartmentController {
    final private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO> >getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<DepartmentDTO>> getDepartmentById(@PathVariable Long id){
        Optional<DepartmentDTO> departmentDTo= departmentService.getDepartmentById(id);
        return departmentDTo
                .map(departmentDTO -> ResponseEntity.ok(departmentDTo))
                .orElseThrow(()->new ResourceNotFoundException("Resource not found with Id "+id));
    }
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartment=departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
   }
   @PutMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentdto,@PathVariable Long id){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentdto,id));
   }
   @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteDepeartmentByID(@PathVariable Long id){
      Boolean deleted= departmentService.deleteDepeartmentByID(id);
      if(deleted) return  ResponseEntity.ok(true);
      return ResponseEntity.notFound().build();
   }

}
