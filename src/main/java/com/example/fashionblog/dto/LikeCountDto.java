package com.example.fashionblog.dto;

import com.example.fashionblog.enums.Rate;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class LikeCountDto {

    private Integer blogId;
    private Rate rate;

}
