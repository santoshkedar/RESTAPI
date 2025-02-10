package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/java/features", tags= "@AddPlace or @DeletePlace", plugin={"json:target/jsonReports/cucumber-report.json" , "html:target/cucumber-html-reports/cucumber-report.html"}, glue= {"stepDefinations"})
//tags= "@AddPlace or @DeletePlace"

public class TestRunner 
{
	
}
