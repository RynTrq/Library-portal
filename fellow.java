import java.util.Scanner;

public class fellow {
    private static int innp;
    public static int currentMemID;
    private static Scanner scanner = new Scanner(System.in);

    public static void isFellow(){
        System.out.println("--------------------");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone No: ");
        String phoneNo = scanner.nextLine();
        if(Record.checkFellow(name,phoneNo)){
            System.out.println("--------------------");
            menu_fellow();
        }
    }

    public static void menu_fellow(){
        do{
            System.out.println("1.   List Available Books");
            System.out.println("2.   List My Books");
            System.out.println("3.   Issue book");
            System.out.println("4.   Return book");
            System.out.println("5.   Pay fine");
            System.out.println("6.   Back");

            System.out.println("--------------------");
            innp = Integer.parseInt(scanner.nextLine());
            System.out.println("--------------------");

            if(innp==1){
                booksList();
            }
            if(innp==3){
                IssueBook();
            }
            if(innp==4){
                returnBook();
            }
            if(innp==6){
                currentMemID = 0;
            }
            if(innp==2){
                listmyBooks();
            }
            if(innp==5){
                payFine();
            }

        }while(innp!=6);
    }

    private static void booksList(){
        Record.printAllBooks();
    }

    private static void IssueBook(){
        System.out.print("Book ID: ");
        int bokID = Integer.parseInt(scanner.nextLine());
        System.out.print("Book Name: ");
        String bokName = scanner.nextLine();
        Record.IssueBook(bokID,bokName);
    }

    private static void returnBook(){
        System.out.print("Enter Book Id: ");
        int bookID = Integer.parseInt(scanner.nextLine());
        Record.returnBook(bookID);
    }

    private static void listmyBooks(){
        Record.listMyBooks();
    }

    private static void payFine(){
        Record.payFine();
    }
}
