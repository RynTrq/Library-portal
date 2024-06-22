import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Library Portal Initialized...");
        
        int inp;
        do{
            System.out.println("--------------------");
        
            System.out.println("1.   Enter as a librarian");
            System.out.println("2.   Enter as a member");
            System.out.println("3.   Exit");

            System.out.println("--------------------");
            inp = Integer.parseInt(scanner.nextLine());
            System.out.println("--------------------");

            if (inp==1){
                librarian.menu_librarian();
            }
            if(inp==2){
                fellow.isFellow();
            }
        }while(inp!=3);
    }
}