package main.java.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.java.controllers.MP3Controller2;
import main.java.models.MP3ModelInterface;

public class MP3View extends JFrame implements ActionListener, TrackObserver, ProgressObserver {

	/**
	 * 
	 */
				private static final long serialVersionUID = 1L;
	MP3ModelInterface model;
	MP3Controller2 controller = null;
	//Other
	DefaultListModel<String> songList = new DefaultListModel<String>();
	
	//Components
	JPanel container = new JPanel();
	JFrame songArt = null;
	JFrame songInfo = null;
	JSlider volSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	JButton btnPlay = new JButton();
	JButton btnAdd = new JButton();
	JButton btnNext = new JButton();
	JButton btnPrev = new JButton();
	JButton btnMute = new JButton();
	JButton btnCover = new JButton();
	JButton btnShSt = new JButton();
	JButton btnShWf = new JButton();
	JButton btnShDi = new JButton();
	JButton btnStop = new JButton();
	
	JButton btnDelete = new JButton();
	JButton btnInfo = new JButton();
	JButton btnArt = new JButton();
	
	JMenuBar topMenu = new JMenuBar();
	JList<String> jSongList = new JList<String>(songList);
	JLabel lblplaying = new JLabel();
	JLabel lblst = new JLabel();
	JLabel lblet = new JLabel();
	JFileChooser chooser = new JFileChooser();
	
	JPanel progressPanel = new JPanel();
	ProgressBar progressBar = new ProgressBar();
	
	//Icons
	String frameIconPath = "main/resources/images/frameicon.png";
	ImageIcon frameIcon = new ImageIcon(getClass().getClassLoader().getResource(frameIconPath));
	String playIconPath = "main/resources/images/playicon.png";
	ImageIcon playIcon = new ImageIcon(getClass().getClassLoader().getResource(playIconPath));
	String pauseIconPath = "main/resources/images/pauseicon.png";
	ImageIcon pauseIcon = new ImageIcon(getClass().getClassLoader().getResource(pauseIconPath));
	String volUpIconPath = "main/resources/images/up.png";
	ImageIcon volUpIcon = new ImageIcon(getClass().getClassLoader().getResource(volUpIconPath));
	String volDownIconPath = "main/resources/images/down.png";
	ImageIcon volDownIcon = new ImageIcon(getClass().getClassLoader().getResource(volDownIconPath));
	String muteIconPath = "main/resources/images/off.png";
	ImageIcon muteIcon = new ImageIcon(getClass().getClassLoader().getResource(muteIconPath));
	String prevIconPath = "main/resources/images/prev.png";
	ImageIcon prevIcon = new ImageIcon(getClass().getClassLoader().getResource(prevIconPath));
	String nextIconPath = "main/resources/images/next.png";
	ImageIcon nextIcon = new ImageIcon(getClass().getClassLoader().getResource(nextIconPath));
	String addIconPath = "main/resources/images/add.png";
	ImageIcon addIcon = new ImageIcon(getClass().getClassLoader().getResource(addIconPath));
	String stopIconPath = "main/resources/images/stop.png";
	ImageIcon stopIcon = new ImageIcon(getClass().getClassLoader().getResource(stopIconPath));
	String deleteIconPath = "main/resources/images/delete.png";
	ImageIcon deleteIcon = new ImageIcon(getClass().getClassLoader().getResource(deleteIconPath));
	String infoIconPath = "main/resources/images/songinfo.png";
	ImageIcon infoIcon = new ImageIcon(getClass().getClassLoader().getResource(infoIconPath));
	String artIconPath = "main/resources/images/songart.png";
	ImageIcon artIcon = new ImageIcon(getClass().getClassLoader().getResource(artIconPath));

	
	/**
	 * Class/Frame constructor
	 */
	public MP3View(MP3ModelInterface model)
	{
		this.model = model;
		this.init();										//Inicializa la vista
		this.updatePlaylistInfo();							//Muestra la playlist añadida por defecto en el JScrollPanel
		this.addListeners();								//Añade EventListener a los botones
		addHoverMessages();									//Agrega mensajes de ayuda
		model.registerObserver((TrackObserver)this);
		model.registerObserver((ProgressObserver)this);
	}
	
