// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:18


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String className;
    private ExtType ExtType;
    private StaticVarList StaticVarList;
    private StaticInitializerList StaticInitializerList;
    private VarDeclList VarDeclList;
    private MethodDecls MethodDecls;

    public ClassDecl (String className, ExtType ExtType, StaticVarList StaticVarList, StaticInitializerList StaticInitializerList, VarDeclList VarDeclList, MethodDecls MethodDecls) {
        this.className=className;
        this.ExtType=ExtType;
        if(ExtType!=null) ExtType.setParent(this);
        this.StaticVarList=StaticVarList;
        if(StaticVarList!=null) StaticVarList.setParent(this);
        this.StaticInitializerList=StaticInitializerList;
        if(StaticInitializerList!=null) StaticInitializerList.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.MethodDecls=MethodDecls;
        if(MethodDecls!=null) MethodDecls.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public ExtType getExtType() {
        return ExtType;
    }

    public void setExtType(ExtType ExtType) {
        this.ExtType=ExtType;
    }

    public StaticVarList getStaticVarList() {
        return StaticVarList;
    }

    public void setStaticVarList(StaticVarList StaticVarList) {
        this.StaticVarList=StaticVarList;
    }

    public StaticInitializerList getStaticInitializerList() {
        return StaticInitializerList;
    }

    public void setStaticInitializerList(StaticInitializerList StaticInitializerList) {
        this.StaticInitializerList=StaticInitializerList;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public MethodDecls getMethodDecls() {
        return MethodDecls;
    }

    public void setMethodDecls(MethodDecls MethodDecls) {
        this.MethodDecls=MethodDecls;
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
        if(ExtType!=null) ExtType.accept(visitor);
        if(StaticVarList!=null) StaticVarList.accept(visitor);
        if(StaticInitializerList!=null) StaticInitializerList.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(MethodDecls!=null) MethodDecls.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtType!=null) ExtType.traverseTopDown(visitor);
        if(StaticVarList!=null) StaticVarList.traverseTopDown(visitor);
        if(StaticInitializerList!=null) StaticInitializerList.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(MethodDecls!=null) MethodDecls.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtType!=null) ExtType.traverseBottomUp(visitor);
        if(StaticVarList!=null) StaticVarList.traverseBottomUp(visitor);
        if(StaticInitializerList!=null) StaticInitializerList.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(MethodDecls!=null) MethodDecls.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(ExtType!=null)
            buffer.append(ExtType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticVarList!=null)
            buffer.append(StaticVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticInitializerList!=null)
            buffer.append(StaticInitializerList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecls!=null)
            buffer.append(MethodDecls.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
