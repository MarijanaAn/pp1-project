package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	public int nVars;
	private boolean errorDetected = false;
	
	private Obj curProg;
	private Obj curNamespace;
	private Struct curType;
	private Obj curMethod;
	
	Logger log = Logger.getLogger(getClass());
	private Struct boolType = Tab.find("bool").getType();
	
	private boolean hasReturn;  
	private boolean hasMain; 
	private boolean inFor;	
	private int address = 0;
	
	//ovo je za B nivo, za proveu parametara
	private List<Struct> actParams = new ArrayList<Struct>();	
	private List<Struct> formParams = new ArrayList<Struct>(); 
	private List<Obj> designators = new ArrayList<Obj>();
	
	
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
	
	
//---------------redefinisanje visit metoda---------------------------
	
	public void visit(ProgramName progName) {
		Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));	
		boolType = Tab.find("bool").getType();  
		curProg = Tab.insert(Obj.Prog, progName.getI1(), Tab.noType);	
		Tab.openScope(); 
	}
	
	public void visit(Program program) { 
		nVars = Tab.currentScope().getnVars(); 
		Tab.chainLocalSymbols(curProg); 
		Tab.closeScope();
		curProg = null; 
		
		if(hasMain == false) {  
			report_error("Program mora imati void main() metodu", program); 
		} 
		
	}
	
	public String TypeToString(int i) {
		String povratna = "";
		switch(i) {
		case 0: povratna =  "none"; break;
		case 1: povratna =  "int"; break;
		case 2: povratna = "char"; break;
		case 3: povratna = "array"; break;
		case 4: povratna = "class"; break;
		case 5: povratna = "bool"; break;
		}
		return povratna;
	}
	
//-----------------------------------------------------namespace------------------------------------------
	
	public void visit(NamespaceName n) {
		Obj ns = Tab.find(n.getName());	
		if(ns == Tab.noObj) {
			curNamespace = Tab.insert(Obj.Type, n.getName(), new Struct(Struct.None));	
			Tab.openScope(); //menjano		
		} else {
			report_error("Namespace sa imenom " + n.getName() + " vec postoji", n);	
			curNamespace = Tab.noObj;
		}
		
	}
	
	public void visit(Namespace n) {
		Tab.chainLocalSymbols(curNamespace); //menjano
		Tab.closeScope(); //menjano
		curNamespace = null;
	}
	
//--------------------------------------------------consts------------------------------------------------
	
	public void visit(NumConst num) {
		Obj name = null;
		if(curNamespace != null) {
			name = Tab.currentScope().findSymbol(num.getI1());
		} else name= Tab.find(num.getI1());
		//find vraca noObj, a findSymbol null pa moramo da proverimo oba
		if(name == null || name == Tab.noObj) {
			if(Tab.intType.assignableTo(curType)) {
				name = Tab.insert(Obj.Con, num.getI1(), curType);
				name.setAdr(num.getN2().intValue());
				name.setLevel(0);
				report_info("Deklarisana konstanta", num);
			}
			else report_error("Tip konstante i vrednost za dodelu nisu kompatibilni", num);
		}else report_error("Deklaracija simbola " + num.getI1() + " vec postoji!", num);
	}
	
	public void visit(CharConst character) {
		Obj name = null;
		if(curNamespace != null) {
			name = Tab.currentScope().findSymbol(character.getI1());
		} else name= Tab.find(character.getI1());
		//find vraca noObj, a findSymbol null pa moramo da proverimo oba
		if(name == null || name == Tab.noObj) {
			if(Tab.charType.assignableTo(curType)) {
				name = Tab.insert(Obj.Con, character.getI1(), curType);
				name.setAdr(character.getC2().charValue());
				name.setLevel(0);
			}
			else report_error("tip konstante i vrednost za dodelu nisu kompatibilni", character);
		}else report_error("Deklaracija simbola " + character.getI1() + " vec postoji!", character);
	}
	
	public void visit(BoolConst b) {
		Obj name = null;
		if(curNamespace != null) {
			name = Tab.currentScope().findSymbol(b.getI1());
		} else name= Tab.find(b.getI1());
		//find vraca noObj, a findSymbol null pa moramo da proverimo oba
		if(name == null || name == Tab.noObj) {
			if(boolType.assignableTo(curType)) {
				name = Tab.insert(Obj.Con, b.getI1(), curType);
				name.setAdr(b.getB2().intValue());
				name.setLevel(0);
			}
			else report_error("tip konstante i vrednost za dodelu nisu kompatibilni", b);
		}else report_error("Deklaracija simbola " + b.getI1() + " vec postoji!", b);
	}
	
