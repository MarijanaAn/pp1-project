// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:17


package rs.ac.bg.etf.pp1.ast;

public class ErrorConstList extends ConstDeclList {

    public ErrorConstList () {
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
        buffer.append("ErrorConstList(\n");

        buffer.append(tab);
        buffer.append(") [ErrorConstList]");
        return buffer.toString();
    }
}