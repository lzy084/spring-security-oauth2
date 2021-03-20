package com.dd.main.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class MenuDto implements Serializable {
    String title;
    String icon;
    String href;
    String target;
    List<MenuDto> child;
}
