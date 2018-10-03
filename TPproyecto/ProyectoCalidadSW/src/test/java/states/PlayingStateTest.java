package test.java.states;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import main.java.models.MP3Model;
import main.java.states.*;

public class PlayingStateTest {
	private MP3Model mp3Model = null;
	private MP3State playingState = null;
	private String playListPath = "src/main/resources/default songs/";
	
	private String[] currentPlaylist = null;
	private int index = 0;
	private BasicPlayer player = null;
	private int playListSize = 0;
	private double volumen = 1.0;
	private boolean isPlaying = false;
	
	
	@Before
	public void setUp(){
		System.out.println("Create Singleton");
		mp3Model = MP3Model.getInstance();
		mp3Model.addPlayList(playListPath);
		mp3Model.setIndex(0);
		mp3Model.play();
		playingState = mp3Model.getPlayingState();
		
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

		Field uniqueMP3;
		uniqueMP3 = MP3Model.class.getDeclaredField("uniqueMP3");
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
	public void testValues(){
		assertEquals(4, this.playListSize);
		assertEquals(0, this.index);
		assertTrue(Double.valueOf(1.0).equals(this.volumen));
		assertTrue(this.isPlaying);
	}

	@Test
	public void testPaused(){
		playingState.paused();
		assertNotEquals("IsPlaying deberia ser false",this.isPlaying, mp3Model.IsPlaying());
		assertTrue("Deberia cambiar de estado a paused",mp3Model.getState() instanceof PausedState);
	}

	@Test
	public void testNextSong(){
		assertTrue(mp3Model.IsPlaying());
		assertEquals("Verifico que el indice arranque en 0",0, mp3Model.getIndex());
		playingState.nextSong();
		assertTrue("Deberia seguir reproduciendo",mp3Model.IsPlaying());
		assertEquals("El nuevo indice deberia ser 1",1, mp3Model.getIndex());
	}

	@Test
	public void testPreviousSong(){
		assertTrue(mp3Model.IsPlaying());
		assertEquals("Verifico que el indice arranque en 0",0, mp3Model.getIndex());
		playingState.previousSong();
		assertTrue("Deberia seguir reproduciendo",mp3Model.IsPlaying());
		assertEquals("El nuevo indice deberia ser 3",3, mp3Model.getIndex());
	}

	@Test
	public void testStop(){
		playingState.stop();
		assertTrue("Deberia pasar al estado stop",mp3Model.getState() instanceof StoppedState);
		assertFalse("IsPlaying deberia ser false",mp3Model.IsPlaying());
	}
}
