package io.github.fengzaiyao.plugin.common.resp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class ResultWrapper<T> implements Serializable {
	/**
	 * 提示信息
	 */
	private String msg;

	/**
	 * 返回码
	 */
	private int code;

	/**
	 * 数据内容
	 */
	private T data;
}
