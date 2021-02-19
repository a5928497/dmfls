package com.yukoon.dmfls.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private String phone;
}
