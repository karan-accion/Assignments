import java.util.Scanner;

interface Lendable{
    void lend();
    void returnItem();
}

abstract class Item{
    protected String id;
    protected String title;
    public Item(){}
    public Item(String id,String title){
        this.id = id;
        this.title = title;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public abstract void display();
}

abstract class Person{
    protected String name;
    public Person(){}
    public Person(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public abstract void showInfo();
}

class LibraryManagementConsole{
    static class Book extends Item implements Lendable{
        private String author;
        private int quantity;

        public Book(){}
        public Book(String id,String title,String author,int quantity){
            setId(id);
            setTitle(title);
            this.author = author;
            this.quantity = quantity;
        }
        public String getAuthor(){
            return author; 
        }
        public void setAuthor(String author){
            this.author = author;
        }
        public int getQuantity(){
            return quantity;
        }
        public void setQuantity(int quantity){
            this.quantity = quantity;
        }
        public void display(){
            System.out.println("ID:" + getId() + " | " + getTitle() + " by " + getAuthor() + " | qty:" + getQuantity());
        }
        public void lend(){
            if (quantity>0) quantity--; 
        }
        public void returnItem(){
            quantity++; 
        }
    }

    static class Books{
        private static final int MAX_BOOKS = 100;
        private Book[] books = new Book[MAX_BOOKS];
        private int count = 0;

        public void addBook(Book b){
            if (count >= MAX_BOOKS) {
                System.out.println("Book list full");
                return;
            }
            books[count++] = b;
            System.out.println("Book added.");
        }

        public Book findById(String id){
            for (int i = 0; i < count; i++){
                if (books[i].getId() != null && books[i].getId().equals(id)) return books[i];
            }
            return null;
        }

        public void upgradeQuantity(String id, int add){
            Book b = findById(id);
            if (b == null){
                System.out.println("Book not found.");
                return;
            }
            b.setQuantity(b.getQuantity() + add);
            System.out.println("Quantity updated.");
        }

        public void searchByTitle(String q){
            boolean found = false;
            for (int i = 0; i < count; i++){
                if (books[i].getTitle().toLowerCase().contains(q.toLowerCase())){
                    books[i].display();
                    found = true;
                }
            }
            if (!found)
                System.out.println("No books match the title.");
        }

        public void searchByAuthor(String q){
            boolean found = false;
            for (int i = 0; i < count; i++){
                if (books[i].getAuthor().toLowerCase().contains(q.toLowerCase())){
                    books[i].display();
                    found = true;
                }
            }
            if (!found)
                System.out.println("No books match the author.");
        }

        public void search(String q){
            searchByTitle(q);
        }

        public void search(String q,boolean byAuthor){
            if (byAuthor) searchByAuthor(q);
            else searchByTitle(q);
        }

        public void showAll(){
            if (count == 0){
                System.out.println("No books in library.");
                return;
            }
            for (int i = 0; i < count; i++){
                Book b = books[i];
                b.display();
            }
        }
    }

    static class User extends Person{
        private String studentId;
        private String[] borrowed = new String[10];
        private int borrowedCount = 0;

        public User(){}
        public User(String name,String studentId){
            super(name);
            this.studentId = studentId;
        }
        public String getStudentId(){
            return studentId;
        }
        public void setStudentId(String studentId){
            this.studentId = studentId;
        }
        public String[] getBorrowed(){
            return borrowed;
        }
        public int getBorrowedCount(){
            return borrowedCount;
        }
        public void borrowBook(String bookId){
            if (borrowedCount >= borrowed.length) return;
            borrowed[borrowedCount++] = bookId;
        }
        public void returnBook(String bookId){
            for (int i = 0; i < borrowedCount; i++){
                if (borrowed[i] != null && borrowed[i].equals(bookId)){
                    for (int j = i; j < borrowedCount - 1; j++) borrowed[j] = borrowed[j+1];
                    borrowedCount--;
                    return;
                }
            }
        }
        public void showInfo(){
            System.out.println("Name:" + getName() + " | ID:" + getStudentId());
        }
    }

    static class Users{
        private static final int MAX_USERS = 100;
        private User[] users = new User[MAX_USERS];
        private int count = 0;

        public void register(User u){
            if (count >= MAX_USERS) {
                System.out.println("User list full");
                return;
            }
            users[count++] = u;
            System.out.println("Student registered.");
        }

        public User findById(String id){
            for (int i = 0; i < count; i++) {
                if (users[i].getStudentId() != null && users[i].getStudentId().equals(id)) return users[i];
            }
            return null;
        }

        public void showAll(){
            if (count == 0) {
                System.out.println("No registered students.");
                return;
            }
            for (int i = 0; i < count; i++) {
                System.out.println("Name:" + users[i].getName() + " | ID:" + users[i].getStudentId());
            }
        }
    }

    static Books library = new Books();
    static Users students = new Users();
    static Scanner sc = new Scanner(System.in);

