import java.util.*;

class EmployeeManagementSystem{

    static class Employee {
        private String name;
        private long employeeId;
        private String designation;
        private int experience;
        private int age;

        public Employee(String name,long employeeId,String designation,int experience,int age){
            setName(name);
            setEmployeeId(employeeId);
            setDesignation(designation);
            setExperience(experience);
            setAge(age);
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            if(name != null && !name.trim().isEmpty()){
                this.name = name;
            }
        }

        public long getEmployeeId(){
            return employeeId;
        }

        public void setEmployeeId(long employeeId){
            if(employeeId > 0){
                this.employeeId = employeeId;
            }
        }

        public String getDesignation(){
            return designation;
        }

        public void setDesignation(String designation){
            if(designation != null && !designation.trim().isEmpty()){
                this.designation = designation;
            }
        }

        public int getExperience(){
            return experience;
        }

        public void setExperience(int experience){
            if(experience >= 0){
                this.experience = experience;
            }
        }

        public int getAge(){
            return age;
        }

        public void setAge(int age){
            if(age >= 18 && age <= 70){
                this.age = age;
            }
        }

        @Override
        public String toString() {
            return "Name: " + name +
                   "\nEmployee ID: " + employeeId +
                   "\nDesignation: " + designation +
                   "\nExperience: " + experience +
                   " years" +
                   "\nAge: " + age;
        }
    }

    static final int MAX_EMPLOYEES = 5;
    static Employee[] employees = new Employee[MAX_EMPLOYEES];
    static int employeeCount = 0;
    static Scanner sc = new Scanner(System.in);

    static Employee inputEmployee(){

        String name;
        long employeeId;
        String designation;
        int experience;
        int age;

        System.out.print("Name: ");
        name = sc.nextLine();

        while(true){
            System.out.print("Employee ID: ");
            try{
                employeeId = sc.nextLong();
                if(employeeId <= 0){
                    System.out.println("Employee ID must be positive.");
                    continue;
                }
                if(employeeExists(employeeId)){
                    System.out.println("Employee ID already exists.");
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid Employee ID.");
                sc.nextLine();
            }
        }

        sc.nextLine();

        System.out.print("Designation: ");
        designation = sc.nextLine();

        while(true){
            System.out.print("Experience (years): ");
            try{
                experience = sc.nextInt();
                if(experience < 0){
                    System.out.println("Experience cannot be negative.");
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid experience.");
                sc.nextLine();
            }
        }

        while(true){
            System.out.print("Age: ");
            try{
                age = sc.nextInt();
                if(age < 18 || age > 70){
                    System.out.println("Age must be between 18 and 70.");
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid age.");
                sc.nextLine();
            }
        }

        sc.nextLine();

        return new Employee(name, employeeId, designation, experience, age);
    }

    static boolean employeeExists(long employeeId){
        for(int i = 0; i < employeeCount; i++){
            if(employees[i].getEmployeeId() == employeeId){
                return true;
            }
        }
        return false;
    }

    static int findEmployeeIndex(long employeeId){
        for(int i = 0; i < employeeCount; i++){
            if(employees[i].getEmployeeId() == employeeId){
                return i;
            }
        }
        return -1;
    }

    static void buildTable(){

        if (employeeCount > 0) {
            System.out.println("Table already contains records.");
            return;
        }

        int entries;

        while(true){

            System.out.print("Enter number of employees: ");

            try {
                entries = sc.nextInt();

                if (entries < 1) {
                    System.out.println("Enter at least one employee.");
                    continue;
                }

                if (entries > MAX_EMPLOYEES) {
                    entries = MAX_EMPLOYEES;
                    System.out.println(
                            "Maximum allowed employees: " + MAX_EMPLOYEES
                    );
                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid number.");
                sc.nextLine();
            }
        }

        sc.nextLine();

        for(int i = 0; i < entries; i++){
            System.out.println("\nEmployee " + (i + 1));
            employees[i] = inputEmployee();
        }

        employeeCount = entries;

        System.out.println("\nTable created successfully.");
    }

    static void insertEmployee(){

        if (employeeCount >= MAX_EMPLOYEES) {
            System.out.println("Employee table is full.");
            return;
        }

        System.out.println("\nEnter employee information:");
        employees[employeeCount] = inputEmployee();
        employeeCount++;
        System.out.println("Employee added successfully.");
    }

    static void removeEmployeeAtIndex(int index){
        for(int i = index; i < employeeCount - 1; i++){
            employees[i] = employees[i + 1];
        }
        employees[employeeCount - 1] = null;
    }

    static void deleteEmployee(){
        System.out.print("Enter Employee ID: ");
        try{
            long employeeId = sc.nextLong();
            sc.nextLine();
            int index = findEmployeeIndex(employeeId);
            if(index == -1){
                System.out.println("Employee not found.");
                return;
            }
            removeEmployeeAtIndex(index);
            employeeCount--;
            System.out.println("Employee deleted successfully.");
        }catch(InputMismatchException e){
            System.out.println("Invalid Employee ID.");
            sc.nextLine();
        }
    }

    static void searchEmployee(){
        System.out.print("Enter Employee ID: ");
        try{
            long employeeId = sc.nextLong();
            sc.nextLine();
            int index = findEmployeeIndex(employeeId);
            if(index == -1){
                System.out.println("Employee not found.");
                return;
            }
            System.out.println("\nEmployee Details");
            System.out.println("----------------");
            System.out.println(employees[index]);
        }catch(InputMismatchException e){
            System.out.println("Invalid Employee ID.");
            sc.nextLine();
        }
    }

    static void displayAllEmployees(){
        if(employeeCount == 0){
            System.out.println("No employee records found.");
            return;
        }
        System.out.println("\nEmployee Records");
        System.out.println("========================");
        for(int i = 0; i < employeeCount; i++){
            System.out.println("\nEmployee " + (i + 1));
            System.out.println("----------------");
            System.out.println(employees[i]);
        }
    }

    static void displayMenu(){
        System.out.println("\n========================================");
        System.out.println("Employee Management System");
        System.out.println("========================================");
        System.out.println("1. Build Table");
        System.out.println("2. Insert New Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. Search Employee");
        System.out.println("5. Display All Employees");
        System.out.println("6. Exit");
        System.out.println("========================================");
        System.out.print("Select an option: ");
        try{
            int option = sc.nextInt();
            sc.nextLine();
            switch(option){
                case 1:
                    buildTable();
                    break;
                case 2:
                    insertEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    searchEmployee();
                    break;
                case 5:
                    displayAllEmployees();
                    break;
                case 6:
                    System.out.println("Thank you for using Employee Management System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }catch(InputMismatchException e){
            System.out.println("Please enter a valid menu option.");
            sc.nextLine();
        }
    }

    public static void main(String[] args){
        while(true){
            displayMenu();
        }
    }
}