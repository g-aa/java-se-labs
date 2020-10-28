package task3_employees;

public class Employee
{
    // Special fields:
    private static int nextEmpID = 1;
    public static String BASE_POSITION = "worker";
    public static int BASE_SALARY = 5000;

    // Instance fields:
    private int     id_number;
    private String  name;
    private String  position;
    private int     salary;

    // полный конструктор:
    Employee(String name, String position, int salary) throws IllegalArgumentException, IndexOutOfBoundsException
    {
        if (name == null || "".equals(name.trim()))
            throw new IllegalArgumentException("Значение поля Name пустое или равно null !");

        if (salary < 0)
            throw new IllegalArgumentException("Значение поля Salary меньше нуля !");

        if (nextEmpID == Integer.MIN_VALUE)
            throw new IndexOutOfBoundsException("Значение id_number превысело: " + Integer.MAX_VALUE);

        id_number = nextEmpID++;
        this.name = name;
        this.position = ("".equals(position.trim())|| BASE_POSITION.equals(position.trim())) ? BASE_POSITION : position;
        this.salary = salary;
    }


    // конструктор с параметром name:
    Employee(String name)
    {
        this(name, BASE_POSITION, BASE_SALARY);
    }


    // конструктор с параметрами name, position:
    Employee(String name, String position)
    {
        this(name, position, (!BASE_POSITION.equals(position))? 6000 : BASE_SALARY);
    }


    public int getID()
    {
        return this.id_number;
    }


    public String getName()
    {
        return this.name;
    }


    public String getPosition()
    {
        return this.position;
    }


    public int getSalary()
    {
        return this.salary;
    }

    @Override
    public String toString()
    {
        return ("Emp id: "+ id_number + ", name: " + name + ", position: " + position + ", salary: " + salary);
    }


    // распечатка массива com.company.Employee в консоль:
    static void printEmpArray(Employee[] Emps)
    {
        if (Emps != null)
        {
            for (Employee item :Emps)
            {
                System.out.println(item != null ? item: "Employee is NULL!");
            }
        }
        else
        {
            System.out.println("Employees array is NULL!");
        }
    }


    // получить должность с максимальным окладом:
    static String positionMaxSalary(Employee[] Emps)
    {
        if(Emps != null)
        {
            Employee tempEmp = Emps[0];
            for (int i = 1; i < Emps.length; i++)
            {
                if (tempEmp == null)
                {
                    tempEmp = Emps[i];
                }
                else if(Emps[i] != null && tempEmp.getSalary() < Emps[i].getSalary())
                {
                        tempEmp = Emps[i];
                }
            }
            return tempEmp.getPosition();
        }
        return "";
    }


    // получить суммарную зарплату:
    static int sumSalary(Employee[] Emps)
    {
        int sumSal = 0;
        if (Emps != null)
        {
            for (Employee item : Emps)
            {
                sumSal += (item != null) ? item.salary : 0;
            }
        }
        return sumSal;
    }
}