	/**
	 * Init Swing graphics UI 
	 */
	private void init()
	{
		//MainView
		setIconImage(frameIcon.getImage());
		setTitle("Music Player - Java - 1.0");
		int _H = 400;
		int _W = 400;
		setSize(_W,_H);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		//Container
		container.setLayout(null);
		getContentPane().add(container);
		

		//Panel de nowPlaying(el que va arriba)
		JPanel panelNP = new JPanel();
		panelNP.setLayout(new BoxLayout(panelNP, BoxLayout.PAGE_AXIS));
		panelNP.setToolTipText("Now Playing");
		panelNP.setBorder(BorderFactory.createMatteBorder(1, 0, 2, 0, Color.gray));
		panelNP.setBounds(5, 0, _W-15, 20);
		lblplaying.setText("Now Playing: ");
		lblplaying.setBounds(5, 0, 100, 40);
		panelNP.add(lblplaying);
		container.add(panelNP);
		
		//Labels song time
		JPanel contSlbl = new JPanel();
		contSlbl.setBounds(10, 15, _W-20, 20);
		contSlbl.add(lblst);
		contSlbl.add(lblet);
		lblst.setText("00:00");
		lblst.setBorder(new EmptyBorder(0, 0, 0, 200));
		lblet.setText("00:00");
		container.add(contSlbl);
		
		//Progress Panel and ProgressBar
		progressPanel.setBounds(0, 40, 400, 20);
		progressPanel.setLayout(null);
 		progressBar.setBounds(0, 0, 400, 40);
 		progressBar.setMinimumSize(new Dimension(400, 40));
 		progressBar.setVisible(true);
 		
 		progressPanel.add(progressBar);
 		container.add(progressPanel);
		
		//Buttons
		int btn_h = 35;		//altura de los botones
		int btn_w = 50;		//ancho de los botones
		int lineW = _W - 10;//ancho de los contenedores
		int line1 = 80;		//posicion "y"

		//Panel para los botones prev,play,stop y next
		JPanel contBtns = new JPanel();
		contBtns.setBounds(0, line1, lineW, btn_h);		
		btnPrev.setIcon(prevIcon);
		btnPrev.setSize(btn_w,btn_h);
		btnPlay.setIcon(playIcon);
		btnPlay.setMnemonic(KeyEvent.VK_SPACE);
		btnPlay.setSize(btn_w,btn_h);
		btnNext.setIcon(nextIcon);
		btnNext.setSize(btn_w,btn_h);
		btnStop.setIcon(stopIcon);
		btnStop.setSize(btn_w,btn_h);
		contBtns.add(btnPrev);
		contBtns.add(btnPlay);
		contBtns.add(btnStop);
		contBtns.add(btnNext);
		container.add(contBtns);
		
		//Panel para botones de volumen
		int line2 = 125;
		btn_w = 30; //hago los siguientes botones mas chicos
		JPanel volBtns = new JPanel();
		volBtns.setBounds(0, line2, lineW, btn_h);
		
		volSlider.setSize(100, 20);
		volSlider.setMinorTickSpacing(2);
		volSlider.setMajorTickSpacing(10);
		volSlider.setPaintTicks(true);
		volSlider.setPaintLabels(false);
		volSlider.setVisible(true);
		volSlider.setValue(volSlider.getMaximum());
		btnMute.setIcon(muteIcon);
		btnMute.setSize(btn_w,btn_h);
		volBtns.add(btnMute);
		volBtns.add(volSlider);
		container.add(volBtns);
		
		//Panel para botones de ver info, agregar y borrar playlist
		int line4 = 170;
		JPanel configBtns = new JPanel();
		configBtns.setBounds(0, line4, lineW, btn_h);
		btnAdd.setIcon(addIcon);
		btnAdd.setSize(btn_w,btn_h);
		btnDelete.setIcon(deleteIcon);
		btnDelete.setSize(btn_w,btn_h);
		btnInfo.setIcon(infoIcon);
		btnInfo.setSize(btn_w,btn_h);
		btnArt.setIcon(artIcon);
		btnArt.setSize(70,btn_h);
		configBtns.add(btnAdd);
		configBtns.add(btnDelete);
		configBtns.add(btnInfo);
		configBtns.add(btnArt);
		container.add(configBtns);		
		
		//SongList
		int h_list = 150;
		int line3 = 215;
		//Panel para la playilist
		JScrollPane listScroller = new JScrollPane(jSongList);
		listScroller.setPreferredSize(new Dimension(_W-10,h_list));
		listScroller.setBounds(0, line3, _W-10, h_list);
		container.add(listScroller);
	}
	
