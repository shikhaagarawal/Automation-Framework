package com.automate.Rules.common;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.automate.Rules.utils.CreateAsset;
import com.automate.Rules.utils.ReadAsset;

/**
 * 
 * @author Shikha A
 *
 */
@Configuration
@ComponentScan(basePackages = "com.aexp.central.automate")
public class ApplicationConfiguration {

	Properties property = new Properties();

	/*{
		if (null == System.getProperty("spring.profiles.active")) {
			System.setProperty("spring.profiles.active", "E0");
		}
		String fileName = (getClass().getClassLoader()
				.getResource("application-" + System.getProperty("spring.profiles.active") + ".properties")).getFile();
		try {
			property.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	@Bean
	public CreateAsset createAsset() {
		return new CreateAsset();
	}

	@Bean
	public DataSetup setup() {
		return new DataSetup();
	}

	@Bean
	public ReadAsset readAsset() {
		return new ReadAsset();
	}

	@Bean
	public DataCleanUp cleanUp(){
		return new DataCleanUp();
	}
	
	public String getProperty(String key) {
		return property.getProperty(key);
	}

}
