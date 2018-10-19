package main.java.states;

import javazoom.jlgui.basicplayer.BasicPlayerException;
import main.java.models.MP3Model;
import main.java.states.MP3State;

public class PlayingState implements MP3State {
	
	MP3Model model;
	
	public PlayingState(MP3Model model){
		this.model = model;
	}

	@Override
	public void paused() {
		try {
			model.getPlayer().pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		model.setState(model.getPausedState());
	}

	@Override
	public void nextSong() {
		int index = (model.getIndex()+1)%model.getPlaylistSize();
		model.setIndex(index);
		model.playNow(index);
	}

	@Override
	public void previousSong() {
		int index = (model.getIndex()-1)%model.getPlaylistSize();
		if(index<0){
			index = model.getPlaylistSize()-1;
		}
		model.setIndex(index);
		model.playNow(index);
	}

	@Override
	public void stop() {
		try {
			model.getPlayer().stop();
			model.setState(model.getStoppedState());
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	//Los siguientes metodos no realizan ninguna accion en el estadoa actual

	public void play() {}
	public void addPlaylist() {}
}
