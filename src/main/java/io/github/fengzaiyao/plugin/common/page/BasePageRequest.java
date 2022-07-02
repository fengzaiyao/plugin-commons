package io.github.fengzaiyao.plugin.common.page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class BasePageRequest implements Serializable {
    /**
     * 分页大小
     */
    private Integer length;

    /**
     * 分页下标
     */
    private Integer pageIndex;

    /**
     * 排序字段
     */
    private String orderField;

    /**
     * 排序方向 {@see SortDirectionEnum}
     */
    private Integer orderAsc;
}
