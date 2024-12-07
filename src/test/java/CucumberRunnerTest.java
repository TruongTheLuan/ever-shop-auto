import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectPackages("")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:build/reports/cucumber-reports.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "")
public class CucumberRunnerTest {

}
