package com.example.fashionblog.dto;

import com.example.fashionblog.enums.Rate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LikeCreateDto {
    private Rate rate;
    private Integer blogId;
}
