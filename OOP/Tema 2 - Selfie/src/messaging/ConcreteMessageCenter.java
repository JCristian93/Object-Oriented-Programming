package messaging;

import java.util.ArrayList;

import components.Component;

public class ConcreteMessageCenter extends MessageCenter{

	private ArrayList<ConcreteMessageCenter> lista_centre_vecine = null;
	private ArrayList<Component> lista_componente = null;
	private ArrayList<Integer> iduri_procesare = new ArrayList<Integer>(); 
	
	public ConcreteMessageCenter(String centerName , ArrayList<Component> lista_componente) {
		super(centerName);
		this.lista_componente = lista_componente;
	}
	
	public void set_lista_centre_vecine (ArrayList<ConcreteMessageCenter> lista_centre_vecine) {
		this.lista_centre_vecine = lista_centre_vecine;
	}
	
	@Override
	protected Message publishAlgorithm(Message message) {
		//daca lista_parsare_mesaje e null , atunci nu avem nici un component conectat la acest centru
		//System.out.println("publish algorithm");
	//	System.out.println(this.getCenterName());
		//deoarece bugul persista f mult , afisez tot ce stiu despre fiecare centru ...
	/*	if (this.lista_centre_vecine == null) System.out.println(this.getCenterName() + " nu are vecini");
		else {
			System.out.println("vecinii centrului" + this.getCenterName() + " sunt : ");
			for (ConcreteMessageCenter centru : lista_centre_vecine) {
				System.out.println(centru.getCenterName());
			}
		}
		if (this.lista_componente == null) System.out.println(this.getCenterName() + " nu are componente");
		else {
			System.out.println("componentele centrului "+ this.getCenterName() + " sunt:");
			for (Component componenta : lista_componente) {
				System.out.println(componenta.getTaskType());
			}
		}*/
		
		Message mesaj_publicat;
		if (this.iduri_procesare != null && this.iduri_procesare.contains(message.getId())) {
		//	System.out.println("1!");
			return null;
		}
		this.iduri_procesare.add(message.getId());
		if (this.lista_componente == null) {
			if (this.lista_centre_vecine == null) return null;
			for (ConcreteMessageCenter vecin : this.lista_centre_vecine) {
				mesaj_publicat = vecin.publish(message);
				if (mesaj_publicat != null) {
					return mesaj_publicat;
				}
			}
			//System.out.println("2!");
			return null;
		}
		for (Component componenta : this.lista_componente) {
			if (componenta.getTaskType().compareTo(message.getTaskType()) == 0) {
				return componenta.notify(message);
			}
		}
		if (this.lista_centre_vecine == null) {
			//System.out.println("4!");
			return null;
		}
		for (ConcreteMessageCenter vecin : this.lista_centre_vecine) {
			mesaj_publicat = vecin.publish(message);
			if (mesaj_publicat != null) {
				return mesaj_publicat;
			}
		}
		//System.out.println("3!");
		return null;
	}
}
