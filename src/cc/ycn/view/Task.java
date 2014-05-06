package cc.ycn.view;

public class Task extends BaseView {
    public String content = "N/A";
    public boolean done = false;

    public Task() {
        super();
    }

    public Task(String content) {
        super();
        this.content = content;
    }
}
