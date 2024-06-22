import java.util.Scanner;
public class librarian {
    private static int inpp;
    private static Scanner scanner = new Scanner(System.in);
    public static int memberID = 1;
    public static int bookID = 1;
    
    public static void menu_librarian(){
        do{
            System.out.println("1.   Register a member");
            System.out.println("2.   Remove a member");
            System.out.println("3.   Add a book");
            System.out.println("4.   Remove a book");
            System.out.println("5.   View all members along with their books and fines to be paid");
            System.out.println("6.   View all books");
            System.out.println("7.   Back");

            System.out.println("--------------------");
            inpp = Integer.parseInt(scanner.nextLine());
            System.out.println("--------------------");

            if (inpp==1){
                addMember();
            }
            if(inpp==2){
                removeMember();
            }
            if (inpp==3){
                addBook();
            }
            if(inpp==4){
                removeBook();
            }
            if(inpp==5){

            }
            if(inpp==6){
                viewAllBook();
            }
        }while(inpp!=7);
    }

    private static void addMember(){
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Phone no: ");
        String phoneNo = scanner.nextLine();
        Members newMember = new Members(name,age,phoneNo,memberID,0);
        if (Record.appendMember(newMember)){
            System.out.println("--------------------");
            System.out.println("Member Successfully Registered with <Member ID: "+(memberID-1)+">!");
            System.out.println("--------------------");
        }
    }

    private static void addBook(){
        System.out.print("Book title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Copies: ");
        int copies = Integer.parseInt(scanner.nextLine());
        Books newBook = new Books(bookTitle,author,copies,bookID);
        if (Record.appendBook(newBook)){
            System.out.println("--------------------");
            System.out.println("Book Added Successfully!");
            System.out.println("--------------------");
        }
    }

    private static void removeMember(){
        System.out.print("Enter member ID to be removed: ");
        int mID = Integer.parseInt(scanner.nextLine());
        int retval = Record.removeMem(mID);
        if (retval==1){
            System.out.println("--------------------");
            System.out.println("Member removed successfully!");
            System.out.println("--------------------");
        }else if(retval==0){
            System.out.println("--------------------");
            System.out.println("The entered member ID does not match to any ID!");
            System.out.println("--------------------");
        }
    }

    private static void removeBook(){
        System.out.print("Enter Book ID to be removed: ");
        int bID = Integer.parseInt(scanner.nextLine());
        if(Record.removeBuk(bID)){
            System.out.println("--------------------");
            System.out.println("Book removed successfully!");
            System.out.println("--------------------");
        }else{
            System.out.println("--------------------");
            System.out.println("The entered book ID does not match to any ID!");
            System.out.println("--------------------");
        }
    }

    private static void viewAllBook(){
        Record.printAllBooks();
    }
}