//-----------------------------------varDecls---------------------------------------------
	
	public void visit(VarDeclName var) {
		Obj variable;
		if(curMethod != null || curNamespace !=null) {
			variable = Tab.currentScope().findSymbol(var.getVarName());
		} else variable = Tab.find(var.getVarName());
		if(variable == Tab.noObj  || variable== null) {
			variable = Tab.insert(Obj.Var, var.getVarName(), curType);
			variable.setFpPos(-1);
			if(curMethod!=null) variable.setLevel(1);
			else {variable.setLevel(0);
			variable.setAdr(address);
			address++;
			}
		}else report_error("Promenljiva " + var.getVarName() + " je vec definisana!", var);
	
		
	}
	
	public void visit(VarDeclArray var) {
		Obj variable;
		if(curMethod != null || curNamespace !=null) {
			variable = Tab.currentScope().findSymbol(var.getVarName());
		} else variable = Tab.find(var.getVarName());
		if(variable == Tab.noObj  || variable== null) {
			variable = Tab.insert(Obj.Var, var.getVarName(), new Struct(Struct.Array, curType));
			variable.setFpPos(-1);
			if(curMethod!=null) variable.setLevel(1);
			else { variable.setLevel(0);
			variable.setAdr(address);
			address++;
			}
		}else report_error("Promenljiva " + var.getVarName() + " je vec definisana!", var);
	}
	
//-----------------------------------methods-------------------------------------------------
	
	public void visit(MethName mn) {
		curMethod = Tab.insert(Obj.Meth, mn.getMethName(), curType);
		mn.obj = curMethod;
		Tab.openScope();
		
		if(curMethod.getName().equalsIgnoreCase("main")) {
			hasMain = true;
		}
	}
	
	//menjano
	public void visit(MethodVoidName mn) {
		curMethod = Tab.insert(Obj.Meth, mn.getMethName(), Tab.noType);
		mn.obj = curMethod;
		Tab.openScope();
		
		if(curMethod.getName().equalsIgnoreCase("main")) {
			hasMain = true;
		}
	}
	
	public void visit(TypeMethod tm) {
		Tab.chainLocalSymbols(curMethod);
		Tab.closeScope();
		
		if(hasReturn == false && curMethod.getType() !=Tab.noType) {
			report_error("Funkcija nema return iskaz!", tm);
		}
		hasReturn = false;
		curMethod = null;
		
	}
	
	public void visit(VoidMethod vm) {
		//curType = Tab.noType;
		Tab.chainLocalSymbols(curMethod);
		Tab.closeScope();
		
		if(curMethod.getName().equalsIgnoreCase("main")) {
			hasMain = true;
		}
		
		if(hasReturn) {
			report_error("Funkcija je void i ne moze imati povratnu vrednost!",vm);
		}
		
		curMethod = null;
		//report_info("Gotova metod ", vm);
	}
	
//-------------------------------types-------------------------
	
	public void visit(TypeName types) {
		Obj typeNode = Tab.find(types.getTName());
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + types.getTName() + " u tabeli simbola" , types);
			types.struct = Tab.noType;
			curType = Tab.noType;
		}
		else {
			if(Obj.Type != typeNode.getKind()) {
				report_error(" Ime " + types.getTName() + " ne predstavlja tip!", types);
				types.struct = Tab.noType;
			}
			else {
				types.struct = typeNode.getType();
				curType = types.struct;
				//report_info("CurType " + curType.getKind() + " se promenio!", types);
			}
		}
	}
	
	public void visit(NamespaceType nt) {
		Obj namespace = Tab.find(nt.getNamespace());
		if(namespace == Tab.noObj) {
			report_error("Namespace sa imenom " + nt.getNamespace() + " ne postoji u tabeli simbola" , nt);
			namespace = Tab.noObj;
			curType = Tab.noType;
			return;
		}
		//treba da dohvatim locals iz namespacea i da proverim da li postoji taj tip
		boolean hasType = false;
		Obj typeNode = null;
		for(Obj names: namespace.getLocalSymbols()) {
			if(names.getName().equals(nt.getTypeName()) && names.getKind() == Obj.Type) {hasType = true; typeNode = names;}
		}
		if(!hasType) report_info("Tip " + nt.getTypeName() + " ne postoji u prostoru imena " + nt.getNamespace(), nt);
		else {
			nt.struct = typeNode.getType();
			curType = nt.struct;
		}
	
	}
	

	
