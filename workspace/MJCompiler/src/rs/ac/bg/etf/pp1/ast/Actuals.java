// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:19


package rs.ac.bg.etf.pp1.ast;

public class Actuals extends ActualPars {

    private ActualParsComplete ActualParsComplete;

    public Actuals (ActualParsComplete ActualParsComplete) {
        this.ActualParsComplete=ActualParsComplete;
        if(ActualParsComplete!=null) ActualParsComplete.setParent(this);
    }

    public ActualParsComplete getActualParsComplete() {
        return ActualParsComplete;
    }

    public void setActualParsComplete(ActualParsComplete ActualParsComplete) {
        this.ActualParsComplete=ActualParsComplete;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParsComplete!=null) ActualParsComplete.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParsComplete!=null) ActualParsComplete.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParsComplete!=null) ActualParsComplete.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Actuals(\n");

        if(ActualParsComplete!=null)
            buffer.append(ActualParsComplete.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Actuals]");
        return buffer.toString();
    }
}
