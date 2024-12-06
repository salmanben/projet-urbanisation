package ma.ensa.urgence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDao categoryDao;

    public List<Category> getCategories() {
        return categoryDao.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryDao.findById(id).orElse(null);
    }
}