	private void addListeners(){
		btnPlay.addActionListener(this);
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		btnAdd.addActionListener(this);
		btnMute.addActionListener(this);
		btnStop.addActionListener(this);
		btnArt.addActionListener(this);
		btnInfo.addActionListener(this);
		btnDelete.addActionListener(this);
		volSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				controller.setVolumen(Double.valueOf(((JSlider) e.getSource()).getValue()) / 100.0);
			}
		});
	}
	
	//Agrego mensajes al apuntar con el mouse en cada boton
	private void addHoverMessages(){
		btnPlay.setToolTipText("Play");
		btnPrev.setToolTipText("Play previous song");
		btnNext.setToolTipText("Play next song");
		btnAdd.setToolTipText("Add song to playlist");
		btnMute.setToolTipText("Mute");
		btnStop.setToolTipText("Stop");
		btnInfo.setToolTipText("View song info");
		btnDelete.setToolTipText("Delete selected song from playlist");
		btnArt.setToolTipText("View Album Art");
		volSlider.setToolTipText("Change volume");
	}
	
	//Metodo para manejar los eventos dependiendo que boton se toco
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnPlay) {
			if(model.IsPlaying()){
				controller.pause();
			}
			else{
				controller.start();
			}
		}
		else if(event.getSource() == btnPrev){
			controller.decreaseBPM();
		}
		else if(event.getSource() == btnNext){
			controller.increaseBPM();
		}
		else if(event.getSource() == btnAdd){
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3 Files", "mp3");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(btnAdd);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	File file = chooser.getSelectedFile();
		    	String path = file.getAbsolutePath();
		    	controller.addPlaylist(path);
		    }
		}
		else if(event.getSource() == btnMute){
			controller.setVolumen(0);
		}
		else if(event.getSource() == btnStop){
			controller.stop();
		}
		else if(event.getSource() == btnArt){
			byte[] imageData = model.getAlbumArt();
            BufferedImage img = null;
			try {
				img = ImageIO.read(new ByteArrayInputStream(imageData));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(songArt != null){
				songArt.setVisible(false);
				songArt.removeAll();
				songArt = null;
			} else {
				songArt = new JFrame();
				songArt.getContentPane().setLayout(new FlowLayout());
				songArt.getContentPane().add(new JLabel(new ImageIcon(img)));
				songArt.pack();
				songArt.setTitle("Album Art");
				songArt.setVisible(true);
			}
		}
		else if(event.getSource() == btnInfo){
			if(songInfo != null){
				songInfo.setVisible(false);
				songInfo.removeAll();
				songInfo = null;
			} else {
				DefaultListModel<String> songDetails = model.getSongInfo();
				songInfo = new JFrame();
		    	JList<String> jSongList = new JList<String>(songDetails);
		    	songInfo.add(jSongList);
		    	songInfo.setTitle("Song Info");
		    	songInfo.setSize(240, 160);
		    	songInfo.setLocationRelativeTo(null);
		    	songInfo.setVisible(true);
			}
		}
		else if(event.getSource() == btnDelete){
			controller.removeTrack(jSongList.getSelectedIndex());
		}
	}
	
	public void setController(MP3Controller2 controller){
		this.controller = controller;
	}
	
	public void MakePlayIcon(){
		btnPlay.setIcon(playIcon);
	}
	
	public void MakePauseIcon(){
		btnPlay.setIcon(pauseIcon);
	}
	
	public void MakeVolSliderMute(){
		volSlider.setValue(volSlider.getMinimum());  //Pone la barra de volumen en minimo
	}

	@Override
	public void updateTrackInfo() {
		lblplaying.setText("Now Playing: " + model.getCurrentTrackName());
		lblet.setText(model.getCurrentSongDuration());
	}

	@Override
	public void updatePlaylistInfo() {
		String[] playlist = model.getCurrentPlaylist();
			songList.clear(); //Borro la songList que habia y le pido al modelo que me de la nueva(el clear es necesario para evitar duplicados)
			for(int i=0;i<playlist.length;i++){
				songList.addElement(playlist[i]);
			}
		jSongList.setSelectedIndex(model.getIndex());
	}

	@Override
	public void updateTrackProgress(int progress, int size) {
		if (progress==0){
			progressBar.reset();
		}
		else {
			progressBar.increase();
		}
		progressBar.setMax(size);
		int minutes = progress/60;
		int seconds = progress%60;
		String a = String.format("%02d:%02d", minutes, seconds);
		lblst.setText(a);
	}
}
