package teiluebungen.threading;

public abstract class Worker {

    protected String name;
    protected boolean shouldRun;

    public Worker(String name) {
        this.name = name;
        this.shouldRun = true;
    }

    protected abstract void work();

    protected void printStarted(){
        System.out.println(this.name + " has been started.");
    }

    protected void printStopped(){
        System.out.println(this.name + " has been stopped.");
    }

    public void stopWorker(){
        this.shouldRun = false;
    }

}
