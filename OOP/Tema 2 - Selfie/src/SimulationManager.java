import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import types.FlashType;
import types.TaskType;
import components.BlackWhite;
import components.Blur;
import components.Component;
import components.Flash;
import components.ImageLoader;
import components.ImageSaver;
import components.NormalPhoto;
import components.RawPhoto;
import components.Sepia;
import components.Zoom;
import messaging.ConcreteMessageCenter;
import messaging.MessageCenter;
import messaging.MessageFlash;
import messaging.MessageImage;
import messaging.MessageLoad;
import messaging.MessageSave;
import messaging.MessageSuccess;
import messaging.MessageZoom;


public class SimulationManager {
	private MessageCenter messageCenter;
	
	public SimulationManager(String networkConfigFile) {
		this.messageCenter = buildNetwork(networkConfigFile);
	}
		
	
	/**
	 * Builds the network of message centers.
	 * @param networkConfigFile configuration file
	 * @return the first message center from the config file
	 */
	private MessageCenter buildNetwork(String networkConfigFile) {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			ArrayList<ConcreteMessageCenter> centre = new ArrayList<ConcreteMessageCenter>();
			br = new BufferedReader(new FileReader(networkConfigFile));
			sCurrentLine = br.readLine();
			int numar_centre = Integer.parseInt(sCurrentLine);
			//creez centrele si le adaug componentele
			for (int i = 0 ; i < numar_centre ; i++) {
				sCurrentLine = br.readLine();
				String [] centru = sCurrentLine.split(" ");
				if (centru.length == 1) {
					centre.add(new ConcreteMessageCenter(centru[0], null));
				}
				else {
					ArrayList<Component> componente = new ArrayList<Component>();
					for (int j = 1 ; j < centru.length ; j++) {
						if (centru[j].equals("ImageLoader")){
							componente.add(new ImageLoader());
						}
						else if (centru[j].equals("ImageSaver")){
							componente.add(new ImageSaver());
						}
						else if (centru[j].equals("BlackWhite")){
							componente.add(new BlackWhite());
						}
						else if (centru[j].equals("Blur")){
							componente.add(new Blur());
						}
						else if (centru[j].equals("Flash")){
							componente.add(new Flash());
						}
						else if (centru[j].equals("NormalPhoto")){
							componente.add(new NormalPhoto());
						}
						else if (centru[j].equals("RawPhoto")){
							componente.add(new RawPhoto());
						}
						else if (centru[j].equals("Sepia")){
							componente.add(new Sepia());
						}
						else if (centru[j].equals("Zoom")){
							componente.add(new Zoom());
						}
						else {
							//am primit o componenta necunoscuta si afisez o eroare
						//	System.out.println("Am primit o componenta necunoscuta , reteaua poate fi compromisa");
						}
					}
					centre.add(new ConcreteMessageCenter(centru[0], componente));
				}
			}
			//creez legaturile intre centre
			//am presupus ca nu exista centre izolatie , fiecare are cel putin un vecin 
			//daca exista centre izolate , atunci va crapa la sigur
			for (int i = 0 ; i < numar_centre ; i++) {
				sCurrentLine = br.readLine();
				String[] vecini = sCurrentLine.split(" ");
				ArrayList<ConcreteMessageCenter> centre_vecine = new ArrayList<ConcreteMessageCenter>();
				for (int j = 1 ; j < vecini.length ; j++) {
					for (int k = 0 ; k < numar_centre ; k++) {
						if (centre.get(k).getCenterName().equals(vecini[j])) {
							centre_vecine.add(centre.get(k));
							break;		//nu e musai break-ul asta , poate fi sters. E doar de eficienta
						}
					}
				}
				centre.get(i).set_lista_centre_vecine(centre_vecine);
			}
		//	System.out.println("message centre trebuia sa fie "+ centre.get(0));
			this.messageCenter = centre.get(0);		//mereu primul centru va fi legat la Simulation Manager
													//sa nu stearga Garbage Collector tot ce am facut pana acum
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		//System.out.println(messageCenter);
		return messageCenter;
	}
	
	
	/**
	 * Reads the commands from stdin and uses the messageCenter to solve all the tasks
	 */
	public void start() {
		
		/* 
		 * Example of usage when the MessageCenter will be implemented *
		 
		MessageLoad load = new MessageLoad(TaskType.IMAGE_LOAD, "image_input.jpg");
		MessageImage image = (MessageImage)this.messageCenter.publish(load);
		
		image.generateId(); //pentru ca utilizam acelasi mesaj image trebuie sa-i generam un nou id
		
		MessageSave save = new MessageSave(TaskType.IMAGE_SAVE, 
					image.getPixels(), image.getWidth(), image.getHeight(), 
					destFile);
		MessageSuccess success = (MessageSuccess)this.messageCenter.publish(save);
		
		
		*/ // 0         1       2    3     4    5   6   7   8   9     10   11    12     13      14       15
		//image_input.jpg selfie.jpg pre(flash=auto;zoom=100,100,300,400) photo(type=normal) post(black_white;blur)
		//!comenzile au lungimi diferite , nu sunt toate cum am pus eu aici 
		try{
			//citesc comanda din stdin
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			input = br.readLine();
			while(input != null && !input.equals("exit")){
			//	System.out.println(input);
				char [] input_char_array = input.toCharArray();
				int nr_cuvinte = 0;
				for (int i = 0 ;i < input.length() ; i++) {
					char c = input_char_array[i];
					if ((c == '(') || (c == ')') || (c == '=') || (c == ';') || (c == ',')){
						input_char_array[i] = ' ';
						nr_cuvinte++;
					}
				}
				nr_cuvinte += 2;
				input = new String (input_char_array);
			//	System.out.println(input);
				//String [] elemente_comanda = input.split(" ");
				StringTokenizer st = new StringTokenizer(input , " ");
				 
				String [] elemente_comanda = new String [nr_cuvinte];
				int contor_elem_comanda = 0;
				while (st.hasMoreElements()) {
					//System.out.println(st.nextElement());
					elemente_comanda[contor_elem_comanda] = new String((String)st.nextElement());
					contor_elem_comanda++;
				}
			/*	for (String elem : elemente_comanda) {
					System.out.println(elem);
				}*/
				
				MessageLoad load = new MessageLoad(TaskType.IMAGE_LOAD, elemente_comanda[0]);
			//	System.out.println("gogu" + messageCenter);
				MessageImage image = (MessageImage)this.messageCenter.publish(load);
			//	System.out.println("? dim image " + image.getWidth() + " " + image.getHeight());
				//aici mergea bine poza
				image.generateId();
				//EXECUTAM RESTUL COMENZII
				//mai intai etapa de pre
				MessageFlash flash;		//flash apare MEREU
			//	System.out.println(elemente_comanda.length);
				if (elemente_comanda[4].equals("auto")) {
					flash = new MessageFlash(TaskType.FLASH, FlashType.AUTO
						, image.getPixels(), image.getWidth(), image.getHeight());
				}
				else if (elemente_comanda[4].equals("on")) {
					flash = new MessageFlash(TaskType.FLASH, FlashType.ON
						, image.getPixels(), image.getWidth(), image.getHeight());
				}
				else {
					flash = new MessageFlash(TaskType.FLASH, FlashType.OFF
							, image.getPixels(), image.getWidth(), image.getHeight());
				}
				image = (MessageImage)this.messageCenter.publish(flash);
				//zoom e OPTIONAL
				image.generateId();
				if (elemente_comanda[5].equals("zoom")) {
			//		System.out.println("! dim image " + image.getWidth() + " " + image.getHeight());
					MessageZoom zoom = new MessageZoom(TaskType.ZOOM, image.getPixels(), image.getWidth(), image.getHeight(),
							Integer.parseInt(elemente_comanda[6]), Integer.parseInt(elemente_comanda[7]),
							Integer.parseInt(elemente_comanda[8]), Integer.parseInt(elemente_comanda[9]));
					image = (MessageImage)this.messageCenter.publish(zoom);
				}
				//acum urmeazaetapa de photo care este influentata ca si etapa de post de catre pre
				int contor = 0;
				MessageImage imagine;		//pare complet useless dar eh ... 
			//	System.out.println("imagine data la raw/normal" + image.getHeight() +" "+ image.getWidth());
				image.generateId();
				
				while (!elemente_comanda[contor].equals("photo")){
					contor++;
				}
				if (elemente_comanda[contor + 2].equals("normal")) {
					imagine = new MessageImage(TaskType.RAW_PHOTO, image.getPixels(), image.getWidth(), image.getHeight());
					imagine.generateId();
					image = (MessageImage) this.messageCenter.publish(imagine);
					imagine = new MessageImage(TaskType.NORMAL_PHOTO, image.getPixels(), image.getWidth(), image.getHeight());
					imagine.generateId();
					image = (MessageImage) this.messageCenter.publish(imagine);
				}
				else {
					imagine = new MessageImage(TaskType.RAW_PHOTO, image.getPixels(), image.getWidth(), image.getHeight());
					imagine.generateId();
					image = (MessageImage) this.messageCenter.publish(imagine);
				}
			//	System.out.println(imagine + "!");
				
				
				/*MessageSave savior = new MessageSave(TaskType.IMAGE_SAVE, 
						image.getPixels(), image.getWidth(), image.getHeight(), 
						"gogudebug.jpg");
				MessageSuccess successoor = (MessageSuccess)this.messageCenter.publish(savior);*/
			//	System.out.println("poza inainte post: " + image);
				//etapa de post
				contor = 0;
				while (!elemente_comanda[contor].equals("post")){
					contor++;
				}
				contor++;
				image.generateId();
				if (elemente_comanda[contor] != null) {
				
					while (contor < elemente_comanda.length) {
				//		System.out.println("nu gasesc partituri " + image);
						if (elemente_comanda[contor].equals("black_white")){
							image.generateId();
							imagine.generateId();
							imagine = new MessageImage(TaskType.BLACK_WHITE, image.getPixels(), image.getWidth(), image.getHeight());
							image = (MessageImage) this.messageCenter.publish(imagine);
				//			System.out.println("am gasit partituri black_white" + image);
							image.generateId();
						}
						else if (elemente_comanda[contor].equals("blur")) {
							image.generateId();
							imagine.generateId();
							imagine = new MessageImage(TaskType.BLUR, image.getPixels(), image.getWidth(), image.getHeight());
							image = (MessageImage) this.messageCenter.publish(imagine);
				//			System.out.println("am gasit partituri blur" + image);
							image.generateId();
						}
						else if (elemente_comanda[contor].equals("sepia")) {
							image.generateId();
							imagine.generateId();
							image = new MessageImage(TaskType.SEPIA , image.getPixels() , image.getWidth() , image.getHeight());
							image = (MessageImage) this.messageCenter.publish(image);
				//			System.out.println("am gasit partituri sepia" + image);
							image.generateId();
						}
						else {
				//			System.out.println("anomalie la linia 211");
						}
						contor++;
					}
				}
				//SALVAM IMAGINEA IN FORMA EI FINALA
			//	System.out.println("blala" + image);
			//	System.out.println("inainte de a salva : " + image.getHeight() + " " + image.getWidth());
				image.generateId();
				MessageSave save = new MessageSave(TaskType.IMAGE_SAVE, 
						image.getPixels(), image.getWidth(), image.getHeight(), 
						elemente_comanda[1]);
				//momentan nu prea stiu ce sa fac cu success
				MessageSuccess success = (MessageSuccess)this.messageCenter.publish(save);
				success.generateId();
				//success = (MessageSuccess)this.messageCenter.publish(success);
				input = br.readLine();
			}
		}catch(IOException io){
			io.printStackTrace();
		}		
	}
	/**
	 * Main method
	 * @param args program arguments
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
		//	System.out.println("Usage: java SimulationManager <network_config_file>");
			return;
		}
		//constructorul creeaza reteaua de centre
		SimulationManager simulationManager = new SimulationManager(args[0]);
		simulationManager.start();
	}

}
	