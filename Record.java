import java.util.ArrayList;
import java.util.List;
public class Record {
    private static Members[] membersList = new Members[100];
    private static int noOfMembers = 0;
    private static fellowData[] fellowMemdataList = new fellowData[100];

    private static Books[] booksList = new Books[100];
    private static int noOfBooks = 0;
    
    public static boolean appendMember(Members mem){
        String inpPhoneNo = mem.phoneNo();
        for(int i =0;i<100;i++){
            if (membersList[i]!=null){
                String phoneNo = membersList[i].phoneNo();
                if (phoneNo.equals(inpPhoneNo)){
                    System.out.println("The entered phone no already exists.");
                    return false;
                }
            }
        }
        membersList[noOfMembers]=mem;
        fellowMemdataList[noOfMembers]=new fellowData(mem.Memberid(),0);
        noOfMembers+=1;
        librarian.memberID+=1;
        return true;
    }

    public static boolean appendBook(Books buk){
        String inpTitle = buk.bookTitle();
        String inpAuthor = buk.author();
        int inpCopies = buk.copies();
        for(int i =0;i<100;i++){
            if (booksList[i]!=null){
                String title = booksList[i].bookTitle();
                String author = booksList[i].author();
                if(inpAuthor.equals(author)){
                    if(inpTitle.equals(title)){
                        System.out.println("--------------------");
                        System.out.println("The entered book already exists,adding the copies.");
                        System.out.println("--------------------");
                        int totalCopies = booksList[i].copies() + inpCopies;
                        Books book = new Books(title,author,totalCopies,librarian.bookID);
                        booksList[i] = null;
                        librarian.bookID+=totalCopies;
                        booksList[noOfBooks]=book;
                        noOfBooks+=1;
                        return false;
                    }
                }
            }
        }
        booksList[noOfBooks]=buk;
        noOfBooks+=1;
        librarian.bookID+=buk.copies();
        for(int i=0;i<10;i++){
            System.out.println(booksList[i]);
        }
            return true;
    }

    public static int removeMem(int memID){
        for(int i = 0;i<100;i++){
            if(membersList[i]!=null){
                int IDOfMem = membersList[i].Memberid();
                if (memID==IDOfMem){
                    fellowMemdataList[i].executeFine();
                    if(fellowMemdataList[i].noOfBooks!=0){
                        System.out.println("--------------------");
                        System.out.println("The given member hasn't submitted all the books.");
                        System.out.println("--------------------");
                        return -1;
                    }else if(fellowMemdataList[i].fineOn1<=0 && fellowMemdataList[i].fineOn2<=0){
                        System.out.println("--------------------");
                        System.out.println("The given member hasn't cleared the fine.");
                        System.out.println("--------------------");
                        return -1;
                    }
                    membersList[i]=null;
                    return 1;
                }
            }
        }
        return 0;
    }

