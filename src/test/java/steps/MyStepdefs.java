package steps;

import io.cucumber.java.en.Given;

public class MyStepdefs {
    @Given("I do something")
    public void iDoSomething() {
        System.out.println("Hello Cucumber!");
    }
}
