package com.controlemployees.controlemployees.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue
import com.controlemployees.controlemployees.domain.Employee
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class EmployeeRepository(private val dynamoDBMapper: DynamoDBMapper) {

    fun addEmployee(employee: Employee) : Employee{
        employee.id = UUID.randomUUID().toString();
        dynamoDBMapper.save(employee)
        return employee
    }

    fun findEmployeeById(id: String) : Employee{
        return dynamoDBMapper.load(Employee::class.java, id)
    }

    fun deleteEmployee(employee: Employee){
        dynamoDBMapper.delete(employee)
    }

    fun editEmployeeData(employee: Employee): Employee{
        dynamoDBMapper.save(employee, buildExpression(employee))
        return employee
    }

    private fun buildExpression(employee: Employee): DynamoDBSaveExpression{
        val dynamoDBSaveExpression = DynamoDBSaveExpression()
        val expectMap : Map<String, ExpectedAttributeValue> = hashMapOf("id" to ExpectedAttributeValue(AttributeValue().withS(employee.id)))
        dynamoDBSaveExpression.expected = expectMap
        return dynamoDBSaveExpression
    }


}