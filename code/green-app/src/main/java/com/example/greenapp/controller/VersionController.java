package com.example.greenapp.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	
	@Value("${project.artifactId}-${project.version}")
	private String version;
	
	@GetMapping("/version")
	public String getVersion() {
		return version;
	}
	
	@GetMapping("/info")
	public String getInfo() {
		String hostName = System.getenv("HOSTNAME");
		
		if(hostName == null || hostName.isEmpty()) {
			try {
				InetAddress addr = InetAddress.getLocalHost();
				hostName = addr.getHostName();
			} catch (Exception e) {
				System.err.println(e);
				hostName = "Unknow";
			}
		}
		String response = version + " | " + getDateNow() + " | " + hostName;
		return response;
	}
	
	public String getDateNow() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSS");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}