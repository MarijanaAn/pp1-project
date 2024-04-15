// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends Statement {

    private ForEnter ForEnter;
    private ForDs1 ForDs1;
    private CondFactList CondFactList;
    private ForDs2 ForDs2;
    private Statement Statement;

    public ForStmt (ForEnter ForEnter, ForDs1 ForDs1, CondFactList CondFactList, ForDs2 ForDs2, Statement Statement) {
        this.ForEnter=ForEnter;
        if(ForEnter!=null) ForEnter.setParent(this);
        this.ForDs1=ForDs1;
        if(ForDs1!=null) ForDs1.setParent(this);
        this.CondFactList=CondFactList;
        if(CondFactList!=null) CondFactList.setParent(this);
        this.ForDs2=ForDs2;
        if(ForDs2!=null) ForDs2.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForEnter getForEnter() {
        return ForEnter;
    }

    public void setForEnter(ForEnter ForEnter) {
        this.ForEnter=ForEnter;
    }

    public ForDs1 getForDs1() {
        return ForDs1;
    }

    public void setForDs1(ForDs1 ForDs1) {
        this.ForDs1=ForDs1;
    }

    public CondFactList getCondFactList() {
        return CondFactList;
    }

    public void setCondFactList(CondFactList CondFactList) {
        this.CondFactList=CondFactList;
    }

    public ForDs2 getForDs2() {
        return ForDs2;
    }

    public void setForDs2(ForDs2 ForDs2) {
        this.ForDs2=ForDs2;
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
        if(ForEnter!=null) ForEnter.accept(visitor);
        if(ForDs1!=null) ForDs1.accept(visitor);
        if(CondFactList!=null) CondFactList.accept(visitor);
        if(ForDs2!=null) ForDs2.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForEnter!=null) ForEnter.traverseTopDown(visitor);
        if(ForDs1!=null) ForDs1.traverseTopDown(visitor);
        if(CondFactList!=null) CondFactList.traverseTopDown(visitor);
        if(ForDs2!=null) ForDs2.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForEnter!=null) ForEnter.traverseBottomUp(visitor);
        if(ForDs1!=null) ForDs1.traverseBottomUp(visitor);
        if(CondFactList!=null) CondFactList.traverseBottomUp(visitor);
        if(ForDs2!=null) ForDs2.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmt(\n");

        if(ForEnter!=null)
            buffer.append(ForEnter.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDs1!=null)
            buffer.append(ForDs1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactList!=null)
            buffer.append(CondFactList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDs2!=null)
            buffer.append(ForDs2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmt]");
        return buffer.toString();
    }
}
