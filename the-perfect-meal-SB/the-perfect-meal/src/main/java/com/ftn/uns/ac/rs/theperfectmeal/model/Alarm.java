package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alarm")
public class Alarm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "timestamp")
	private Date timestamp;

	@Column(name = "alarm_text")
	private String alarmText;

	public Alarm() {
		super();
	}

	public Alarm(String alarmText) {
		super();
		this.timestamp = Date.from(Instant.now());
		this.alarmText = alarmText;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getAlarmText() {
		return alarmText;
	}

	public void setAlarmText(String alarmText) {
		this.alarmText = alarmText;
	}

}
