package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	
	public CodeGenerator() {
		chrMethod();
		ordMethod();
		lenMethod();
		
	}
	
	Logger log = Logger.getLogger(getClass());
	private boolean errorDetected = false;
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();	
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());	
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 	
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)	
			msg.append (" na liniji ").append(line);	
		log.info(msg.toString());
	}

	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void chrMethod() {
		Obj obj = Tab.find("chr");
		obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(obj.getLevel());
		Code.put(obj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void ordMethod()
	{
		Obj obj2 = Tab.find("ord");
		obj2.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(obj2.getLevel());
		Code.put(obj2.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	

	public void lenMethod() {
		Obj obj3 = Tab.find("len");
		obj3.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(obj3.getLevel());
		Code.put(obj3.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);		
	}
	
	public void visit(PrintStmt ps) {
		if(ps.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else if(ps.getExpr().struct == Tab.charType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}else {Code.loadConst(5);
			Code.put(Code.print);}  //ovo je za boolean, i on je int
	}
	
	public void visit(PrintStmts ps) {
		
		int sirina = ps.getN1().intValue();
		Code.loadConst(sirina);
		
		if(ps.getExpr().struct == Tab.intType) {
			Code.put(Code.print);
		} else if(ps.getExpr().struct == Tab.charType) {
			Code.put(Code.bprint);
			
		}else Code.put(Code.print);  //ovo je za boolean, i on je int
	}
	
	public void visit(ReturnExpr re) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	

	public void visit(ReturnNoExpr re) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReadDesignator rd) {
		if(rd.getDesignator().obj.getType() != Tab.charType) {
			Code.put(Code.read);
		}else Code.put(Code.bread);
		Code.store(rd.getDesignator().obj);
	}
	
	public void visit(MethName mn) {
//		if(mn.getMethName().equals("main")) {
//			mainPc = Code.pc;
//		}
		mn.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(mn.obj.getLevel());
		Code.put(mn.obj.getLocalSymbols().size());
	}
	//dodato
	public void visit(MethodVoidName mn) {
		if(mn.getMethName().equals("main")) {
			mainPc = Code.pc;
		}
		mn.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(mn.obj.getLevel());
		Code.put(mn.obj.getLocalSymbols().size());
	}
	
	public void visit(TypeMethod tm) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(VoidMethod vm) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(Consts c) {
		
	}
	
	public void visit(MinusTermExpr mte) {
		Code.put(Code.neg);
	}
	
	public void visit(AddopTermExpr ate) {
		if(ate.getAddop().getClass() == Plus.class) Code.put(Code.add);
		else Code.put(Code.sub);
	}
	
	public void visit(MulTerm mt) {
		if(mt.getMulop().getClass() == Multiply.class) Code.put(Code.mul);
		else {
			if(mt.getMulop().getClass() == Divide.class) Code.put(Code.div);
			else Code.put(Code.rem);
		}
	}
	
	public void visit(Var v) {
		if(v.getDesignator().obj.getKind()==Obj.Elem) Code.load(v.getDesignator().obj);
		//mozda je bolje dodavati bas u imenu niza i klasama za niz nego ovde
	}
	
	public void visit(NumFactor f) {
		Code.loadConst(f.getN1().intValue());
	//	report_info("" + f.getN1().intValue(), f);
	}
	
	public void visit(BoolFactor f) {
		Code.loadConst(f.getB1().intValue());
	}
	
	public void visit(CharFactor f) {
		Code.loadConst(f.getC1().charValue());
	}
	
	
	public void visit(NewTypeExpr nte) {
		Code.put(Code.newarray);
		if(nte.getType().struct == Tab.charType) Code.put(0);
		else Code.put(1);
	}
	
	public void visit(AssignExpr ae) {
		Obj assign = ae.getDesignator().obj;
		Code.store(assign);
	}
	
	public void visit(Inc inc) {
		Obj i = inc.getDesignator().obj;
		if(i.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		//Code.load(i);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(i);
	}
	
	public void visit(Dec dec) {
		Obj i = dec.getDesignator().obj;
		if(i.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		//ako radis za c ovde proveri da li je field
		//Code.load(i);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(i);
	}
	
	//???????????
	public void visit(DesignatorIdent de) {
		SyntaxNode parent = de.getParent();
		if(AssignExpr.class != parent.getClass() && ActualParDes.class!=parent.getClass() && FuncCall.class!=parent.getClass() ) Code.load(de.obj);
	}
	
	public void visit(DesignatorScope de) {
		SyntaxNode parent = de.getParent();
		if(AssignExpr.class != parent.getClass() && ActualParDes.class!=parent.getClass() && FuncCall.class!=parent.getClass() ) Code.load(de.obj);
	}
	
	
	public void visit(DesignatorName dn) {
		SyntaxNode parent = dn.getParent();
		if(AssignExpr.class != parent.getClass()  && ActualParDes.class!=parent.getClass()&& FuncCall.class!=parent.getClass() ) Code.load(dn.obj);
	}
	
	public void visit(DesignatorNamespace dn) {
		SyntaxNode parent = dn.getParent();
		if(AssignExpr.class != parent.getClass()  && ActualParDes.class!=parent.getClass()&& FuncCall.class!=parent.getClass() ) Code.load(dn.obj);
	}
	
//----------------------------------za B nivo-------------------------------------------------
	
	private List<Integer> ifLista = new ArrayList<Integer>();
	private List<Integer> elseLista = new ArrayList<Integer>();
	private List<Integer> orLista = new ArrayList<Integer>();
	private List<Integer> andLista = new ArrayList<Integer>(); 
	
	//putFalseJump se koristi za uslovne skokove, drugi parametar je 0 ako skacemo unapred
	//putJump je za bezuslovne skokove, ima samo adresu kao param
	
	public void visit(IfStatement is) {
		int i = ifLista.size()-1; //da bih dobila zadnji element, indeksiranje je od 0!
		Code.fixup(ifLista.remove(i));
	}
	
	public void visit(IfElseStatement ies) {
		//Code.putJump(0);
		//elseLista.add(Code.pc-2);  //ovo ne sme zajedno da stoji
		int i = elseLista.size()-1; 
		Code.fixup(elseLista.remove(i));
	}
	
	public void visit(ElseList el) {
		Code.putJump(0);
		elseLista.add(Code.pc-2);
		int i = ifLista.size()-1; 
		Code.fixup(ifLista.remove(i));
	}
	
//	public void visit(SingleCondition cond) {
//		Code.putJump(0);
//		ifLista.add(Code.pc-2);
//		//while(!orLista.isEmpty())Code.fixup(orLista.remove(0));
//		for(int i = orLista.size()-1;i >=0; i--) {
//			Code.fixup(orLista.remove(i));
//		}
//	}
//	
//	public void visit(MultCondition cond) {
//		Code.putJump(0);
//		ifLista.add(Code.pc-2);
//		//while(!orLista.isEmpty())Code.fixup(orLista.remove(0));
//		for(int i = orLista.size()-1;i >=0; i--) {
//			Code.fixup(orLista.remove(i));
//		}
//	}
//	
//	public void visit(SingleCondTerm cond) {
//		Code.putJump(0);
//		orLista.add(Code.pc-2);
//		//while(!andLista.isEmpty() ) Code.fixup(andLista.remove(0));//proveri da li se uzima sa kraja ili pocetka liste
//		for(int i = andLista.size()-1;i >=0; i--) {
//			Code.fixup(andLista.remove(i));
//		}
//	}
//	
//	public void visit(MultCondTerm cond) {
//		Code.putJump(0);
//		orLista.add(Code.pc-2);
//		//while(!andLista.isEmpty() ) Code.fixup(andLista.remove(0));
//		for(int i = andLista.size()-1;i >=0; i--) {
//			Code.fixup(andLista.remove(i));
//		}
//	}
	
	public void visit(ConditionComplete cond) {
		Code.putJump(0);
		ifLista.add(Code.pc-2);
		for(int i = orLista.size()-1;i >=0; i--) {
			Code.fixup(orLista.remove(i));
		}
	}
	
	public void visit(CondTermComplete cond) {
		Code.putJump(0);
		orLista.add(Code.pc-2);
		for(int i = andLista.size()-1;i >=0; i--) {
			Code.fixup(andLista.remove(i));
		}
	}
	
	
	public void visit(NoRelExpr nre) {
		Code.loadConst(1);//1 je true, zato ucitavamo 1
		Code.putFalseJump(Code.eq, 0);
		if(nre.getParent().getClass() == SingleCondTerm.class ||nre.getParent().getClass() == MultCondTerm.class ) andLista.add(Code.pc-2);
		else {
			//uslovni skok se gore ucitava
			forEnd.push(Code.pc - 2);
			
			Code.putJump(0);
			forBody.push(Code.pc - 2);
			
			forDs.push(Code.pc);
			continueBack.push(Code.pc);
			
		}
	}
	
	public void visit(RelExpr re) {
		if(re.getRelop().getClass() == Equal.class) Code.putFalseJump(Code.eq, 0);
		else if(re.getRelop().getClass() == Nequal.class) Code.putFalseJump(Code.ne, 0);
		else if(re.getRelop().getClass() == Greater.class) Code.putFalseJump(Code.gt, 0);
		else if(re.getRelop().getClass() == Gequal.class) Code.putFalseJump(Code.ge, 0);
		else if(re.getRelop().getClass() == Less.class) Code.putFalseJump(Code.lt, 0);
		else if(re.getRelop().getClass() == Lequal.class) Code.putFalseJump(Code.le, 0);
		if(re.getParent().getClass() == SingleCondTerm.class ||re.getParent().getClass() == MultCondTerm.class )andLista.add(Code.pc-2);
		else {
			forEnd.push(Code.pc - 2);
			
			Code.putJump(0);
			forBody.push(Code.pc - 2);
			
			forDs.push(Code.pc);
			continueBack.push(Code.pc);
		}
	}

	
	//-----------------------------pozivi funkcija i act pars------------------------------------------
	
	public void visit(FuncCall fc) {
		if(fc.getDesignator().obj.getKind()==Obj.Meth) {
			int offset = fc.getDesignator().obj.getAdr()-Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
			
		}
	}
	
	public void visit(ActualParDes des) {
		if(des.getDesignator().obj.getKind()==Obj.Meth) {
			Code.put(Code.call);
			Code.put2(des.getDesignator().obj.getAdr()-Code.pc);
		}
	}
	
	//------------------------------------------for-------------------------------------------
	private Stack< Integer> forEnd = new Stack<Integer>();
	private Stack< Integer> forBody = new Stack<Integer>();
	private Stack< Integer> forCond = new Stack<Integer>();
	private Stack< Integer> forDs = new Stack<Integer>();
	private int forCount=0; 
	private Stack<Integer> continueStack = new Stack<Integer>();
	private Stack<Integer> breakStack = new Stack<Integer>();
	private Stack<Integer> continueCountStack = new Stack<Integer>();
	private Stack<Integer> breakCountStack = new Stack<Integer>();
	
	private Stack<Integer> continueBack = new Stack<Integer>();
	
	public void visit(ForStmt fs) {
		if(forDs.size()>0) Code.putJump(forDs.pop());
		
		while(!breakStack.empty() && !breakCountStack.empty() && breakCountStack.peek() == forCount) {
			//if(breakCountStack.peek() == forCount) {
			breakCountStack.pop();
			Code.fixup(breakStack.pop());
			//}
		}
		
//		while(!continueBack.empty() && !continueCountStack.empty() && continueCountStack.peek() == forCount) {
//			//if(continueCountStack.peek() == forCount) {
//				continueCountStack.pop();
//				Code.fixup(continueBack.pop());
//			//}
//		}
		
		if(forEnd.size()>0) Code.fixup(forEnd.pop());
		
		forCount--;
	}
	
	public void visit(ForDs1 for1) {
		forCount++;
		forCond.push(Code.pc);
	}
	
//	public void visit(MultipleDs1 ds) {
//		//forStack.push(Code.pc);
//		forCount++;
//		//Code.putJump(0);
//		forCond.push(Code.pc);
//	}
//	
//	public void visit(SingleDs1 ds) {
//		//forStack.push(Code.pc);
//		forCount++;
//		//Code.putJump(0);
//		forCond.push(Code.pc);
//	}
//	
//	public void visit(NoDsList1 ds) {
//		//forStack.push(Code.pc);
//		forCount++;
//		//Code.putJump(0);
//		forCond.push(Code.pc);
//	}
	
	public void visit(ForDs2 for2) {
		if(forCond.size()>0) Code.putJump(forCond.pop());
		if(forBody.size()>0) Code.fixup(forBody.pop());
	}
	
//	public void visit(MultipleDs2 ds) {
//		if(forCond.size()>0) Code.putJump(forCond.pop());
//		if(forBody.size()>0) Code.fixup(forBody.pop());
//	}
//	
//	public void visit(SingleDs2 ds) {
//		if(forCond.size()>0) Code.putJump(forCond.pop());
//		
//		if(forBody.size()>0) Code.fixup(forBody.pop());
//	}
//	
//	public void visit(NoDsList2 ds) {
//		if(forCond.size()>0) Code.putJump(forCond.pop());
//		if(forBody.size()>0) Code.fixup(forBody.pop());
//	}
	
	
	public void visit(ForEnter fe)  {

	}
	
	public void visit(Continue cnt) {
		if(continueBack.size() >0) {
			int adr = continueBack.peek();
			Code.putJump(adr);
		}
		
		continueCountStack.push(forCount);
		//continueStack.push(Code.pc -2);
	}
	
	public void visit(Break br) {
			Code.putJump(0);
		
		breakCountStack.push(forCount);
		breakStack.push(Code.pc -2);
	}
	
	public void visit(CondFacts cf) {
		
	}
	
	public void visit(NoCondFact cf) {
		//ovde uvek skace na stmt
		Code.putJump(0);
		forBody.push(Code.pc - 2);
		forDs.push(Code.pc);
		continueBack.push(Code.pc);
	}
}