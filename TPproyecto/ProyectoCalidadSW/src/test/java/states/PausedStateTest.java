//package test.java.states;
//
//import static org.junit.Assert.*;
//
//import java.lang.reflect.Field;
//
//import javazoom.jlgui.basicplayer.BasicPlayer;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import main.java.models.MP3Model;
//import main.java.states.*;;
//
//public class PausedStateTest {
//	private static MP3Model mp3Model = null;
//	private static MP3State pauseState = null;
//	private static String playListPath = "src/main/resources/default songs/";
//	
//	private String[] currentPlaylist = null;
//	private int index = 0;
//	private BasicPlayer player = null;
//	private int playListSize = 0;
//	private double volumen = 1.0;
//	private boolean isPlaying = false;
//	
//	
//	@BeforeClass
//	public static void classSetup(){
//		System.out.println("Create Singleton");
//		mp3Model = MP3Model.getInstance();
//		mp3Model.addPlayList(playListPath);
//		pauseState = mp3Model.getPausedState();
//	}
//	
//	@Before
//	public void setUp()  {
//		mp3Model.pause();
//		
//		this.currentPlaylist = mp3Model.getCurrentPlaylist();
//		this.index = mp3Model.getIndex();
//		this.playListSize = mp3Model.getPlaylistSize();
//		this.volumen = mp3Model.getVolumen();
//		this.isPlaying = mp3Model.IsPlaying();
//	}
//	
//	@After
//	public void tearDown() throws Exception {
//		System.out.println("Delete Singleton");
//		mp3Model.stop();
//		mp3Model.setIndex(0);
//
//		Field uniqueMP3 = MP3Model.class.getDeclaredField("uniqueMP3");
//		uniqueMP3.setAccessible(true);
//		uniqueMP3.set(null, null);
//		
//		this.currentPlaylist = null;
//		this.index = 0;
//		this.isPlaying = false;
//	}
//
//	@Test
//	public void testValues(){  //Testea la inicializacion
//		assertEquals(4, this.playListSize);
//		assertEquals(0, this.index);
//		assertTrue(Double.valueOf(1.0).equals(this.volumen));
//		assertFalse(this.isPlaying);
//	}
//
//	@Test
//	public void testPlay(){		
//		pauseState.play();
//		assertTrue("Deberia estar reproduciendo",mp3Model.IsPlaying());
//	}
//
//	@Test
//	public void testNextSong(){
//		assertFalse("IsPlaying deberia ser false",mp3Model.IsPlaying());
//		assertEquals("El Indice deberia ser 0",0, mp3Model.getIndex());
//		pauseState.nextSong();
//		assertEquals("El indice ahora deberia ser 1",1, mp3Model.getIndex());
//		assertTrue("El volumen deberia mantenerse constante",Double.valueOf(this.volumen).equals(mp3Model.getVolumen()));
//		assertTrue("Y ahora deberia estar reproduciendo",mp3Model.IsPlaying());
//	}
//	
//	@Test
//	public void testNextSongCircular(){
//		for(int i = 0; i<currentPlaylist.length;i++){
//			pauseState.nextSong();
//			mp3Model.pause();
//		}
//		assertEquals("El indice deberia volver a cero",0,mp3Model.getIndex());
//	}
//
//	@Test
//	public void testPreviousSong(){
//		assertFalse("IsPlaying deberia ser false",mp3Model.IsPlaying());
//		assertEquals("El Indice deberia ser 0",0, mp3Model.getIndex());
//		pauseState.previousSong();
//		assertEquals("El indice ahora deberia ser 3",3, mp3Model.getIndex());
//		assertTrue("El volumen deberia mantenerse constante",Double.valueOf(this.volumen).equals(mp3Model.getVolumen()));
//		assertTrue("Y ahora deberia estar reproduciendo",mp3Model.IsPlaying());
//	}
//
//	@Test
//	public void testStop(){
//		pauseState.stop();
//		assertTrue(mp3Model.getState() instanceof StoppedState);
//	}
//}
