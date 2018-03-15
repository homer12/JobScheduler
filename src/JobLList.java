public class JobLList implements WaitingListADT<JobNode> {
    private JobNode head;
    private int size;

    public JobLList(){
        head = null;
        size = 0;
    }

    public void schedule(JobNode newObject){
        size++;

        if( head == null ){
            /*
            before:     head = null
                               _______
            after:      head = |node0| -> null
            */

            head = newObject;
        }else{
            /*                 _______
            before:     head = |node0| -> null
                               _______    _______
            after:      head = |node0| -> |node1| -> null
            */

            JobNode preNode = null;
            JobNode curNode = head;

            while( curNode != null && curNode.getPriority() >= newObject.getPriority() ){
                preNode = curNode;
                curNode = curNode.getNext();
            }

            if( preNode == null ){
                // Insert the new node in the first place
                newObject.setNext(head);
                head = newObject;
            }else{
                newObject.setNext(curNode);
                preNode.setNext(newObject);
            }
        }
        /*
        JobNode preNode = head;
        JobNode curNode = head.getNext();

        while( curNode != null && curNode.getPriority() >= newObject.getPriority() ){
            preNode = curNode;
            curNode = curNode.getNext();
        }

        preNode.setNext(newObject);
        newObject.setNext(curNode);*/
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }


    public int clean(float cleaningTime){
        if( head == null )
            return 0;

        JobNode preNode = null;
        JobNode curNode = head;
        int jobsCleanedCount = 0;

        while( curNode != null ){
            if( curNode.getArrivalTime() + curNode.getTimeToLive() < cleaningTime ){
                if( preNode != null ){
                    preNode.setNext(curNode.getNext());
                    curNode = curNode.getNext();
                    jobsCleanedCount++;
                }else{
                    // Delete the first node
                    curNode = curNode.getNext();
                    head = curNode;
                }
            }else{
                preNode = curNode;
                curNode = curNode.getNext();
            }
        }

        /*JobNode preNode = head;
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
        */
        size -= jobsCleanedCount;
        return jobsCleanedCount;
    }

    public void clear(){
        size = 0;
        head = null;
    }

    public WaitingListADT<JobNode> duplicate(){
        JobLList newJobLList = new JobLList();

        JobNode curNode = head;

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

        JobNode curNode = head;
        while( curNode != null ){
            str.append("\n"+"job #"+curNode.getJobId()+" : "+curNode.getDescription()+" (UID "+curNode.getUserId()
                    +") "+curNode.getPriority());
            curNode = curNode.getNext();
        }
        return str.toString();
    }
}
