package com.ar.example.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Sort {
    

    public static void main(){
        final List<Employee> employees = new ArrayList<Employee>();
        insertIntoEmployee(employees);
        sort(employees);
        sortLambdaExp(employees);
    }

    static void insertIntoEmployee(final List<Employee> employees) {
        employees.add(new Employee(2,"M",60));
        employees.add(new Employee(1,"O",35));
        employees.add(new Employee(3,"Q",30));
    }

    static void sort(final List<Employee> employees){
        employees.sort(new Comparator<Employee>(){
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getId()>=o2.getId()?1:0;
            }
        });
    }
    
    static void sortLambdaExp(final List<Employee> employees){
        employees.sort((e1,e2)->e1.getId()>=e2.getId()?1:0);
    }
    
    static void getEmployeeNames(final List<Employee> employees){
        employees.forEach(e->e.getName());
    }
    
}