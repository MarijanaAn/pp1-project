// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private ConditionComplete ConditionComplete;
    private Statement Statement;

    public IfStatement (ConditionComplete ConditionComplete, Statement Statement) {
        this.ConditionComplete=ConditionComplete;
        if(ConditionComplete!=null) ConditionComplete.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ConditionComplete getConditionComplete() {
        return ConditionComplete;
    }

    public void setConditionComplete(ConditionComplete ConditionComplete) {
        this.ConditionComplete=ConditionComplete;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionComplete!=null) ConditionComplete.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionComplete!=null) ConditionComplete.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionComplete!=null) ConditionComplete.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(ConditionComplete!=null)
            buffer.append(ConditionComplete.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
