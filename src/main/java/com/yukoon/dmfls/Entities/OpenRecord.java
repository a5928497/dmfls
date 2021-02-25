package com.yukoon.dmfls.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class OpenRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//0已登记未确认，1已确认
	@Column(name = "IS_OPEN")
	private Integer is_open;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "SC_ID")
	private Securities securities;

	@Column(name = "OPEN_DATE")
	private String date;
}
