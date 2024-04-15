// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class TypeName extends Type {

    private String tName;

    public TypeName (String tName) {
        this.tName=tName;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName=tName;
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
        buffer.append("TypeName(\n");

        buffer.append(" "+tab+tName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeName]");
        return buffer.toString();
    }
}
