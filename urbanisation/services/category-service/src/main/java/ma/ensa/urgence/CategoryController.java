package ma.ensa.urgence;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public List<Category> getAll() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }
}
