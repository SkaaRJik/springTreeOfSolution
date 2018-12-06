package ru.filippov.springTree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.filippov.springTree.repos.FilesRepo;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTreeApplicationTests {
	@Value("${upload.path}")
	String uploadPath;


	@Test
	public void filesLoader() throws IOException {
		FilesRepo filesRepo = new FilesRepo();
		Iterable<String> files = filesRepo.scan(this.uploadPath);
		int a = 0;
	}

	@Test
	public void contextLoads() {
	}

}