//------------------------------------------formals-------------------------------------------
	
	public void visit(FormParsVar form) {
		
		if(curMethod != null) {
			Obj var = Tab.currentScope().findSymbol(form.getVarName());
			if (curMethod.getName().equalsIgnoreCase("main") && hasMain) {
				report_error("Void main metoda ne sme imati parametre", form);
			}
				
			if(curType != Tab.noType) { 
				if(var == Tab.noObj || var == null) { 
					//report_info("Dodajemo u metodu", form);
					var = Tab.insert(Obj.Var, form.getVarName(), curType);
					var.setFpPos(curMethod.getLevel());
					curMethod.setLevel(curMethod.getLevel() + 1);	
				}
			}else {
				report_error("Tip promenljive " + form.getVarName() + " ne postoji!", form);
			}
		} else {	
			report_error("Formalni parametar " + form.getVarName() + " se ne nalazi u metodi", form);
		}
		
	}
	
	public void visit(FormParsVarList form) { 
		if(curMethod != null) {
			Obj var = Tab.currentScope().findSymbol(form.getVarName());
			
			if (curMethod.getName().equalsIgnoreCase("main") && hasMain) {
				report_error("Void main metoda ne sme imati parametre", form);
			}
			
			if(curType != Tab.noType) {
				if(var == Tab.noObj || var==null) {
					var = Tab.insert(Obj.Var, form.getVarName(), curType);
					var.setFpPos(curMethod.getLevel());
					curMethod.setLevel(curMethod.getLevel() + 1);
				}
			}else {
				report_error("Tip promenljive " + form.getVarName() + " ne postoji!", form);
			}
		} else {
			report_error("Formalni parametar " + form.getVarName() + " se ne nalazi u metodi", form);
			}
		}
		

	public void visit(FormParsArray form) {
		if(curMethod != null) {
			Obj var = Tab.currentScope().findSymbol(form.getArrName());
			
			if (curMethod.getName().equalsIgnoreCase("main") && hasMain) {
				report_error("Void main metoda ne sme imati parametre", form);
			}
			
			if(curType != Tab.noType) {
				if(var == Tab.noObj || var==null) {
					var = Tab.insert(Obj.Var, form.getArrName(), new Struct(Struct.Array, curType));
					var.setFpPos(curMethod.getLevel());
					curMethod.setLevel(curMethod.getLevel() + 1);
				}
			}else {
				report_error("Tip promenljive " + form.getArrName() + " ne postoji!", form);
			}
		} else {
			report_error("Formalni parametar " + form.getArrName() + " se ne nalazi u metodi", form);
		}
		
	}

	public void visit(FormParsArrayList form) {
		if(curMethod != null) {
			Obj var = Tab.currentScope().findSymbol(form.getArrName());
			if (curMethod.getName().equalsIgnoreCase("main") && hasMain) {
				report_error("Void main metoda ne sme imati parametre", form);	
			}
			
			if(curType != Tab.noType) {  
				if(var == Tab.noObj || var == null) {
					var = Tab.insert(Obj.Var, form.getArrName(), new Struct(Struct.Array, curType));	
					var.setFpPos(curMethod.getLevel());
					curMethod.setLevel(curMethod.getLevel() + 1);
				} 
			}else  {
				report_error("Tip promenljive " + form.getArrName() + " ne postoji!", form);   
			}
		} else {
			report_error("Formalni parametar " + form.getArrName() + " se ne nalazi u metodi", form);
		}
		
	}
