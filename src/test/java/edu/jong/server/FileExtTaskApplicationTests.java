package edu.jong.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.jong.server.enums.FixedExtensionState;
import edu.jong.server.model.ExtensionInfo;
import edu.jong.server.service.FixedExtensionService;
import edu.jong.server.service.LockTestService;

@SpringBootTest(classes = SpringExtension.class)
@EntityScan(basePackages = "edu.jong.server")
@EnableJpaRepositories(basePackages = "edu.jong.server")
@ComponentScan(basePackages = "edu.jong.server")
@EnableAutoConfiguration
class FileExtTaskApplicationTests {

	@Autowired
	LockTestService lockTestService;

	@Autowired
	FixedExtensionService fixedExtensionService;
	
	@Test
	void lockTest() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);	// Thread 갯수 
		executor.initialize();

		// when 
		ExtensionInfo target = fixedExtensionService.add("locktest", FixedExtensionState.UNCHECKED);
		
		// given
		executor.execute(() -> {
			try {
				long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
		        
				// 업데이트를 위한 조회(데이터 잠금)  후 3초 대기 후 저장
				System.out.println(lockTestService.checkOrUncheck(target.getNo(), FixedExtensionState.CHECKED, 3000));
				        
				long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
				long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
				System.out.println(Thread.currentThread().getId() + " 실행시간(초) : "+secDiffTime);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		});
		executor.execute(() -> {
			try {
				long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
		        
				// 업데이트를 위한 조회(데이터 잠금)  후 1초 대기 후 저장
				// 하지만 먼저 수정을 위해 데이터를 점유한 Thread가 있기 때문에 해당 트랜잭션 종료 후 데이터 수정
				// 따라서 약 3초 후 데이터 저장 
				System.out.println(lockTestService.checkOrUncheck(target.getNo(), FixedExtensionState.UNCHECKED, 1000));
				
				long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
				long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
				System.out.println(Thread.currentThread().getId() + " 실행시간(초) : "+secDiffTime);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		});

		latch.await();

		// then
		ExtensionInfo result = lockTestService.get(target.getNo());
		assertEquals(FixedExtensionState.UNCHECKED, result.getState());
	}
	
}
