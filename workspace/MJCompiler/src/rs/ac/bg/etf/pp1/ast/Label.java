// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:19


package rs.ac.bg.etf.pp1.ast;

public class Label implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String lName;

    public Label (String lName) {
        this.lName=lName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName=lName;
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
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Label(\n");

        buffer.append(" "+tab+lName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Label]");
        return buffer.toString();
    }
}