//--------------------------------------stmts---------------------------------------
	
	public void visit(ReturnNoExpr rne) {
		if(curMethod.getType() == Tab.noType) {
			report_error("Return se ne moze pojaviti u metodi koja nije void!", rne);
		}
	}
	
	public void visit(ReturnExpr re) {
		if(curMethod.getType() != re.getExpr().struct) {
			report_error("Povratna vrednost nije odgovarajuceg tipa!", re);
		}
		hasReturn = true;
	}
		
	//--------------------------------------expr----------------------------
	
	public void visit(TermExpr te) {
		te.struct = te.getTerm().struct;
	}
	
	public void visit(MinusTermExpr mte) {
		Struct term = mte.getTerm().struct;
		if(term == Tab.intType) {
			mte.struct = term;
		}
		else {
			report_error("Nekompatibilni tipovi! Tip mora biti int!", null);
			mte.struct = Tab.noType;
		}
	}
	
	public void visit(AddopTermExpr ate) {
		Struct expr = ate.getExpr().struct;
		Struct term = ate.getTerm().struct;
		if(term.equals(expr)) {
		if(term == Tab.intType && expr == Tab.intType) {
			ate.struct = term;
		}else {
			report_error("Nekompatibilni tipovi u +/-! Tipovi moraju biti int!", null);
			ate.struct = Tab.noType;
		}
	}
		else {
			report_error("Nekompatibilni tipovi u +/-! Tipovi moraju biti kompatibilni!", null);
			ate.struct = Tab.noType;
		}
	}
	
	public void visit(FactorTerm ft) {
		ft.struct = ft.getFactor().struct;
	}
	
	public void visit(MulTerm mt) {
		Struct factor = mt.getFactor().struct;
		Struct term = mt.getTerm().struct;
		if(term == Tab.intType && factor == Tab.intType) {
			mt.struct = factor;
		}else {
			report_error("Nekompatibilni tipovi u *, /, %! Tipovi moraju biti int!", null);
			mt.struct = Tab.noType;
		}
	}
	
	public void visit(NumFactor num) {
		num.struct = Tab.intType;
		//report_info("U num factoru smo", num);
	}
	
	public void visit(CharFactor character) {
		character.struct = Tab.charType;
	}
	
	public void visit(BoolFactor b) {
		b.struct = boolType;
	}
	
	public void visit(Var designator) {
		designator.struct = designator.getDesignator().obj.getType();
	}
	
	public void visit(Expression expr) {
		expr.struct = expr.getExpr().struct;
	}
	
	public void visit(NewTypeExpr nte) {
		if(nte.getExpr().struct != Tab.intType) {
			report_error("Niz se ne moze napraviti! Velicina niza morabiti tipa int!", null);
			nte.struct = Tab.noType;
		}else {
			nte.struct = new Struct(Struct.Array, curType);
		}
	}
	
