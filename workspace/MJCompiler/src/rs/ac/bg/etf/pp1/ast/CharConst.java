// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:17


package rs.ac.bg.etf.pp1.ast;

public class CharConst extends Consts {

    private String I1;
    private Character C2;

    public CharConst (String I1, Character C2) {
        this.I1=I1;
        this.C2=C2;
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public Character getC2() {
        return C2;
    }

    public void setC2(Character C2) {
        this.C2=C2;
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
        buffer.append("CharConst(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        buffer.append(" "+tab+C2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConst]");
        return buffer.toString();
    }
}
