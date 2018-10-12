package com.helapu.lynx.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GoodUnitEnum implements IEnum<String> {
	
	AMOUNT("AMOUNT", "数量"),
	AREA("AREA", "面积");
    
    private String value;
    private String desc;

    GoodUnitEnum(final String value, final String desc) {
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
