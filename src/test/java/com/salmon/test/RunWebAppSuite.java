package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "target/test-classes", tags = {"@mobile"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runjenkins",
        "json:target/cucumber-report/runjenkins/cucumber.json",
        "rerun:target/cucumber-report/runjenkins/rerun.txt"},
        glue = "com.salmon.test.step_definitions.webapp")
public class RunWebAppSuite extends AbstractTestNGCucumberTests {

}
