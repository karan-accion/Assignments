import java.util.*;

class EmployeeManagementSystem{
        static class Employee{
        private String name;
        private long employeeId;
        private String designation;
        private int experience;
        private int age;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        
        public long getEmployeeId(){
            return employeeId;
        }
        public void setEmployeeId(long employeeId){
            this.employeeId = employeeId;
        }
        
        public String getDesignation(){
            return designation;
        }
        public void setDesignation(String designation){
            this.designation = designation;
        }
        
        public int getExperience(){
            return experience;
        }
        public void setExperience(int experience){
            this.experience = experience;
        }
        
        public int getAge(){
            return age;
        }
        public void setAge(int age){
            this.age = age;
        }
    }

    static int employeeCount;
    static final int MAX_EMPLOYEES = 5;
    static Employee employees[] = new Employee[MAX_EMPLOYEES];
    static Scanner sc = new Scanner(System.in);

    static void buildTable(){
        System.out.println("Build The Table\n");
        System.out.println("Maximum Entries can be " + MAX_EMPLOYEES);
        System.out.println("Enter the number of Entries required: ");
        employeeCount = sc.nextInt();
        if (employeeCount > MAX_EMPLOYEES){
            System.out.println("Maximum number of Entries are " + MAX_EMPLOYEES);
            employeeCount = MAX_EMPLOYEES;
        }
        System.out.println("Enter the following data:");
        for (int i = 0; i < employeeCount; i++){
            System.out.print("Name: ");
            employees[i] = new Employee();
            employees[i].setName(sc.next());
            System.out.print("Employee ID: ");
            employees[i].setEmployeeId(sc.nextLong());
            System.out.print("Designation: ");
            employees[i].setDesignation(sc.next());
            System.out.print("Experience: ");
            employees[i].experience = sc.nextInt();
            System.out.print("Age: ");
            employees[i].age = sc.nextInt();
        }
        displayMenu();
    }

    static void insertEmployee(){
        if (employeeCount < MAX_EMPLOYEES){
            int index = employeeCount;
            employeeCount++;
            System.out.println("Enter the information of the new Employee:");
            System.out.print("Name: ");
            employees[index] = new Employee();
            employees[index].name = sc.next();
            System.out.print("Employee ID: ");
            employees[index].employeeId = sc.nextLong();
            System.out.print("Designation: ");
            employees[index].designation = sc.next();
            System.out.print("Experience: ");
            employees[index].experience = sc.nextInt();
            System.out.print("Age: ");
            employees[index].age = sc.nextInt();
        } 
        else{
            System.out.println("Employee Table Full");
        }
        displayMenu();
    }

    static void removeEmployeeAtIndex(int index){
        for (int j = index; j < employeeCount - 1; j++){
            employees[j].name = employees[j + 1].name;
            employees[j].employeeId = employees[j + 1].employeeId;
            employees[j].designation = employees[j + 1].designation;
            employees[j].experience = employees[j + 1].experience;
            employees[j].age = employees[j + 1].age;
        }
    }

    static void deleteEmployee(){
        System.out.println("Enter the Employee ID to Delete Record: ");
        long employeeId = sc.nextLong();
        for (int i = 0; i < employeeCount; i++){
            if (employees[i].employeeId == employeeId){
                removeEmployeeAtIndex(i);
                employeeCount--;
                System.out.println("Employee record deleted successfully.");
                break;
            }
        }
        displayMenu();
    }

    static void searchEmployee(){
        System.out.println("Enter the Employee ID to Search Record: ");
        long employeeId = sc.nextLong();
        for (int i = 0; i < employeeCount; i++){
            if (employees[i].employeeId == employeeId){
                System.out.println("\nEmployee Details:");
                System.out.println("Name: " + employees[i].name);
                System.out.println("Employee ID: " + employees[i].employeeId);
                System.out.println("Designation: " + employees[i].designation);
                System.out.println("Experience: " + employees[i].experience);
                System.out.println("Age: " + employees[i].age);
                break;
            }
        }
        displayMenu();
    }

    static void displayMenu(){
        System.out.println("\n========================================");
        System.out.println("Employee Management System");
        System.out.println("========================================");
        System.out.println("1. Build Table");
        System.out.println("2. Insert New Employee");
        System.out.println("3. Delete Employee Record");
        System.out.println("4. Search Employee Record");
        System.out.println("5. Exit");
        System.out.println("========================================");
        System.out.print("Select an option (1-5): ");
        int option = sc.nextInt();
        switch (option){
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
                System.out.println("Thank you for using Employee Management System. Goodbye!");
                return;
            default:
                System.out.println("Invalid option. Please select options 1 through 5.");
                displayMenu();
        }
    }

    public static void main(String[] args){
        displayMenu();
    }
}