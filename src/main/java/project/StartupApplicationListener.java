package project;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    GenerateTestService generateTestService;

    public StartupApplicationListener(GenerateTestService generateTestService) {
        this.generateTestService = generateTestService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateTestService.generate();
    }
}
