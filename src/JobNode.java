/**
 * File Header COMES HERE
 */

/**
 * JavaDoc Class Header COMES HERE
 *
 */

public class JobNode {

    // Class Fields
    private static int jobCount = 0; // number of jobs already created

    // Object Fields
    private int jobId; 			// unique job identifier
    private float arrivalTime;  // arrival time in seconds
    private int userId;			// identifier of the user that created the job
    private int priority; 		// job priority
    private int timeToLive; 	// job Time To Live in seconds
    private String description; // job description

    private JobNode next; // reference to the next job in the linked list

    // Constructor using fields
    /**
     * Description of the constructor comes here
     * @param arrivalTime
     * @param userId
     * @param priority
     * @param ttl
     * @param description
     */
    public JobNode(float arrivalTime, int userId, int priority,
                   int ttl, String description) {
        this.jobId = ++jobCount;
        this.arrivalTime = arrivalTime;
        this.userId = userId;
        this.priority = priority;
        this.timeToLive = ttl;
        this.description = description;
        this.next = null;
    }

    public JobNode(int jobId, float arrivalTime, int userId, int priority,
                   int ttl, String description) {
        this.jobId = jobId;
        this.arrivalTime = arrivalTime;
        this.userId = userId;
        this.priority = priority;
        this.timeToLive = ttl;
        this.description = description;
        this.next = null;
    }



    // TODO Add Getters and Setters Methods as needed


    public float getArrivalTime() {
        return arrivalTime;
    }

    public static int getJobCount() {
        return jobCount;
    }

    public int getJobId() {
        return jobId;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public int getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public JobNode getNext() {
        return next;
    }

    public void setNext(JobNode next) {
        this.next = next;
    }

    /**
     * This method returns a new reference to a copy of the current JobNode
     * @return a new reference to a copy of this (instanceof JobNode)
     */
    public JobNode copy() {
        JobNode newJobNode = new JobNode(jobId, arrivalTime, userId, priority,
                    timeToLive, description);
        return newJobNode;
    }
}