    static void addNewBook(){
        Book b = new Book();
        System.out.print("Enter Book ID: ");
        sc.nextLine();
        String bid = sc.nextLine().trim();
        if (bid.length() == 0){
            System.out.println("Invalid ID");
            displayMenu();
            return;
        }
        b.setId(bid);
        System.out.print("Enter Title: ");
        b.setTitle(sc.nextLine());
        System.out.print("Enter Author: ");
        b.setAuthor(sc.nextLine());
        System.out.print("Enter Quantity: ");
        String qtys = sc.nextLine().trim();
        int qty = 0;
        try{
            qty = Integer.parseInt(qtys);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid quantity");
            displayMenu();
            return;
        }
        b.setQuantity(qty);
        library.addBook(b);
        displayMenu();
    }

    static void upgradeBookQuantity(){
        sc.nextLine();
        System.out.print("Enter Book ID to upgrade: ");
        String id = sc.nextLine().trim();
        if (id.length() == 0){ System.out.println("Invalid ID"); displayMenu(); return; }
        System.out.print("Enter amount to add: ");
        String adds = sc.nextLine().trim();
        int add = 0;
        try{
            add = Integer.parseInt(adds);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid amount");
            displayMenu();
            return;
        }
        library.upgradeQuantity(id, add);
        displayMenu();
    }

    static void searchBook(){
        sc.nextLine();
        System.out.print("Enter title keyword: ");
        String q = sc.nextLine();
        library.searchByTitle(q);
        displayMenu();
    }

    static void showAllBooks(){
        library.showAll();
        displayMenu();
    }

    static void registerStudent(){
        User u = new User();
        System.out.print("Enter Student ID: ");
        sc.nextLine();
        String sid = sc.nextLine().trim();
        if (sid.length() == 0){
            System.out.println("Invalid ID");
            displayMenu();
            return;
        }
        u.setStudentId(sid);
        System.out.print("Enter Name: ");
        u.setName(sc.nextLine());
        students.register(u);
        displayMenu();
    }

    static void showAllStudents(){
        students.showAll();
        displayMenu();
    }

    static void checkOutBook(){
        sc.nextLine();
        System.out.print("Enter Student ID: ");
        String sid = sc.nextLine().trim();
        User u = students.findById(sid);
        if (u == null){
            System.out.println("Student not found.");
            displayMenu();
            return;
        }
        System.out.print("Enter Book ID to check out: ");
        String bid = sc.nextLine().trim();
        Book b = library.findById(bid);
        if (b == null){
            System.out.println("Book not found.");
            displayMenu();
            return;
        }
        if (b.getQuantity() <= 0){
            System.out.println("Book not available.");
            displayMenu();
            return;
        }
        b.lend();
        u.borrowBook(bid);
        System.out.println("Book checked out.");
        displayMenu();
    }

    static void checkInBook(){
        sc.nextLine();
        System.out.print("Enter Student ID: ");
        String sid = sc.nextLine().trim();
        User u = students.findById(sid);
        if (u == null){
            System.out.println("Student not found.");
            displayMenu();
            return;
        }
        System.out.print("Enter Book ID to check in: ");
        String bid = sc.nextLine().trim();
        Book b = library.findById(bid);
        if (b == null){
            System.out.println("Book not found.");
            displayMenu();
            return;
        }
        b.returnItem();
        u.returnBook(bid);
        System.out.println("Book checked in.");
        displayMenu();
    }

    static void displayMenu(){
        System.out.println("\n========================================");
        System.out.println("Library Management Console");
        System.out.println("========================================");
        System.out.println("1. Exit Application");
        System.out.println("2. Add a New Book");
        System.out.println("3. Upgrade Quantity of a Book");
        System.out.println("4. Search a Book");
        System.out.println("5. Show All Books");
        System.out.println("6. Register Student");
        System.out.println("7. Show All Registered Students");
        System.out.println("8. Check Out Book");
        System.out.println("9. Check-In Book");
        System.out.println("========================================");
        System.out.print("Select an option (1-9): ");
        String opt = sc.nextLine().trim();
        int option = 0;
        try{
            option = Integer.parseInt(opt);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid option. Please select 1-9.");
            displayMenu();
            return; 
        }
        switch (option){
            case 1:
                System.out.println("Goodbye");
                return;
            case 2:
                addNewBook();
                break;
            case 3:
                upgradeBookQuantity();
                break;
            case 4:
                searchBook();
                break;
            case 5:
                showAllBooks();
                break;
            case 6:
                registerStudent();
                break;
            case 7:
                showAllStudents();
                break;
            case 8:
                checkOutBook();
                break;
            case 9:
                checkInBook();
                break;
            default:
                System.out.println("Invalid option. Please select 1-9.");
                displayMenu();
        }
    }

    public static void main(String[] args){
        displayMenu();
    }
}
