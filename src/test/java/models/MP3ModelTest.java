package test.java.models;

import java.lang.reflect.Field;

import javax.swing.DefaultListModel;

import main.java.models.MP3Model;
import main.java.states.PlayingState;
import main.java.states.StoppedState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MP3ModelTest {
	private MP3Model mp3Model = null;
	private String playListPath = "src/main/resources/default songs/";
	private String pinkPantherSongName = "Pink Panther Theme.mp3";
	private String emptySongName = "Daft Punk - One More Time.mp3";
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Create Singleton");
		mp3Model = MP3Model.getInstance();
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
	}
	
	@Test
	public void testGetCurrentPlaylist(){
		String[] playList = mp3Model.getCurrentPlaylist();
		assertEquals(0, playList.length); 
	}
	
	@Test
	public void testAddPlayList(){
		assertEquals(0, mp3Model.getCurrentPlaylist().length); 
		
		mp3Model.addPlayList(playListPath);
		assertEquals(5, mp3Model.getCurrentPlaylist().length); 
	}
	
	@Test
	public void testClearPlayList(){
		mp3Model.addPlayList(playListPath);
		assertEquals(5, mp3Model.getCurrentPlaylist().length); 
		
		mp3Model.clearPlaylist();
		assertEquals(0, mp3Model.getCurrentPlaylist().length); 
	}
	
	@Test
	public void testGetPlayListSize(){
		assertEquals(0, mp3Model.getCurrentPlaylist().length);
		assertEquals(0, mp3Model.getPlaylistSize());
		
		mp3Model.addPlayList(playListPath);
		
		assertEquals(5, mp3Model.getCurrentPlaylist().length);
		assertEquals(5, mp3Model.getPlaylistSize());
	}
	
	@Test
	public void testGetIndex(){
		assertEquals(0, mp3Model.getPlaylistSize());
		assertEquals(0, mp3Model.getIndex());
		
		mp3Model.addPlayList(playListPath);

		assertEquals(5, mp3Model.getPlaylistSize());
		assertEquals(0, mp3Model.getIndex());
	}
	
	@Test
	public void testSetIndex(){
		assertEquals(0, mp3Model.getPlaylistSize());
		
		int indexValue = mp3Model.getIndex();
		assertFalse(mp3Model.setIndex(2));
		assertEquals(indexValue, mp3Model.getIndex());
		
		mp3Model.addPlayList(playListPath);
		assertEquals(5, mp3Model.getPlaylistSize());
		
		assertTrue(mp3Model.setIndex(2));
		assertEquals(2, mp3Model.getIndex());
		
		assertFalse(mp3Model.setIndex(5));
		assertEquals("El indice deberia seguir siendo 2",2,mp3Model.getIndex());
		
		assertFalse(mp3Model.setIndex(-1));
		assertEquals("El indice deberia seguir siendo 2",2,mp3Model.getIndex());
	}
	
	@Test
	public void testPlayNow(){
		mp3Model.addPlayList(playListPath);
		
		mp3Model.playNow(2);
		assertEquals(2, mp3Model.getIndex());
	}
	
	@Test
	public void testPlay(){
		mp3Model.addPlayList(playListPath);
		
		mp3Model.setIndex(2);
		mp3Model.play();

		assertTrue(mp3Model.IsPlaying());
	}
	
	@Test
	public void testIsPlaying(){
		assertFalse(mp3Model.IsPlaying());
		
		mp3Model.addPlayList(playListPath);
		mp3Model.playNow(2);
		
		assertTrue(mp3Model.IsPlaying());
	}

	@Test
	public void testStop(){
		mp3Model.addPlayList(playListPath);
		mp3Model.setIndex(2);
		mp3Model.play();

		assertTrue(mp3Model.IsPlaying());
		
		mp3Model.stop();
		assertFalse(mp3Model.IsPlaying());
	}

	@Test
	public void testPause(){
		mp3Model.addPlayList(playListPath);
		mp3Model.setIndex(2);
		mp3Model.play();
		
		assertTrue(mp3Model.IsPlaying());
		
		mp3Model.pause();
		assertFalse(mp3Model.IsPlaying());
	}
	
	@Test
	public void testGetPlaylistSize(){
		assertEquals(0, mp3Model.getPlaylistSize());
		
		mp3Model.addPlayList(playListPath + pinkPantherSongName);
		assertEquals(1, mp3Model.getPlaylistSize());
	}
	
	@Test
	public void testGetCurrentTrackName(){
		mp3Model.addPlayList(playListPath + pinkPantherSongName);
		mp3Model.setIndex(0);
		mp3Model.play();
		
		assertTrue(mp3Model.IsPlaying());
		assertEquals("Pink Panther Theme", mp3Model.getCurrentTrackName());
	}
	
	@Test
	public void testGetCurrentSongDuration(){
		mp3Model.addPlayList(playListPath + pinkPantherSongName);
		mp3Model.setIndex(0);
		mp3Model.play();
		
		assertTrue(mp3Model.IsPlaying());
		assertEquals("01:08", mp3Model.getCurrentSongDuration());
	}
	
	@Test
	public void testRemovePlayList(){
		mp3Model.addPlayList(playListPath);
		assertEquals(5, mp3Model.getPlaylistSize());
		
		mp3Model.removePlayList(2);
		assertEquals(4, mp3Model.getPlaylistSize());
	}
	
	@Test
	public void testSetState(){
		mp3Model.setState(mp3Model.getEmptyState());
		assertFalse(mp3Model.IsPlaying());
		
		mp3Model.setState(mp3Model.getPlayingState());
		assertTrue(mp3Model.IsPlaying());
		
		mp3Model.setState(mp3Model.getEmptyState());
		assertFalse(mp3Model.IsPlaying());
	}
	
	@Test
	public void testSetVolumen(){
		mp3Model.setVolumen(0);
		assertTrue(Double.valueOf(0).equals(mp3Model.getVolumen()));
		
		mp3Model.setVolumen(1);
		assertTrue(Double.valueOf(1).equals(mp3Model.getVolumen()));
	}
	
	@Test
	public void testRemoveLastPlayListItem(){
		mp3Model.addPlayList(playListPath + pinkPantherSongName);
		mp3Model.setIndex(0);
		mp3Model.play();
		
		assertTrue(mp3Model.IsPlaying());
		
		mp3Model.removePlayList(0);
		assertEquals(0, mp3Model.getPlaylistSize());
		assertFalse(mp3Model.IsPlaying());
	}
	
	@Test
	public void testNextSongAtRemovePlayListItem(){
		mp3Model.addPlayList(playListPath);
		mp3Model.setIndex(3);
		mp3Model.play();

		assertTrue(mp3Model.IsPlaying());
		
		mp3Model.removePlayList(3);
		assertFalse(mp3Model.IsPlaying());
		assertEquals(2, mp3Model.getIndex());
	}
	
	//-------------------------------------------------
	//		Tests Gestion de Calidad de Software
	//-------------------------------------------------
	
	@Test
	public void testSingleton() {
		MP3Model mp3Model2 = MP3Model.getInstance();
		assertEquals(mp3Model, mp3Model2);
	}
	
	@Test
	public void testNextSong() {
		mp3Model.addPlayList(playListPath);
		mp3Model.play();
		
		assertEquals(mp3Model.getIndex(), 0);
		mp3Model.nextSong();
		assertEquals(mp3Model.getIndex(), 1);
	}
	
	@Test
	public void testPreviousSong() {
		mp3Model.addPlayList(playListPath);
		mp3Model.play();
		
		assertEquals(mp3Model.getIndex(), 0);
		mp3Model.previousSong();
		assertEquals(mp3Model.getIndex(), mp3Model.getPlaylistSize() - 1);
	}
	
	@Test
	public void testSetIncorrectVolumen(){
		double currentVolume = mp3Model.getVolumen();
		
		mp3Model.setVolumen(-1);
		assertTrue(Double.valueOf(currentVolume).equals(mp3Model.getVolumen()));
		
		mp3Model.setVolumen(5);
		assertTrue(Double.valueOf(currentVolume).equals(mp3Model.getVolumen()));
	}
	
	@Test
	public void testGetState() {
		mp3Model.addPlayList(playListPath);
		
		mp3Model.play();
		assertTrue(mp3Model.getState() instanceof PlayingState);
		
		mp3Model.stop();
		assertTrue(mp3Model.getState() instanceof StoppedState);	
	}
	
	@Test
	public void testGetAlbumArt() {
		mp3Model.addPlayList(playListPath);
		assertNotEquals(mp3Model.getAlbumArt(), null);
	}
	
	@Test
	public void testGetCurrentSongInfo() {
		
		String track = "Track: 2" ;
		String artist = "Artist: Lalo Schifrin";
		String title = "Title: Mission Impossible Theme";
		String album = "Album: Mission Impossible Soundtrack";
		String year = "Year: 1996";
		String genre = "Genre: Soundtrack";
		
		mp3Model.addPlayList(playListPath);
		DefaultListModel<String> songInfo = mp3Model.getSongInfo();
		
		assertEquals(track, songInfo.get(0));
		assertEquals(artist, songInfo.get(1));
		assertEquals(title, songInfo.get(2));
		assertEquals(album, songInfo.get(3));
		assertEquals(year, songInfo.get(4));
		assertEquals(genre, songInfo.get(5));
	}
	
	@Test
	public void testGetCurrentSongDurationEmpty(){
		assertEquals("00:00", mp3Model.getCurrentSongDuration());
	}
	
	@Test
	public void testGetCurrentSongNameEmpty() {
		assertEquals("", mp3Model.getCurrentTrackName());
	}
	
	@Test
	public void testGetPlayList() {
		assertEquals(0, mp3Model.getPlaylist().size());
		
		mp3Model.addPlayList(playListPath);
		assertEquals(5, mp3Model.getPlaylist().size());
		
		mp3Model.removePlayList(0);
		assertEquals(4, mp3Model.getPlaylist().size());
		
		mp3Model.clearPlaylist();
		assertEquals(0, mp3Model.getPlaylist().size());
	}
	
	@Test
	public void testGetSongInfoEmptySong() {
		mp3Model.addPlayList(playListPath + emptySongName);
		
		String track = "Track: null";
		String artist = "Artist: null";
		String title = "Title: Daft Punk - One More Time";
		String album = "Album: null";
		String year = "Year: null";
		String genre = "Genre: null";
		
		DefaultListModel<String> songInfo = mp3Model.getSongInfo();
		
		assertEquals(track, songInfo.get(0));
		assertEquals(artist, songInfo.get(1));
		assertEquals(title, songInfo.get(2));
		assertEquals(album, songInfo.get(3));
		assertEquals(year, songInfo.get(4));
		assertEquals(genre, songInfo.get(5));
	}
	
	@Test
	public void testGetAlbumArtEmptySong() {
		mp3Model.addPlayList(playListPath + emptySongName);
		assertEquals(null, mp3Model.getAlbumArt());		
	}
	
	@Test
	public void testRemoveSongEmptyPlaylist() {
		assertEquals(0, mp3Model.getPlaylistSize());
		mp3Model.removePlayList(1);
		assertEquals(0, mp3Model.getPlaylistSize());
	}
	
	@Test
	public void testAddNotMp3File() {
		mp3Model.addPlayList(pinkPantherSongName + ".aac");
		assertEquals(0, mp3Model.getPlaylistSize());
	}
	
}
