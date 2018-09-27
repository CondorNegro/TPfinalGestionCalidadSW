package main.java.models;

public class ProgressThread implements Runnable{
	Thread thread;
	int maximum;
	int value;
	boolean playing;
	MP3Model model;

	public ProgressThread() {
		thread = new Thread(this);
		thread.start();
		playing = false;
		value = 0;
	}
	public void run() {
		while(true) {
			//Si estoy reproduciendo aumento value
			if(playing){
				value++;
				//Si ya llegue al final de la cancion
				if(value==maximum+1){
					model.nextSong(); //Paso a la siguiente
					value = 0; //Reseteo el valor a 0
				}
				model.notifyProgressObservers(value); //Cada un segundo notifico a los Progress0bservers
				try {
					Thread.sleep(1000);
				} catch (Exception e) {};	
			}
			else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setMax(int i){
		maximum = i;
	}
	public void start(){
		playing = true;
	}
	public void stop(){
		playing = false;
	}
	public void reset(){
		value = 0;
		model.notifyProgressObservers(value);
	}
	public void setModel(MP3Model newModel){
		model = newModel;
	}
}
