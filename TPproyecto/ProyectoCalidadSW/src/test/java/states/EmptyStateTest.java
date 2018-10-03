package test.java.states;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import javazoom.jlgui.basicplayer.BasicPlayer;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import main.java.models.MP3Model;
import main.java.states.MP3State;
import main.java.states.StoppedState;

public class EmptyStateTest {
	private MP3Model mp3Model = null;
	private MP3State emptyState = null;
	private String playListPath = "src/main/resources/default songs/";

	
	private String[] currentPlaylist = null;
	private int index = 0;
	private BasicPlayer player = null;
	private int playListSize = 0;
	private double volumen = 1.0;
	private boolean isPlaying = false;
	
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Create Singleton");
		mp3Model = MP3Model.getInstance();
		emptyState = mp3Model.getEmptyState();
		
		this.currentPlaylist = mp3Model.getCurrentPlaylist().clone();
		this.index = mp3Model.getIndex();
		this.player = mp3Model.getPlayer();
		this.playListSize = mp3Model.getPlaylistSize();
		this.volumen = mp3Model.getVolumen();
		this.isPlaying = mp3Model.IsPlaying();
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("Delete Singleton");
		mp3Model.stop();
		mp3Model.getPlayer().stop();
		mp3Model.clearPlaylist();

		Field uniqueMP3 = MP3Model.class.getDeclaredField("uniqueMP3");
		uniqueMP3.setAccessible(true);
		uniqueMP3.set(null, null);
		
		this.currentPlaylist = null;
		this.index = 0;
		this.player = null;
		this.playListSize = 0;
		this.volumen = 1.0;
		this.isPlaying = false;
	}

	@Test
	public void testAddPlayList(){
		assertEquals("La playlist deberia estar vacia",0,mp3Model.getPlaylistSize());
		emptyState.addPlaylist();
		assertTrue("Deberia haber pasado a stopped state",mp3Model.getState() instanceof StoppedState);
		assertEquals("El indice deberia haberse seteado correctamente",this.index, mp3Model.getIndex());
		assertTrue(Double.valueOf(this.volumen).equals(mp3Model.getVolumen()));
	}
}