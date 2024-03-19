package ru.artemiy3.eventextractor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.artemiy3.eventextractor.service.EventExtractionService;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
class EventExtractorApplicationTests {

	@Test
	void contextLoads() {
	}

//	public static void main(String[] args) throws IOException {
//		EventExtractionService service = new EventExtractionService();
//		String[] tags = service.getTags("Jimmy came home and tried to handle the problem, but it was too complex.");
//
//		System.out.println(Arrays.toString(tags));
//	}

}
