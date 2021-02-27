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
public class Securities implements Comparable<Securities>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "OP_URL")
	private String opUrl;

	@Column(name = "ORDER_NO")
	private Integer order;

	@Column(name = "IMG_URL")
	private String imgUrl;

	@Column(name = "OP_BTN_TEXT")
	private String opBtnText;

	//fasle隐藏，true显示
	@Column(name = "SHOW_FLAG",columnDefinition = "bool default true")
	private Boolean showFlag;

	@Override
	public int compareTo(Securities o) {

			return this.order - o.order;
	}
}

