public class JobLList implements WaitingListADT<JobNode> {
    private JobNode head;
    private int size;

    public JobLList(){
        head = new JobNode(-1,-1,-1,-1,-1,"blank head node");
        size = 0;
    }

    public void schedule(JobNode newObject){
        size++;

        JobNode preNode = head;
        JobNode curNode = head.getNext();

        while( curNode != null && curNode.getPriority() >= newObject.getPriority() ){
            preNode = curNode;
            curNode = curNode.getNext();
        }

        preNode.setNext(newObject);
        newObject.setNext(curNode);
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }


    public int clean(float cleaningTime){
        JobNode preNode = head;
        JobNode curNode = head.getNext();
        int jobsCleanedCount = 0;

        while( curNode != null ){
            if( curNode.getArrivalTime() + curNode.getTimeToLive() < cleaningTime ){
                preNode.setNext(curNode.getNext());
                curNode = preNode.getNext();
                jobsCleanedCount++;
            }else{
                preNode = curNode;
                curNode = curNode.getNext();
            }
        }

        size -= jobsCleanedCount;
        return jobsCleanedCount;
    }

    public void clear(){
        head.setNext(null);
        size = 0;
    }

    public WaitingListADT<JobNode> duplicate(){
        JobLList newJobLList = new JobLList();

        JobNode curNode = head.getNext();

        while( curNode != null ){
            newJobLList.schedule(curNode.copy());
            curNode = curNode.getNext();
        }

        return newJobLList;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append("Job List is empty: "+isEmpty());
        str.append("\n"+"The size is: "+size+" job(s).");

        JobNode curNode = head.getNext();
        while( curNode != null ){
            str.append("\n"+"job #"+curNode.getJobId()+" : "+curNode.getDescription()+" (UID "+curNode.getUserId()
                    +") "+curNode.getPriority());
            curNode = curNode.getNext();
        }
        return str.toString();
    }
}
