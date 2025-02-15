// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class StaticVars extends StaticVarList {

    private StaticVarList StaticVarList;
    private VarDecl VarDecl;

    public StaticVars (StaticVarList StaticVarList, VarDecl VarDecl) {
        this.StaticVarList=StaticVarList;
        if(StaticVarList!=null) StaticVarList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public StaticVarList getStaticVarList() {
        return StaticVarList;
    }

    public void setStaticVarList(StaticVarList StaticVarList) {
        this.StaticVarList=StaticVarList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticVarList!=null) StaticVarList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticVarList!=null) StaticVarList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticVarList!=null) StaticVarList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StaticVars(\n");

        if(StaticVarList!=null)
            buffer.append(StaticVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StaticVars]");
        return buffer.toString();
    }
}
