package com.helapu.lynx.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RentTypeEnum implements IEnum<String> {
	MONTH("month", "按月租赁"),
	YEAR("year", "按年租赁");

	private String value;
    private String desc;

    RentTypeEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }
}
