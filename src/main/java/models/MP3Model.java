package main.java.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import main.java.views.BPMObserver;
import main.java.views.BeatObserver;
import main.java.views.ProgressObserver;
import main.java.views.TrackObserver;
import main.java.states.*;

public class MP3Model implements MP3ModelInterface {
	
	private static MP3Model uniqueMP3 = null;
	
	private BasicPlayer player;
	private ArrayList<String> playlist;
	private ArrayList<BPMObserver> bpmObservers;
	private ArrayList<BeatObserver> beatObservers;
	private ArrayList<TrackObserver> trackObservers;
	private ArrayList<ProgressObserver> progressObservers;
	//States
	private MP3State currentState;
	private MP3State playing;
	private MP3State stopped;
	private MP3State empty;
	private MP3State paused;
	//-----------------------------
	private int index;
	private double volumen;
	//ProgressThread es un hilo que cuenta segundos
	private ProgressThread progressThread;
	
	private MP3Model(){
		this.player = new BasicPlayer();
		this.bpmObservers = new ArrayList<BPMObserver>();
		this.beatObservers = new ArrayList<BeatObserver>();
		this.index = 0;	
		this.volumen = 1;			//maximo volumen
		this.empty = new EmptyState(this);
		this.stopped = new StoppedState(this);
		this.paused = new PausedState(this);
		this.playing = new PlayingState(this);
		this.currentState = empty;
		this.playlist = new ArrayList<String>();
		this.trackObservers = new ArrayList<TrackObserver>();
		this.progressThread = new ProgressThread();
		progressThread.setModel(this);
		this.progressObservers = new ArrayList<ProgressObserver>();
	}
	
	public static MP3Model getInstance(){
		if (uniqueMP3 == null){
			uniqueMP3 = new MP3Model();
		}
		return uniqueMP3;
	}

