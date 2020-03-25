package cc.vant.seckillmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cc.vant.seckillmall.mapper")
public class SeckillMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillMallApplication.class, args);
    }

}
