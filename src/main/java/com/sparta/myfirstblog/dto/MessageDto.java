package com.sparta.myfirstblog.dto;

import com.sparta.myfirstblog.entity.StatusEnum;
import lombok.Getter;

@Getter
public class MessageDto {
    private String message;
    private StatusEnum status;

    public MessageDto(String message, StatusEnum status){
        this.message = message;
        this.status = status;
    }
}
