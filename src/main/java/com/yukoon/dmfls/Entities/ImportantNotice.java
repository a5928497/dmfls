package com.yukoon.dmfls.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.nio.channels.Channel;

@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImportantNotice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "des")
	private String des;

	//fasle隐藏，true显示
	@Column(name = "SHOW_FLAG",columnDefinition = "bool default true")
	private Boolean showFlag;

	@Column(name = "ORDER_NO")
	private Integer order;

	@JoinColumn(name = "sc_id")
	@ManyToOne
	private Securities securities;
}
