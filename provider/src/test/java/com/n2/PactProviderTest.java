package com.n2;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.n2.ProviderApplication;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@RunWith(PactRunner.class)
@Provider("test_provider")
@PactFolder("pacts")
//@PactBroker()
public class PactProviderTest {

  @TestTarget
  public final Target target = new HttpTarget("http", "localhost", 8080, "/");

  private static ConfigurableWebApplicationContext application;

  @BeforeClass
  public static void start() {
    application = (ConfigurableWebApplicationContext) SpringApplication.run(ProviderApplication.class);
  }

  @State("a person named John")
  public void toGetState() {
  }

}
