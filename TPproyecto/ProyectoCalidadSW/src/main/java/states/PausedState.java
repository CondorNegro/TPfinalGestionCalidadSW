package main.java.states;

import javazoom.jlgui.basicplayer.BasicPlayerException;
import main.java.models.MP3Model;

public class PausedState implements MP3State {
	
	MP3Model model;
	
	public PausedState(MP3Model model){
		this.model = model;
	}

	@Override
	public void play() {
		try {
			model.getPlayer().resume();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		model.setState(model.getPlayingState());
	}

	@Override
	public void nextSong() {
		model.setIndex((model.getIndex()+1)%model.getPlaylistSize());
		model.playNow(model.getIndex());
		model.setState(model.getPlayingState());
	}

	@Override
	public void previousSong() {
		int index = (model.getIndex()-1)%model.getPlaylistSize();
		if(index < 0){
			index = model.getPlaylistSize()-1;
		}
		model.setIndex(index);
		model.playNow(model.getIndex());
		model.setState(model.getPlayingState());
	}

	@Override
	public void stop() {
		try {
			model.getPlayer().stop();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		model.setState(model.getStoppedState());
	}
	
	//Los siguientes metodos no realizan ninguna accion en el estadoa actual
	public void paused() {}
	public void addPlaylist() {}
}