    public static boolean removeBuk(int bukID){
        for(int i=0;i<100;i++){
            if(booksList[i]!=null){
                int IDOfBuk = booksList[i].bookID();
                if (bukID>=IDOfBuk && bukID<(IDOfBuk+booksList[i].copies())){
                    if ((booksList[i].copies()-1)>0){
                        Books book = new Books(booksList[i].bookTitle(),booksList[i].author(),(booksList[i].copies()-1),booksList[i].bookID());
                        booksList[i]=book;
                        return true;
                    }
                    else{
                        booksList[i]=null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void printAllBooks(){
        System.out.println("--------------------");
        for(int i=0;i<100;i++){
            if(booksList[i]!=null){
                for(int k = booksList[i].bookID();k<(booksList[i].bookID()+(booksList[i].copies()));k++){
                    System.out.println("Book ID - "+k);
                    System.out.println("Name - "+booksList[i].bookTitle());
                    System.out.println("Author - "+booksList[i].author());
                    System.out.println();
                }
            }
        }
        System.out.println("--------------------");
    }


    public static boolean checkFellow(String name, String phoneNo){
        for(int i=0 ; i<100 ; i++){
            if (membersList[i]!=null){
                if(membersList[i].phoneNo().equals(phoneNo)){
                    if(membersList[i].name().equals(name)){
                        System.out.println();
                        System.out.println("Welcome "+name+". <Member ID: "+membersList[i].Memberid()+">!");
                        fellow.currentMemID = membersList[i].Memberid();
                        return true;
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Member with Name: "+name+" and Phone No: "+phoneNo+" doesn't exist.");
        return false;
    }

    public static void IssueBook(int bookID,String bookName){
        int k=-1;
        for(int j=0;j<100;j++){
            if (booksList[j]!=null){
                if (bookID>=booksList[j].bookID() && bookID<(booksList[j].bookID()+booksList[j].copies())){
                    k=j;
                    break;
                }
            }
            if (j==99){
                System.out.println("--------------------");
                System.out.println("The given book ID is not present in the library.");
                System.out.println("--------------------");
                k=-1;
            }
        }
        if(k!=-1){
            for (int i =0;i<100;i++){
                if (fellowMemdataList[i]!=null){
                    if(fellowMemdataList[i].MEMid==fellow.currentMemID){
                        if(fellowMemdataList[i].noOfBooks<=2){
                            fellowMemdataList[i].executeFine();
                            if(fellowMemdataList[i].fineOn1==0 && fellowMemdataList[i].fineOn2==0){
                                fellowMemdataList[i].issuedbookslist[fellowMemdataList[i].noOfBooks]=booksList[k];
                                fellowMemdataList[i].starTime[fellowMemdataList[i].noOfBooks]=System.currentTimeMillis();
                                fellowMemdataList[i].noOfBooks+=1;
                                booksList[k]=new Books(booksList[k].bookTitle(), booksList[k].author(), (booksList[k].copies()-1), booksList[k].bookID());
                                if (booksList[k].copies()==0){
                                    booksList[k]=null;
                                }
                                System.out.println("--------------------");
                                System.out.println("Book Issued successfully!");
                                System.out.println("--------------------");
                            }else{
                                System.out.println("--------------------");
                                System.out.println("You've got some fine to be cleared.You can't issue any book now.");
                                System.out.println("--------------------");
                            }
                        }else{
                            System.out.println("--------------------");
                            System.out.println("You already have "+fellowMemdataList[i].noOfBooks +" books, you can;t issue any more.");
                            System.out.println("--------------------");
                        }
                    } 
                }
            }
        }
    }

    public static void returnBook(int bookID){
        int k = -1;
        for(int i =0;i<100;i++){
            if (fellowMemdataList[i]!=null){
                if(fellow.currentMemID==fellowMemdataList[i].MEMid);
                k = i;
                break;
            }
        }
        int index = -1;
        if(bookID>=fellowMemdataList[k].issuedbookslist[0].bookID() && bookID<(fellowMemdataList[k].issuedbookslist[0].bookID()+fellowMemdataList[k].issuedbookslist[0].copies())){
            index=0;
        }else if(bookID>=fellowMemdataList[k].issuedbookslist[1].bookID() && bookID<(fellowMemdataList[k].issuedbookslist[1].bookID()+fellowMemdataList[k].issuedbookslist[1].copies())){
            index=1;
        }
        if(index==-1){
            System.out.println("--------------------");
            System.out.println("The specified bookID is not available with you.");
            System.out.println("--------------------");
        }else{
            fellowMemdataList[k].executeFine();
            fellowMemdataList[k].issuedbookslist[index]=null;
            fellowMemdataList[k].noOfBooks-=1;
            System.out.println("--------------------");
            System.out.println("The Book has been returned successfully!");
            System.out.println("--------------------");
        }

    }

    public static void listMyBooks(){
        for(int i=0;i<100;i++){
            if (fellowMemdataList[i]!=null){
                if (fellowMemdataList[i].MEMid==fellow.currentMemID){
                    if(fellowMemdataList[i].noOfBooks==0){
                        System.out.println("--------------------");
                        System.out.println("You have no books issued.");
                        System.out.println("--------------------");
                    }else{
                        System.out.println("--------------------");
                        if(fellowMemdataList[i].issuedbookslist[0]!=null){
                            System.out.println("Book ID: "+fellowMemdataList[i].issuedbookslist[0].bookID());
                            System.out.println("Book Title: "+fellowMemdataList[i].issuedbookslist[0].bookTitle());
                            System.out.println("Author: "+fellowMemdataList[i].issuedbookslist[0].author());
                            System.out.println();

                        }
                        if(fellowMemdataList[i].issuedbookslist[1]!=null){
                            System.out.println("Book ID: "+fellowMemdataList[i].issuedbookslist[1].bookID());
                            System.out.println("Book Title: "+fellowMemdataList[i].issuedbookslist[1].bookTitle());
                            System.out.println("Author: "+fellowMemdataList[i].issuedbookslist[1].author());
                            System.out.println();
                        }
                        System.out.println("--------------------");
                    }
                    break;
                }
            }
        }
    }

    public static void payFine(){
        for(int i=0;i<100;i++){
            if (fellowMemdataList[i]!=null){
                if (fellowMemdataList[i].MEMid==fellow.currentMemID){
                    fellowMemdataList[i].executeFine();
                    System.out.println("--------------------");
                    System.out.println("Your total fine of Rs"+(fellowMemdataList[i].fineOn1+fellowMemdataList[i].fineOn2)+" has been payed successfully!");
                    System.out.println("--------------------");
                    long timeNow = System.currentTimeMillis();
                    fellowMemdataList[i].starTime[0]=timeNow;
                    fellowMemdataList[i].starTime[0]=timeNow;
                }
            }
        }
    }

}