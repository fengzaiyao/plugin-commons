package io.github.fengzaiyao.plugin.common.page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
public class PageResult<T> implements Serializable {
    /**
     * 当前页码
     */
    private long number;

    /**
     * 查询分页长度
     */
    private int limit;

    /**
     * 查询结果
     */
    private List<T> content;

    /**
     * 总数
     */
    private long totalElements;

    /**
     * 总页数
     */
    private long totalPages;
}

