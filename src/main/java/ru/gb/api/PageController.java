package ru.gb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // Вернет index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Вернет login.html
    }

    @GetMapping("/issue")
    public String issue() {
        return "issue"; // Вернет issue.html, если доступен
    }

    @GetMapping("/reader")
    public String reader() {
        return "reader"; // Вернет reader.html, если доступен
    }

    @GetMapping("/books")
    public String books() {
        return "books"; // Вернет books.html, если доступен
    }
}
