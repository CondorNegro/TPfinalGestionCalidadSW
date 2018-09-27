package main.java.states;

import main.java.models.MP3Model;

public class EmptyState implements MP3State {
	
	MP3Model model;
	
	public EmptyState(MP3Model model){
		this.model = model;
	}
	
	@Override
	public void addPlaylist() {
		model.setState(model.getStoppedState());
	}
	
	//Los siguientes metodos no realizan ninguna accion en el estado actual
	@Override
	public void play() {}

	@Override
	public void paused() {}

	@Override
	public void nextSong() {}

	@Override
	public void previousSong() {}

	@Override
	public void stop() {}

}