//--------------------------------designator statement----------------------------------------
	
	public void visit(AssignExpr ae) {
		if(ae.getDesignator().obj.getKind() == Obj.Var || 
		ae.getDesignator().obj.getKind() == Obj.Elem || ae.getDesignator().obj.getKind() == Obj.Fld) {
			//provera da li je designator element niza, polje ili promenljiva
			Struct desgn = ae.getDesignator().obj.getType();
			Struct expr = ae.getExpr().struct;
			if(!expr.assignableTo(desgn)) {
				//greska ako nije dodeljivo
				report_error("Desna strana izraza nije dodeljiva levoj!", ae);
			}else {
				//report_info("U delu smo za dodelu vrednosti pormenljivoj", ae);
			}
		}else {
			report_error("Promenljiva " + ae.getDesignator().obj.getName() + " mora biti polje, promenljiva ili element niza!", null);
		}
	}
	
	public void visit(Inc inc) {
		if(inc.getDesignator().obj.getKind() == Obj.Var || 
		inc.getDesignator().obj.getKind() == Obj.Elem || inc.getDesignator().obj.getKind() == Obj.Fld) {
			//moglo je i bez fielda, to je za C nivo
			if(inc.getDesignator().obj.getType() != Tab.intType) {
				report_error("Promenljiva " + inc.getDesignator().obj.getName() + " mora biti int tipa!", null);
			}
		}else {
			report_error("Promenljiva " + inc.getDesignator().obj.getName() + " mora biti polje objekta unutrasnje klase, promenljiva ili element niza!", null);
		}
	}
	
	public void visit(Dec dec) {
		if(dec.getDesignator().obj.getKind() == Obj.Var || 
		dec.getDesignator().obj.getKind() == Obj.Elem || dec.getDesignator().obj.getKind() == Obj.Fld) {
			if(dec.getDesignator().obj.getType() != Tab.intType) {
				report_error("Promenljiva " + dec.getDesignator().obj.getName() + " mora biti int tipa!", null);
			}
		}else {
			report_error("Promenljiva " + dec.getDesignator().obj.getName() + " mora biti polje objekta unutrasnje klase, promenljiva ili element niza!", null);
		}
	}
	
	//----------------------------------------statement------------------------------------
	
	public void visit(ReadDesignator rd) {
		if(rd.getDesignator().obj.getKind() == Obj.Var || rd.getDesignator().obj.getKind() == Obj.Elem 
				|| rd.getDesignator().obj.getKind() == Obj.Fld) {
			if(rd.getDesignator().obj.getType() != Tab.intType && rd.getDesignator().obj.getType() != Tab.charType 
				&&	rd.getDesignator().obj.getType() != boolType) {
				//ako nije nijedno, onda je greska
				report_error("Promenljiva " + rd.getDesignator().obj.getName() + " mora biti int, char ili bool!", null);
			}
			
		} else {
			report_error("Promenljiva " + rd.getDesignator().obj.getName() + " mora biti polje unutar objekta, promenljiva ili element niza!", null);
		}
	}
	
	public void visit(PrintStmt ps) {
		Struct print = ps.getExpr().struct;
		if(print != Tab.intType && print != Tab.charType && print != boolType ) {
			report_error("Promenljiva mora biti int, char ili bool!", null);
		}
	}
	
	public void visit(PrintStmts ps) {
		Struct print = ps.getExpr().struct;
		if(print != Tab.intType && print != Tab.charType && print != boolType) {
			report_error("Promenljiva mora biti int, char ili bool!", null);
		}
	}
	
	public void visit(DesignatorName dn) {
		Obj desgn = Tab.find(dn.getI1());
		dn.obj = desgn;
		if(desgn == Tab.noObj) {
			report_error("Ime " + dn.getI1() + " ne postoji u tabeli simbola(nije deklarisano)", dn);
		} else if(desgn.getType().getKind() != Struct.Array) {
			report_error("Promenljiva " + dn.getI1() + " mora biti niz", dn);
			desgn = Tab.noObj;
			//dn.obj = desgn;
			
		}else {
			
		}
		//morace da se prepravi zbog namespacea
	}

	
	public void visit(DesignatorIdent d) {
		Obj desgn = Tab.find(d.getName());
		if(desgn == Tab.noObj) {
			report_error("Ime " + d.getName() + " ne postoji u tabeli simbola(nije deklarisano)", d);
		} else {
			d.obj = desgn;
			if(desgn.getKind() == Obj.Var) {
				report_info("Nadjena promenljiva " + desgn.getName() +": " + TypeToString(desgn.getType().getKind())+ ", " + 
			desgn.getAdr() + ", " + desgn.getLevel() , d);
			}
			else if(desgn.getKind() == Obj.Con) report_info("Nadjena konstanta " + desgn.getName() +": " + TypeToString(desgn.getType().getKind()) + ", " + 
					desgn.getAdr() + ", " + desgn.getLevel() , d);
			else if(desgn.getKind() == Obj.Meth) report_info("Nadjen poziv metode " + desgn.getName() +": " + TypeToString(desgn.getType().getKind()) + ", " + 
					desgn.getAdr() + ", " + desgn.getLevel() , d);
		}
	}
	
	public void visit(DesignatorArray d) {
		//Obj arr = Tab.find(d.getDesignatorName().getI1());
		d.obj = d.getDesignatorName().obj;
		Obj arr = d.obj;
		Struct expr = d.getExpr().struct;
		if(expr !=Tab.intType) {
				report_error("Duzina niza mora biti tipa int!", d);
				arr = Tab.noObj;
				expr = Tab.noType;
			} else {
				//ako je sve korektno ovde smo
				d.obj = new Obj(Obj.Elem, arr.getName() + "[.]", arr.getType().getElemType());
				d.obj.setAdr(d.getDesignatorName().obj.getAdr());
			}
		//}
	}
	
	public void visit(DesignatorScope ds) {
		Obj namespace = Tab.find(ds.getNamespace());
		if(namespace == Tab.noObj) report_error("Namespace sa imenom " + ds.getNamespace() + " ne postoji", ds);
		else {
			boolean hasName = false;
			Obj name = null;
			for(Obj names: namespace.getLocalSymbols()) {
				if(names.getName().equals(ds.getI1())) {hasName = true; name = names;}
			}
			if(!hasName) report_error("Promenljiva " + ds.getI1() + " ne postoji u prostoru imena " + ds.getNamespace(), ds);
			else {
			ds.obj = name;
			if(name.getKind() == Obj.Var) 
				report_info("Nadjena promenljiva " + name.getName() +": " + TypeToString(name.getType().getKind()) + ", " + 
						name.getAdr() + ", " + name.getLevel() , ds);
			else if(name.getKind() == Obj.Con) report_info("Nadjena konstanta " + name.getName() +": " + TypeToString(name.getType().getKind()) + ", " + 
					name.getAdr() + ", " + name.getLevel() , ds);
			else if(name.getKind() == Obj.Meth) report_info("Nadjen poziv metode " + name.getName() +": " + TypeToString(name.getType().getKind()) + ", " + 
					name.getAdr() + ", " + name.getLevel() , ds);
			}
		}
	}
	
	public void visit(DesignatorNamespace dn) {  
		Obj namespace = Tab.find(dn.getNamespace());
		if(namespace == Tab.noObj) report_error("Namespace sa imenom " + dn.getNamespace() + " ne postoji", dn);
		else {
			boolean hasName = false;
			Obj name = null;
			for(Obj names: namespace.getLocalSymbols()) {
				if(names.getName().equals(dn.getName())) {hasName = true; name = names;
				report_info("" + names.getType().getKind(), dn);}
			}
			report_info(hasName + "", dn);
			if(!hasName) {report_error("Promenljiva " + dn.getName() + " ne postoji u prostoru imena " + dn.getNamespace(), dn);}
//			else if(name.getType().getKind() != Struct.Array) {
//				report_error("Promenljiva " + dn.getName() + " mora biti niz", dn);
//				name = Tab.noObj;
//			}
			else dn.obj = name;
		}
	}
	
	public void visit(DesignatorArrayScope das) {  
		das.obj = das.getDesignatorNamespace().obj;
		Obj arr = das.obj;
		Struct expr = das.getExpr().struct;
	if(expr !=Tab.intType) {
				report_error("Duzina niza mora biti tipa int!", das);
				arr = Tab.noObj;
				expr = Tab.noType;
			} else {
				//ako je sve korektno ovde smo
				das.obj = new Obj(Obj.Elem, arr.getName() + "[.]", arr.getType().getElemType());
				das.obj.setAdr(das.getDesignatorNamespace().obj.getAdr());
				//report_info("Na kraju smo designator niza", d);
			}
	}
	
	
	public boolean passed() {
		return !errorDetected;
	}
	
