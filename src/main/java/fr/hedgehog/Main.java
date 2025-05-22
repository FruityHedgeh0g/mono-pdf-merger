package fr.hedgehog;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.ApplicationPath;

@QuarkusMain
@ApplicationPath("/api")
public class Main {

    public static void main(String... args) {
        Quarkus.run(args);
    }
}