	@Override
	public void play() {
		currentState.play();
		notifyBPMObservers();
		notifyTrackObservers();
		try {
			player.setGain(volumen);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		//Cuando comienzo a reproducir una nueva cancion obtengo su duracion y se la paso al hilo
		//y le digo que empiece a contar
		progressThread.setMax(getCurrentSongDurationSec());
		progressThread.start();
	}

	@Override
	public void pause() {
		currentState.paused();
		progressThread.stop();
	}
	
	@Override
	public void addPlayList(String path) {
		File file = new File(path);				// Uso la ruta para crear un nuevo File
		if (file.isFile()) { 					// Si la ruta es una sola cancion
			if (file.getAbsolutePath().endsWith(".mp3")){
				playlist.add(path);
			}
		} else { 								// Si la ruta es una carpeta con canciones
			File list[] = file.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					if(list[i].getAbsolutePath().endsWith(".mp3")){
						playlist.add(list[i].getAbsolutePath());
					}
				}
			}
		}
		currentState.addPlaylist();
		notifyTrackObservers();
	}

	@Override
	public void previousSong() {
		currentState.previousSong();
		this.setVolumen(volumen);
		notifyTrackObservers();
		notifyBPMObservers();
		progressThread.reset();
		progressThread.setMax(getCurrentSongDurationSec());
		progressThread.start();
	}

	@Override
	public void nextSong() {
		currentState.nextSong();
		this.setVolumen(volumen);
		notifyTrackObservers();
		notifyBPMObservers();
		progressThread.reset();
		progressThread.setMax(getCurrentSongDurationSec());
		progressThread.start();
	}

	@Override
	public void stop() {
		progressThread.stop();
		progressThread.reset();
		currentState.stop();
	}

	@Override
	public void setVolumen(double volumen) {
		if(volumen<0 || volumen>1){
			return;
		}
		else{
			this.volumen = volumen;
			try {
				player.setGain(volumen);
			} catch (BasicPlayerException e) {
				e.printStackTrace();
			}
		}
	}
	
	public double getVolumen(){
		return volumen;
	}
	
	public boolean setIndex (int index){
		if ( index > (playlist.size()-1) || index < 0){
			return false;
		}
		this.index = index;
		return true;
	}

	@Override
	public int getIndex() {
		return index;
	}
	
	public String getCurrentTrackName(){		
		if (currentState instanceof EmptyState){
			return "";
		}
		String path = playlist.get(index);
		Mp3File mp3file = null;
		try {
			mp3file = new Mp3File(path);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		String songName = mp3file.getId3v2Tag().getTitle();
		//String songName = mp3file.getId3v2Tag().getTitle();
		if(songName==null){
			return new File(path).getName();
		}
		return songName;
	}

	public String getCurrentSongDuration(){
		if (currentState instanceof EmptyState){
			return "00:00";
		}
		String path = playlist.get(index);
		Mp3File song = null;
		try {
			song = new Mp3File(path);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		long duration = song.getLengthInSeconds();
		int minutes = (int)duration/60;
		int seconds = (int)duration%60;
		String songDuration = String.format("%02d:%02d", minutes, seconds+1);
		return songDuration;
	}
	
	public int getCurrentSongDurationSec(){
		String path = playlist.get(index);
		Mp3File song = null;
		try {
			song = new Mp3File(path);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		int duration = (int)song.getLengthInSeconds();
		return duration+1;
	}

	@Override
	public boolean IsPlaying() {
		if (currentState instanceof PlayingState ){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public String[] getCurrentPlaylist() {
		String[] playlistArray = new String[playlist.size()];
		Mp3File song = null;
		for(int i=0;i<playlist.size();i++){
			String path = playlist.get(i);
			try {
				song = new Mp3File(path);
			} catch (UnsupportedTagException | InvalidDataException | IOException e) {
				e.printStackTrace();
			}
			String songTitle = song.getId3v2Tag().getTitle();
			if(songTitle==null){
				playlistArray[i] = new File(path).getName();
			}		
			else{
				playlistArray[i] = songTitle;
			}
		}
		return playlistArray;
	}

	public ArrayList<String> getPlaylist() {
		return this.playlist;
	}
	
	@Override
	public DefaultListModel<String> getSongInfo() {
		Mp3File song = null;
		try {
			song = new Mp3File(playlist.get(index));
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		if (song.hasId3v2Tag()) {
			ID3v2 songTag = song.getId3v2Tag();
			DefaultListModel<String> songDetails = new DefaultListModel<String>();
	    	songDetails.addElement("Track: " + songTag.getTrack());
	    	songDetails.addElement("Artist: " + songTag.getArtist());
	    	songDetails.addElement("Title: " + songTag.getTitle());
	    	songDetails.addElement("Album: " + songTag.getAlbum());
	    	songDetails.addElement("Year: " + songTag.getYear());
	    	songDetails.addElement("Genre: " + songTag.getGenreDescription());
			return songDetails;
		}
		return null;
	}
	
	@Override
	public byte[] getAlbumArt(){
		Mp3File song = null;
		try {
			song = new Mp3File(playlist.get(index));
			song = new Mp3File(playlist.get(index));
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		if (song.hasId3v2Tag()) {
			byte[] albumArt = song.getId3v2Tag().getAlbumImage();
			return albumArt;
		}
		return new byte[0];
		//return null;
	}
	
	@Override
	public void clearPlaylist() {
		playlist.clear();
//		notifyTrackObservers();
		this.setIndex(0);
	}
	
	public MP3State getState(){
		return this.currentState;
	}
	
	public void setState(MP3State newState){
		this.currentState = newState;
	}
	
	public MP3State getPlayingState(){return playing;}
	public MP3State getPausedState() {return paused;}
	public MP3State getEmptyState()  {return empty;}
	public MP3State getStoppedState(){return stopped;}
	
	public BasicPlayer getPlayer(){
		return player;
	}
	
	public int getPlaylistSize(){
		return playlist.size();
	}
	
	//Metodo usado por el estado, para no perder encapsulamiento.
	//En lugar de proveer acceso al arraylist playlist simplemente 
	//muestro el indice de la cancion a reproducir con un getter, y proveo
	//el metodo playNow para que comience a sonar segun el indice que le paso
	public void playNow(int index){
		this.setIndex(index);
		File f = new File(playlist.get(this.getIndex()));
		try {
			player.open(f);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		try {
			this.setState(playing);
			player.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removePlayList(int index) {
		if(!playlist.isEmpty()){
			//Si solo hay una cancion en la playlist paso al estado empty
			if (playlist.size() == 1){
				this.stop();
				this.setState(empty);
			}
			//Si la cancion elegida es la que estoy reproduciendo paso al estado stop
			else if(this.getIndex() == index){
				this.stop();
				this.setState(stopped);
				setIndex(getIndex()-1);	//Si borro la cancion actual, apunto a la anterior con index
			}
			//Arregla el error de borrar una cancion anterior a la actual mientras se esta reproduciendo
			if(getIndex()>index){
				setIndex(getIndex()-1);
			}
			
			
			playlist.remove(index); //Borro la cancion en index
		}
		this.notifyTrackObservers();
	}
	

	/////////////////////////////////////////////////////////////////////////////////
	//Metodos del patron Observer
	/////////////////////////////////////////////////////////////////////////////////
	
	public void notifyProgressObservers(int progress) {
		for (int i = 0; i < progressObservers.size(); i++) {
			ProgressObserver observer = (ProgressObserver) progressObservers.get(i);
			observer.updateTrackProgress(progress, getCurrentSongDurationSec());
		}
	}

	public void registerObserver(ProgressObserver o) {
		progressObservers.add(o);	
	}
	
	public void removeObserver(ProgressObserver o) {
		int i = progressObservers.indexOf(o);
		if (i >= 0) {
			progressObservers.remove(i);
		}
	}
	
	public void registerObserver(TrackObserver o){
		trackObservers.add(o);
	}
	
	public void removeObserver(TrackObserver o){
		int i = trackObservers.indexOf(o);
		if (i >= 0){
			trackObservers.remove(i);
		}
	}

	public void notifyTrackObservers(){
		for (int i = 0; i < trackObservers.size(); i++){
			TrackObserver observer = trackObservers.get(i);
			observer.updateTrackInfo();
			observer.updatePlaylistInfo();
		}
	}
	
	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}
	
	public void removeObserver(BPMObserver o) {
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}

	public void notifyBPMObservers() {
		for (int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver) bpmObservers.get(i);
			observer.updateBPM();
		}
	}

	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);	
	}
	
	public void removeObserver(BeatObserver o) {
		int i = beatObservers.indexOf(o);
		if (i >= 0) {
			beatObservers.remove(i);
		}
	}
}