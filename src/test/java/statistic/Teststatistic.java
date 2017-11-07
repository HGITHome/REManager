package statistic;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Teststatistic {

	@Test
	public void test() {
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		
		System.out.println(p.encode("123"));
	}
}
