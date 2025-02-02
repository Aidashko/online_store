package com.example.online_store_1.service.impl;

import com.example.online_store_1.mapper.GoodMapper;
import com.example.online_store_1.models.Category;
import com.example.online_store_1.models.Good;
import com.example.online_store_1.models.Image;
import com.example.online_store_1.models.dto.GoodDto;
import com.example.online_store_1.repository.GoodRepo;
import com.example.online_store_1.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService{
    private final GoodRepo goodRepo;
    private final GoodMapper goodMapper;

    @Override
    public void saveGood(Good good) {
        goodRepo.save(good);
    }

    @Override
    public Good findById(Long id) {
        return goodRepo.findById(id).get();
    }

    @Override
    public GoodDto getById(Long id) {
        Good good = goodRepo.findById(id).get();
        GoodDto goodDto = goodMapper.toDto(good);
        goodDto.setCategoryName(good.getCategory().getName());
        goodDto.setImages(good.getImages().stream().map(Image::getPath).toList());
        return goodDto;
    }

    @Override
    public List<Good> findAll() {
        return goodRepo.findAll();
    }

    @Override
    public GoodDto getByName(String name) {
        Good good = goodRepo.findByName(name).orElse(null);
        GoodDto goodDto = goodMapper.toDto(good);
        return goodDto;
    }

    @Override
    public Good getByRating(double rating) {
        return goodRepo.findByRating(rating);
    }

    @Override
    public List<Good> getAllByRatingAbove(double rating) {
        return goodRepo.findAllByRatingIsGreaterThanEqual(rating);
    }

    @Override
    public List<Good> getAllByCategory(Category category) {
        return goodRepo.findAllByCategory(category);
    }

    @Override
    public Good updateGood(Good good) {
        return goodRepo.save(good);
    }

    @Override
    public void addImage(MultipartFile file, Long id) {
        File newFile = new File("C:\\Users\\user\\OneDrive\\Рабочий стол\\online_store_1\\src\\main\\resources\\good_images\\Milano.png\\"+file.getOriginalFilename());
        try {
            newFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(file.getBytes());
            outputStream.close();
            Good good = findById(id);
            updateGood(good);
        } catch (IOException e) {
            System.out.println("Error creating file");;
        }

    }
}
