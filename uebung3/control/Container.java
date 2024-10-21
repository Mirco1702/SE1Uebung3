package org.hbrs.se1.ws24.uebung3.control;

import java.util.List;

import org.hbrs.se1.ws24.uebung3.exceptions.ContainerException;
import org.hbrs.se1.ws24.uebung3.exceptions.PersistenceException;
import org.hbrs.se1.ws24.uebung3.model.Member;

import java.util.ArrayList;


public class Container {
	
	// stat. Var. hält einzige Instanz
	private static Container instance;
	
	// Datenstruktur zur Speicherung der Memberobjekte
	private List<Member> memberList;
	
	
	// priv. Konstruktor verhindert Instanziierung von externen Klassen
	private Container() {
		memberList = new ArrayList<>();
	}
	
	
	public static Container getInstance() {
		if(instance == null) {
			instance = new Container();
		}
		return instance;
	}
	
	
	public void addMember(Member member) throws ContainerException{
		for(Member m : memberList) {
			if(m.getID() == member.getID()) {
				throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits im Container vorhanden!");
			}
		}
		memberList.add(member);
	}
	
	
	public String deleteMember(int id) {
		for(Member m : memberList) {
			if(m.getID() == id) {
				if(memberList.remove(m) == true) {
					return "Das Member-Objekt mit der ID " + id + " wurde erfolgreich gelöscht";
				}
			}
		}
		return "Es existiert kein Member-Objekt mit der ID " + id + " im Container";
	}
	
	
	public int size() {
		return memberList.size();
	}
	
	
    public List<Member> getCurrentList() {
        return memberList; 
    }
	
	
	public void store() throws PersistenceException {
		 PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
	     persistenceStrategy.save(memberList);
	}
	
	
	public void load() throws PersistenceException {
        PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
        memberList = persistenceStrategy.load();
    }

}
