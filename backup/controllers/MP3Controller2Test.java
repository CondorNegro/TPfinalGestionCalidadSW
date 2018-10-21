package test.java.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.controllers.MP3Controller2;
import main.java.models.MP3Model;
import main.java.views.MP3View;

public class MP3Controller2Test {
	
	private MP3Model model = null;
	private String playListPath = "src/main/resources/default songs/";
	private MP3Controller2 controller = null;
	private MP3View view = null;
	
	@Before
	public void setUp() throws Exception {
		model = MP3Model.getInstance();
		view = new MP3View(model);
		controller = new MP3Controller2(model,view);
		view.setController(controller);
		
		model.addPlayList(playListPath);
		
		view.setVisible(false);
	}
	
	@After
	public void tearDown() throws Exception {
		model.stop();
		model.getPlayer().stop();
		model.clearPlaylist();

		Field uniqueMP3 = MP3Model.class.getDeclaredField("uniqueMP3");
		uniqueMP3.setAccessible(true);
		uniqueMP3.set(null, null);
		
		view = null;
		controller = null;
	}
	
	@Test
	public void testStart() {
		controller.start();
		assertTrue(model.IsPlaying());
	}
	
	@Test
	public void testStop() {
		controller.start();
		controller.stop();
		assertFalse(model.IsPlaying());
	}
	
	@Test
	public void testIncreaseBPM() {
		controller.start();
		controller.increaseBPM();
		
		assertTrue(model.IsPlaying());
		assertEquals(1, model.getIndex());
	}
	
	@Test
	public void testDecreaseBPM() {
		controller.start();
		controller.decreaseBPM();
		
		assertTrue(model.IsPlaying());
		assertEquals(model.getPlaylistSize() - 1, model.getIndex());
	}
	
	@Test
	public void testSetVolume() {
		controller.setVolumen(0.0);
		assertTrue(Double.valueOf(0).equals(model.getVolumen()));
		
		controller.setVolumen(0.9);
		assertTrue(Double.valueOf(0.9).equals(model.getVolumen()));
	}
		
	@Test
	public void testPause() {
		assertFalse(model.IsPlaying());
		controller.pause();
		assertFalse(model.IsPlaying());
	}
	
	@Test
	public void testRemoveTrack() {
		int previousPlaylistLength = model.getPlaylistSize();
		controller.removeTrack(1);
		int newPlaylistLength = model.getPlaylistSize();

		assertEquals(previousPlaylistLength, newPlaylistLength + 1);
	}
}
