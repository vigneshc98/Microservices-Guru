package guru.springframework.sfgjms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

	public static void main(String[] args) throws Exception {

////We are using Artemis ActiveMQ server thats why no more need of this Embedded server config (any way no need to configure this,
// adding server dependecy is enough, spring will auto configure this)
//		ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//				.setPersistenceEnabled(false)
//				.setJournalDirectory("target/data/journal")
//				.setSecurityEnabled(false)
//				.addAcceptorConfiguration("invm", "vm://0"));
//
//		server.start();

		SpringApplication.run(SfgJmsApplication.class, args);
	}

}
