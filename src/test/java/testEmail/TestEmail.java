package testEmail;

import org.junit.Test;

import com.dgut.modules.common.email.SendEmail;

public class TestEmail {

	@Test
	public void testEmail() {
		SendEmail.send("2465824792@qq.com","发送邮箱","测试发送邮箱");
	}
}
