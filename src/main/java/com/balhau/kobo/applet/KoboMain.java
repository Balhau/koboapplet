package com.balhau.kobo.applet;

import java.applet.Applet;
import java.awt.Graphics;

import com.balhau.kobo.device.DeviceUtils;



 
public class KoboMain extends Applet{
	
	 
	
	 public void init(){
		 
	 }
	
	 public void paint(Graphics g) {
	        g.drawString("Test kobo!", 50, 25);
	 }
	 
	 private String getKoboSystemInfo(){
		 return "Lolada";//new DeviceTest().getFiles();
	 }

} 
