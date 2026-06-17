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
        if(id != null && !id.trim().isEmpty()){
            this.id = id.trim();
        }
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        if(title != null && !title.trim().isEmpty()){
            this.title = title.trim();
        }
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
        if(name != null && !name.trim().isEmpty()){
            this.name = name.trim();
        }
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
            setAuthor(author);
            setQuantity(quantity);
        }

        public String getAuthor(){
            return author;
        }

        public void setAuthor(String author){
            if(author != null && !author.trim().isEmpty()){
                this.author = author.trim();
            }
        }

        public int getQuantity(){
            return quantity;
        }

        public void setQuantity(int quantity){
            if(quantity >= 0){
                this.quantity = quantity;
            }
        }

        @Override
        public void display(){
            System.out.println("ID:" + getId() + " | " + getTitle() + " by " + getAuthor() + " | qty:" + getQuantity());
        }

        @Override
        public void lend(){
            if(quantity > 0){
                quantity--;
            }
        }

        @Override
        public void returnItem(){
            quantity++;
        }
    }

    static class Books{
        private static final int MAX_BOOKS = 100;
        private Book[] books = new Book[MAX_BOOKS];
        private int count = 0;

        public void addBook(Book b){
            if(count >= MAX_BOOKS){
                System.out.println("Book list full.");
                return;
            }
            books[count++] = b;
            System.out.println("Book added successfully.");
        }

        public Book findById(String id){
            for(int i = 0; i < count; i++){
                if(books[i].getId() != null &&
                   books[i].getId().equals(id)){
                    return books[i];
                }
            }
            return null;
        }

        public void upgradeQuantity(String id,int add){
            Book b = findById(id);
            if(b == null){
                System.out.println("Book ID "+id+" not found. Please check the Book ID and try again.");
                return;
            }
            b.setQuantity(b.getQuantity() + add);
            System.out.println("Quantity updated successfully.");
        }

        public void searchByTitle(String q){
            boolean found = false;
            for(int i = 0; i < count; i++){
                if(books[i].getTitle()
                        .toLowerCase()
                        .contains(q.toLowerCase())){
                    books[i].display();
                    found = true;
                }
            }
            if(!found){
                System.out.println("No books match the title.");
            }
        }

        public void searchByAuthor(String q){
            boolean found = false;
            for(int i = 0; i < count; i++){
                if(books[i].getAuthor()
                        .toLowerCase()
                        .contains(q.toLowerCase())){
                    books[i].display();
                    found = true;
                }
            }
            if(!found){
                System.out.println("No books match the author.");
            }
        }

        public void search(String q){
            searchByTitle(q);
        }

        public void search(String q,boolean byAuthor){
            if(byAuthor){
                searchByAuthor(q);
            }
            else{
                searchByTitle(q);
            }
        }

        public void showAll(){
            if(count == 0){
                System.out.println("No books in library.");
                return;
            }
            for(int i = 0; i < count; i++){
                books[i].display();
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
            if(studentId != null &&
               !studentId.trim().isEmpty()){
                this.studentId = studentId.trim();
            }
        }

        public String[] getBorrowed(){
            return borrowed;
        }

        public int getBorrowedCount(){
            return borrowedCount;
        }

        public boolean hasBorrowed(String bookId){
            for(int i = 0; i < borrowedCount; i++){
                if(borrowed[i] != null &&
                    borrowed[i].equals(bookId)){
                    return true;
                }
            }
            return false;
        }

        public void borrowBook(String bookId){
            if(borrowedCount >= borrowed.length){
                System.out.println("Borrowing limit reached.");
                return;
            }
            borrowed[borrowedCount++] = bookId;
        }

        public void returnBook(String bookId){
            for(int i = 0; i < borrowedCount; i++){
                if(borrowed[i] != null &&
                   borrowed[i].equals(bookId)){
                    for(int j = i;
                        j < borrowedCount - 1;
                        j++){
                        borrowed[j] = borrowed[j + 1];
                    }
                    borrowed[borrowedCount - 1] = null;
                    borrowedCount--;
                    return;
                }
            }
        }

        @Override
        public void showInfo(){
            System.out.println("Name:" + getName() + " | ID:" + getStudentId());
        }
    }

    static class Users{
        private static final int MAX_USERS = 100;
        private User[] users = new User[MAX_USERS];
        private int count = 0;
        public void register(User u){
            if(count >= MAX_USERS){
                System.out.println("User list full.");
                return;
            }
            users[count++] = u;
            System.out.println("Student registered successfully.");
        }

        public User findById(String id){
            for(int i = 0; i < count; i++){
                if(users[i].getStudentId() != null &&
                   users[i].getStudentId().equals(id)){
                    return users[i];
                }
            }
            return null;
        }

        public void showAll(){
            if(count == 0){
                System.out.println("No registered students.");
                return;
            }
            for(int i = 0; i < count; i++){
                System.out.println("Name:" + users[i].getName() + " | ID:" + users[i].getStudentId());
            }
        }
    }   
    
    static Books library = new Books();
    static Users students = new Users();
    static Scanner sc = new Scanner(System.in);
    static String getValidInput(String prompt){
        
        while(true){
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if(input.length() == 0){
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            return input;
        }
    }

    static void addNewBook(){
        String bid = getValidInput(
            "Enter Book ID: "
        );
        if(library.findById(bid) != null){
            System.out.println("Book ID already exists.");
            return;
        }

        String title = getValidInput(
            "Enter Title: "
        );

        String author = getValidInput(
            "Enter Author: "
        );

        int quantity;

        while(true){
            System.out.print(
                "Enter Quantity: "
            );
            try{
                quantity = Integer.parseInt(
                    sc.nextLine().trim()
                );
                if(quantity < 0){
                    System.out.println("Quantity cannot be negative.");
                    continue;
                }
                break;
            }
            catch(NumberFormatException e){
                System.out.println("Invalid quantity.");
            }
        }

        Book b = new Book(
            bid,
            title,
            author,
            quantity
        );
        library.addBook(b);
    }

    static void upgradeBookQuantity(){
        String id = getValidInput(
            "Enter Book ID to upgrade: "
        );

        int add;
        while(true){
            System.out.print(
                "Enter amount to add: "
            );
            try{
                add = Integer.parseInt(
                    sc.nextLine().trim()
                );
                if(add <= 0){
                    System.out.println("Quantity increment must be positive.");
                    continue;
                }
                break;
            }
            catch(NumberFormatException e){
                System.out.println("Invalid amount.");
            }
        }
        library.upgradeQuantity(id, add);
    }

    static void searchBook(){
        String q = getValidInput(
            "Enter title keyword: "
        );
        library.searchByTitle(q);
    }

    static void showAllBooks(){
        library.showAll();
    }

    static void registerStudent(){
        String sid = getValidInput(
            "Enter Student ID: "
        );
        if(students.findById(sid) != null){
            System.out.println("Student ID already exists.");
            return;
        }

        String name = getValidInput(
            "Enter Name: "
        );

        User u = new User(
            name,
            sid
        );

        students.register(u);
    }

    static void showAllStudents(){
        students.showAll();
    }

    static void checkOutBook(){
        String sid = getValidInput(
            "Enter Student ID: "
        );

        User u = students.findById(sid);
        if(u == null){
            System.out.println("Student ID "+sid+" not found. Please check the Student ID and try again.");
            return;
        }

        String bid = getValidInput(
            "Enter Book ID to check out: "
        );

        Book b = library.findById(bid);
        if(b == null){
            System.out.println("Book ID "+bid+" not found. Please check the Book ID and try again.");
            return;
        }

        if(u.hasBorrowed(bid)){
            System.out.println("Student has already borrowed this book.");
            return;
        }

        if(b.getQuantity() <= 0){
            System.out.println("Book not available.");
            return;
        }

        b.lend();
        u.borrowBook(bid);
        System.out.println("Book checked out successfully.");
    }

    static void checkInBook(){
        String sid = getValidInput(
            "Enter Student ID: "
        );

        User u = students.findById(sid);
        if(u == null){
            System.out.println("Student ID "+sid+" not found. Please check the Student ID and try again.");
            return;
        }

        String bid = getValidInput(
            "Enter Book ID to check in: "
        );

        Book b = library.findById(bid);
        if(b == null){
            System.out.println("Book ID "+bid+" not found. Please check the Book ID and try again.");
            return;
        }

        if(!u.hasBorrowed(bid)){
            System.out.println("This student has not borrowed that book.");
            return;
        }

        b.returnItem();
        u.returnBook(bid);
        System.out.println("Book checked in successfully.");
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
        int option;
        try{

            option = Integer.parseInt(opt);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid option. Please select 1-9.");
            return;
        }

        switch(option){
            case 1:
                System.out.println("Goodbye");
                System.exit(0);
                break;

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
        }
    }

    public static void main(String[] args){
        while(true){
            displayMenu();
        }
    }
}