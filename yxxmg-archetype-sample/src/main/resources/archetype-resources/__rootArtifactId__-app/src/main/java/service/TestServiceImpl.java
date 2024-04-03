package ${package}.service;
import ${package}.api.TestService;
import org.springframework.stereotype.Service;
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "test";
    }
}