//----------------------------------------Dodatak za B nivo-------------------------------------------------
	
	
	//---------------------------------------------conds---------------------------------
		
		public void visit(SingleCondition cond) { 
			if(cond.getCondTermComplete().struct == Tab.noType) {
				report_error("Doslo je do greske u uslovu", cond);
			} else { 
				if(cond.getCondTermComplete().struct.assignableTo(boolType)) cond.struct = cond.getCondTermComplete().struct;
				else { 
					cond.struct = Tab.noType;
					report_error("Uslov mora biti bool tipa", cond);
					//mozda ne treba i ovde provera jer sam proverila u ifu i else, ako smeta obrisi
				}
				
			}
		}
		
		public void visit(MultCondition cond) {  
			if(cond.getCondition().struct == Tab.noType || cond.getCondTermComplete().struct == Tab.noType ) {
				cond.struct = Tab.noType;
				report_error("Doslo je do greske u uslovu", cond);
			}else {
				if(cond.getCondTermComplete().struct.assignableTo(boolType)) cond.struct = cond.getCondTermComplete().struct;
				else { 
					cond.struct = Tab.noType;
					report_error("Uslov mora biti bool tipa", cond);
					//mozda ne treba i ovde provera jer sam proverila u ifu i else, ako smeta obrisi
				}
			}
		}
		
		public void visit(ConditionComplete cond) {
			cond.struct = cond.getCondition().struct;
		}
		
		public void visit(SingleCondTerm cond) {
			if(cond.getCondFact().struct == Tab.noType) {
				report_error("Doslo je do greske u uslovu", cond);
			} else { 
				cond.struct = cond.getCondFact().struct;
			}
		}
		
		public void visit(MultCondTerm cond) {
			if(cond.getCondTerm().struct == Tab.noType || cond.getCondFact().struct == Tab.noType ) {
				cond.struct = Tab.noType;
				report_error("Doslo je do greske u uslovu", cond);
			}else {  
				cond.struct = cond.getCondFact().struct;
			}
		}
		
		public void visit(CondTermComplete cond) {
			cond.struct = cond.getCondTerm().struct;
		}
		
		public void visit(RelExpr re) { 
			//provera da li su kompatibilni tipovi
			if(re.getExpr1().struct.compatibleWith(re.getExpr().struct)) {
			//ako su kompatibilni provera za nizove i klase
				if(re.getExpr().struct.getKind() == Struct.Class || re.getExpr1().struct.getKind() == Struct.Class ||
						re.getExpr().struct.getKind() == Struct.Array || re.getExpr().struct.getKind() == Struct.Array) {
					if(re.getRelop().getClass() != Equal.class && re.getRelop().getClass() != Nequal.class) {
						//ako je klasa ili niz i ako operatori nisu == ili != greska
						re.struct = Tab.noType;
						report_error("Uz promenljive tipa niza ili klase mogu se koristiti samo == i != ", re);
					}
				}else {
					re.struct = boolType;
				}
			}
		}
		
		public void visit(NoRelExpr nre) {
			nre.struct = nre.getExpr().struct;
			
			if(nre.getExpr().struct != boolType) {
				report_error("Uslovni faktor mora biti bool tipa!", nre);
				nre.struct = Tab.noType;
			}
		}
