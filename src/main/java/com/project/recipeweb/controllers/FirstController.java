package com.project.recipeweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping ("/")
    public String appWorks () {
        return "Приложение запущено";
    }

    @GetMapping ("/info")
    public String info () {
        return "Создатель: Попова Елизавета. " + " Сайт рецептов." + " Дата создания: 27.02." + " В приложении содержатся лучшие кулинарные рецепты, подобранные избранными шеф-поварами России. Здесь вы сможете найти, сохранить понравившиеся рецепты для дальнейшей реализации на вашей кухне.";
    }

}
