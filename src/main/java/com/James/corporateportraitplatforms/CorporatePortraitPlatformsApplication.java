package com.James.corporateportraitplatforms;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.James.corporateportraitplatforms.mapper")
@Slf4j
public class CorporatePortraitPlatformsApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(CorporatePortraitPlatformsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

//	private void mapperTest() {
//		Company company = companyExtendMapper.selectByPrimaryKeyComplete("28");
//
//		log.info("Company {}", company.getId());
//		log.info("K {}", company.getKnowledgeReport());
//		company.getTags().forEach(o -> log.info("TagItem {}", o));
//	}

//	private void generate() throws Exception {
//		List<String> warnings = new ArrayList<>();
//		ConfigurationParser cp = new ConfigurationParser(warnings);
//		Configuration config = cp.parseConfiguration(
//				this.getClass().getResourceAsStream("/generatorConfig.xml"));
//
//		DefaultShellCallback callback = new DefaultShellCallback(true);
//		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//		myBatisGenerator.generate(null);
//	}
}