//prijavljivace mi duple greske		
//		public void visit(IfStatement i) {
//			if(i.getCondition().struct != boolType) {
//				report_error("Uslov u if-u " + i.getCondition().struct.getKind() + " mora biti bool tipa", i);
//			}
//		}
		
//		public void visit(IfElseStatement i) {
//			if(i.getCondition().struct != boolType) {
//				report_error("Uslov u if/else " + i.getCondition().struct.getKind() + " mora biti bool tipa", i);
//			}
//		}
		
//		public void visit(ElseList el) {
//			//ipak ne treba nista ovde
//			
//		}
		
	//---------------------------------------------vezano za for----------------------------------

	private int	forLoop=0;
	
	public void visit(Break br) { 
		if(forLoop<1) report_error("Break naredba se sme naci samo unutar for petlje!", br);
	}
	
	public void visit(Continue cnt) {  
		if(forLoop<1) report_error("Continue naredba se sme naci samo unutar for petlje!", cnt);
	}
	
	public void visit(ForStmt f ) { 
		inFor=true;
		forLoop -=1;
		//ne moze ovo da se proverava jer ako imamo for u foru izaci ce iz oba
	}
	
	public void visit(ForEnter f ) {
		forLoop +=1;
	}
	
	//------------------------------------------sad sve sa actual pars---------------------------------
	
	public void visit(ActualParDes a) {
		Obj des = a.getDesignator().obj;
		if(des.getKind() != Obj.Meth) { //za C se ovo valjda menja 
			des = Tab.noObj;
			report_error("Designator "+ des.getName()+ " mora da bude metoda" , a);
			
		}else {
			
			for(Obj formals: a.getDesignator().obj.getLocalSymbols()) {
				//dodajemo parametre u listu ako su ispravni
				if(formals.getLevel() == 1 && formals.getKind() ==Obj.Var && formals.getFpPos()>-1)  {
					formParams.add(formals.getType());
					//report_info("dodato u listu" + formals.getType(), a);
				}
			}
			//sad proveravamo da li se poklapa sa actpars
			if(formParams.size() ==actParams.size()) {
				//ako su iste velicine sad proveravamo za svaki parametar
				int i =0;
				for(i=0; i < actParams.size(); i++) {//mozda treba da se koristi assignableTo
					if(actParams.get(i).assignableTo(formParams.get(i))) {
						//onda je okej
					}else {
						report_error("Tipovi stvarnih i formalnih parametara nisu kompatibilni", a);
					}
				}
				
				//ako je sve ispravno mozda ovde mogu da praznim obe liste
				formParams.clear();
				actParams.clear();
			}else {
				formParams.clear();
				report_error("Lista stvarnih parametara nije iste velicine kao lista formalnih parametara!", a);
			}
		}
	}
	
	public void visit(NewTypeActual nta) { 
		//ovo je za C nivo 
		 
	}
	
	public void visit(FuncCall fc) {	
		Obj des = fc.getDesignator().obj;  	
		if(des.getKind() != Obj.Meth) { //za C se ovo valjda menja 
			des = Tab.noObj;	
			report_error("Designator "+ des.getName()+ " mora da bude metoda" , fc);
		}else {
			fc.struct = fc.getDesignator().obj.getType();
			for(Obj formals: fc.getDesignator().obj.getLocalSymbols()) {
				//dodajemo parametre u listu ako su ispravni
				if(formals.getLevel() == 1 && formals.getKind() ==Obj.Var && formals.getFpPos()>=0)  { //getFpPos sam dodala da mi ne bi stavljao i lokalne promenljive u listu
					formParams.add(formals.getType());
					//report_info("dodato u listu" + formals.getType(), fc);
				}
			}
			//sad proveravamo da li se poklapa sa actpars
			if(formParams.size() == actParams.size()) {
				//ako su iste velicine sad proveravamo za svaki parametar
				int i =0;
				for(i=0; i < actParams.size(); i++) {//mozda treba da se koristi assignableTo
					if(actParams.get(i).assignableTo(formParams.get(i))) {
						//onda je okej
					}else {
						report_error("Tipovi stvarnih i formalnih parametara nisu kompatibilni", fc);
					}
				}
				
				//ako je sve ispravno mozda ovde mogu da praznim obe liste
				formParams.clear();
				actParams.clear();
			}else {
				formParams.clear();
				report_error("Lista stvarnih parametara nije iste velicine kao lista formalnih parametara!", fc);
			}
		}
		
	}
	
	public void visit(ActualParams ap) { 
		//treba expr nekako da ubacim da se broji i proverava sa formalnim par
		actParams.add(ap.getExpr().struct);
		//report_info("prom " + ap.getExpr(), ap);
	}
	
	public void visit(ActualParam ap) { 
		//ovde isto to, samo sto ovde terminira, ali isto se pise
		actParams.add(ap.getExpr().struct);
		//report_info("prom " + ap.getExpr(), ap);
	}
	
	public void visit(Actuals a) {
		//ovo je za listu actpars
		
	}
	
	
	
	public void visit(Assignment a) {
		Obj nizDodela = a.getDesignator1().obj;  //ovo je niz cije vrednosti treba da dodeljujemo levoj strani
		Obj niz = a.getDesignator().obj;
		if(nizDodela.getType().getKind() !=Struct.Array) {
			report_error("Promenljiva " + nizDodela.getName() + " mora biti niz", a);
			return;
		}
		if(niz.getType().getKind() !=Struct.Array) {
			report_error("Promenljiva " + niz.getName() + " mora biti niz", a);
			return;
		} else {
			if(niz.getType().assignableTo(nizDodela.getType())) {
				//onda je okej, dalja provera
				Collection<Obj> objects = nizDodela.getLocalSymbols();
				List<Obj> elements = new ArrayList<Obj>();
				for(Obj temp: objects) {
					elements.add(temp);
				}
				
				if(designators.size() > elements.size()) {
					report_error("Zabranjena dodela! Broj promenljivih je veci od duzine niza!", a);
				}
				
				for(int i = 0; i <designators.size(); i++) {
					if(!designators.get(i).getType().assignableTo(elements.get(i).getType()) && designators.get(i) != Tab.noObj) {	
						//ako nije assignable i nije noObj, jer sam stavljala da je prazan u onoj klasi gne ne postoji
						report_error("Promenljiva " + designators.get(i).getName() + " nije kompatibilna pri dodeli sa nizom", a);
						break;
					} 
				}
			
			}
			else {
				report_error("Promenljive " + nizDodela.getName() + " i " + niz.getName() + " nisu kompatibilne pri dodeli" , a);
			}
		}
		
	}
	
	public void visit(HasDesignator d) {
		Obj desgn = d.getDesignator().obj;
		if(desgn.getKind() == Obj.Elem || desgn.getKind() == Obj.Var) {
			designators.add(desgn);
		}
	}
	
	public void visit(NoDesignator d) {
		designators.add(Tab.noObj);
	}
}
