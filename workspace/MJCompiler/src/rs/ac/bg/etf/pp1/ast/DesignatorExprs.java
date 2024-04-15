// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:19


package rs.ac.bg.etf.pp1.ast;

public class DesignatorExprs extends DesignatorExpr {

    private DesignatorExpr DesignatorExpr;
    private Expr Expr;

    public DesignatorExprs (DesignatorExpr DesignatorExpr, Expr Expr) {
        this.DesignatorExpr=DesignatorExpr;
        if(DesignatorExpr!=null) DesignatorExpr.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorExpr getDesignatorExpr() {
        return DesignatorExpr;
    }

    public void setDesignatorExpr(DesignatorExpr DesignatorExpr) {
        this.DesignatorExpr=DesignatorExpr;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorExpr!=null) DesignatorExpr.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorExpr!=null) DesignatorExpr.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorExpr!=null) DesignatorExpr.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorExprs(\n");

        if(DesignatorExpr!=null)
            buffer.append(DesignatorExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorExprs]");
        return buffer.toString();
    }
}
