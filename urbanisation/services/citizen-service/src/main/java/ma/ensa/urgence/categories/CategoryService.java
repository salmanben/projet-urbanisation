package ma.ensa.urgence.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryService {
    
    private final RestTemplate restTemplate;
    @Value("${spring.application.services.category-service.url}")
    private  String categoryServiceUrl;

    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CategoryResponse> getCategories() {
        return restTemplate.getForObject(categoryServiceUrl, List.class);
    }
}
