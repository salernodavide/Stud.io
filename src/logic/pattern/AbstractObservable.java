package logic.pattern;

import java.util.List;

public class AbstractObservable {
	protected List<Observer> observers;
	
	public void attachObserver(Observer observer) {
		this.observers.add(observer);
	}
	
	public void detachObserver(Observer observer) {
		this.observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(int i=0; i<this.observers.size();i++)
			observers.get(i).update();
	}
}