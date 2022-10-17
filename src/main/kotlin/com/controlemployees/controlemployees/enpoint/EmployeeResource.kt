package com.controlemployees.controlemployees.enpoint

import com.controlemployees.controlemployees.domain.Employee
import com.controlemployees.controlemployees.repository.EmployeeRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeResource(private val employeeRepository: EmployeeRepository) {

    @GetMapping("/employees/{id}")
    fun getEmployee(@PathVariable id: String): ResponseEntity<Employee>{
        return ResponseEntity.ok().body(employeeRepository.findEmployeeById(id))
    }

    @PostMapping("/employees")
    fun saveEmployee(@RequestBody employee: Employee): ResponseEntity<Employee>{
        return ResponseEntity.status(201).body(employeeRepository.addEmployee(employee))
    }

    @PutMapping("/employees")
    fun editEmployee(@RequestBody employee: Employee): ResponseEntity<Employee>{
        return ResponseEntity.status(200).body(employeeRepository.editEmployeeData(employee))
    }

    @DeleteMapping("/employees")
    fun removeEmployee(@RequestBody employee: Employee): ResponseEntity<Void>{
        employeeRepository.deleteEmployee(employee)
        return ResponseEntity.noContent().build()
    }

}