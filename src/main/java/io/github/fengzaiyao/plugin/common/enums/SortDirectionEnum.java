package io.github.fengzaiyao.plugin.common.enums;

public enum SortDirectionEnum {
    /**
     * 升序排序
     */
    ASC(1),

    /**
     * 降序排序
     */
    DESC(-1);

    private int direction;

    SortDirectionEnum(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
