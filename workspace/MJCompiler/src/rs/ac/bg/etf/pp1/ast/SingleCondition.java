// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class SingleCondition extends Condition {

    private CondTermComplete CondTermComplete;

    public SingleCondition (CondTermComplete CondTermComplete) {
        this.CondTermComplete=CondTermComplete;
        if(CondTermComplete!=null) CondTermComplete.setParent(this);
    }

    public CondTermComplete getCondTermComplete() {
        return CondTermComplete;
    }

    public void setCondTermComplete(CondTermComplete CondTermComplete) {
        this.CondTermComplete=CondTermComplete;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTermComplete!=null) CondTermComplete.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTermComplete!=null) CondTermComplete.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTermComplete!=null) CondTermComplete.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleCondition(\n");

        if(CondTermComplete!=null)
            buffer.append(CondTermComplete.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleCondition]");
        return buffer.toString();
    }
}
