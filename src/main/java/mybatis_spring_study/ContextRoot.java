package mybatis_spring_study;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ContextDataSource.class})
public class ContextRoot {

}
