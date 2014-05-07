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

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("[");
        buff.append("content=" + content);
        buff.append(",done=" + done);
        buff.append("]");
        return buff.toString();
    }
}
