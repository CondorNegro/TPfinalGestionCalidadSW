package main.java.models;

import javax.swing.DefaultListModel;

import main.java.views.BPMObserver;
import main.java.views.BeatObserver;
import main.java.views.ProgressObserver;
import main.java.views.TrackObserver;

public interface MP3ModelInterface {
	void play();

	void pause();

	void setVolumen(double volumen);

	void addPlayList(String Path);

	void removePlayList(int index);

	void previousSong();

	void nextSong();
	
	void stop();
	
	void registerObserver(BeatObserver o);

	void removeObserver(BeatObserver o);

	void registerObserver(BPMObserver o);

	void removeObserver(BPMObserver o);
	
	double getVolumen();
	
	boolean setIndex(int index);
	
	int getIndex();
	
	boolean IsPlaying();
	
	String getCurrentTrackName();
	
	String getCurrentSongDuration();
	
	int getCurrentSongDurationSec();
	
	String[] getCurrentPlaylist();
	
	void registerObserver(TrackObserver o);
	
	void removeObserver(TrackObserver o);

	void clearPlaylist();

	DefaultListModel<String> getSongInfo();

	byte[] getAlbumArt();
	
	void registerObserver(ProgressObserver o);
	
	void removeObserver (ProgressObserver o);
	
}