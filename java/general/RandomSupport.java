package general;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.random.RandomDataGenerator;


public class RandomSupport {

	private RandomDataGenerator random;
	
	private Set<Integer> mainBag;
	
	private List<Set<Integer>> bagServer;
	

	public RandomSupport() {
		random = new RandomDataGenerator();
		bagServer = new LinkedList<Set<Integer>>();
	}

	
	public  RandomDataGenerator getRandomGenerator () {
		return random;
	}
	
	public void setSeed (){
		random.reSeed();
	}
	
	public int newBagInServer (int size){
		
		Set<Integer> aux = new HashSet<Integer>();
		
		for (int i = 0; i<size;i++){
			aux.add(new Integer(i));			
		}
		
		bagServer.add(aux);
		
		return bagServer.indexOf(aux);		
	
	}
	
	
	public void emptyAndFillMainBag (int size){
		
		mainBag = new HashSet<Integer>();
		
		for (int i = 0; i<size;i++){
			mainBag.add(new Integer(i));			
		}
		
	}
	
	public void emptyMainBag (){
		mainBag = new HashSet<Integer>();
	}
	
	
	public void putMarblesIntoMainBag (Collection<Integer> marbles){
		mainBag.addAll(marbles);
	}
	
	public void putWholeBagAndRemoveMarblesIntoMainBag (Set<Integer> bag, Collection<Integer> marbles){
		
		mainBag.addAll(bag);
		mainBag.removeAll(marbles);
		
	}
	
	public void putWholeBagIntoMainBag (Set<Integer> bag){
		
		mainBag.addAll(bag);
		
	}
	
	public void putMarblesIntoServerSlot (Collection<Integer> marbles, int slot){
		(bagServer.get(slot)).addAll(marbles);
	}
	
	public int sizeOfMainBag(){
		return mainBag.size();
	}
	
	public int sizeOfServerSlot (int slot){
		return (bagServer.get(slot)).size();
	}
	
	public String toString (){
		
		String r = "Bag of "+mainBag.size()+" marbles:\n";
				
		for (Integer marble:mainBag){
			r+=marble+"  \n";
		}
		
		return r;
		
	}
	
	public String getServerSlotString (int slot){
		
		Set<Integer> bag = bagServer.get(slot);
			
		String r = "Bag of "+bag.size()+" marbles ["+slot+"]:\n";
		
		for (Integer marble:bag){
			r+=marble+"  \n";
		}
		
		return r;
		
	}
		
	//SACAR BOLAS
	
	public int extractAmarbleFromMainBag (){
		
		int r = 0;
		
		Object[] shot = random.nextSample(mainBag, 1);
		
		Integer marble = (Integer) shot [0];
		
		mainBag.remove(marble);
		
		r = marble.intValue();
		
		return r;
		
	}
	
	public int extractAmarbleFromServerSlot (int slot){
		
		int r = 0;
		
		Object[] shot = random.nextSample((bagServer.get(slot)), 1);
		
		Integer marble = (Integer) shot [0];
		
		(bagServer.get(slot)).remove(marble);
		
		r = marble.intValue();
		
		return r;
		
	}
	
	public int[] extractNmarblesFromMainBag (int n){
		
		int [] r = new int [n];
		
		Object[] shot = random.nextSample(mainBag, n);
		
		for (int i=0;i<n;i++){
			
			Integer marble = (Integer)shot [i];
			
			mainBag.remove(marble);
			
			r[i] = marble.intValue();
		
		}
				
		return r;
		
	}
	
	public int[] extractNmarblesFromServerSlot (int n, int slot){
		
		int [] r = new int [n];
		
		Object[] shot = random.nextSample((bagServer.get(slot)), n);
		
		for (int i=0;i<n;i++){
			
			Integer marble = (Integer)shot [i];
			
			(bagServer.get(slot)).remove(marble);
			
			r[i] = marble.intValue();
		
		}
				
		return r;
		
	}
	
	
	public double getPercentage (){
		return random.nextUniform(0,1);
	}
	
	public int getNumberFromInterval (int lower, int upper){
		
		int r = lower;
		
		if (lower<upper)
			r = random.nextInt(lower, upper);
		
		return r;
	}
	
	public double getNumberFromInterval (double lower, double upper){
		
		double r = lower;
		
		if (lower<upper)
			r = random.nextUniform(lower, upper);
		
		return r;
	}
	
	
	//Secure
	
	public double getCriptograpghicNumber (double lower, double upper){
				
		double r = lower;
			
		if (lower<upper){
		
			long auxlower = (long) lower;
			
			long auxupper = (long) upper;
			
			r = random.nextSecureLong(auxlower,auxupper);
		
		}		
		
		return r;
		
	}
	
	
	//GST
	
	public GST extractOneGSTfromSet (Set<GST> st){
		
		Object[] shot = random.nextSample(st, 1);
		
		return (GST)shot[0];
		
	}
	
	
	
	
	
	
}
