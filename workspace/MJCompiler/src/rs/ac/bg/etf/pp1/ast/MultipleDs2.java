// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class MultipleDs2 extends DsList2 {

    private DsList2 DsList2;
    private DesignatorStatement DesignatorStatement;

    public MultipleDs2 (DsList2 DsList2, DesignatorStatement DesignatorStatement) {
        this.DsList2=DsList2;
        if(DsList2!=null) DsList2.setParent(this);
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
    }

    public DsList2 getDsList2() {
        return DsList2;
    }

    public void setDsList2(DsList2 DsList2) {
        this.DsList2=DsList2;
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DsList2!=null) DsList2.accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DsList2!=null) DsList2.traverseTopDown(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DsList2!=null) DsList2.traverseBottomUp(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleDs2(\n");

        if(DsList2!=null)
            buffer.append(DsList2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleDs2]");
        return buffer.toString();
    }
}
