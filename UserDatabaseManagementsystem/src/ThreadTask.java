public class ThreadTask extends Thread {
    private String taskName;

    public ThreadTask(String taskName) {
        this.taskName = taskName;
    }

    public void run() {
        System.out.print(taskName);
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}

