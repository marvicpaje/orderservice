package com.ibm.library.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
   features = "classpath:features",
   plugin = {"pretty", "html:target/cucumber_report.html"}
)
public class CucumberTestRunner {

}
