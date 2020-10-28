/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3_employees;

/**
 *
 * @author Andrey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String[]    names = { null, "Клара", "Праско́вья", null, "Софья", "Зоя", "Герда", "Виргиния", null, "Фаина" };
        String[]    positions = { null, null, "engineer", null, "physicist", "programmer", "physicist", "engineer", null, "engineer"};
        int[]       salary = {0, 0, 31000, 0, 0, 19000, 25000, 45000, 0, 45000};

        Employee[] employees = new Employee[names.length];
        for (int j = 0; j < employees.length; j++) {
            try {
                if(positions[j] != null && salary[j] != 0) {
                    employees[j] = new Employee(names[j], positions[j], salary[j]);
                }
                else if (salary[j] == 0) {
                    employees[j] = new Employee(names[j], positions[j]);
                }
                else
                {
                    employees[j] = new Employee(names[j]);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Перечень employees:");
        Employee.printEmpArray(employees);
        System.out.println("Позиция с наибольшей ЗП: " + Employee.positionMaxSalary(employees));
        System.out.println("Суммарная ЗП" + Employee.sumSalary(employees));
    }
    
}
