package com.example.online_store_1.mapper;

import com.example.online_store_1.models.Good;
import com.example.online_store_1.models.Image;
import com.example.online_store_1.models.dto.GoodDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoodMapper implements BaseMapper<Good, GoodDto> {
    @Override
    public GoodDto toDto(Good good) {
        GoodDto dto = new GoodDto();
        dto.setName(good.getName());
        dto.setPrice(good.getPrice());
        dto.setQuantity(good.getQuantity());
        dto.setRating(good.getRating());
        dto.setImages(good.getImages().stream().map(Image::getPath).toList());
        dto.setCategoryName(good.getCategory().getName());
        return dto;
    }

    @Override
    public Good toEntity(GoodDto goodDto) {
        Good good = new Good();
        good.setName(goodDto.getName());
        good.setPrice(goodDto.getPrice());
        good.setQuantity(goodDto.getQuantity());
        good.setRating(goodDto.getRating());
        return good;
    }

    @Override
    public List<Good> toEntityList(List<GoodDto> goodDtos) {
        List<Good> goods = new ArrayList<>();
//        for (GoodDto dto:goodDtos){
//            goods.add(toEntity(dto));
//        }
//        return goods;
        return goodDtos.stream().map(this::toEntity).toList();
    }

    @Override
    public List<GoodDto> toDtoList(List<Good> goods) {
        return goods.stream().map(this::toDto).toList();
    }
}
