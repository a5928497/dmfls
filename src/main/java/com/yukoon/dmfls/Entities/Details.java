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
public class Details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "des")
	private String des;

	//0隐藏，1显示
	@Column(name = "IS_SHOW")
	private Integer is_show;

	@Column(name = "ORDER_NO")
	private Integer order;

	@JoinColumn(name = "sc_id")
	@ManyToOne
	private Securities securities;
}
