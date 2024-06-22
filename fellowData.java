public class fellowData {
    protected int MEMid;
    protected int noOfBooks;
    protected int fineOn1 = 0;
    protected int fineOn2 = 0;

    protected long[] starTime = new long[2];

    protected Books[] issuedbookslist = new Books[2];

    public fellowData(int MEMid,int noOfBooks){
        this.MEMid = MEMid;
        this.noOfBooks = noOfBooks;
    }

    public void executeFine(){
        long endTime = System.currentTimeMillis();
        if(issuedbookslist[0]!=null){
            long secs = (endTime - starTime[0])/(long)1000;
            if (secs>10){
                fineOn1 = (int)(secs-10)*3;
            }
        }
        if(issuedbookslist[1]!=null){
            long secs = (endTime - starTime[1])/(long)1000;
            if (secs>10){
                fineOn2 = (int)(secs-10)*3;
            }
        }
    }

}
