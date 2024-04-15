// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class ForDs1 implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private DsList1 DsList1;

    public ForDs1 (DsList1 DsList1) {
        this.DsList1=DsList1;
        if(DsList1!=null) DsList1.setParent(this);
    }

    public DsList1 getDsList1() {
        return DsList1;
    }

    public void setDsList1(DsList1 DsList1) {
        this.DsList1=DsList1;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DsList1!=null) DsList1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DsList1!=null) DsList1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DsList1!=null) DsList1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForDs1(\n");

        if(DsList1!=null)
            buffer.append(DsList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForDs1]");
        return buffer.toString();
    }
}
