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
public class Details implements Comparable<Details> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "des")
	private String des;

	//false隐藏，true显示
	@Column(name = "SHOW_FLAG",columnDefinition = "bool default true")
	private Boolean showFlag;

	@Column(name = "ORDER_NO")
	private Integer order;

	@JoinColumn(name = "sc_id")
	@ManyToOne
	private Securities securities;

	@Override
	public int compareTo(Details o) {
		return this.order - o.order;
	}
}
