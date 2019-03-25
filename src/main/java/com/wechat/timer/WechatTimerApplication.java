package com.wechat.timer;

import com.wechat.timer.exception.AesException;
import com.wechat.timer.util.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class WechatTimerApplication implements CommandLineRunner{

	@Value("${wechat.token}")
	private String tokenValue;

	public static void main(String[] args) {
		SpringApplication.run(WechatTimerApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {
		logMsg();
	}

	private void logMsg(){
		log.info("Token:{}", tokenValue);
		log.info("Running......");
	}


	/**
	 *
	 * @param signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
	 * @param timestamp	时间戳
	 * @param nonce	随机数
	 * @param echostr	随机字符串
	 * @return
	 */
	@GetMapping("/checksignature")
	public String checkSignature(@RequestParam(name = "signature") String signature,
								  @RequestParam(name = "timestamp") String timestamp,
								  @RequestParam(name = "nonce") String nonce,
								  @RequestParam(name = "echostr") String echostr){
		String reMsg = "";
		try {
			log.info("signature:{},timestamp:{},nonce:{},encypt:{}",
					signature, timestamp, nonce, echostr);
			String sha1 = SHA1.getSHA1(tokenValue, timestamp, nonce, echostr);
			log.info("SHA1:{}", sha1);
			if (signature != null && signature.equals(sha1)){
				reMsg = echostr;
			}
		} catch (AesException e) {
			log.error("签名校验失败", e);
		}
		return reMsg;
	}

}

