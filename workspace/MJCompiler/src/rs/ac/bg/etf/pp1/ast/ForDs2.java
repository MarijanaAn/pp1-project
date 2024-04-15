// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class ForDs2 implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private DsList2 DsList2;

    public ForDs2 (DsList2 DsList2) {
        this.DsList2=DsList2;
        if(DsList2!=null) DsList2.setParent(this);
    }

    public DsList2 getDsList2() {
        return DsList2;
    }

    public void setDsList2(DsList2 DsList2) {
        this.DsList2=DsList2;
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
        if(DsList2!=null) DsList2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DsList2!=null) DsList2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DsList2!=null) DsList2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForDs2(\n");

        if(DsList2!=null)
            buffer.append(DsList2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForDs2]");
        return buffer.toString();
    }
}
