// generated with ast extension for cup
// version 0.8
// 4/1/2024 15:34:19


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(MethodDecl MethodDecl);
    public void visit(DesignatorExpr DesignatorExpr);
    public void visit(Relop Relop);
    public void visit(DsList2 DsList2);
    public void visit(Assignop Assignop);
    public void visit(DsList1 DsList1);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(StaticInitializerList StaticInitializerList);
    public void visit(ExtType ExtType);
    public void visit(StatementList StatementList);
    public void visit(NamespaceList NamespaceList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(CondFactList CondFactList);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(VarDeclIdent VarDeclIdent);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(FormParams FormParams);
    public void visit(ActualParamList ActualParamList);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(DesignatorList DesignatorList);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ActualPars ActualPars);
    public void visit(VarDecls VarDecls);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(Type Type);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Consts Consts);
    public void visit(StaticVarList StaticVarList);
    public void visit(FormPars FormPars);
    public void visit(MethodDecls MethodDecls);
    public void visit(Modulus Modulus);
    public void visit(Divide Divide);
    public void visit(Multiply Multiply);
    public void visit(Minus Minus);
    public void visit(Plus Plus);
    public void visit(Lequal Lequal);
    public void visit(Less Less);
    public void visit(Gequal Gequal);
    public void visit(Greater Greater);
    public void visit(Nequal Nequal);
    public void visit(Equal Equal);
    public void visit(Assign Assign);
    public void visit(Label Label);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParams ActualParams);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(ActualParsComplete ActualParsComplete);
    public void visit(NoDesignatorExpr NoDesignatorExpr);
    public void visit(DesignatorExprs DesignatorExprs);
    public void visit(DesignatorNamespace DesignatorNamespace);
    public void visit(DesignatorName DesignatorName);
    public void visit(DesignatorArrayScope DesignatorArrayScope);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(DesignatorScope DesignatorScope);
    public void visit(NewTypeActual NewTypeActual);
    public void visit(NewTypeExpr NewTypeExpr);
    public void visit(Expression Expression);
    public void visit(BoolFactor BoolFactor);
    public void visit(CharFactor CharFactor);
    public void visit(NumFactor NumFactor);
    public void visit(FuncCall FuncCall);
    public void visit(Var Var);
    public void visit(MulTerm MulTerm);
    public void visit(FactorTerm FactorTerm);
    public void visit(AddopTermExpr AddopTermExpr);
    public void visit(TermExpr TermExpr);
    public void visit(MinusTermExpr MinusTermExpr);
    public void visit(NoCondFact NoCondFact);
    public void visit(CondFacts CondFacts);
    public void visit(NoRelExpr NoRelExpr);
    public void visit(RelExpr RelExpr);
    public void visit(MultCondTerm MultCondTerm);
    public void visit(SingleCondTerm SingleCondTerm);
    public void visit(CondTermComplete CondTermComplete);
    public void visit(ConditionError ConditionError);
    public void visit(MultCondition MultCondition);
    public void visit(SingleCondition SingleCondition);
    public void visit(ConditionComplete ConditionComplete);
    public void visit(NoDesignators NoDesignators);
    public void visit(NoDesignator NoDesignator);
    public void visit(HasDesignator HasDesignator);
    public void visit(ErrorDesStmt ErrorDesStmt);
    public void visit(Assignment Assignment);
    public void visit(Dec Dec);
    public void visit(Inc Inc);
    public void visit(ActualParDes ActualParDes);
    public void visit(AssignExpr AssignExpr);
    public void visit(ElseList ElseList);
    public void visit(NoDsList2 NoDsList2);
    public void visit(MultipleDs2 MultipleDs2);
    public void visit(SingleDs2 SingleDs2);
    public void visit(NoDsList1 NoDsList1);
    public void visit(MultipleDs1 MultipleDs1);
    public void visit(SingleDs1 SingleDs1);
    public void visit(ForEnter ForEnter);
    public void visit(ForDs2 ForDs2);
    public void visit(ForDs1 ForDs1);
    public void visit(Stmt Stmt);
    public void visit(ForStmt ForStmt);
    public void visit(PrintStmts PrintStmts);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadDesignator ReadDesignator);
    public void visit(IfStatement IfStatement);
    public void visit(IfElseStatement IfElseStatement);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(Continue Continue);
    public void visit(Break Break);
    public void visit(DStm DStm);
    public void visit(NoStms NoStms);
    public void visit(Stms Stms);
    public void visit(NamespaceType NamespaceType);
    public void visit(TypeName TypeName);
    public void visit(FormParsError FormParsError);
    public void visit(FormParsVarList FormParsVarList);
    public void visit(FormParsArrayList FormParsArrayList);
    public void visit(FormParsVar FormParsVar);
    public void visit(FormParsArray FormParsArray);
    public void visit(NoFormParams NoFormParams);
    public void visit(FormParam FormParam);
    public void visit(MethodVoidName MethodVoidName);
    public void visit(MethName MethName);
    public void visit(VoidMethod VoidMethod);
    public void visit(TypeMethod TypeMethod);
    public void visit(NoStaticInitializer NoStaticInitializer);
    public void visit(StaticInitializers StaticInitializers);
    public void visit(StaticInitializer StaticInitializer);
    public void visit(NoStaticVars NoStaticVars);
    public void visit(StaticVars StaticVars);
    public void visit(NoExtendsType NoExtendsType);
    public void visit(ExtendsType ExtendsType);
    public void visit(NoVarDeclarations NoVarDeclarations);
    public void visit(VarDelcarations VarDelcarations);
    public void visit(NoMethodDeclaration NoMethodDeclaration);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(NoMethDecls NoMethDecls);
    public void visit(MethDecls MethDecls);
    public void visit(ClassDecl ClassDecl);
    public void visit(VarDeclArray VarDeclArray);
    public void visit(VarDeclName VarDeclName);
    public void visit(NoVarDecls NoVarDecls);
    public void visit(ErrorVarList ErrorVarList);
    public void visit(VarDeclars VarDeclars);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(NoBrackets NoBrackets);
    public void visit(Brackets Brackets);
    public void visit(CharConst CharConst);
    public void visit(BoolConst BoolConst);
    public void visit(NumConst NumConst);
    public void visit(NoConstDecl NoConstDecl);
    public void visit(ErrorConstList ErrorConstList);
    public void visit(ConstDecls ConstDecls);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(NoDecl NoDecl);
    public void visit(ErrorDecls ErrorDecls);
    public void visit(DeclClass DeclClass);
    public void visit(DeclVar DeclVar);
    public void visit(DeclConst DeclConst);
    public void visit(NamespaceName NamespaceName);
    public void visit(Namespace Namespace);
    public void visit(NoNamespace NoNamespace);
    public void visit(Namespaces Namespaces);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}