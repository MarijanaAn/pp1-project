// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends Statement {

    private ConditionComplete ConditionComplete;
    private Statement Statement;
    private ElseList ElseList;
    private Statement Statement1;

    public IfElseStatement (ConditionComplete ConditionComplete, Statement Statement, ElseList ElseList, Statement Statement1) {
        this.ConditionComplete=ConditionComplete;
        if(ConditionComplete!=null) ConditionComplete.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseList=ElseList;
        if(ElseList!=null) ElseList.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
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

    public ElseList getElseList() {
        return ElseList;
    }

    public void setElseList(ElseList ElseList) {
        this.ElseList=ElseList;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionComplete!=null) ConditionComplete.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseList!=null) ElseList.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionComplete!=null) ConditionComplete.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseList!=null) ElseList.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionComplete!=null) ConditionComplete.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseList!=null) ElseList.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

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

        if(ElseList!=null)
            buffer.append(ElseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}
